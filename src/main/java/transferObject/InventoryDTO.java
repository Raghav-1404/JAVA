package transferObject;

import java.util.Date;

public class InventoryDTO {
	
    private int itemId;
    private String itemName;
    private int quantity=1;
    private double price;
    private boolean isSurPlus= false;
    
    

    public InventoryDTO(String name, int itemId, double price) {
        this.itemId = itemId;
        this.itemName = name;
        this.quantity = quantity;
    }
    public InventoryDTO() {
    }
    
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return itemName;
    }

    public void setName(String name) {
        this.itemName = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isSurPlus() {
		return isSurPlus;
	}
	public void setSurPlus(boolean isSurPlus) {
		this.isSurPlus = isSurPlus;
	}
    

}
