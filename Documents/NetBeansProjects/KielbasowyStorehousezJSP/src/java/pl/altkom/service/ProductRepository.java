
package pl.altkom.service;

import java.util.List;
import javax.ejb.Local;
import pl.altkom.entity.Product;


@Local
public interface ProductRepository {

    String NAME = "name";
    String PRICE = "price";
    String CATEGORY = "category";
    
    enum FilterType {
        NAME, PRICE, CATEGORY
    }
    
    Long add(Product product);
    
    List<Product> getAll(String order);

    List<Product> getAll(FilterType filterType, String value, String order); 

    void remove(Long id);

    void update(Product product);

}
