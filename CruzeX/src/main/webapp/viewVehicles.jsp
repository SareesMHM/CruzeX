<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="CruzeX.webapp.Model.Vehicle" %>

<!DOCTYPE html>
<html>
<head>
    <title>Vehicle List</title>
    
    <!-- Bootstrap for styling -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            padding-top: 20px;
        }
        .table th, .table td {
            vertical-align: middle;
            text-align: center;
        }
        .vehicle-img {
            width: 120px;
            height: 80px;
            object-fit: cover;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<div class="container">
    <h2 class="text-center">Available Vehicles</h2>

    <%-- Display message if exists --%>
    <c:if test="${not empty message}">
        <div class="alert alert-info text-center">${message}</div>
    </c:if>

    <br>

    <table class="table table-striped table-bordered">
        <thead class="table-dark">
            <tr>
                <th>Vehicle ID</th>
                <th>Vehicle Name</th>
                <th>Image</th>
                <th>Category</th>
                <th>Monthly Fee</th>
                <th>Book</th>
            </tr>
        </thead>

        <tbody>
            <c:choose>
                <c:when test="${not empty sessionScope.vehicleList}">
                    <c:forEach var="vehicle" items="${sessionScope.vehicleList}">
                        <tr>
                            <td>${vehicle.vehicleID}</td>
                            <td>${vehicle.vehicleName}</td>

                            <%-- Vehicle Image Display --%>
                            <td>
                                <img src="img/car/${vehicle.image}" class="vehicle-img" 
                                     onerror="this.src='img/car/default.jpg';" alt="Vehicle Image">
                            </td>

                            <td>${vehicle.category}</td>
                            <td>$${vehicle.monthFee}</td>

                            <%-- Booking Button --%>
                            <td>
                                <a href="Add-Booking.jsp?vehicleId=${vehicle.vehicleID}&driverId=${vehicle.driverID}" 
                                   class="btn btn-success btn-sm">Book Now</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr><td colspan="6" class="text-center">No vehicles found.</td></tr>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
</div>

</body>
</html>
