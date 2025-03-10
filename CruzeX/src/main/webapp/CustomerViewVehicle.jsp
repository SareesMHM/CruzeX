<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Available Vehicles</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2 class="mt-4 mb-4 text-center">Available Vehicles for Booking</h2>

    <!-- Check if the user is logged in -->
    <%
        HttpSession userSession = request.getSession();
        Integer customerID = (Integer) userSession.getAttribute("customerId");
        if (customerID == null) {
            response.sendRedirect("Login-Customer.jsp"); // Redirect if user is not logged in
            return;
        }
    %>

    <div class="row">
        <c:choose>
            <c:when test="${not empty sessionScope.vehicleList}">
                <c:forEach var="vehicle" items="${sessionScope.vehicleList}">
                    <div class="col-md-4 mb-4">
                        <div class="card">
                            <img src="img/car/${vehicle.image}" class="card-img-top" alt="${vehicle.vehicleName}" style="height: 200px; object-fit: cover;">
                            <div class="card-body">
                                <h5 class="card-title">${vehicle.vehicleName}</h5>
                                <p class="card-text"><strong>Category:</strong> ${vehicle.category}</p>
                                <p class="card-text"><strong>Monthly Fee:</strong> $${vehicle.monthFee}</p>
                                <p class="card-text"><strong>Assigned Driver ID:</strong> ${vehicle.driverID}</p>

                                <!-- Booking button redirects to Add-Booking.jsp with pre-filled values -->
                                <a href="Add-Booking.jsp?vehicleID=${vehicle.vehicleID}&driverID=${vehicle.driverID}&customerID=<%= customerID %>&monthFee=${vehicle.monthFee}" 
                                   class="btn btn-success w-100">Book Now</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr><td colspan="6" class="text-center">No vehicles found.</td></tr>
            </c:otherwise>
        </c:choose>
        
    </div>
</div>
</body>
</html>