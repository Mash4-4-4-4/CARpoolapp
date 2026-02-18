# 🚗 CarPooling Web Application

A full-stack carpooling web application built with Java Servlets, JSP, and PostgreSQL.

## Features

- ✅ User Registration & Authentication
- ✅ Search Available Rides
- ✅ Book Rides
- ✅ Offer Rides
- ✅ Real-time Seat Availability
- ✅ Booking Management
- ✅ Responsive Design

## Tech Stack

**Frontend:**
- JSP (JavaServer Pages)
- HTML5/CSS3
- Responsive Design

**Backend:**
- Java Servlets
- JDBC
- Session Management

**Database:**
- PostgreSQL

**Server:**
- Apache Tomcat 9+

## Quick Start

### Prerequisites
```bash
- Java JDK 8+
- Apache Tomcat 9+
- PostgreSQL 12+
- PostgreSQL JDBC Driver
```

### Option 1: Using Setup Script (Recommended)
```bash
cd carpooling-frontend
./setup.sh
```

### Option 2: Manual Setup

1. **Create Database:**
```sql
CREATE DATABASE ridesys;
```

2. **Create Tables:**
```sql
-- See SETUP_GUIDE.md for full SQL
CREATE TABLE users (...);
CREATE TABLE rides (...);
CREATE TABLE bookings (...);
```

3. **Update Database Config:**
Edit `WEB-INF/classes/DBConnection.java` with your credentials.

4. **Compile:**
```bash
cd WEB-INF/classes
javac -cp ".:$CATALINA_HOME/lib/servlet-api.jar:postgresql.jar" *.java
```

5. **Deploy:**
```bash
cp -r carpooling-frontend $CATALINA_HOME/webapps/carpooling
```

6. **Start Tomcat:**
```bash
$CATALINA_HOME/bin/startup.sh
```

7. **Access:**
```
http://localhost:8080/carpooling/
```

## Project Structure

```
carpooling-frontend/
├── index.jsp              # Landing page
├── login.jsp              # Login
├── register.jsp           # Registration
├── dashboard.jsp          # User dashboard
├── search-rides.jsp       # Search & book rides
├── add-ride.jsp           # Add new ride
├── booking-success.jsp    # Booking confirmation
├── css/
│   └── style.css         # Styles
├── WEB-INF/
│   ├── web.xml           # Deployment descriptor
│   ├── lib/              # Libraries (PostgreSQL jar)
│   └── classes/          # Compiled classes
│       ├── *Servlet.java # Servlet controllers
│       ├── *DAO.java     # Database access
│       └── *.java        # Models
├── SETUP_GUIDE.md        # Detailed setup instructions
└── setup.sh              # Quick setup script
```

## Usage

### Register New Account
1. Go to registration page
2. Enter name, email, password
3. Submit to create account

### Login
1. Enter registered email and password
2. Access dashboard after successful login

### Search Rides
1. From dashboard, click "Search Rides"
2. Enter source, destination, and number of seats
3. View available rides
4. Click "Book Now" to reserve seats

### Offer a Ride
1. From dashboard, click "Offer a Ride"
2. Enter route details and pricing
3. Submit to make ride available

## Database Schema

### Users Table
```sql
user_id (PK) | name | email | password | created_at
```

### Rides Table
```sql
ride_id (PK) | source | destination | avail_seats | price | created_at
```

### Bookings Table
```sql
booking_id (PK) | user_id (FK) | ride_id (FK) | seats_booked | total_price | booking_date
```

## API Endpoints (Servlets)

| Servlet | URL | Method | Description |
|---------|-----|--------|-------------|
| LoginServlet | /LoginServlet | POST | Authenticate user |
| RegisterServlet | /RegisterServlet | POST | Register new user |
| SearchRidesServlet | /SearchRidesServlet | POST | Search available rides |
| BookRideServlet | /BookRideServlet | POST | Book a ride |
| AddRideServlet | /AddRideServlet | POST | Add new ride |
| LogoutServlet | /LogoutServlet | GET | Logout user |

## Screenshots

### Home Page
Modern landing page with features overview

### Dashboard
Centralized hub for all user actions

### Search Results
List of available rides with booking options

### Booking Confirmation
Success page with booking details

## Development

### Adding New Features

1. **Create JSP Page:**
```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Your HTML here -->
```

2. **Create Servlet:**
```java
@WebServlet("/YourServlet")
public class YourServlet extends HttpServlet {
    // Handle request
}
```

3. **Update DAO if needed:**
```java
public class YourDAO {
    public void yourMethod() {
        // Database operations
    }
}
```

### Testing

1. **Test Database Connection:**
```bash
javac tempmain.java
java tempmain
```

2. **Check Tomcat Logs:**
```bash
tail -f $CATALINA_HOME/logs/catalina.out
```

3. **View Application in Browser:**
```
http://localhost:8080/carpooling/
```

## Troubleshooting

### Can't connect to database
- Verify PostgreSQL is running
- Check credentials in DBConnection.java
- Ensure database exists

### 404 Error
- Verify Tomcat is running
- Check application is deployed in webapps/
- Verify URL path

### Compilation errors
- Check CLASSPATH includes servlet-api.jar and postgresql.jar
- Ensure JDK version compatibility

### See SETUP_GUIDE.md for more troubleshooting tips

## Security Notes

⚠️ **This is a development version**

For production, implement:
- Password hashing (BCrypt)
- HTTPS/SSL
- Input validation
- CSRF protection
- XSS prevention
- Session security

## Future Enhancements

- [ ] View booking history
- [ ] Cancel bookings
- [ ] User ratings & reviews
- [ ] Real-time notifications
- [ ] Payment integration
- [ ] Mobile app
- [ ] Route mapping
- [ ] Chat between users

## Contributing

1. Fork the repository
2. Create feature branch
3. Commit changes
4. Push to branch
5. Create Pull Request

## License

This project is for educational purposes.

## Support

For detailed setup instructions, see `SETUP_GUIDE.md`

For issues:
1. Check Tomcat logs
2. Review database connection
3. Verify compilation

## Author

Built with ❤️ for learning Java web development

---

Happy Carpooling! 🚗💨
