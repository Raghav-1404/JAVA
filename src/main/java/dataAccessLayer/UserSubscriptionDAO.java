package dataAccessLayer;

import transferObject.UserSubscriptionDTO;
import java.util.List;

public interface UserSubscriptionDAO {
    void addUserSubscription(UserSubscriptionDTO subscription);
    void removeUserSubscription(int id);
    List<UserSubscriptionDTO> getUserSubscriptionsByLocation(String location);
}