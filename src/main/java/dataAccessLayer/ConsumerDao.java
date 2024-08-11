package dataAccessLayer;

import java.util.List;


import transferObject.ConsumerDTO;

public interface ConsumerDao {
	
    List<ConsumerDTO> getAllConsumers();
	    
}
