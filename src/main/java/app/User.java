package app;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class User extends GeneralUser implements Serializable{    
    @Column
    private String address;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ClientOrder> orders;

    public User(){}

    public User(String firstname, String surname, String address, String email, String password){
        super(firstname, surname, email, password);
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ClientOrder> getOrders() {
        return this.orders;
    }

    public void setOrders(List<ClientOrder> orders) {
        this.orders = orders;
    }
}