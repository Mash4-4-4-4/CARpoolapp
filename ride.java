package carpool;

public class Ride
{
	int rideId;
	String source;
	String destination;
	int availSeats;
	double price;
	Ride(String source,String destination,double price,int rideId,int availSeats)
	{
		this.source=source;
		this.destination=destination;
		this.rideId=rideId;
		this.availSeats=availSeats;
		this.price=price;
	}
	public boolean matches(String src,String dest)
	{
		return source.equalsIgnoreCase(src)&&destination.equalsIgnoreCase(dest);
		
	}
	public boolean cabbook(int seats)
	{
		return availSeats>=seats;
	}
	public void bookSeats(int seats)
	{
		availSeats--;
	}
	public double totprice(int seats)
	{
		return seats*price;
	}
	public int getrideid()
	{
		return rideId;
	}
	
	
	@Override
	public String toString() {
		return "ride [ride_id=" + rideId + ", source=" + source + ", destination=" + destination + ", seats=" + availSeats
				+ ", fare=" + price + "]";
	}

}
