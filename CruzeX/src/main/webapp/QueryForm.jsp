<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Submit Query - CruzeX</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .query-form {
            max-width: 600px;
            margin: 50px auto;
            padding: 30px;
            background-color: #f8f9fa;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>

<!-- Navbar (optional) -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold" href="CustomerHomePage.jsp">CruzeX</a>
        <div class="collapse navbar-collapse">
            <div class="navbar-nav ms-auto">
                <a class="nav-link" href="CustomerHomePage.jsp">Home</a>
                <a class="nav-link" href="BookingController?type=viewCustomerBookings">My Bookings</a>
                <a class="nav-link active" href="QueryForm.jsp">Support</a>
                <a class="nav-link" href="LogoutController">Logout</a>
            </div>
        </div>
    </div>
</nav>

<!-- Query Form -->
<div class="query-form">
    <h3 class="text-center mb-4">Submit Your Query</h3>
    <form action="QueryController" method="post">
        <div class="mb-3">
            <label for="userName" class="form-label">Full Name</label>
            <input type="text" class="form-control" id="userName" name="userName" required>
        </div>
        <div class="mb-3">
            <label for="userEmail" class="form-label">Email Address</label>
            <input type="email" class="form-control" id="userEmail" name="userEmail" required>
        </div>
        <div class="mb-3">
            <label for="userMessage" class="form-label">Your Message</label>
            <textarea class="form-control" id="userMessage" name="userMessage" rows="5" required></textarea>
        </div>
        <input type="hidden" name="type" value="add"/>
        <div class="d-grid">
            <button type="submit" class="btn btn-primary">Submit Query</button>
        </div>
    </form>
</div>

<script>
            var message = "<%= request.getAttribute("message") %>";
            if (message) {
                alert(message);
            }
        </script>

</body>
</html>