package serverlets;

import dataAccessImpl.FoodItemDAOImpl;
import dataAccessLayer.FoodItemDAO;
import transferObject.FoodItemDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/claim-food")
public class ClaimFoodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FoodItemDAO foodItemDAO;

    public void init() {
        foodItemDAO = new FoodItemDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<FoodItemDTO> foodItems = foodItemDAO.getAllFoodItems();
        request.setAttribute("foodItems", foodItems);
        request.getRequestDispatcher("food_items.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int claimedQuantity = Integer.parseInt(request.getParameter("quantity"));
        FoodItemDTO foodItem = foodItemDAO.getFoodItemById(id);

        if (foodItem != null && foodItem.getQuantity() >= claimedQuantity) {
            int newQuantity = foodItem.getQuantity() - claimedQuantity;
            foodItem.setQuantity(newQuantity);
            foodItemDAO.updateFoodItem(foodItem);
            response.sendRedirect("claim-food?success=true");
        } else {
            response.sendRedirect("claim-food?error=insufficient_quantity");
        }
    }
}
