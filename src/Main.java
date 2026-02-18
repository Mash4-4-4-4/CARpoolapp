
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        RideBooking app = new RideBooking();

        System.out.println("Enter email:");
        String email = sc.nextLine();

        System.out.println("Enter password:");
        String password = sc.nextLine();

        User user = app.signin(email, password);

        if (user == null) {
            System.out.println("User not found. Registering...");
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            user = app.Register(name, email, password);
            System.out.println("Welcome, " + user.getName());
        }

        System.out.print("Enter source: ");
        String source = sc.nextLine();

        System.out.print("Enter destination: ");
        String destination = sc.nextLine();

        System.out.print("Enter seats required: ");
        int seats = Integer.parseInt(sc.nextLine());

        List<Ride> available = app.searchrides(source, destination, seats);

        if (available.isEmpty()) {
            System.out.println("No seats available");
            return;
        }

        Ride selectedRide = available.get(0);

        Booking booking = app.bookRide(user, selectedRide, seats);

        if (booking != null) {
            System.out.println("Ride booked successfully");
            System.out.println(booking);
        }
    }
}
