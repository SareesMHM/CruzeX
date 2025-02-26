<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<style>
    body {
        background-image: url('img/ima (4).jpg');
        background-size: cover;
        background-position: center;
        background-attachment: fixed;
        min-height: 100vh;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    
    .container {
        background-color: rgba(255, 255, 255, 0.9);
        border-radius: 10px;
        padding: 40px;
        box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
    }
    
    .navbar {
        background-color: rgba(255, 255, 255, 0.9);
        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
    }
    
    .navbar-brand {
        font-weight: bold;
        color: #333;
        margin-right: 20px;
    }
    
    .navbar-toggler-icon {
        border: none;
    }
    
    .form-control {
        border-radius: 10px;
    }
    
    .btn-primary {
        border-radius: 10px;
    }
</style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="img/ima (1).webp" alt="Logo" width="50" height="50" class="d-inline-block align-text-top rounded-circle">
            CruzeX
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link" href="Dashboard.jsp">Home</a>
                <a class="nav-link active" href="CustomerLogReg.jsp">Customer</a>
                <a class="nav-link" href="AvailableCars.jsp">Available Cars</a>
            </div>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="row justify-content-center">
        <script>
            var message = "<%= request.getAttribute("message") %>";
            if (message) {
                alert(message);
            }
        </script>

        <div class="col-md-6">
            <h2 class="text-center mb-4">Register</h2>
            <form method="post" action="registerCustomerController">
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
                    <input type="password" class="form-control" id="customerPassword" name="customerPassword" required>
                </div>
                <input type="hidden" name="type" value="add">
                <button type="submit" class="btn btn-primary w-100">Register</button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
