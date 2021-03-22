package app;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product implements Serializable{
    @Id
    private int id;

    @Column
    private String title;

    @Column
    private String category;

    @Column
    private int stock;

    @Column
    private double price;

    @Column(length = 1000)
    private String description;

    @Column(length = 500)
    private String photos;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems;

    public Product(){}

    public Product(int id, int stock, double price, String description){
        this.id = id;
        this.stock = stock;
        this.price = price;
        this.description = description;
    } 

    public Product(int id, String title, String category, int stock, double price, String description, String photos){
        this.id = id;
        this.title = title;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.description = description;
        this.photos = photos;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotos() {
        return this.photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public List<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public String getPhoto(int i){
        if(photos.length() < 1){
            return "";
        }
        String []pics = photos.split("#");
        if(!(i >= pics.length)){
            return pics[i];
        }
        return photos;
    }
}
