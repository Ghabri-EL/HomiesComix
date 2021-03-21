package app;

import java.io.Serializable;

public class CartItem implements Serializable{
    private int id;
    private Product product;
    private int quantity;
    private double subtotal;

    //to parse from json object
    public CartItem(int id, Product product, int quantity){
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        computeSubtotal();
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
    
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        computeSubtotal();
    }

    public double getSubtotal() {
        return this.subtotal;
    }

    public void computeSubtotal(){
        if(product != null){
            this.subtotal = quantity * product.getPrice();
        }   
    }
    //discounts subtotal by percentage
    //input should be in decimal format 30% = 0.3
    public void discountSubtotal(double disc){
        double finalTotal =  subtotal - (subtotal * disc); 
        this.subtotal = finalTotal;
    }
}