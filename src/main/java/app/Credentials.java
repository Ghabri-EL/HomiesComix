package app;

public class Credentials {
    private String email;
    private String password;
    private boolean isAdmin = false;

    public Credentials(){}

    public Credentials(String email, String password, boolean admin){
        this.email = email;
        this.password = password;
        this.isAdmin = admin;
    }

    public Credentials(String email, String password){
        this.email = email;
        this.password = password;
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

    public boolean isAdmin() {
        return this.isAdmin;
    }

    public boolean getAdmin() {
        return this.isAdmin;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
    }
}
