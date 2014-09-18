package pl.altkom.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pl.altkom.entity.Product;

/**
 *
 * @author Edward
 */
@Stateless
public class JpaProductRepository implements ProductRepository {

    private static final String JPA_GET_ALL = "select p from Product p"; 
    private static final String JPA_GET_BY_NAME = "select p from Product p where p.name like :name";
    private static final String JPA_GET_BY_PRICE = "select p from Product p where p.price like :price";
    private static final String JPA_GET_BY_CATEGORY = "select p from Product p where p.category like :category";

    @PersistenceContext(unitName = "KielbasowyStorehousePU")
    private EntityManager entityManager;

    private String prepareQuery(String baseQuery, String order) {
        return baseQuery + " order by p." + order;
    }

    @Override
    public Long add(Product product) {
        entityManager.persist(product);
        entityManager.flush();
        entityManager.refresh(product);
        return product.getId();
    }

    @Override
    public List<Product> getAll(String order) {
        return entityManager
                .createQuery(prepareQuery(JPA_GET_ALL, order))
                .getResultList();

    }

    @Override
    public List<Product> getAll(FilterType filterType, String value, String order) {
        Query query;
        switch (filterType) {
            case NAME:
                query = entityManager
                        .createQuery(prepareQuery(JPA_GET_BY_NAME, order))
                        .setParameter("name", value + "%");
                break;
            case PRICE:

                int min = Integer.parseInt(value);
                int max = Integer.parseInt(value);
                query = entityManager
                        .createQuery(prepareQuery(JPA_GET_BY_PRICE, order))
                        .setParameter("min", min)
                        .setParameter("max", max);
                break;
            case CATEGORY:
                query = entityManager
                        .createQuery(prepareQuery(JPA_GET_BY_CATEGORY, order))
                        .setParameter("category", value + "%");
                break;
            default:
                throw new IllegalArgumentException();
        }
        return query.getResultList();
    }

    @Override
    public void remove(Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product != null) {
            entityManager.remove(product);
        }
    }

    @Override
    public void update(Product product) {

        entityManager.merge(product);
    }

}
