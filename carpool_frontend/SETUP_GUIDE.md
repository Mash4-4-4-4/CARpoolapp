# CarPooling Web Application - Setup Guide

## Project Structure
```
carpooling-frontend/
├── index.jsp                    # Home page
├── login.jsp                    # Login page
├── register.jsp                 # Registration page
├── dashboard.jsp                # User dashboard
├── search-rides.jsp             # Search and view rides
├── add-ride.jsp                 # Add new ride
├── booking-success.jsp          # Booking confirmation
├── css/
│   └── style.css               # All styles
└── WEB-INF/
    ├── web.xml                 # Deployment descriptor
    └── classes/
        ├── LoginServlet.java
        ├── RegisterServlet.java
        ├── SearchRidesServlet.java
        ├── BookRideServlet.java
        ├── AddRideServlet.java
        ├── LogoutServlet.java
        ├── Booking.java
        ├── BookingDAO.java
        ├── DBConnection.java
        ├── Main.java
        ├── Ride.java
        ├── RideBooking.java
        ├── RideDAO.java
        ├── User.java
        └── UserDAO.java
```

## Prerequisites
1. Java JDK 8 or higher
2. Apache Tomcat 9 or higher
3. PostgreSQL database
4. PostgreSQL JDBC Driver (postgresql-42.x.x.jar)
5. Servlet API (provided by Tomcat)

## Database Setup

### 1. Create Database
```sql
CREATE DATABASE ridesys;
```

### 2. Create Tables
```sql
-- Users table
CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Rides table
CREATE TABLE rides (
    ride_id SERIAL PRIMARY KEY,
    source VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    avail_seats INTEGER NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Bookings table
CREATE TABLE bookings (
    booking_id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(user_id),
    ride_id INTEGER REFERENCES rides(ride_id),
    seats_booked INTEGER NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 3. Insert Sample Rides (Optional)
```sql
INSERT INTO rides(source, destination, avail_seats, price) VALUES
    ('Noida', 'Delhi', 4, 250.00),
    ('Delhi', 'Gurgaon', 3, 300.00),
    ('Noida', 'Ghaziabad', 5, 150.00),
    ('Delhi', 'Faridabad', 4, 200.00),
    ('Gurgaon', 'Noida', 4, 280.00);
