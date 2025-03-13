<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - CruzeX</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        body {
            background-image: url('img/ima 1.jpg');
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .form-container {
            background-color: rgba(255, 255, 255, 0.95);
            border-radius: 15px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            padding: 40px;
        }

        .form-container h2 {
            font-weight: bold;
            color: #333;
            margin-bottom: 30px;
        }

        .btn-login {
            border-radius: 10px;
            background-color: #343a40;
            color: #ffffff;
            font-weight: bold;
        }

        .btn-login:hover {
            background-color: #495057;
        }

        #errorMessage {
            display: block;
            color: red;
            font-weight: bold;
            margin-top: 15px;
        }

        .footer {
            background-color: #343a40;
            color: white;
            text-align: center;
            padding: 8px 0;
            margin-top: auto;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
    <div class="container-fluid">
        <img class="logo" alt="Logo" src="img/x (4).webp" style="border-radius:50%; width:50px; height:50px;">
        <p class="fw-bold fs-4 mt-3 ms-2">MCC</p>
        <div class="collapse navbar-collapse justify-content-end">
            <div class="navbar-nav">
                <a class="nav-link mx-md-3" href="CruzeXHome.jsp">Home</a>
                <a class="nav-link active mx-md-3" href="Register.jsp">Register</a>
            </div>
        </div>
    </div>
</nav>

<!-- Login Form -->
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 form-container">
            <h2>Login</h2>
            <form action="CustomerLoginController" method="post" onsubmit="return validateForm()">
                <div class="form-group mt-4">
                    <label for="username">Username:</label>
                    <input type="text" id="username" class="form-control" name="username" required>
                </div>
                <div class="form-group mt-4">
                    <label for="password">Password:</label>
                    <input type="password" id="password" class="form-control" name="password" required>
                </div>

                <div class="checkbox mt-4">
                    <a href="ForgotPassword.jsp">Forgot Password?</a>
                </div>
                <div class="checkbox mt-2">
                    <a href="Register.jsp">Don't have an account? Register here</a>
                </div>

                <button type="submit" class="btn btn-login mt-4">Login</button>
            </form>

            <!-- Error Message Display -->
            <c:if test="${not empty errorMessage}">
                <div id="errorMessage">${errorMessage}</div>
            </c:if>
               
        </div>
    </div>
</div>

<!-- Footer -->
<footer class="footer">
    <div class="container">
        <p>&copy; 2025 MCC. All rights reserved.</p>
    </div>
</footer>

<script>
    function validateForm() {
        var username = document.getElementById("username").value.trim();
        var password = document.getElementById("password").value.trim();

        if (username === "" || password === "") {
            alert("Username and password are required.");
            return false;
        }
        return true;
    }
</script>

 

</body>
</html>