import java.sql.*;

public class BookingDAO {

    public Booking createBooking(User user, Ride ride, int seats) {

        try (Connection conn = DBConnection.getConnection()) {

            double totalPrice = seats * ride.getPrice();

            String sql = "INSERT INTO bookings(user_id, ride_id, seats_booked, total_price) VALUES (?, ?, ?, ?) RETURNING booking_id";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, user.getUserId());
            ps.setInt(2, ride.getrideid());
            ps.setInt(3, seats);
            ps.setDouble(4, totalPrice);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int bookingId = rs.getInt("booking_id");

                return new Booking(
                        user,
                        ride,
                        seats,
                        totalPrice
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
