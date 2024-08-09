package dataAccessImpl;

import dataAccessLayer.UserSubscriptionDAO;
import transferObject.UserSubscriptionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserSubscriptionDAOImpl implements UserSubscriptionDAO {
    private Connection conn;

    public UserSubscriptionDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void addUserSubscription(UserSubscriptionDTO subscription) {
        try {
            String query = "INSERT INTO user_subscriptions (user_email, user_phone, location, food_preferences, communication_method) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, subscription.getUserEmail());
            ps.setString(2, subscription.getUserPhone());
            ps.setString(3, subscription.getLocation());
            ps.setString(4, subscription.getFoodPreferences());
            ps.setString(5, subscription.getCommunicationMethod());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserSubscription(int id) {
        try {
            String query = "DELETE FROM user_subscriptions WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<UserSubscriptionDTO> getUserSubscriptionsByLocation(String location) {
        List<UserSubscriptionDTO> subscriptions = new ArrayList<>();
        try {
            String query = "SELECT * FROM user_subscriptions WHERE location = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, location);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserSubscriptionDTO subscription = new UserSubscriptionDTO();
                subscription.setId(rs.getInt("id"));
                subscription.setUserEmail(rs.getString("user_email"));
                subscription.setUserPhone(rs.getString("user_phone"));
                subscription.setLocation(rs.getString("location"));
                subscription.setFoodPreferences(rs.getString("food_preferences"));
                subscription.setCommunicationMethod(rs.getString("communication_method"));
                subscriptions.add(subscription);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subscriptions;
    }
}