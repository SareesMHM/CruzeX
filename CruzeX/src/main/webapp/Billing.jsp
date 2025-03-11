<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Billing Invoice</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script>
        window.onload = function () {
            window.print();
        };
    </script>
</head>
<body>
<div class="container">
    <h2 class="mt-4 text-center">Billing Invoice</h2>
    <div class="card p-4">
        <p><strong>Booking ID:</strong> <%= request.getParameter("bookingID") %></p>
        <p><strong>Customer ID:</strong> <%= request.getParameter("customerID") != null ? request.getParameter("customerID") : "N/A" %></p>
        <p><strong>Discount:</strong> <%= request.getParameter("discount") %></p>
        <p><strong>Tax:</strong> <%= request.getParameter("tax") %></p>
        <p><strong>Amount Paid (LKR):</strong> <%= request.getParameter("amount") %></p>
        
        <p><strong>Payment Status:</strong> <%= request.getParameter("paymentStatus") %></p>
        <button onclick="window.print()" class="btn btn-secondary">Print Invoice</button>
        <a href="CustomerHomePage.jsp" class="btn btn-primary">Return to Home</a>
    </div>
</div>
</body>
</html>