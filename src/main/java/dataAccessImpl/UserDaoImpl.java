package dataAccessImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccessLayer.DataSource;
import dataAccessLayer.UserDao;
import transferObject.UserDTO;
import transferObject.UserType;

public class UserDaoImpl implements UserDao {
	
	Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    
	@Override
	public UserDTO getUserById(int userId) {
		UserDTO user = null;
	    try {
	        con = DataSource.getConnection();
	        String query = "SELECT * FROM Users WHERE userid = ?";
	        ps = con.prepareStatement(query);
	        ps.setInt(1, userId);
	        rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            user = new UserDTO();
	            user.setUserId(rs.getInt("userid"));
	            user.setName(rs.getString("username"));
	            user.setEmail(rs.getString("email"));
	            user.setPassword(rs.getString("password"));
	            user.setUserType(UserType.valueOf(rs.getString("usertype")));
	        }
	    } catch (SQLException | IOException e) {
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
	    return user;
	}
	


	@Override
	public List<UserDTO> getAllUsers() {
		List<UserDTO> users = new ArrayList<>();
	    try {
	        con = DataSource.getConnection();
	        String query = "SELECT * FROM Users";
	        ps = con.prepareStatement(query);
	        rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            UserDTO user = new UserDTO();
	            user.setUserId(rs.getInt("userid"));
	            user.setName(rs.getString("username"));
	            user.setEmail(rs.getString("email"));
	            user.setPassword(rs.getString("password"));
	            // Convert user type from string to enum
	            user.setUserType(UserType.valueOf(rs.getString("usertype")));
	            users.add(user);
	        }
	    } catch (SQLException | IOException e) {
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
	    return users;
	}

	@Override
	public void addUser(UserDTO user) {
		try {
            con = DataSource.getConnection();
            ps = con.prepareStatement("INSERT INTO Users (username,email,password,usertype) VALUES (?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getUserType().name());
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
	}

	@Override
	public void updateUser(UserDTO user) {
		try {
	        con = DataSource.getConnection();
	        ps = con.prepareStatement("UPDATE Users SET username=?, email=?, password=?, usertype=? WHERE userid=?");
	        ps.setString(1, user.getName());
	        ps.setString(2, user.getEmail());
	        ps.setString(3, user.getPassword());
	        ps.setString(4, user.getUserType().name());
	        ps.setInt(5, user.getUserId());
	        ps.executeUpdate();
	    } catch (SQLException | IOException e) {
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
	}

	@Override
	public void deleteUser(int userId) {
		try {
	        con = DataSource.getConnection();
	        ps = con.prepareStatement("DELETE FROM Users WHERE userid=?");
	        ps.setInt(1, userId);
	        ps.executeUpdate();
	    } catch (SQLException | IOException e) {
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
		
	}

}
