package app;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AppController {
        
    @Autowired
    AdminRepository adminDb;

    @Autowired
    SessionHandler sessionCred;

    @Autowired
    UserRepository userDb;

    @Autowired
    PasswordHandler passwordHandler;

    @Autowired
    ImageHandler imageHandler;

    @Autowired
    ProductRepository productDb;

    @Autowired
    ShoppingCart shoppingCart;

    @Autowired
    OrderItemRepository orderItemDb;

    @Autowired
    ClientOrderRepository clientOrderDb;

    @GetMapping("/")
    public String homePage(Model model){
        model.addAttribute("credentials", sessionCred.getCredentials());
        return "home.html";
    }

    @GetMapping("/login_signup")
    public String login_signupPage(HttpServletResponse response){    
        if(sessionCred.getCredentials() != null){
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }        
        return "login_signup.html";
    }

    @PostMapping("/login")
    public void loginHandler(String email, String password, HttpServletResponse response){
        Admin admin = null;
        admin = adminDb.findAdminByEmailAndPassword(email, passwordHandler.hashPass(password));
        User user = null;
        user = userDb.findUserByEmailAndPassword(email, passwordHandler.hashPass(password));
        if(admin != null || user != null){            
            if(admin != null){            
                sessionCred.setCredentials(new Credentials(admin, true));
            }
            else{
                sessionCred.setCredentials(new Credentials(user));
            }            
        
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                response.sendRedirect("/login_signup?re=logfail&msg=Failed+to+log+in.+Wrong+email+or+password.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping("/signup")
    public void signupHandler(String firstname, String surname, String address,
                                String email, String password, String password_confirm, HttpServletResponse response){
        User user = null;
        user = userDb.findUserByEmail(email);        
        String msg = "";
        if(user == null){
            User saveUser = new User(firstname, surname, address, email, passwordHandler.hashPass(password)); 
            userDb.save(saveUser);           
            msg="Registered+Successfully";
            try {
                response.sendRedirect("/login_signup?re=success&msg="+msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            if(!password.equals(password_confirm)){
                msg="Passwords+do+not+match";
            }
            else{
                msg="User+already+exits";
            }

            try {
                response.sendRedirect("/login_signup?re=signfail&msg="+msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } 
    
    @GetMapping("/logout")
    public void logout(HttpServletResponse response){
        sessionCred.setCredentials(null);
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("credentials", sessionCred.getCredentials());
        model.addAttribute("products", productDb.findAll());
        return "products.html";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        model.addAttribute("credentials", sessionCred.getCredentials());
        Optional<Product> findProduct = productDb.findById(id);   
        Product product = findProduct.get();
        model.addAttribute("product", product);
        return "details.html";
    }
    //Deny access to details page without a product id
    @GetMapping("/details")
    public void detailsNull(HttpServletResponse response){
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/profile")
    public String profile(Model model){        
        model.addAttribute("credentials", sessionCred.getCredentials());
        return "profile.html";
    }

    @GetMapping("/userdetails")
    public String returnUserDetails(Model model){
        model.addAttribute("credentials", sessionCred.getCredentials());
        if(sessionCred.getCredentials().isAdmin()){
            return "admin_details.html";
        }
        return "user_details.html";
    }
   
    @PostMapping("/sell_product")
    public void sellProduct(int id, String title, String category, int stock, double price,
                            String description, @RequestParam(name = "images") MultipartFile[] images,
                            HttpServletResponse response){
        Optional<Product> findProduct = productDb.findById(id);         

        if(findProduct.isPresent()){
            Product saveProduct = findProduct.get();
            saveProduct.setStock(saveProduct.getStock() + stock);
            productDb.save(saveProduct);
        }
        else{
            Product saveProduct = new Product(id, title, category.toLowerCase(), stock, price, description, imageHandler.saveImages(id, images));
            productDb.save(saveProduct);
        }
        try {
            response.sendRedirect("/products");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/sell_form")
    public String sellForm(){
        return "sell_product.html";
    }

    @GetMapping("/cart")
    public String cart(Model model){
        model.addAttribute("credentials", sessionCred.getCredentials());
        model.addAttribute("shop_cart", shoppingCart);
        return "shop_cart.html";
    }

    @PostMapping("/addToCart")
    public @ResponseBody String addToCart(@RequestBody CartItem cartItem){
        Optional<Product> findProduct = productDb.findById(cartItem.getId());
        Product product = findProduct.get();
        cartItem.setProduct(product);
        cartItem.computeSubtotal();
        boolean result = shoppingCart.addItem(cartItem);
        String msg="";

        if(result){
            msg="&diams; Product added successfully to the shopping cart &diams;";
        }
        else{
            msg="&diams; Failed to add product to shopping cart &diams;";
        }
        return  msg;
    }

    @GetMapping("/remove_product/{id}")
    public @ResponseBody Integer removeProduct(@PathVariable int id){
        shoppingCart.removeItem(id);
        return id;
    }

    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("shop_cart", shoppingCart);
        return "checkout.html";
    }

    @PostMapping("/payment")
    public void paymentProcessing(HttpServletResponse response){
        ClientOrder order = new ClientOrder();
        for(CartItem item : shoppingCart.getCartItems()){
            
            Product product = item.getProduct();
            //constructor takes quantity, price, subtotal or quantity, price, subtotal, product, order
            //OrderItem orderItem = new OrderItem(item.getQuantity(), product.getPrice(), );
        }


        try {
            response.sendRedirect("/products");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}