package dataAccessImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccessLayer.DataSource;
import dataAccessLayer.InventoryDao;
import transferObject.InventoryDTO;

public class InventoryDaoImpl implements InventoryDao {
	Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    InventoryDTO inventoryItem = null;
    
	@Override
	public InventoryDTO getInventoryItemById(int itemId) {
		 try {
		        con = DataSource.getConnection();
		        String query = "SELECT * FROM inventory WHERE itemid = ?";
		        ps = con.prepareStatement(query);
		        ps.setInt(1, itemId);
		        rs = ps.executeQuery();
		        
		        if (rs.next()) {
		            // Retrieve data from the result set and create an InventoryDTO object
		            int id = rs.getInt("itemid");
		            String itemName = rs.getString("itemname");	         
		            int quantity = rs.getInt("quantity");
		            double price = rs.getDouble("price");
		            boolean isSPlus =rs.getBoolean("isItemSurplus");
		            // Assuming you have a constructor in InventoryDTO to set these values
		            inventoryItem = new InventoryDTO(itemName, id, price);
		        }
		    } catch (SQLException | IOException e) {
		        e.printStackTrace();
		    } finally {
		        // Close the resources
		        try {
		            if (rs != null) rs.close();
		            if (ps != null) ps.close();
		            if (con != null) con.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		    
		    return inventoryItem;
	}

	@Override
	public List<InventoryDTO> getAllInventoryItems() {
		List<InventoryDTO> inventoryItems = new ArrayList<>();
		
        try {
            con = DataSource.getConnection();
            String query = "SELECT * FROM inventory";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                InventoryDTO item = new InventoryDTO();
                item.setItemId(rs.getInt("itemid"));
                item.setItemName(rs.getString("itemname"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                item.setSurPlus(rs.getBoolean("isItemSurplus"));
                inventoryItems.add(item);
            }
        } catch (SQLException|IOException  e) {
            e.printStackTrace();
        }  finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return inventoryItems;
	}

	@Override
	public List<InventoryDTO> getSurplusInventoryItems() {
		List<InventoryDTO> surplusItems = new ArrayList<>();
        

        try {
            con = DataSource.getConnection();
            String query = "SELECT * FROM inventory WHERE isItemSurplus = TRUE";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                InventoryDTO item = new InventoryDTO();
                item.setItemId(rs.getInt("itemid"));
                item.setItemName(rs.getString("itemname"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price"));
                item.setSurPlus(true);
                surplusItems.add(item);
            }
        } catch (SQLException|IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return surplusItems;
    }
	


	@Override
	public void addInventoryItem(InventoryDTO item) {
		 try {
	            con = DataSource.getConnection();
	            String query = "INSERT INTO inventory (itemname, quantity, price, isItemSurplus) VALUES (?, ?, ?, ?)";
	            ps = con.prepareStatement(query);
	            ps.setString(1, item.getItemName());
	            ps.setInt(2, item.getQuantity());
	            ps.setDouble(3, item.getPrice());
	            ps.setBoolean(4, item.isSurPlus());
	            ps.executeUpdate();
	        } catch (SQLException|IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (ps != null) ps.close();
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
		


	@Override
	public void updateInventoryItem(InventoryDTO item) {
		
	        try {
	            con = DataSource.getConnection();
	            String query = "UPDATE inventory SET itemname = ?, quantity = ?, price = ?, isItemSurplus = ? WHERE itemid = ?";
	            ps = con.prepareStatement(query);
	            ps.setString(1, item.getItemName());
	            ps.setInt(2, item.getQuantity());
	            ps.setDouble(3, item.getPrice());
	            ps.setBoolean(4, item.isSurPlus());
	            ps.setInt(5, item.getItemId());
	            ps.executeUpdate();
	        } catch (SQLException|IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (ps != null) ps.close();
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
		
	

	@Override
	public void deleteInventoryItem(int itemId) {
		 try {
	            con = DataSource.getConnection();
	            String query = "DELETE FROM inventory WHERE itemid = ?";
	            ps = con.prepareStatement(query);
	            ps.setInt(1, itemId);
	            ps.executeUpdate();
	        } catch (SQLException | IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (ps != null) ps.close();
	                if (con != null) con.close();
	            } catch (SQLException  e) {
	                e.printStackTrace();
	            }
	        }		
	}
	

}
