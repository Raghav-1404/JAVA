package serverlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dataAccessLayer.InventoryDao;
import dataAccessImpl.InventoryDaoImpl;
import transferObject.InventoryDTO;
/**
 * Servlet implementation class ReatilerServlet
 */
@WebServlet("/RetailerServlet")
public class RetailerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	InventoryDao inventoryDao;   
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public RetailerServlet() {
        super();
        inventoryDao = new InventoryDaoImpl();  
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Retrieve all items from the database
        
        List<InventoryDTO> items = inventoryDao.getAllInventoryItems();
        
        // Set the attribute to pass the items to the JSP page
        request.setAttribute("items", items);
        
        // Forward the request to the retailer.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("retailer.jsp");
        dispatcher.forward(request, response);
//		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the request
		String editItemId = request.getParameter("editItemId");
        String editItemName = request.getParameter("editItemName");
        String editQuantity = request.getParameter("editQuantity");
        String editPrice = request.getParameter("editPrice");

        
        // Create InventoryDTO object with the retrieved parameters
        InventoryDTO newItem = new InventoryDTO( editItemName, Integer.parseInt(editItemId) , Double.parseDouble(editPrice));

        inventoryDao.updateInventoryItem(newItem);

        // Redirect back to the doGet method to refresh the page with updated inventory
        response.sendRedirect(request.getContextPath() + "/RetailerServlet");
    }

}
