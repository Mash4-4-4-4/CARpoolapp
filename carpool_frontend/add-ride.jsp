<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Add Ride - CarPooling</title>
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
        <div class="form-container">
            <h2>Offer a Ride</h2>
            <p class="subtitle">Share your journey and help others while earning!</p>
            
            <% if(request.getAttribute("success") != null) { %>
                <div class="alert alert-success">
                    <%= request.getAttribute("success") %>
                </div>
            <% } %>
            
            <% if(request.getAttribute("error") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>
            
            <form action="AddRideServlet" method="post">
                <div class="form-group">
                    <label>From (Source)</label>
                    <input type="text" name="source" placeholder="e.g., Noida" required>
                </div>
                
                <div class="form-group">
                    <label>To (Destination)</label>
                    <input type="text" name="destination" placeholder="e.g., Delhi" required>
                </div>
                
                <div class="form-group">
                    <label>Available Seats</label>
                    <input type="number" name="seats" min="1" max="8" placeholder="How many seats available?" required>
                    <small>Number of passengers you can accommodate</small>
                </div>
                
                <div class="form-group">
                    <label>Price per Seat (₹)</label>
                    <input type="number" step="0.01" name="price" placeholder="e.g., 250.00" required>
                    <small>Amount per passenger</small>
                </div>
                
                <button type="submit" class="btn btn-primary btn-block">Add Ride</button>
            </form>
        </div>
    </div>
</body>
</html>
