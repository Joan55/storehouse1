package pl.training.view.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.PostActivate;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.persistence.PrePersist;
import pl.training.entity.Client;
import pl.training.service.ClientsRepository;

import static pl.training.service.ClientsRepository.*;

@ConversationScoped
@ManagedBean
public class Manager implements Serializable{
    
    @Inject
    private Conversation conversation;
    
    @Inject
    private ClientsRepository repository;
    private String filterValue;
    private FilterType filterType = FilterType.LAST_NAME;
    private List<Client> clients;
    private String order = LAST_NAME;
    private Client editClient;
    
    @PostConstruct
    public void init() {
        editClient = new Client();
        clients = repository.getAll(order);
    }

    public String getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(String filterValue) {
        this.filterValue = filterValue;
    }

    public ClientsRepository.FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(ClientsRepository.FilterType filterType) {
        this.filterType = filterType;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
    
    public void filter() {
        clients = repository.getAll(filterType, filterValue, order);
    }
    
    public void reset() {
        filterType = ClientsRepository.FilterType.LAST_NAME;
        filterValue = "";
        order = LAST_NAME;
        init();
    }
    
    public void restFilterValue() {
        filterValue = "";
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Client getEditClient() {
        return editClient;
    }

    public void setEditClient(Client editClient) {
        this.editClient = editClient;
    }
    
    public void remove(Long id) {
        repository.remove(id);
        init();
    }

    public String update() {
        conversation.end();
        repository.update(editClient);
        System.out.println(editClient.toString());
        init();
        return "manager";
    }
    
    public String edit(Client client) {
        conversation.begin();
        editClient = client;
        return "edit";
    }
    
}
