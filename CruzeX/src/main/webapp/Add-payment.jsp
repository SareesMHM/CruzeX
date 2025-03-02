<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Payment</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2 class="mt-4 mb-4 text-center">Complete Your Payment</h2>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card p-4">
                <form method="post" action="PaymentController">
                    <div class="mb-3">
                        <label for="bookingID" class="form-label">Booking ID:</label>
                        <input type="text" class="form-control" id="bookingID" name="bookingID" value="<%= request.getParameter("bookingID") %>" readonly>
                    </div>
                    <div class="mb-3">
                        <label for="customerID" class="form-label">Customer ID:</label>
                        <input type="text" class="form-control" id="customerID" name="customerID" value="<%= request.getParameter("customerID") %>" readonly>
                    </div>
                    <div class="mb-3">
                        <label for="amount" class="form-label">Amount (LKR):</label>
                        <input type="text" class="form-control" id="amount" name="amount" value="<%= request.getParameter("amount") %>" readonly>
                    </div>
                    <div class="mb-3">
                        <label for="cardNumber" class="form-label">Card Number:</label>
                        <input type="text" class="form-control" id="cardNumber" name="cardNumber" required>
                    </div>
                    <div class="mb-3">
                        <label for="expiryDate" class="form-label">Expiry Date:</label>
                        <input type="date" class="form-control" id="expiryDate" name="expiryDate" required>
                    </div>
                    <div class="mb-3">
                        <label for="cvcNumber" class="form-label">CVC:</label>
                        <input type="number" class="form-control" id="cvcNumber" name="cvcNumber" required>
                    </div>
                    <input type="hidden" name="type" value="add">
                    <button type="submit" class="btn btn-primary w-100">Pay Now</button>
                </form>
            </div>
        </div>
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