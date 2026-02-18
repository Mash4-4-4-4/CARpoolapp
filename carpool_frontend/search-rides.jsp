<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, Ride" %>
<%
    if(session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Search Rides - CarPooling</title>
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
        <h2>Search Available Rides</h2>
        
        <div class="search-form">
            <form action="SearchRidesServlet" method="post">
                <div class="form-row">
                    <div class="form-group">
                        <label>From (Source)</label>
                        <input type="text" name="source" placeholder="e.g., Noida" required>
                    </div>
                    
                    <div class="form-group">
                        <label>To (Destination)</label>
                        <input type="text" name="destination" placeholder="e.g., Delhi" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Seats Required</label>
                        <input type="number" name="seats" min="1" max="8" value="1" required>
                    </div>
                    
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Search</button>
                    </div>
                </div>
            </form>
        </div>
        
        <%
        List<Ride> rides = (List<Ride>) request.getAttribute("rides");
        if(rides != null && !rides.isEmpty()) {
        %>
            <h3 class="results-title">Available Rides (<%= rides.size() %>)</h3>
            <div class="rides-list">
                <% for(Ride ride : rides) { %>
                    <div class="ride-card">
                        <div class="ride-header">
                            <h4><%= ride.source %> → <%= ride.destination %></h4>
                            <span class="ride-price">₹<%= String.format("%.2f", ride.getPrice()) %>/seat</span>
                        </div>
                        <div class="ride-details">
                            <div class="detail-item">
                                <span class="label">Available Seats:</span>
                                <span class="value"><%= ride.getAvailSeats() %></span>
                            </div>
                            <div class="detail-item">
                                <span class="label">Ride ID:</span>
                                <span class="value">#<%= ride.getrideid() %></span>
                            </div>
                        </div>
                        <form action="BookRideServlet" method="post" class="book-form">
                            <input type="hidden" name="rideId" value="<%= ride.getrideid() %>">
                            <div class="book-controls">
                                <label>Seats to book:</label>
                                <input type="number" name="seats" min="1" max="<%= ride.getAvailSeats() %>" value="1" required>
                                <button type="submit" class="btn btn-success">Book Now</button>
                            </div>
                        </form>
                    </div>
                <% } %>
            </div>
        <% } else if(request.getAttribute("searched") != null) { %>
            <div class="no-results">
                <h3>No rides available</h3>
                <p>Try different locations or check back later.</p>
            </div>
        <% } %>
    </div>
</body>
</html>
