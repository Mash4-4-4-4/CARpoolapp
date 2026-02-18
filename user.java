package carpool;

public class User 
{
	String name;
	String email;
	int UserId;
	String password;
	User(String name,String email,String password,int UserId)
	{
		this.name=name;
		this.email=email;
		this.password=password;
		this.UserId=UserId;
	}
	String getEmail()
	{
		return email;
	}
	String getName()
	{
		return name;
	}
	int getUserId()
	{
		return UserId;
	}
	boolean checkpswd(String password)
	{
		return this.password.equals(password);
	}
	
	@Override 
	public String toString()
	{
		return "user[email="+email+",name="+name+",user_id="+UserId+"]";
	}
	

}
