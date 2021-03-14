package app;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public void loginHandler(Credentials credentials, HttpServletResponse response){
        Admin admin = null;
        admin = adminDb.findAdminByEmailAndPassword(credentials.getEmail(), passwordHandler.hashPass(credentials.getPassword()));
        User user = null;
        user = userDb.findUserByEmailAndPassword(credentials.getEmail(), passwordHandler.hashPass(credentials.getPassword()));
        if(admin != null || user != null){
            if(admin != null){
                credentials.setAdmin(true);
            }
            
            sessionCred.setCredentials(credentials);
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
    
    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("credentials", sessionCred.getCredentials());
        return "products.html";
    }

    @GetMapping("/details")
    public String details(Model model){
        model.addAttribute("credentials", sessionCred.getCredentials());
        return "details.html";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        model.addAttribute("credentials", sessionCred.getCredentials());
        model.addAttribute("users",userDb.findAll());
        return "profile.html";
    }

    @GetMapping("/user/{id}")
    public String findUser(@PathVariable("id") Integer id, Model model){
        model.addAttribute("user", userDb.findById(id).get());
        return "user.html";
    }
}
