package backend;
public class booking 
{
	int booking_id;
	ride ride;
	user user;
	int seat_booked;
	double total_fare;
	public booking(int booking_id, ride ride, user user, int seat_booked, double total_fare) {
		super();
		this.booking_id = booking_id;
		this.ride = ride;
		this.user = user;
		this.seat_booked = seat_booked;
		this.total_fare = total_fare;
	}
	@Override
	public String toString() {
		return "booking [booking_id=" + booking_id + ", seat_booked=" + seat_booked + ", total_fare=" + total_fare
				+ "]";
	}
	
	

}
