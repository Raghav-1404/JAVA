package dataAccessImpl;

import dataAccessLayer.FoodItemDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import transferObject.FoodItemDTO;

public class FoodItemDAOImpl implements FoodItemDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/food_database";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Jatin@16";

    private static final String SELECT_FOOD_ITEM_BY_ID = "SELECT * FROM food_items WHERE id = ?";
    private static final String SELECT_ALL_FOOD_ITEMS = "SELECT * FROM food_items";
    private static final String INSERT_FOOD_ITEM = "INSERT INTO food_items (name, quantity, retailer) VALUES (?, ?, ?)";
    private static final String UPDATE_FOOD_ITEM = "UPDATE food_items SET name = ?, quantity = ?, retailer = ? WHERE id = ?";
    private static final String DELETE_FOOD_ITEM = "DELETE FROM food_items WHERE id = ?";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public FoodItemDTO getFoodItemById(int id) {
        FoodItemDTO foodItem = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FOOD_ITEM_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                String retailer = rs.getString("retailer");
                foodItem = new FoodItemDTO(id, name, quantity, retailer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodItem;
    }

    @Override
    public List<FoodItemDTO> getAllFoodItems() {
        List<FoodItemDTO> foodItems = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FOOD_ITEMS);) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                String retailer = rs.getString("retailer");
                foodItems.add(new FoodItemDTO(id, name, quantity, retailer));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodItems;
    }

    @Override
    public void addFoodItem(FoodItemDTO foodItem) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FOOD_ITEM);) {
            preparedStatement.setString(1, foodItem.getName());
            preparedStatement.setInt(2, foodItem.getQuantity());
            preparedStatement.setString(3, foodItem.getRetailer());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFoodItem(FoodItemDTO foodItem) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FOOD_ITEM);) {
            preparedStatement.setString(1, foodItem.getName());
            preparedStatement.setInt(2, foodItem.getQuantity());
            preparedStatement.setString(3, foodItem.getRetailer());
            preparedStatement.setInt(4, foodItem.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFoodItem(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FOOD_ITEM);) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFoodItemQuantity(int id, int newQuantity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object selectAllFoodItems() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
