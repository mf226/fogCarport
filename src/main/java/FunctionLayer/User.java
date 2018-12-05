package FunctionLayer;

/**
 * The purpose of User is to...
 * @author kasper
 */
public class User {
    
    private int id; // just used to demo retrieval of autogen keys in UserMapper
    private String email;
    private String password; // Should be hashed and secured
    private Role role;
    
    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = Role.CUSTOMER;
    }

    public User(int id, String email, String password, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User( String email, String password, Role role) {
        //set default role as Customer
        this.email = email;
        this.password = password;
        this.role = role;       
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

}
