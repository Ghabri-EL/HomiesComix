package app;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User extends GeneralUser{    
    @Column
    private String address;

    //@OneToMany
    //private List<Order> orders;

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

    // public List<Order> getOrders() {
    //     return this.orders;
    // }

    // public void setOrders(List<Order> orders) {
    //     this.orders = orders;
    // }

}