<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Booking Dashboard</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
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
            <a class="link-warning nav-link px-5 mx-5" href="AdminHomePage.jsp">&laquo; Go to Home</a>
        </li>
        <li class="nav-item">
            <a class="text-white nav-link" href="BookingController">Bookings</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Search-Booking.jsp">Search & Update Booking</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="BookingController?type=">Add Booking</a>
        </li>
    </ul>

    <br/>

    <!-- Show success or error message -->
    <c:if test="${not empty message}">
        <div class="alert alert-info text-center">${message}</div>
    </c:if>

    <div class="container">
        <h2 class="text-center">Booking List</h2>

        <table class="table table-striped">
            <thead>
                <tr class="table-dark">
                    <th>Booking ID</th>
                    <th>Customer ID</th>
                    <th>Vehicle ID</th>
                    <th>Driver ID</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Pickup</th>
                    <th>Drop</th>
                    <th>Distance (km)</th>
                    <th>Fare (LKR)</th>
                    <th>Tax</th>
                    <th>Discount</th>
                    <th>Total</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty sessionScope.bookingList}">
                        <c:forEach var="booking" items="${sessionScope.bookingList}">
                            <tr>
                                <td>${booking.bookingID}</td>
                                <td>${booking.customerID}</td>
                                <td>${booking.vehicleID}</td>
                                <td>${booking.driverID}</td>
                                <td>${booking.bookingDate}</td>
                                <td>${booking.bookingTime}</td>
                                <td>${booking.pickupLocation}</td>
                                <td>${booking.dropLocation}</td>
                                <td>${booking.distance}</td>
                                <td>${booking.fare}</td>
                                <td>${booking.tax}</td>
                                <td>${booking.discount}</td>
                                <td>${booking.totalFare}</td>
                                <td>
                                    <form action="BookingController" method="post" style="display:inline;">
                                        <input type="hidden" name="type" value="delete" />
                                        <input type="hidden" name="bookingID" value="${booking.bookingID}" />
                                        <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr><td colspan="14" class="text-center">No bookings found.</td></tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</div>
    <script>
    var message = "<%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>";
    if (message.trim() !== "") {
        alert(message);
    }
</script>

</body>
</html>