<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script>
    function displayErrorMessage(message) {
        var errorMessageDiv = document.getElementById("errorMessage");
        errorMessageDiv.innerText = message;
        errorMessageDiv.style.display = "block";
    }
</script>
<style>
    body {
    background-image: url('img/ima 1.jpg'); 
      background-size: cover;
      background-position: center; 
      background-repeat: no-repeat; 
      height: 100vh; 
      
        background-color: #f8f9fa;
        min-height: 100vh;
        display: flex;
        flex-direction: column;
    }
    .logo {
        border-radius: 50%;
        width: 50px;
        height: 50px;
    }
    .navbar {
        background-color: rgba(255, 255, 255, 0.9);
        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
    }
    .form-container {
        background-color: rgba(255, 255, 255, 0.9);
        border-radius: 15px;
        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        padding: 40px;
    }
    .form-container h2 {
        font-weight: bold;
        color: #333333;
        margin-bottom: 30px;
    }
    .form-group label {
        font-weight: bold;
        color: #333333;
    }
    .form-control {
        border-radius: 10px;
    }
    .btn-login {
        border-radius: 10px;
        background-color: #ff6b6b;
        color: #ffffff;
        font-weight: bold;
        transition: background-color 0.3s ease;
    }
    .btn-login:hover {
        background-color: #ff4d4d;
    }
    .checkbox a {
        color: #333333;
    }
    #errorMessage {
        display: none;
        color: #ff0000;
        margin-top: 10px;
    }
    .footer {
        background-color: #343a40;
        color: rgba(255, 255, 255, 0.9);
        text-align: center;
        padding: 5px 0;
        margin-top: auto;
    }
</style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light" >
  <div class="container-fluid" >
      <img class="logo" alt="Logo" src="img/ima (1).webp">
    <p class="fw-bold fs-4 mt-3"> CruzeX</p>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
      <div class="navbar-nav">
        <a class="nav-link mx-0 mx-md-3  fs-6" href="Dashboard.jsp">Home</a>
        <a class="nav-link active mx-0 mx-md-3 fs-6" aria-current="page" href="Register.jsp">Customer</a>
        <a class="nav-link mx-0 mx-md-3 fs-6" href="AvailableTest.jsp">Available Car</a>
      </div>
    </div>
  </div>
</nav>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 form-container">
            <h2>Login</h2>
            <form action="CustomerLoginController" method="post">
                <div class="form-group mt-4">
                    <label for="username">Username:</label>
                    <input type="text" id="username" class="form-control" name="username" required>
                </div>
                <div class="form-group mt-4">
                    <label for="password">Password:</label>
                    <input type="password" id="password" class="form-control" name="password" required>
                </div>
                <div class="checkbox mt-4">
                    <a class="nav-link active  fs-6" aria-current="page" href="Register.jsp">Register</a>
                </div>
                <button type="submit" class="btn btn-secondary mb-5 mt-4" onclick="return validateForm()">Login</button>
            </form>
            <div id="errorMessage"></div>
        </div>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <p>Copyright &copy; 2025</p>
    </div>
</footer>

<script>
    function validateForm() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        
        if (username.trim() === "" || password.trim() === "") {
            displayErrorMessage("Username and password are required.");
            return false;
        }
        return true;
    }
</script>

</body>
</html>
