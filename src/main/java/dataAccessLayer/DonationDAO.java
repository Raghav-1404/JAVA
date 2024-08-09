package dataAccessLayer;

import transferObject.DonationDTO;
import java.util.List;

public interface DonationDAO {
    void addDonation(DonationDTO donation);
    List<DonationDTO> getDonationsByUser(String userEmail);
    List<DonationDTO> getAllDonations();
    void updateDonationStatus(int donationId, String status);
}