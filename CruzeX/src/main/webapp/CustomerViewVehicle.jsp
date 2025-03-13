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
    <nav class="navbar navbar-expand-lg navbar-light bg-light" >
  <div class="container-fluid" >
      <img style="border-radius:50%;width: 70px;height: 70px; margin:0 10px 0 50px " alt="" src="img/x (4).webp">
    <p class="fw-bold fs-4 mt-3" style="color: black;">Available Vehicles for Booking</p>
    <button style="margin:0 30px;" class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup" style="margin:0 50px;">
      <div class="navbar-nav" style="#ffff">
          <a class="nav-link active mx-0 mx-md-3 fs-6" aria-current="page" href="CruzeXHome.jsp">Home</a>
         
        <a class="nav-link mx-0 mx-md-3 fs-6" href="VehicleController?type=customer">View Vehicle</a>
        <a class="nav-link mx-0 mx-md-3 fs-6" href="QueryForm.jsp">Ask Your Queries</a>
        
        
      </div>
    </div>
  </div>
</nav>


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
                                <p class="card-text"><strong>Per 1Km:</strong> $${vehicle.monthFee}</p>
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