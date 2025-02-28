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
            <a class="link-warning nav-link px-5 mx-5" href="AdminHomePage.jsp"> << Go to Home</a>
        </li>
        <li class="nav-item">
            <a class="text-white nav-link" href="VehicleDashboard.jsp">Store</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Search-Vehicle-Details.jsp">Search Vehicle & Update</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Add-Vehicle-Details.jsp">Add</a>
        </li>
    </ul>

    <br/>
    
    <!-- Display message if exists -->
    <c:if test="${not empty message}">
        <div class="alert alert-info text-center">${message}</div>
    </c:if>

    <br/>

    <div class="container">
        <h2 class="text-center">Vehicle List</h2>
        <table class="table table-striped">
            <thead>
                <tr class="table-dark">
                    <th>Vehicle ID</th>
                    <th>Vehicle Name</th>
                    <th>Image</th>
                    <th>Category</th>
                    <th>Month Fee</th>
                    <th>Remove from List</th>
                </tr>
            </thead>
            <tbody>
                <!-- Check if vehicleList is empty -->
                <c:choose>
                    <c:when test="${not empty vehicleList}">
                        <c:forEach var="vehicle" items="${vehicleList}">
                            <tr>
                                <td>${vehicle.VehicleID}</td>
                                <td>${vehicle.VehicleName}</td>
                                <td>
                                    <img src="<c:out value="${vehicle.Image}" />" alt="Vehicle Image" width="100"
                                         onerror="this.src='default-image.jpg';"/>
                                </td>
                                <td>${vehicle.Category}</td>
                                <td>${vehicle.MonthFee}</td>
                                <td>
                                    <form method="post" action="VehicleController">
                                        <input type="hidden" name="vehicleId" value="${vehicle.VehicleId}"/>
                                        <input type="hidden" name="type" value="delete"/>
                                        <button type="submit" class="btn btn-danger btn-remove">Remove</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6" class="text-center">No vehicles found.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</div>
<br/>

<!-- Improved JavaScript message handling -->
<script>
    var message = "<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>";
    if (message.trim() !== "") {
        alert(message);
    }
</script>

</body>
</html>
