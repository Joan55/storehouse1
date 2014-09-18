/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.altkom.view.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import pl.altkom.entity.Product;
import pl.altkom.service.ProductRepository;
import static pl.altkom.service.ProductRepository.*;
import pl.altkom.service.ProductRepository.FilterType;


@ConversationScoped
@ManagedBean
public class Manager implements Serializable {

    @Inject
    private ProductRepository repository; 
    private List<Product> products; 
    private String order = NAME; 
    private FilterType filterType = FilterType.NAME;
    private String filterValue;
    @Inject
    private Conversation conversation;
    private Product editProduct;

    public FilterType getFilterType() {
        return filterType;
    }

    public Product getEditProduct() {
        return editProduct;
    }

    public void setEditProduct(Product editProduct) {
        this.editProduct = editProduct;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }

    public ProductRepository getRepository() {
        return repository; // getter do repository
    }

    public void setRepository(ProductRepository repository) {
        this.repository = repository; // setter do repository
    }

    public List<Product> getProducts() {
        return products; // getter do cargos
    }

    public void setProducts(List<Product> products) {
        this.products = products; // setter do cargos
    }

    @PostConstruct
    public void init() {
        products = repository.getAll(order); 
        editProduct = new Product();
    }

    public void filter() {
        products = repository.getAll(filterType, filterValue, order);
    }

    public void resetFilterValue() {
        filterValue = "";
    }

    public String comeBack() {
        return "index";
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order; 
    }

    public void remove(Long id) {
        repository.remove(id);
        init();
    }

    public String edit(Product product) {
        conversation.begin();
        editProduct = product;
        return "edit";
    }

    public String update() {
        conversation.end();
        repository.update(editProduct);
        System.out.println(editProduct.toString());
        init();
        return "manager";
    }

    public void reset() {
        filterType = ProductRepository.FilterType.NAME;
        filterValue = "";
        order = NAME;
        init();
    }
}
