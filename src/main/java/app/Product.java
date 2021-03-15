package app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
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

    @Column
    private String description;

    @Column
    private String photos;

    public Product(){}

    public Product(int id, String title, String category, int stock, double price, String photos){
        this.id = id;
        this.title = title;
        this.category = category;
        this.stock = stock;
        this.price = price;
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

}
