package buisnessLayer;

import java.util.List;

import dataAccessImpl.UserDaoImpl;
import transferObject.UserDTO;

public class UserBuisnessLogic {
	
	
	private UserDaoImpl userDao = null;

	
	public UserBuisnessLogic() {
        this.userDao = new UserDaoImpl();
    } 
	
	public UserDTO getUserById(int userId) {
		return userDao.getUserById(userId);
	}


	public List<UserDTO> getAllUsers() {
		return userDao.getAllUsers();
	}


	public void addUser(UserDTO user) {
		userDao.addUser(user);
	}

	public void updateUser(UserDTO user) {
		userDao.updateUser(user);
		
	}

	public void deleteUser(int userId) {
		userDao.deleteUser(userId);
	}

}
