<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Vehicles</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2 class="mt-4 mb-4">Available Vehicles</h2>
    <table class="table table-striped">
        <thead>
            <tr class="table-dark">
                <th>Vehicle ID</th>
                <th>Vehicle Name</th>
                <th>Category</th>
                <th>Month Fee</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="vehicle" items="${vehicleList}">
                <tr>
                    <td>${vehicle.vehicleID}</td>
                    <td>${vehicle.vehicleName}</td>
                    <td>${vehicle.category}</td>
                    <td>${vehicle.monthFee}</td>
                    <td>
                        <!-- "Book Now" button that redirects to Booking.jsp with vehicle details -->
                        <a href="Add-Booking.jsp?vehicleID=${vehicle.vehicleID}&vehicleName=${vehicle.vehicleName}&category=${vehicle.category}" 
                           class="btn btn-success">Book Now</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>