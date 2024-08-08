package dataAccessLayer;

import java.util.List;
import transferObject.FoodItemDTO;

public interface FoodItemDAO {
    FoodItemDTO getFoodItemById(int id);
    List<FoodItemDTO> getAllFoodItems();
    void addFoodItem(FoodItemDTO foodItem);
    void updateFoodItem(FoodItemDTO foodItem);
    void deleteFoodItem(int id);

    public void updateFoodItemQuantity(int id, int newQuantity);
}
