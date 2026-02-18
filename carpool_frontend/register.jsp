<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register - CarPooling</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <div class="form-container">
            <h2>Create New Account</h2>
            
            <% if(request.getAttribute("error") != null) { %>
                <div class="alert alert-error">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>
            
            <form action="RegisterServlet" method="post">
                <div class="form-group">
                    <label>Full Name</label>
                    <input type="text" name="name" placeholder="Enter your name" required>
                </div>
                
                <div class="form-group">
                    <label>Email Address</label>
                    <input type="email" name="email" placeholder="Enter your email" required>
                </div>
                
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" name="password" placeholder="Create a password" required>
                </div>
                
                <button type="submit" class="btn btn-primary btn-block">Register</button>
            </form>
            
            <div class="form-footer">
                <p>Already have an account? <a href="login.jsp">Login here</a></p>
                <p><a href="index.jsp">Back to Home</a></p>
            </div>
        </div>
    </div>
</body>
</html>
