package pl.training.service;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pl.training.entity.Client;

@Stateless
public class JpaClientsRepository implements ClientsRepository {

    private static final String JPA_GET_ALL = "select c from Client c";
    private static final String JPA_GET_BY_LAST_NAME = "select c from Client c where c.lastName like :lastName";
    private static final String JPA_GET_BY_FIRST_NAME = "select c from Client c where c.firstName like :firstName";
    private static final String JPA_GET_BY_SEX = "select c from Client c where c.sex = :sex";
    private static final String JPA_GET_BY_IS_PARENT = "select c from Client c where c.parent = :parent";
    private static final String JPA_GET_BY_AGE = "select c from Client c where c.dateOfBirth BETWEEN :start and :end";
    
    @PersistenceContext(unitName = "itlove")
    private EntityManager entityManager;
    
    @Override
    public Long add(Client client) {
        entityManager.persist(client);
        entityManager.flush();
        entityManager.refresh(client);
        return client.getId();
    }

    private String prepareQuery(String baseQuery, String order) {
        return baseQuery + " order by c." + order;
    }
    
    @Override
    public List<Client> getAll(String order) {
        return entityManager
                .createQuery(prepareQuery(JPA_GET_ALL, order))
                .getResultList();
    }

    @Override
    public List<Client> getAll(FilterType filterType, String value, String order) {
        Query query;
        switch (filterType) {
            case LAST_NAME:
                query = entityManager
                        .createQuery(prepareQuery(JPA_GET_BY_LAST_NAME, order))
                        .setParameter("lastName", value + "%");
                break;
            case FIRST_NAME:
                query = entityManager
                        .createQuery(prepareQuery(JPA_GET_BY_FIRST_NAME, order))
                        .setParameter("firstName", value + "%");
                break;
            case SEX:
                query = entityManager
                        .createQuery(prepareQuery(JPA_GET_BY_SEX, order))
                        .setParameter("sex", Client.Sex.valueOf(value));   
                break;
            case IS_PARENT:
                query = entityManager
                        .createQuery(prepareQuery(JPA_GET_BY_IS_PARENT, order))
                        .setParameter("parent", Boolean.valueOf(value));   
                break;
            case AGE:
                Calendar now = Calendar.getInstance();
                now.add(Calendar.YEAR, -Integer.parseInt(value));
                now.set(Calendar.MONTH, 1);
                now.set(Calendar.DAY_OF_MONTH, 1);
                Date start = now.getTime();
                now.set(Calendar.MONTH, 12);
                now.set(Calendar.DAY_OF_MONTH, 31);
                Date end = now.getTime();                
                query = entityManager
                        .createQuery(prepareQuery(JPA_GET_BY_AGE, order))
                        .setParameter("start", start)
                        .setParameter("end", end);
                break;                        
            default:
                throw new IllegalArgumentException();
        } 
        
        return query.getResultList();
    }

    @Override
    public void remove(Long id) {
        Client client = entityManager.find(Client.class, id);
        if (client != null) {
            entityManager.remove(client);
        }
    }

    @Override
    public void update(Client client) {
        entityManager.merge(client);
    }
    
}
