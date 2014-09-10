package pl.training.service;

import java.util.List;
import javax.ejb.Local;
import pl.training.entity.Client;

@Local
public interface ClientsRepository {
    
    String LAST_NAME = "lastName";
    String DATE_OF_BIRTH = "dateOfBirth";
    String SEX = "sex";
    
    enum FilterType {
        LAST_NAME, FIRST_NAME, SEX, AGE, IS_PARENT
    }
    
    Long add(Client client);
    
    List<Client> getAll(String order);
    
    List<Client> getAll(FilterType filterType, String value, String order);
    
    void remove(Long id);
    
    void update(Client client);
    
}
