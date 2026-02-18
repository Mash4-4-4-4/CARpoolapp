public class User {

    String name;
    String email;
    String password;
    int UserId;

    // Constructor for NEW user before ID is known
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Constructor for EXISTING user from database
    public User(String name, String email, String password, int UserId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.UserId = UserId;
    }

    public String getEmail() { return email; }
    public String getName() { return name; }
    public int getUserId() { return UserId; }

    public boolean checkpswd(String password) {
        return this.password.equals(password);
    }
}
