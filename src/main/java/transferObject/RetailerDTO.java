package transferObject;

import java.util.List;

public class RetailerDTO extends UserDTO {

	
    private List<InventoryDTO> inventory;

    
    public RetailerDTO(int userId, String name, String email, UserType userType, List<InventoryDTO> inventory) {
        super(name, email, userType);
        this.inventory = inventory;
    }
    public RetailerDTO() {
    	
	}

    public List<InventoryDTO> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryDTO> inventory) {
        this.inventory = inventory;
    }
    
}
	


