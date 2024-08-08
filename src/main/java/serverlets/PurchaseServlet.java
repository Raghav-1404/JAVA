package serverlets;

import dataAccessImpl.RetailerDAOImpl;
import dataAccessLayer.RetailerDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import transferObject.ItemDTO;

@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet {
    private RetailerDAO retailerDAO = new RetailerDAOImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ItemDTO> items = retailerDAO.getAllItems();
        request.setAttribute("items", items);
        RequestDispatcher dispatcher = request.getRequestDispatcher("purchase.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemIdStr = request.getParameter("itemId");
        String quantityStr = request.getParameter("quantity");

        int itemId = Integer.parseInt(itemIdStr);
        int quantity = Integer.parseInt(quantityStr);

        double discountRate = retailerDAO.getDiscountRate();
        List<ItemDTO> items = retailerDAO.getAllItems();
        ItemDTO itemToPurchase = null;

        for (ItemDTO item : items) {
            if (item.getId() == itemId) {
                itemToPurchase = item;
                break;
            }
        }

        if (itemToPurchase != null) {
            double price = itemToPurchase.getPrice() * (1 - discountRate);
            request.setAttribute("message", "Purchased " + quantity + " of " + itemToPurchase.getName() + " for $" + price);
            retailerDAO.updateInventory(itemId, quantity);
        } else {
            request.setAttribute("message", "Item not found.");
        }

        doGet(request, response);
    }
}
