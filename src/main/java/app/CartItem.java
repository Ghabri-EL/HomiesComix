package app;

import java.io.Serializable;

public class CartItem implements Serializable{
    private int id;
    private String title;
    private double price = 0.0;
    private String image;
    private int quantity = 0;
    private double subtotal = 0.0;

    //to parse from json object
    public CartItem(int id, int quantity){
        this.id = id;
        this.quantity = quantity;
    }

    public CartItem(int quantity){
        this.quantity = quantity;
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

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return this.subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void computeSubtotal(){
        this.subtotal = this.price * this.quantity;
    }
    //discounts subtotal by percentage
    //input should be in decimal format 30% = 0.3
    public void discountSubtotal(double disc){
        double finalTotal =  subtotal - (subtotal * disc); 
        this.subtotal = finalTotal;
    }
}