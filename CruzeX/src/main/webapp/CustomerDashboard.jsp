<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .nav-link {
            color: #ffffff;
        }
        .table th, .table td {
            vertical-align: middle;
            text-align: center;
        }
        .btn-action {
            padding: 0.375rem 0.75rem;
        }
        .container {
            margin-top: 30px;
        }
        .table-responsive {
            overflow-x: auto;
        }
    </style>
</head>
<body>
    <!-- Navigation Bar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="AdminHomePage.jsp">CruzeX</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="CustomerDashboard.jsp">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="SearchCustomer.jsp">Search & Update</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Register.jsp">Add Customer</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-warning" href="AdminHomePage.jsp"><< Go Back</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Message Alert -->
    <div class="container">
        <c:if test="${not empty message}">
            <div class="alert alert-info text-center">${message}</div>
        </c:if>

        <!-- Customer List -->
        <h3 class="text-center mt-4">Customer Management</h3>

        <div class="table-responsive mt-3">
            <table class="table table-striped table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>Customer ID</th>
                        <th>Full Name</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>Username</th>
                        <th>Address</th>
                        <th>Gender</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="customer" items="${customerList}">
                        <tr>
                            <td>${customer.customerID}</td>
                            <td>${customer.customerFullName}</td>
                            <td>${customer.customerPhoneNumber}</td>
                            <td>${customer.customerEmail}</td>
                            <td>${customer.customerUsername}</td>
                            <td>${customer.customerAddress}</td>
                            <td>${customer.gender}</td>
                            <td>
                                <!-- Edit Button -->
                                <a href="SearchCustomer.jsp?customerID=${customer.customerID}" class="btn btn-warning btn-sm">Edit</a>

                                <!-- Delete Button -->
                                <form action="CustomerController" method="post" style="display:inline;">
                                    <input type="hidden" name="customerID" value="${customer.customerID}">
                                    <input type="hidden" name="type" value="delete">
                                    <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <c:if test="${empty customerList}">
                <p class="text-center text-muted">No customers found.</p>
            </c:if>
        </div>
    </div>
</body>
</html>