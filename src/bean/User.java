package bean;
public class User {
    private String Username;
    private String Password;
    public User() {}
    public User(String username, String password) {
        this.Username = username;
        this.Password = password;
    }
    public void setUsername(String Username_) {
        this.Username = Username_;
    }
    public String getUsername() {
        return Username;
    }
    public void setPassword(String Password_) {
        this.Password = Password_;
    }
    public String getPassword() {
        return Password;
    }
}
