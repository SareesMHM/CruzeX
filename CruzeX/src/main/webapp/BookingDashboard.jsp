<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Google Maps API -->
    <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_GOOGLE_MAPS_API_KEY&libraries=places"></script>

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
        .btn-remove, .btn-edit, .btn-view {
            padding: 0.375rem 0.75rem;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <!-- Navigation Bar -->
    <ul class="nav justify-content-center bg-dark py-2">
        <li class="nav-item">
            <a class="link-warning nav-link px-5 mx-5" href="CustomerHomePage.jsp"> << Go to Home</a>
        </li>
        <li class="nav-item">
            <a class="text-white nav-link" href="BookingDashboard.jsp">Home</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Search-Booking.jsp">Search & Update</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Add-Booking.jsp">New Booking</a>
        </li>
    </ul>
    <br/>
    
    <!-- Display message (e.g., confirmation) -->
    <c:if test="${not empty message}">
        <div class="alert alert-info">${message}</div>
    </c:if>
    <br/>

    <!-- Booking Table -->
    <div class="container">
        <h3 class="text-center mb-4">All Bookings</h3>
        <table class="table table-striped">
            <thead>
                <tr class="table-dark">
                    <th>Booking ID</th>
                    <th>Customer ID</th>
                    <th>Vehicle ID</th>
                    <th>Driver ID</th>
                    <th>Booking Date</th>
                    <th>Booking Time</th>
                    <th>Pick-Up</th>
                    <th>Drop</th>
                    <th>Distance (KM)</th>
                    <th>Total Fare (LKR)</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="booking" items="${bookingList}">
                    <tr>
                        <td>${booking.bookingID}</td>
                        <td>${booking.customerID}</td>
                        <td>${booking.vehicleID}</td>
                        <td>${booking.driverID}</td>
                        <td>${booking.bookingDate}</td>
                        <td>${booking.bookingTime}</td>
                        <td>${booking.pickupLocation}</td>
                        <td>${booking.dropLocation}</td>
                        <td id="distance-${booking.bookingID}">Calculating...</td>
                        <td id="fare-${booking.bookingID}">-</td>
                        <td>
                            <!-- Delete Booking Form -->
                            <form method="post" action="BookingController" style="display:inline;">
                                <input type="hidden" name="bookingID" value="${booking.bookingID}"/>
                                <input type="hidden" name="type" value="delete"/>
                                <button type="submit" class="btn btn-danger btn-remove">Remove</button>
                            </form>
                            <!-- Edit Booking -->
                            <a href="Search-Booking.jsp?bookingID=${booking.bookingID}" class="btn btn-warning btn-edit">Edit</a>
                            <!-- View on Map -->
                            <button class="btn btn-primary btn-view" onclick="viewOnMap('${booking.pickupLocation}', '${booking.dropLocation}')">View</button>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty bookingList}">
                    <tr>
                        <td colspan="11" class="text-center text-muted">No bookings available.</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>
<br/>

<!-- Google Maps Distance Calculation -->
<script>
    function calculateDistance(bookingID, pickup, drop, monthFee) {
        var service = new google.maps.DistanceMatrixService();
        
        service.getDistanceMatrix({
            origins: [pickup],
            destinations: [drop],
            travelMode: google.maps.TravelMode.DRIVING,
            unitSystem: google.maps.UnitSystem.METRIC,
        }, function(response, status) {
            if (status === google.maps.DistanceMatrixStatus.OK) {
                var distanceText = response.rows[0].elements[0].distance.text; 
                var distanceValue = response.rows[0].elements[0].distance.value / 1000; // Convert to KM
                
                document.getElementById("distance-" + bookingID).innerText = distanceValue.toFixed(2) + " KM";
                
                var fare = distanceValue * monthFee;
                document.getElementById("fare-" + bookingID).innerText = "LKR " + fare.toFixed(2);
            } else {
                document.getElementById("distance-" + bookingID).innerText = "Error";
                document.getElementById("fare-" + bookingID).innerText = "Error";
            }
        });
    }

    function viewOnMap(pickup, drop) {
        var url = https://www.google.com/maps/dir/?api=1&origin=${encodeURIComponent(pickup)}&destination=${encodeURIComponent(drop)}&travelmode=driving;
        window.open(url, "_blank");
    }

    window.onload = function() {
        <c:forEach var="booking" items="${bookingList}">
            var bookingID = "${booking.bookingID}";
            var pickup = "${booking.pickupLocation}";
            var drop = "${booking.dropLocation}";
            var monthFee = parseFloat("${booking.vehicle.monthFee}");
            
            calculateDistance(bookingID, pickup, drop, monthFee);
        </c:forEach>
    };
</script>

</body>
</html>