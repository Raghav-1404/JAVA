package servlet;

import dataAccessLayer.DonationDAO;
import dataAccessImpl.DonationDAOImpl;
import transferObject.DonationDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/donate")
public class DonationServlet extends HttpServlet {
    private DonationDAO donationDAO;

    @Override
    public void init() throws ServletException {
        Connection conn = (Connection) getServletContext().getAttribute("DBConnection");
        donationDAO = new DonationDAOImpl(conn);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String foodType = request.getParameter("foodType");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String charityName = request.getParameter("charityName");

        DonationDTO donation = new DonationDTO();
        donation.setUserEmail(email);
        donation.setFoodType(foodType);
        donation.setQuantity(quantity);
        donation.setCharityName(charityName);

        donationDAO.addDonation(donation);

        // Redirect to the donation success page
        response.sendRedirect("donation_success.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<DonationDTO> donations = donationDAO.getAllDonations();
        request.setAttribute("donations", donations);
        request.getRequestDispatcher("donation_list.jsp").forward(request, response);
    }
}