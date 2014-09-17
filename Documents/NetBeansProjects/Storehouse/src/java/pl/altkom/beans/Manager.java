/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.altkom.beans;

import java.util.List;
import javax.ejb.Local;
import pl.altkom.entity.Product;

/**
 *
 * @author Edward
 */
@Local
public interface Manager {
     
    public void saveProductData(Product product);
    
    public List<Product> getProducts();

    public List<Product> getProducts(String name, long min, long max, String category, String sort, String order);

    public void deleteProduct(int id);

    public Product getProductById(int id);

    public void updateProduct(Product product);
}


