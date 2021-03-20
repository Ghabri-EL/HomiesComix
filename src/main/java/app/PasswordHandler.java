package app;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

@Component
public class PasswordHandler {
    public String hashPass(String s){
        String hashedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
           // md.update(salt);
            byte[] hashedPwd = md.digest(s.getBytes(StandardCharsets.UTF_8));
            StringBuilder buildPsdStr = new StringBuilder();
            for(int i = 0; i < hashedPwd.length; i++){
                buildPsdStr.append(Integer.toString((hashedPwd[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashedPassword = buildPsdStr.toString();
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return hashedPassword;
    }

    public boolean match(String pwd1, String pwd2){
        return pwd1.equals(hashPass(pwd2));
    }
}
