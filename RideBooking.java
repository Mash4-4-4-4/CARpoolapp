package backend;
import java.util.ArrayList;
import java.util.List;
public class RideBooking {
	List<user> userlist = new ArrayList<>();
	
	public user login(String email, String password)
	{ 
		for(user user:userlist)
		{
			if(user.email==email&&user.password==password)
			{
				System.out.println("user created");
				return user;
			}
		}
		return null;

	}
	
	 public user signup(int user_id, String name, String email, String password) {
	        user user = new user(user_id, name, email, password);
	        userlist.add(user);
	        return user;
	 }
		public List<ride> rideList=new ArrayList<>();
		public void createRide(int id,String source, String destination,int seats,double fare){
			ride ride1 =new ride(id,source,destination,seats,fare);
			rideList.add(ride1);
		}
		public List<ride> showAllRides(){
			return rideList;
		}
	}

