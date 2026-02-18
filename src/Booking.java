
public class Booking
{
    int bookingId;
    Ride ride;
    User user;
    int seatsbooked;
    double totprice;
    Booking(User user, Ride ride, int seatsbooked, double totprice)
    {
        this.bookingId=bookingId;
        this.user=user;
        this.ride=ride;
        this.seatsbooked=seatsbooked;
        this.totprice=totprice;
    }
    @Override
    public String toString()
    {
        return "Booking[bookingid:"+bookingId+
                ",user:"+user.getName()+
                ",rideid"+ride.getrideid()+
                ",seats:"+seatsbooked+
                ",totalroce"+totprice+"]";
    }
}