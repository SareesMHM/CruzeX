<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Billing Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2 class="text-center mt-4">Billing Details</h2>
    <div class="card p-4">
        <p><strong>Billing ID:</strong> ${billing.billingID}</p>
        <p><strong>Booking ID:</strong> ${billing.bookingID}</p>
        <p><strong>Vehicle ID:</strong> ${billing.vehicleID}</p>
        <p><strong>Total Amount:</strong> LKR ${billing.totalAmount}</p>
        <p><strong>Billing Date:</strong> ${billing.billingDate}</p>
    </div>
    
    <!-- Proceed to Payment -->
    <form method="post" action="PaymentController">
        <input type="hidden" name="bookingID" value="${billing.bookingID}" />
        <input type="hidden" name="amount" value="${billing.totalAmount}" />
        <button type="submit" class="btn btn-success mt-3">Proceed to Payment</button>
    </form>
</div>
</body>
</html>