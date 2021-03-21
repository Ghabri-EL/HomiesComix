package app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private int quantity;
    @Column
    private double price;
    @Column
    private double subtotal;

    @ManyToOne
    private Product product;

    @ManyToOne
    private ClientOrder clientOrder;

    public OrderItem(){};

    public OrderItem(int quantity, double price, double subtotal, Product product, ClientOrder clientOrder){
        this.product = product;
        this.clientOrder = clientOrder;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }

    public OrderItem(int quantity, double price, double subtotal){
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }

    public long getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSubtotal() {
        return this.subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ClientOrder getClientOrder() {
        return this.clientOrder;
    }

    public void setClientOrder(ClientOrder clientOrder) {
        this.clientOrder = clientOrder;
    }
}
