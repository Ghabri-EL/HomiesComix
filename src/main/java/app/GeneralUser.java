package app;

import javax.persistence.MappedSuperclass;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@MappedSuperclass
public class GeneralUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstname")
    private String firstName;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String password;

    public GeneralUser() {
    }

    public GeneralUser(String firstname, String surname, String email, String password) {
        this.firstName = firstname;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstName;
    }

    public void setFirstname(String firstname) {
        this.firstName = firstname;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GeneralUser id(int id) {
        setId(id);
        return this;
    }

    public GeneralUser firstname(String firstname) {
        setFirstname(firstname);
        return this;
    }

    public GeneralUser surname(String surname) {
        setSurname(surname);
        return this;
    }

    public GeneralUser email(String email) {
        setEmail(email);
        return this;
    }

    public GeneralUser password(String password) {
        setPassword(password);
        return this;
    }
}
