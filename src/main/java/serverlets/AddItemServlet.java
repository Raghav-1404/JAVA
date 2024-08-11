package serverlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import transferObject.InventoryDTO;

import java.io.IOException;

import dataAccessImpl.InventoryDaoImpl;
import dataAccessLayer.InventoryDao;

@WebServlet("/addItemServlet")
public class AddItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    InventoryDao inventoryDao;
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the add-item.jsp page
//        request.getRequestDispatcher("add-item.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// Retrieve parameters from the request
        String itemName = request.getParameter("itemName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        boolean surplus = request.getParameter("surplus") != null;
        
        InventoryDTO newItem = new InventoryDTO(itemName, quantity, price);
        
     // Add the new item to the database using the InventoryDao
        InventoryDao inventoryDao = new InventoryDaoImpl();
        inventoryDao.addInventoryItem(newItem);
        
        response.sendRedirect(request.getContextPath() + "/RetailerServlet");
        
    }
}
