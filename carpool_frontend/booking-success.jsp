<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    User user = (User) session.getAttribute("user");
    Booking booking = (Booking) session.getAttribute("lastBooking");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Booking Successful - CarPooling</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav class="navbar">
        <div class="nav-brand">
            <h3>🚗 CarPooling</h3>
        </div>
        <div class="nav-links">
            <a href="dashboard.jsp" class="btn btn-sm">Dashboard</a>
            <a href="LogoutServlet" class="btn btn-sm">Logout</a>
        </div>
    </nav>
    
    <div class="container">
        <div class="success-container">
            <div class="success-icon">✅</div>
            <h2>Booking Successful!</h2>
            <p class="success-message">Your ride has been booked successfully.</p>
            
            <% if(booking != null) { %>
            <div class="booking-details">
                <h3>Booking Details</h3>
                <div class="detail-row">
                    <span class="label">Passenger:</span>
                    <span class="value"><%= booking.user.getName() %></span>
                </div>
                <div class="detail-row">
                    <span class="label">Route:</span>
                    <span class="value"><%= booking.ride.source %> → <%= booking.ride.destination %></span>
                </div>
                <div class="detail-row">
                    <span class="label">Seats Booked:</span>
                    <span class="value"><%= booking.seatsbooked %></span>
                </div>
                <div class="detail-row">
                    <span class="label">Total Price:</span>
                    <span class="value">₹<%= String.format("%.2f", booking.totprice) %></span>
                </div>
            </div>
            <% } %>
            
            <div class="action-buttons">
                <a href="search-rides.jsp" class="btn btn-primary">Book Another Ride</a>
                <a href="dashboard.jsp" class="btn">Go to Dashboard</a>
            </div>
        </div>
    </div>
</body>
</html>
