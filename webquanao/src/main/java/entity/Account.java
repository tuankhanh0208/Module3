package entity;

public class Account {
    private int id;
   private String user;
   private String pass;
   private String role;


    public Account(int id, String user, String pass,String role) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.role=role;

    }

    public Account(String user, String pass) {
        this.user = user;
        this.pass = pass;

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


}
