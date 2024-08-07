package dataAccessImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccessLayer.ConsumerDao;
import dataAccessLayer.DataSource;
import transferObject.ConsumerDTO;
import transferObject.UserType;

public class ConsumerDaoImpl implements ConsumerDao{

	Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
	@Override
	public List<ConsumerDTO> getAllConsumers() {
		List<ConsumerDTO> consumers = new ArrayList<>();
		
		 try {
	            con = DataSource.getConnection();
	            String query = "SELECT * FROM Users WHERE usertype = ?";
	            ps = con.prepareStatement(query);
	            ps.setString(1, UserType.Consumer.name());
	            rs = ps.executeQuery();

	            while (rs.next()) {
	                ConsumerDTO consumer = new ConsumerDTO();
	                // Populate ConsumerDTO from the result set
	                consumer.setUserId(rs.getInt("userid"));
	                consumer.setName(rs.getString("username"));
	                consumer.setEmail(rs.getString("email"));
	            
	                consumers.add(consumer);
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

	        return consumers;
		
	}


}
