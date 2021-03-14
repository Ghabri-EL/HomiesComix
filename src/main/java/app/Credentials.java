package app;

public class Credentials {
    GeneralUser genUser;
    private boolean admin = false;

    public Credentials(){}

    public Credentials(Admin adminUsr, boolean isAdmin){
        this.genUser = adminUsr;
        this.admin = isAdmin;
    }

    public Credentials(User user){
        this.genUser = user;
    }

    public GeneralUser getGenUser() {
        return this.genUser;
    }

    public void setGenUser(GeneralUser genUser) {
        this.genUser = genUser;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public boolean getAdmin() {
        return this.admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
