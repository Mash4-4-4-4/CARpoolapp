

import java.util.ArrayList;
import java.util.List;

public class RideBooking
{
    UserDAO userDAO = new UserDAO();
    RideDAO rideDAO = new RideDAO();
    BookingDAO bookingDAO = new BookingDAO();


    public User signin(String email, String password) {
        return userDAO.findByEmailAndPassword(email, password);
    }

    public User Register(String name, String email, String password) {
        return userDAO.register(name, email, password);
    }


    public void addRide(String source, String destination, int seats, double price) {
        rideDAO.addRide(source, destination, seats, price);
    }


    public Booking bookRide(User user, Ride ride, int seats) {

        if (ride.getAvailSeats() < seats) {
            return null;
        }

        rideDAO.updateSeats(ride.getrideid(), seats);

        Ride updatedRide = rideDAO.getRideById(ride.getrideid());

        return bookingDAO.createBooking(user, updatedRide, seats);
    }


    public List<Ride> searchrides(String source, String destination, int seats) {
        return rideDAO.searchRides(source, destination, seats);
    }
    public Ride getRideById(int rideId) {
        return rideDAO.getRideById(rideId);
    }


}