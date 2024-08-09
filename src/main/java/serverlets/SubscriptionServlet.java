package serverlets;

import dataAccessLayer.UserSubscriptionDAO;
import dataAccessImpl.UserSubscriptionDAOImpl;
import transferObject.UserSubscriptionDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/subscribe")
public class SubscriptionServlet extends HttpServlet {
    private UserSubscriptionDAO subscriptionDAO;

    @Override
    public void init() throws ServletException {
        Connection conn = (Connection) getServletContext().getAttribute("DBConnection");
        subscriptionDAO = new UserSubscriptionDAOImpl(conn);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String location = request.getParameter("location");
        String foodPreferences = request.getParameter("foodPreferences");
        String communicationMethod = request.getParameter("communicationMethod");

        UserSubscriptionDTO subscription = new UserSubscriptionDTO();
        subscription.setUserEmail(email);
        subscription.setUserPhone(phone);
        subscription.setLocation(location);
        subscription.setFoodPreferences(foodPreferences);
        subscription.setCommunicationMethod(communicationMethod);

        subscriptionDAO.addUserSubscription(subscription);

        response.sendRedirect("subscription_success.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String location = request.getParameter("location");
        List<UserSubscriptionDTO> subscriptions = subscriptionDAO.getUserSubscriptionsByLocation(location);
        request.setAttribute("subscriptions", subscriptions);
        request.getRequestDispatcher("subscription_list.jsp").forward(request, response);
    }
}