package dataAccessImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccessLayer.DataSource;
import dataAccessLayer.RetailerDao;
import transferObject.RetailerDTO;
import transferObject.UserType;

public class RetailerDaoImpl implements RetailerDao {
	Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    
    
    
    
	@Override
	public RetailerDTO getRetailerById(int userId) {
		    RetailerDTO retailer = null;
		    
		    return retailer;
		}

	
	@Override
	public List<RetailerDTO> getAllRetailers() {
		
		  List<RetailerDTO> retailers = new ArrayList<>();

	        try {
	            con = DataSource.getConnection();
	            String query = "SELECT * FROM Users WHERE usertype = ?";
	            ps = con.prepareStatement(query);
	            ps.setString(1, UserType.Retailer.name());
	            rs = ps.executeQuery();

	            while (rs.next()) {
	                RetailerDTO retailer = new RetailerDTO();
	                // Populate RetailerDTO from the result set
	                retailer.setUserId(rs.getInt("userid"));
	                retailer.setName(rs.getString("username"));  
	                retailer.setEmail(rs.getString("email"));             

	                retailers.add(retailer);
	            }
	        } catch (SQLException | IOException e) {
	            e.printStackTrace();
	        } finally {
	            // Close resources in the finally block
	            try {
	                if (rs != null) rs.close();
	                if (ps != null) ps.close();
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return retailers;
	    }
	


}
