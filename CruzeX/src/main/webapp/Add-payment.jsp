<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Secure Payment</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://js.stripe.com/v3/"></script>
</head>
<body>
<div class="container">
    <h2 class="mt-4 mb-4 text-center">Complete Your Payment</h2>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card p-4">
                <form id="payment-form">
                    <div class="mb-3">
                        <label for="bookingID" class="form-label">Booking ID:</label>
                        <input type="text" class="form-control" id="bookingID" name="bookingID" value="<%= request.getParameter("bookingID") %>" readonly>
                    </div>
                    <div class="mb-3">
                        <label for="customerID" class="form-label">Customer ID:</label>
                        <input type="text" class="form-control" id="customerID" name="customerID" value="<%= request.getParameter("customerID") %>" readonly>
                    </div>
                      <label>Estimated Fare (LKR):</label>
                <input type="text" class="form-control" id="fare" name="fare" readonly/>
                     <div class="mb-3">
                        <label for="amount" class="form-label">Tax (7%)::</label>
                        <input type="text" class="form-control" id="tex" name="tex" value="<%= request.getParameter("amount") %>" readonly>
                    </div>
                     <div class="mb-3">
                        <label for="amount" class="form-label">Discount (7%):</label>
                        <input type="text" class="form-control" id="discount" name="discount" value="<%= request.getParameter("amount") %>" readonly>
                    </div>
                   <label>Total Fare (LKR):</label>
                <input type="text" class="form-control" id="totalFare" name="totalFare" readonly />

                    <!-- Stripe Card Element -->
                    <div class="mb-3">
                        <label for="card-element" class="form-label">Credit or Debit Card:</label>
                        <div id="card-element" class="form-control"></div>
                        <div id="card-errors" class="text-danger mt-2" role="alert"></div>
                    </div>

                    <button type="submit" class="btn btn-primary w-100" id="submit-button">Pay Now</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    var stripe = Stripe("pk_test_51R03Ce07fEoJmqEFY8VGA8XnvPyz08F2mzG0KoiDo2MaQjmbK5lbCzwdFXoEwfQxdWdJtuCFq9jFxVwlc6M5LKrT00J3v74C5c"); // Replace with your Stripe public key
    var elements = stripe.elements();
    var card = elements.create("card");
    card.mount("#card-element");

    document.getElementById("payment-form").addEventListener("submit", function(event) {
        event.preventDefault();
        stripe.createToken(card).then(function(result) {
            if (result.error) {
                document.getElementById("card-errors").textContent = result.error.message;
            } else {
                processPayment(result.token);
            }
        });
    });

    function processPayment(token) {
        var bookingID = document.getElementById("bookingID").value;
        var customerID = document.getElementById("customerID").value;
        var tex = document.getElementById("tex").value;
        var discount = document.getElementById("discount").value;
        var fare = document.getElementById("fare").value;
        var totalFare = document.getElementById("totalFare").value;

        fetch("StripePaymentServlet", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: new URLSearchParams({
                stripeToken: token.id,
                bookingID: bookingID,
                customerID: customerID,
                amount: fare,
                tax: tax,
                discount: discount,
                totalFare: totalFare
            }).toString()
        })
        .then(response => response.text())  // First get raw text response
        .then(text => {
            console.log("Raw Response:", text);  // Debugging
            try {
                let data = JSON.parse(text);  // Try parsing JSON
                if (data.success) {
                    window.location.href = "Billing.jsp?customerID=" + data.customerID + "&bookingID=" + data.bookingID + "&amount=" + totalFare + "&paymentStatus=Successful";
                } else {
                    alert("Payment failed: " + data.message);
                }
            } catch (error) {
                console.error("JSON Parse Error:", error);
                alert("Unexpected server response. Check console.");
            }
        })
        .catch(error => alert("Error processing payment: " + error));
    }
</script>
</body>
</html>
