package dataAccessImpl;

import dataAccessLayer.RetailerDAO;
import java.util.ArrayList;
import java.util.List;
import transferObject.ItemDTO;

public class RetailerDAOImpl implements RetailerDAO {
    private List<ItemDTO> inventory;
    private double discountRate = 0.1; // 10% discount rate

    public RetailerDAOImpl() {
        inventory = new ArrayList<>();
        inventory.add(new ItemDTO(1, "Apple", 1.0));
        inventory.add(new ItemDTO(2, "Banana", 0.5));
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return inventory;
    }

    @Override
    public void updateInventory(int itemId, int quantity) {
        for (ItemDTO item : inventory) {
            if (item.getId() == itemId) {
                int newQuantity = item.getQuantity() - quantity;
                item.setQuantity(newQuantity);
                System.out.println("Inventory updated: " + item.getName() + " now has " + newQuantity + " left.");
                return;
            }
        }
        System.out.println("Item not found in inventory.");
    }

    @Override
    public double getDiscountRate() {
        return discountRate;
    }
}
