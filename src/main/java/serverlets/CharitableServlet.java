package serverlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import transferObject.InventoryDTO;

import java.io.IOException;
import java.util.List;

import dataAccessImpl.InventoryDaoImpl;
import dataAccessImpl.UserDaoImpl;
import dataAccessLayer.InventoryDao;
import dataAccessLayer.UserDao;

/**
 * Servlet implementation class CharitableServlet
 */
@WebServlet("/CharitableServlet")
public class CharitableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao; 
	InventoryDao inventoryDao; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CharitableServlet() {
        super();
        userDao =new UserDaoImpl();
        inventoryDao = new InventoryDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 List<InventoryDTO> surplusItems = inventoryDao.getSurplusInventoryItems();
	        
	        // Set the attribute to pass the items to the JSP page
	        request.setAttribute("surplusItems", surplusItems);
	        
	        // Forward the request to the retailer.jsp page
	        RequestDispatcher dispatcher = request.getRequestDispatcher("CharitableOrg.jsp");
	        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ItemId = request.getParameter("itemId");

        inventoryDao.deleteInventoryItem(Integer.parseInt(ItemId));

        // Redirect back to the doGet method to refresh the page with updated inventory
        response.sendRedirect(request.getContextPath() + "/CharitableServlet");
	}

}
