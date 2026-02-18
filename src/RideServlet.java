import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/bookRide")
public class RideServlet extends HttpServlet {

    final RideBooking rideBooking = new RideBooking();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        int rideId = Integer.parseInt(request.getParameter("rideId"));
        int seatsToBook = Integer.parseInt(request.getParameter("seats"));

        // 1️⃣ Sign in or Register
        User user = rideBooking.signin(email, password);

        if (user == null) {
            user = rideBooking.Register(name, email, password);
        }

        // 2️⃣ Fetch existing ride
        Ride ride = rideBooking.getRideById(rideId);

        // 3️⃣ Book ride once
        Booking booking = rideBooking.bookRide(user, ride, seatsToBook);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (booking == null) {
            out.println("<h2>Seats not available!</h2>");
        } else {
            out.println("<h2>Booking Successful!</h2>");
            out.println("<p>" + booking.toString() + "</p>");
        }
    }
}
