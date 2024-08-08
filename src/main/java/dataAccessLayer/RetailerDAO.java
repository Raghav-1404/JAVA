package dataAccessLayer;

import java.util.List;
import transferObject.ItemDTO;

public interface RetailerDAO {
    List<ItemDTO> getAllItems();
    void updateInventory(int itemId, int quantity);
    double getDiscountRate();
}
