package carpool;
import java.util.List;
import java.util.Scanner;
public class Main 
{
  public static void main(String args[])
  {
	  Scanner sc=new Scanner(System.in);
	  RideBooking app=new RideBooking();
	  app.addRide(new Ride("Noida", "Delhi", 1, 3, 100));
      app.addRide(new Ride("Gurgaon", "Delhi", 2, 2, 150));
      
      System.out.println("enter email: ");
      String email=sc.next();
      
      System.out.println("enter Password: ");
      String password=sc.next();
      
      User user=app.signin(email, password);
      
      if(user==null)
      {
    	  System.out.println("User not found. Registering...");
          System.out.print("Enter name: ");
          String name=sc.next();
          user=app.Register(name, email, password);
          System.out.println("Welcome, " + user.getName());
          
          System.out.print("Enter source: ");
          String source = sc.next();

          System.out.print("Enter destination: ");
          String destination = sc.next();

          System.out.print("Enter seats required: ");
          int seats = sc.nextInt();
          List<Ride>available=app.searchrides(source, destination, seats);
          if(available.isEmpty())
          {
        	  System.out.println("no seats available");
        	  return ;
          }
          Ride selectedRide=available.get(0);
          System.out.println("ride found:");
          
          Booking booking=app.bookRide(user, selectedRide, seats);
          
          if(booking!=null)
          {
        	  System.out.println("ride booked successfully");
        	  System.out.println(booking);
          }
      }
      
  }
}
