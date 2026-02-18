package carpool;

import java.util.ArrayList;
import java.util.List;

public class RideBooking 
{
	private ArrayList<User>users=new ArrayList<>();
	private ArrayList<Ride>rides=new ArrayList<>();
	private ArrayList<Booking>bookings=new ArrayList<>();
      
	public User signin(String email,String password)
	{
		for(User u : users)
		{
			if(u.getEmail().equals(email)&&u.checkpswd(password))
			{
				return u;
			}
		}
		return null;
	}
	public User Register(String name,String email,String password)
	{
		int user_id=users.size()+1;
		User user=new User(name,email,password,user_id);
		users.add(user);
		return user;
	}
	
	public void addRide(Ride ride)
	{
		rides.add(ride);
	}
	public List<Ride> searchrides(String source,String destination,int seats)
	{
		List<Ride>result=new ArrayList<>();
		for(Ride r:rides)
		{
			if(r.matches(source, destination)&&r.cabbook(seats))
				result.add(r);		
			}
		return result;
	}
	
	public Booking bookRide(User user,Ride ride,int seats)
	{
		if(!ride.cabbook(seats))
		{
			System.out.println("seats not available");
			return null;
		}
		ride.bookSeats(seats);
		double price=ride.totprice(seats);
		
		Booking booking =new Booking(bookings.size()+1,
				user,ride,seats,price);
		bookings.add(booking);
		return booking;
	}
}
