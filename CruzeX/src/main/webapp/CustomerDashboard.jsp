<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            padding-top: 20px;
        }
        .nav-link {
            color: #ffffff;
        }
        .table th, .table td {
            vertical-align: middle;
            text-align: center;
        }
        .btn-remove {
            padding: 0.375rem 0.75rem;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <ul class="nav justify-content-center bg-dark py-2">
        <li class="nav-item">
            <a class="link-warning nav-link px-5 mx-5" href="AdminHomePage.jsp"> << Go to Home</a>
        </li>
        <li class="nav-item">
            <a class="text-white nav-link" href="CustomerController">Home</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Search-Payment.jsp">Search & Update</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Add-payment.jsp">Add Payment</a>
        </li>
    </ul>

    <br/>

    <div class="container">
        <h3 class="text-center">Payment Transactions</h3>
        <table class="table table-striped">
            <thead>
                <tr class="table-dark">
                    <th>Payment ID</th>
                    <th>Cardholder Name</th>
                    <th>Card Number</th>
                    <th>Expiry Date</th>
                    <th>CVC</th>
                    <th>Payment Date</th>
                    <th>Customer ID</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="payment" items="${paymentList}">
                    <tr>
                        <td>${payment.paymentId}</td>
                        <td>${payment.cardholderName}</td>
                        <td>**** **** **** ${payment.cardNumber.substring(payment.cardNumber.length() - 4)}</td>
                        <td>${payment.expiryDate}</td>
                        <td>***</td>
                        <td>${payment.paymentDate}</td>
                        <td>${payment.customerId}</td>
                        <td>
                            <form method="post" action="payment">
                                <input type="hidden" name="paymentId" value="${payment.paymentId}"/>
                                <input type="hidden" name="type" value="delete"/>
                                <button type="submit" class="btn btn-danger btn-sm">Remove</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <c:if test="${empty paymentList}">
            <p class="text-center text-muted">No payments found.</p>
        </c:if>
    </div>
</div>
<br/>
</body>
</html>
