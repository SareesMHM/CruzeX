<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - CruzeX</title>

    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        body {
            background-image: url('img/ima (4).jpg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .container {
            background-color: rgba(255, 255, 255, 0.95);
            border-radius: 10px;
            padding: 40px;
            width: 450px;
            box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
        }

        .form-control {
            border-radius: 10px;
        }

        .btn-primary {
            border-radius: 10px;
        }

        #passwordMessage {
            font-size: 14px;
        }
    </style>

    <script>
        function validatePasswords() {
            var password = document.getElementById("customerPassword").value;
            var confirmPassword = document.getElementById("confirmPassword").value;
            var message = document.getElementById("passwordMessage");

            if (password.length < 6) {
                message.innerHTML = "Password must be at least 6 characters long!";
                message.style.color = "red";
                return false;
            }

            if (password !== confirmPassword) {
                message.innerHTML = "Passwords do not match!";
                message.style.color = "red";
                return false;
            } else {
                message.innerHTML = "Passwords match ?";
                message.style.color = "green";
                return true;
            }
        }
    </script>

</head>
<body>

<!-- Registration Form -->
<div class="container">
    <h2 class="text-center mb-4">Register</h2>

    <form method="post" action="registerCustomerController" onsubmit="return validatePasswords();">
        <div class="mb-3">
            <label for="customerFullName" class="form-label">Full Name:</label>
            <input type="text" class="form-control" id="customerFullName" name="customerFullName" required>
        </div>
        <div class="mb-3">
            <label for="customerPhoneNumber" class="form-label">Phone Number:</label>
            <input type="tel" class="form-control" id="customerPhoneNumber" name="customerPhoneNumber" required>
        </div>
        <div class="mb-3">
            <label for="dateOfBirth" class="form-label">Date of Birth:</label>
            <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" required>
        </div>
        <div class="mb-3">
            <label for="customerAddress" class="form-label">Address:</label>
            <input type="text" class="form-control" id="customerAddress" name="customerAddress" required>
        </div>
        <div class="mb-3">
            <label for="gender" class="form-label">Gender:</label>
            <select class="form-control" id="gender" name="gender" required>
                <option value="">Select Gender</option>
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="customerEmail" class="form-label">Email:</label>
            <input type="email" class="form-control" id="customerEmail" name="customerEmail" required>
        </div>
        <div class="mb-3">
            <label for="customerUsername" class="form-label">Username:</label>
            <input type="text" class="form-control" id="customerUsername" name="customerUsername" required>
        </div>
        <div class="mb-3">
            <label for="customerPassword" class="form-label">Password:</label>
            <input type="password" class="form-control" id="customerPassword" name="customerPassword" required onkeyup="validatePasswords();">
        </div>
        <div class="mb-3">
            <label for="confirmPassword" class="form-label">Confirm Password:</label>
            <input type="password" class="form-control" id="confirmPassword" required onkeyup="validatePasswords();">
            <small id="passwordMessage"></small>
        </div>

        <input type="hidden" name="type" value="add">
        <button type="submit" class="btn btn-primary w-100">Register</button>
    </form>

    <div class="text-center mt-3">
        <a href="ForgotPassword.jsp">Forgot Password?</a>
    </div>
</div>

 <script>
            var message = "<%= request.getAttribute("message") %>";
            if (message) {
                alert(message);
            }
        </script>

</body>
</html>