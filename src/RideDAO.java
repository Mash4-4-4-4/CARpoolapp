import java.sql.*;
import java.util.*;

public class RideDAO {

    public void updateSeats(int rideId, int seatsBooked) {

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "UPDATE rides SET avail_seats = avail_seats - ? WHERE ride_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, seatsBooked);
            ps.setInt(2, rideId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Insert new ride into database
    public void addRide(String source, String destination, int seats, double price) {

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "INSERT INTO rides(source, destination, avail_seats, price) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, source);
            ps.setString(2, destination);
            ps.setInt(3, seats);
            ps.setDouble(4, price);

            ps.executeUpdate();

            System.out.println("Ride added to database successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Search rides from database
    public List<Ride> searchRides(String source, String destination, int seats) {

        List<Ride> rides = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM rides WHERE source=? AND destination=? AND avail_seats>=?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, source);
            ps.setString(2, destination);
            ps.setInt(3, seats);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                rides.add(new Ride(
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getDouble("price"),
                        rs.getInt("ride_id"),
                        rs.getInt("avail_seats")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rides;
    }
    public Ride getRideById(int rideId) {
        try (Connection conn = DBConnection.getConnection()) {

            String sql = "SELECT * FROM rides WHERE ride_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, rideId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Ride(
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getDouble("price"),
                        rs.getInt("ride_id"),
                        rs.getInt("avail_seats")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
