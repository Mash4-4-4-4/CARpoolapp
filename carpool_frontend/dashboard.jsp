<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Check if user is logged in
    if(session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - CarPooling</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav class="navbar">
        <div class="nav-brand">
            <h3>🚗 CarPooling</h3>
        </div>
        <div class="nav-links">
            <span class="user-welcome">Welcome, <%= user.getName() %>!</span>
            <a href="LogoutServlet" class="btn btn-sm">Logout</a>
        </div>
    </nav>
    
    <div class="container">
        <h2>Dashboard</h2>
        
        <% if(request.getAttribute("success") != null) { %>
            <div class="alert alert-success">
                <%= request.getAttribute("success") %>
            </div>
        <% } %>
        
        <div class="dashboard-cards">
            <div class="card">
                <div class="card-icon">🔍</div>
                <h3>Search Rides</h3>
                <p>Find available rides for your journey</p>
                <a href="search-rides.jsp" class="btn btn-primary">Search Now</a>
            </div>
            
            <div class="card">
                <div class="card-icon">🚘</div>
                <h3>Offer a Ride</h3>
                <p>Share your ride with others</p>
                <a href="add-ride.jsp" class="btn btn-primary">Add Ride</a>
            </div>
            
            <div class="card">
                <div class="card-icon">📋</div>
                <h3>My Bookings</h3>
                <p>View your booked rides</p>
                <a href="MyBookingsServlet" class="btn btn-primary">View Bookings</a>
            </div>
        </div>
    </div>
</body>
</html>