```

### 4. Update DBConnection.java
Update the database credentials in `WEB-INF/classes/DBConnection.java`:
```java
private static final String URL = "jdbc:postgresql://localhost:5432/ridesys";
private static final String USER = "postgres";
private static final String PASSWORD = "your_password_here";
```

## Compilation Steps

### 1. Navigate to the classes directory
```bash
cd carpooling-frontend/WEB-INF/classes
```

### 2. Compile all Java files
```bash
javac -cp ".:/path/to/tomcat/lib/servlet-api.jar:/path/to/postgresql-42.x.x.jar" *.java
```

**Example for Linux/Mac:**
```bash
javac -cp ".:$CATALINA_HOME/lib/servlet-api.jar:/usr/share/java/postgresql.jar" *.java
```

**Example for Windows:**
```bash
javac -cp ".;C:\tomcat\lib\servlet-api.jar;C:\path\to\postgresql-42.x.x.jar" *.java
```

### 3. Verify compilation
Check that `.class` files are generated:
```bash
ls *.class
```

## Deployment Steps

### Method 1: Deploy to Tomcat webapps

1. **Create WAR structure:**
```bash
cd carpooling-frontend
jar -cvf carpooling.war *
```

2. **Deploy WAR:**
- Copy `carpooling.war` to `$CATALINA_HOME/webapps/`
- Tomcat will automatically extract it

### Method 2: Direct deployment

1. **Copy entire folder:**
```bash
cp -r carpooling-frontend $CATALINA_HOME/webapps/carpooling
```

2. **Add PostgreSQL JDBC driver:**
```bash
cp postgresql-42.x.x.jar $CATALINA_HOME/webapps/carpooling/WEB-INF/lib/
# OR copy to Tomcat's lib folder
cp postgresql-42.x.x.jar $CATALINA_HOME/lib/
```

### 3. Start Tomcat
```bash
$CATALINA_HOME/bin/startup.sh    # Linux/Mac
$CATALINA_HOME\bin\startup.bat   # Windows
```

### 4. Access the application
Open browser and navigate to:
```
http://localhost:8080/carpooling/
```

## Application Features

### 1. User Registration
- New users can register with name, email, and password
- Email must be unique
- Automatically creates user account in database

### 2. User Login
- Login with email and password
- Session management for authenticated users
- Automatic redirect to dashboard after login

### 3. Dashboard
- View three main options:
  - Search Rides
  - Offer a Ride
  - My Bookings (future feature)

### 4. Search Rides
- Search by source, destination, and number of seats
- View all available rides matching criteria
- See ride details: price, available seats, route
- Book rides directly from search results

### 5. Book Ride
- Select number of seats to book
- Automatic seat availability check
- Database updates for seat reduction
- Booking confirmation with details

### 6. Add Ride
- Drivers can add new rides
- Specify source, destination, seats, and price
- Rides immediately available for booking

### 7. Logout
- Secure session invalidation
- Redirect to login page

## Troubleshooting

### Issue: ClassNotFoundException for PostgreSQL driver
**Solution:** Ensure postgresql jar is in WEB-INF/lib/ or Tomcat's lib/

### Issue: Cannot connect to database
**Solution:** 
- Check PostgreSQL is running: `sudo systemctl status postgresql`
- Verify database name, username, password in DBConnection.java
- Check PostgreSQL allows connections: edit `pg_hba.conf`

### Issue: Servlets not found (404 error)
**Solution:**
- Verify all .java files are compiled to .class
- Check servlet annotations are correct
- Restart Tomcat after changes

### Issue: JSP compilation errors
**Solution:**
- Check import statements at top of JSP files
- Ensure User, Ride, Booking classes are compiled
- Clear Tomcat work directory: `$CATALINA_HOME/work/Catalina/localhost/carpooling/`

### Issue: Session not working
**Solution:**
- Check cookies are enabled in browser
- Verify session timeout in web.xml
- Ensure session attributes are set correctly

## Testing the Application

### 1. Test Registration
- Go to http://localhost:8080/carpooling/register.jsp
- Fill in details and register
- Should redirect to login page

### 2. Test Login
- Use registered email and password
- Should redirect to dashboard

### 3. Test Add Ride
- From dashboard, click "Add Ride"
- Fill in ride details
- Verify ride added to database:
  ```sql
  SELECT * FROM rides;
  ```

### 4. Test Search and Book
- From dashboard, click "Search Rides"
- Enter source and destination
- Click "Book Now" on a ride
- Verify booking in database:
  ```sql
  SELECT * FROM bookings;
  ```

## Database Queries for Monitoring

### View all users
```sql
SELECT * FROM users;
```

### View all rides
```sql
SELECT * FROM rides ORDER BY created_at DESC;
```

### View all bookings
```sql
SELECT b.booking_id, u.name, r.source, r.destination, 
       b.seats_booked, b.total_price 
FROM bookings b
JOIN users u ON b.user_id = u.user_id
JOIN rides r ON b.ride_id = r.ride_id
ORDER BY b.booking_date DESC;
```

### Check available seats for a ride
```sql
SELECT ride_id, source, destination, avail_seats 
FROM rides 
WHERE ride_id = 1;
```

## Security Considerations (For Production)

⚠️ **Important:** This is a development version. For production:

1. **Hash passwords:** Use BCrypt or similar
   ```java
   // Don't store plain text passwords!
   String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
   ```

2. **SQL Injection protection:** Already using PreparedStatements ✓

3. **Session security:** Add to web.xml:
   ```xml
   <session-config>
       <cookie-config>
           <http-only>true</http-only>
           <secure>true</secure>
       </cookie-config>
   </session-config>
   ```

4. **Input validation:** Validate all user inputs

5. **HTTPS:** Use SSL/TLS in production

## Future Enhancements

- View booking history
- Cancel bookings
- Rating system for drivers
- Real-time seat availability
- Email notifications
- Payment integration
- Map integration for routes
- User profiles with photos

## Support

For issues or questions:
1. Check Tomcat logs: `$CATALINA_HOME/logs/catalina.out`
2. Check application logs in console
3. Verify database connections
4. Review this setup guide

## File Checklist

Before deployment, ensure you have:
- [ ] All JSP files in root directory
- [ ] style.css in css/ folder
- [ ] web.xml in WEB-INF/
- [ ] All .java files compiled to .class in WEB-INF/classes/
- [ ] PostgreSQL JDBC driver in WEB-INF/lib/ or Tomcat lib/
- [ ] Database tables created
- [ ] DBConnection.java updated with correct credentials
- [ ] Tomcat running

Good luck with your CarPooling application! 🚗
