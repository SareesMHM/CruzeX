<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search and Update Booking</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

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
    </style>
</head>
<body>
<div class="container-fluid">
    <!-- Navigation Bar -->
    <ul class="nav justify-content-center bg-dark py-2">
        <li class="nav-item">
            <a class="link-warning nav-link px-5 mx-5" href="AdminHomePage.jsp"> << Go to Home</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="BookingDashboard.jsp">All Bookings</a>
        </li>
        <li class="nav-item">
            <a class="text-white nav-link" href="Search-Booking.jsp">Search & Update</a>
        </li>
        
    </ul>
    <br/>

    <!-- Search & Update Booking Form -->
    <div class="container form-group p-5 mx-5 mb-4 bg-light">
        <div class="row">
            <!-- Search Booking -->
            <div class="col-md-6">
                <h3>Search Booking</h3>
                <form method="get" action="BookingController">
                    <div class="form-group">
                        <label>Enter Booking ID:</label>
                        <input type="text" class="form-control" name="bookingID" required>
                        <input type="hidden" name="type" value="specific">
                        <button type="submit" class="btn btn-info mt-2">Search</button>
                    </div>
                </form>
                <br/>
                <p>${searchResult}</p>
            </div>

            <!-- Edit Booking -->
            <div class="col-md-6">
                <h3>Edit Booking</h3>
                <form method="post" action="BookingController">
                    <label for="bookingID">Booking ID:</label>
                    <input type="text" readonly class="form-control" id="bookingID" name="bookingID" value="${not empty booking ? booking.bookingID : ''}"/>
                    <br/>

                    <label for="customerID">Customer ID:</label>
                    <input type="text" class="form-control" id="customerID" name="customerID" value="${not empty booking ? booking.customerID : ''}" readonly/>
                    <br/>

                    <label for="vehicleID">Vehicle ID:</label>
                    <input type="text" class="form-control" id="vehicleID" name="vehicleID" value="${not empty booking ? booking.vehicleID : ''}" readonly/>
                    <br/>

                    <label for="driverID">Driver ID:</label>
                    <input type="text" class="form-control" id="driverID" name="driverID" value="${not empty booking ? booking.driverID : ''}" readonly/>
                    <br/>

                    <label for="bookingDate">Booking Date:</label>
                    <input type="date" class="form-control" id="bookingDate" name="bookingDate" value="${not empty booking ? booking.bookingDate : ''}" required/>
                    <br/>

                    <label for="bookingTime">Booking Time:</label>
                    <input type="time" class="form-control" id="bookingTime" name="bookingTime" value="${not empty booking ? booking.bookingTime : ''}" required/>
                    <br/>

                    <!-- Google Maps Autocomplete for Locations -->
                    <label for="pickupLocation">Pick-Up Location:</label>
                    <input type="text" class="form-control" id="pickupLocation" name="pickupLocation" value="${not empty booking ? booking.pickupLocation : ''}" required/>
                    <br/>

                    <label for="dropLocation">Drop Location:</label>
                    <input type="text" class="form-control" id="dropLocation" name="dropLocation" value="${not empty booking ? booking.dropLocation : ''}" required/>
                    <br/>

                    <label for="distance">Distance (KM):</label>
                    <input type="text" class="form-control" id="distance" name="distance" readonly/>
                    <br/>

                    <label for="monthFee">Vehicle Monthly Fee (LKR):</label>
                    <input type="text" class="form-control" id="monthFee" name="monthFee" value="${not empty booking ? booking.vehicle.monthFee : ''}" readonly/>
                    <br/>

                    <label for="fare">Total Fare (LKR):</label>
                    <input type="text" class="form-control" id="fare" name="fare" readonly/>
                    <br/>

                    <input type="hidden" name="type" value="update"/>
                    <button type="submit" class="btn btn-warning">Update</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Google Maps Distance Calculation -->
<!--<script>
    function initAutocomplete() {
        var pickup = new google.maps.places.Autocomplete(document.getElementById("pickupLocation"));
        var drop = new google.maps.places.Autocomplete(document.getElementById("dropLocation"));
    }

    function calculateDistance() {
        var service = new google.maps.DistanceMatrixService();
        var pickup = document.getElementById("pickupLocation").value;
        var drop = document.getElementById("dropLocation").value;

        if (pickup && drop) {
            service.getDistanceMatrix({
                origins: [pickup],
                destinations: [drop],
                travelMode: google.maps.TravelMode.DRIVING,
                unitSystem: google.maps.UnitSystem.METRIC,
            }, function(response, status) {
                if (status === google.maps.DistanceMatrixStatus.OK) {
                    var distanceText = response.rows[0].elements[0].distance.text; 
                    var distanceValue = response.rows[0].elements[0].distance.value / 1000; // Convert to KM
                    document.getElementById("distance").value = distanceValue.toFixed(2);

                    var monthFee = parseFloat(document.getElementById("monthFee").value);
                    var fare = distanceValue * monthFee;
                    document.getElementById("fare").value = fare.toFixed(2);
                } else {
                    alert("Error calculating distance.");
                }
            });
        }
    }

    document.getElementById("dropLocation").addEventListener("change", calculateDistance);
    document.getElementById("pickupLocation").addEventListener("change", calculateDistance);

    google.maps.event.addDomListener(window, 'load', initAutocomplete);
</script>-->
 <script>
                    var message = "<%= request.getAttribute("message") %>";
                    if (message) {
                        alert(message);
                    }
                </script>

</body>
</html>