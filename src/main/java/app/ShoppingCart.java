package app;

import java.util.ArrayList;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@Component
@SessionScope
public class ShoppingCart {
    private ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
    private double total;
    @Autowired
    ProductRepository productDb;

    public void setCartItems(){
        this.cartItems = new ArrayList<CartItem>();
    }

    public ArrayList<CartItem> getCartItems() {
        return this.cartItems;
    }

    public double getTotal() {
        return this.total;
    }

    public void computeTotal() {
        double sum = 0.00;
        for(CartItem item: cartItems){
            sum += item.getSubtotal();
        }
        total = sum;
    }

    public boolean changeQuantity(CartItem changeItem){
        int index = 0;
        Optional<Product> findProduct = productDb.findById(changeItem.getId());
        Product product = findProduct.get();
        if(product.getStock() < changeItem.getQuantity()){
            return false;
        } 
        for(CartItem item : cartItems){
            if(item.getId() == changeItem.getId()){
                item.setQuantity(changeItem.getQuantity());
                item.computeSubtotal();
                computeTotal();
                return true;
            }
            index++;
        }
        return false;
    }

    public void removeItem(int id){
        int index = 0;
        for(CartItem item : cartItems){
            if(item.getId() == id){
                cartItems.remove(index);
                computeTotal();
                break;
            }
            index++;
        }
    }
    
    public boolean addItem(CartItem toAddItem){        
        Optional<Product> findProduct = productDb.findById(toAddItem.getId());
        Product product = findProduct.get();   
        if(toAddItem.getQuantity() > product.getStock()){
            return false;
        }     
        boolean added = false;
        for(CartItem item : cartItems){
            if(item.getId() == toAddItem.getId()){
                int newQty = item.getQuantity() + toAddItem.getQuantity();
                if(!(newQty > product.getStock())){                    
                    item.setQuantity(newQty);
                    item.computeSubtotal();                    
                    computeTotal();
                    added = true;
                }
                else{
                    return false;
                }
            }            
        }   

        if(!added){
            cartItems.add(toAddItem);
            computeTotal();
            added = true;
        }
        return added;
    }
}
