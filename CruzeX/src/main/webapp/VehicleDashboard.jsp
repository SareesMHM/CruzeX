<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Vehicle List</title>
    
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
            <a class="link-warning nav-link px-5 mx-5" aria-current="page" href="AdminHomePage.jsp"> << Go to Home</a>
        </li>
        <li class="nav-item">
            <a class="text-white nav-link" aria-current="page" href="VehicleDashboard.jsp">Store</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Search-Vehicle-Details.jsp">Search Vehicle & Update</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Add-Vehicle-Details.jsp">Add</a>
        </li>
    </ul>

    <br/>
    <p>${message}</p>
    <br/>

    <div class="container">
        <table class="table table-striped">
            <thead>
                <tr class="table-dark">
                    <th>Vehicle ID</th>
                    <th>Vehicle Name</th>
                    <th>Image</th>
                    <th>Category</th>
                    <td>Month Fee</td>
                    <th>Remove from List</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="vehicle" items="${vehicleList}">
                    <tr>
                        <td>${vehicle.vehicleId}</td>
                        <td>${vehicle.vehicleName}</td>
                        <td><img src="${vehicle.image}" alt="Vehicle Image" width="100"/></td>
                        <td>${vehicle.category}</td>
                        <td>${vehicle.monthFee}</td>
                        <td>
                            <form method="post" action="VehicleController">
                                <input type="hidden" name="vehicleId" value="${vehicle.vehicleId}"/>
                                <input type="hidden" name="type" value="delete"/>
                                <button type="submit" class="btn btn-danger">Remove</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<br/>
</body>
</html>
