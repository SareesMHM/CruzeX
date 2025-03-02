<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <meta charset="UTF-8">
    <title>Search and Update Payment</title>
    <style>
        /* Custom Styles */
        .form-container {
            background-color: #ffffff;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <!-- Navigation Bar -->
    <ul class="nav justify-content-center bg-dark py-2">
        <li class="nav-item">
            <a class="link-warning nav-link px-5 mx-5" href="CustomerHomePage.jsp"> << Go to Home</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="PaymentDashboard.jsp">Store</a>
        </li>
        <li class="nav-item">
            <a class="text-white nav-link" href="Search-Payment.jsp">Search & Update</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Add-Payment.jsp">Add</a>
        </li>
    </ul>
    <br/>

    <div class="container">
        <div class="row">
            <!-- Search Payment -->
            <div class="col-md-6">
                <div class="form-container">
                    <h3>Search Payment</h3>
                    <form method="get" action="PaymentController">
                        <label for="paymentId">Enter Payment ID:</label>
                        <input type="text" class="form-control" name="paymentId" required>
                        <input type="hidden" name="type" value="specific">
                        <button type="submit" class="btn btn-info mt-3">Search</button>
                    </form>
                    <br/>
                    <p>${searchResult}</p>
                </div>
            </div>

            <!-- Edit Payment -->
            <div class="col-md-6">
                <div class="form-container">
                    <h3>Edit Payment</h3>
                    <form method="post" action="PaymentController">
                        <label for="paymentId">Payment ID:</label>
                        <input type="text" readonly class="form-control" id="paymentId" name="paymentId" value="${not empty payment ? payment.paymentId : ''}"/>
                        <br/>

                        <label for="price">Amount [LKR]:</label>
                        <input type="number" class="form-control" id="price" name="price" value="${not empty payment ? payment.price : ''}"/>
                        <br/>

                        <label for="paymentDate">Payment Date:</label>
                        <input type="date" class="form-control" id="paymentDate" name="paymentDate" value="${not empty payment ? payment.paymentDate : ''}"/>
                        <br/>

                        <label for="cardNumber">Card Number:</label>
                        <input type="text" class="form-control" id="cardNumber" name="cardNumber" value="${not empty payment ? payment.cardNumber : ''}"/>
                        <br/>

                        <label for="expiryDate">Expiry Date:</label>
                        <input type="text" class="form-control" id="expiryDate" name="expiryDate" value="${not empty payment ? payment.expiryDate : ''}"/>
                        <br/>

                        <label for="cvcNumber">CVC Number:</label>
                        <input type="text" class="form-control" id="cvcNumber" name="cvcNumber" value="${not empty payment ? payment.cvcNumber : ''}"/>
                        <br/>

                        <label for="cardholderName">Cardholder Name:</label>
                        <input type="text" class="form-control" id="cardholderName" name="cardholderName" value="${not empty payment ? payment.cardholderName : ''}"/>
                        <br/>

                        <label for="customerId">Customer ID:</label> <!-- ✅ Fixed from "patientId" -->
                        <input type="number" class="form-control" id="customerId" name="customerId" value="${not empty payment ? payment.customerId : ''}"/>
                        <input type="hidden" name="type" value="update"/>
                        <br/>

                        <button type="submit" class="btn btn-warning">Update</button>            
                    </form> 
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>