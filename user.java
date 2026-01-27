package backend;
public class user
{
	String email;
	String name;
	String password;
	int user_id;
	public user(int user_id, String name,String email, String password) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "user [email=" + email + ", name=" + name + ", password=" + password + ", user_id=" + user_id + "]";
	}
	

}
