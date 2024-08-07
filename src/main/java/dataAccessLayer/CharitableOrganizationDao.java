package dataAccessLayer;

import java.util.List;
import transferObject.CharitableOrganizationDTO;


public interface CharitableOrganizationDao {
    List<CharitableOrganizationDTO> getAllCharitableOrganizations();

}
