package bean;
public class Person {
    private String Username;
    private String Name;
    private Integer Age;
    private String Teleno;

    public Person() {}
    public Person(String Username_, String Name_, Integer Age_, String Teleno_) {
        this.Username = Username_;
        this.Name = Name_;
        this.Age = Age_;
        this.Teleno = Teleno_;
    }
    public Person(String Username_, String Name_, String Teleno_) {
        this.Username = Username_;
        this.Name = Name_;
        this.Age = null;
        this.Teleno = Teleno_;
    }
    public Person(String Username_, String Name_, Integer Age_) {
        this.Username = Username_;
        this.Name = Name_;
        this.Age = Age_;
        this.Teleno = "";
    }
    public Person(String Username_, String Name_) {
        this.Username = Username_;
        this.Name = Name_;
        this.Age = null;
        this.Teleno = "";
    }

    public void setUsername(String Username_) {
        this.Username = Username_;
    }
    public String getUsername() {
        return Username;
    }
    
    public void setName(String Name_) {
        this.Name = Name_;
    }
    public String getName() {
        return Name;
    }
    
    public void setAge(Integer Age_) {
        this.Age = Age_;
    }
    public Integer getAge() {
        return Age;
    }
    
    public void setTeleno(String Teleno_) {
        this.Teleno = Teleno_;
    }
    public String getTeleno() {
        return Teleno;
    }
}
