package dataAccessImpl;

import dataAccessLayer.DonationDAO;
import transferObject.DonationDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DonationDAOImpl implements DonationDAO {
    private Connection conn;

    public DonationDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addDonation(DonationDTO donation) {
        try {
            String query = "INSERT INTO donations (user_email, food_type, quantity, charity_name, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, donation.getUserEmail());
            ps.setString(2, donation.getFoodType());
            ps.setInt(3, donation.getQuantity());
            ps.setString(4, donation.getCharityName());
            ps.setString(5, "Pending");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DonationDTO> getDonationsByUser(String userEmail) {
        List<DonationDTO> donations = new ArrayList<>();
        try {
            String query = "SELECT * FROM donations WHERE user_email = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, userEmail);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DonationDTO donation = new DonationDTO();
                donation.setId(rs.getInt("id"));
                donation.setUserEmail(rs.getString("user_email"));
                donation.setFoodType(rs.getString("food_type"));
                donation.setQuantity(rs.getInt("quantity"));
                donation.setCharityName(rs.getString("charity_name"));
                donation.setStatus(rs.getString("status"));
                donations.add(donation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return donations;
    }

    @Override
    public List<DonationDTO> getAllDonations() {
        List<DonationDTO> donations = new ArrayList<>();
        try {
            String query = "SELECT * FROM donations";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DonationDTO donation = new DonationDTO();
                donation.setId(rs.getInt("id"));
                donation.setUserEmail(rs.getString("user_email"));
                donation.setFoodType(rs.getString("food_type"));
                donation.setQuantity(rs.getInt("quantity"));
                donation.setCharityName(rs.getString("charity_name"));
                donation.setStatus(rs.getString("status"));
                donations.add(donation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return donations;
    }

    @Override
    public void updateDonationStatus(int donationId, String status) {
        try {
            String query = "UPDATE donations SET status = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, status);
            ps.setInt(2, donationId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}