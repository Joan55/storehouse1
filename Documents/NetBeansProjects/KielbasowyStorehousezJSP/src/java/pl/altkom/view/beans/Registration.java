

package pl.altkom.view.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import pl.altkom.entity.Product;
import pl.altkom.service.ProductRepository;



@RequestScoped 
@ManagedBean
public class Registration {
    @Inject
    private ProductRepository repository;
    private Product product = new Product();

    public ProductRepository getRepository() {
        return repository;
    }

    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    
    public String save() { // metoda save. dodaje cargo do repozytorium (tu odwołuje się do metody z interfejsu)
        repository.add(product);
        return "index?faces-redirect=true"; // metoda nie jest Void więc coś ma zwracać. Ta zwraca kawałek URLa, pozwalającego na powrót do index.xhtml po kliknięciu "Dodaj towar"
    }
    
}
