// FoodItemDTO.java
package transferObject;
public class FoodItemDTO {
    private int id;
    private String name;
    private int quantity;
    private String retailer;

    // Constructors
    public FoodItemDTO() {}

    public FoodItemDTO(int id, String name, int quantity, String retailer) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.retailer = retailer;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getAllId() {
        return id;
    }

    public void setAllId(int id) {
        this.id = id;
    }
    
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }
}
