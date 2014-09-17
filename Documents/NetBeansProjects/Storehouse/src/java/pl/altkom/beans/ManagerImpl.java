/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.altkom.beans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pl.altkom.entity.Product;
@Stateless
public class ManagerImpl implements Manager {

    @PersistenceContext(unitName = "StorehousePU")
    private EntityManager em;

    @Override
    public void saveProductData(Product product) {
        em.persist(product);
    }

   @Override
    public List<Product> getProducts() {
        return em.createNamedQuery("getAll", Product.class)
                .getResultList();
    }

    @Override
    public List<Product> getProducts(String name, long min, long max, String category, String sort, String order) {
      
        return em.createQuery("select p from Product p where (p.name like :name) and (p.price between :min and :max)and (p.category like :category)  order by p." + sort + " " + order, Product.class)
                .setParameter("name", name)
                .setParameter("min", min)
                .setParameter("max", max)
                .setParameter("category", category) 
                .getResultList();
    }

    @Override
    public void deleteProduct(int id) {
        Product product = em.find(Product.class, id);
        em.remove(product);
    }

    @Override
    public Product getProductById(int id) {
        return em.createNamedQuery("getById", Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void updateProduct(Product product) {
        em.merge(product);
    }

}
