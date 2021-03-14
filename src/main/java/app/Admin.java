package app;

import javax.persistence.Entity;

@Entity
public class Admin extends GeneralUser{
    public Admin(){}

    public Admin(String firstName, String surname, String email, String password){
        super(firstName, surname, email, password);
    }   
}
