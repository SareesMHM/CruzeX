<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, CruzeX.webapp.Model.Vehicle" %>
<%@ include file="header.jsp" %>

<%
    List<Vehicle> vehicleList = (List<Vehicle>) session.getAttribute("vehicleList");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Available Vehicles</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

    <h2>Available Vehicles</h2>

    <table border="1">
        <tr>
            <th>Vehicle ID</th>
            <th>Model</th>
            <th>Type</th>
            <th>Status</th>
            <th>Image</th>
            <th>Action</th>
        </tr>
        <% if (vehicleList != null) {
            for (Vehicle vehicle : vehicleList) { %>
                <tr>
                    <td><%= vehicle.getVehicleID() %></td>
                    <td><%= vehicle.getVehicleName() %></td>
                    <td><%= vehicle.getCategory() %></td>
                    <td><%= vehicle.getMonthFee() %></td>
                    <td>
                        <img src="<%= vehicle.getImage() %>" alt="Vehicle Image" style="width:100px;">
                    </td>
                    <td>
                        <% if ("Available".equals(vehicle.getStatus())) { %>
                            <a href="Add-Booking.jsp?vehicleId=<%= vehicle.getVehicleID() %>">Book Now</a>
                        <% } else { %>
                            Unavailable
                        <% } %>
                    </td>
                </tr>
        <%  }
        } %>
    </table>

</body>
</html>
