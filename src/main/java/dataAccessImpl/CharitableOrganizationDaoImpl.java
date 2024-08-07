package dataAccessImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccessLayer.CharitableOrganizationDao;
import dataAccessLayer.DataSource;
import transferObject.CharitableOrganizationDTO;
import transferObject.UserType;

public class CharitableOrganizationDaoImpl implements CharitableOrganizationDao{



	@Override
	public List<CharitableOrganizationDTO> getAllCharitableOrganizations() {
        List<CharitableOrganizationDTO> charitableOrganizations = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DataSource.getConnection();
            String query = "SELECT * FROM Users WHERE usertype = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, UserType.Charitable_Organization.name());
            rs = ps.executeQuery();

            while (rs.next()) {
                CharitableOrganizationDTO charity = new CharitableOrganizationDTO();
                // Populate CharitableOrganizationDTO from the result set
                charity.setUserId(rs.getInt("userid"));
                charity.setName(rs.getString("username"));
                charity.setEmail(rs.getString("email"));
                charitableOrganizations.add(charity);
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

        return charitableOrganizations;
    }

	
}
