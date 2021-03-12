package app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    
    @GetMapping("/")
    public String homePage(){
        return "home.html";
    }

    @GetMapping("/login_signup")
    public String login_signupPage(){
        return "login_signup.html";
    }
}
