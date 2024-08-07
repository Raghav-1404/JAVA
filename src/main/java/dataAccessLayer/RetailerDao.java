package dataAccessLayer;

import java.util.List;

import transferObject.RetailerDTO;

public interface RetailerDao {
	
	RetailerDTO getRetailerById(int userId);
    List<RetailerDTO> getAllRetailers();

	
}
