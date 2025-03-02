<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_GOOGLE_MAPS_API_KEY&libraries=places"></script>
    
    <meta charset="UTF-8">
    <title>Booking</title>
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }
        .container-fluid {
            padding-top: 20px;
        }
        .nav-link {
            color: #ffffff;
        }
        .nav-link:hover {
            color: #ffc107;
        }
        .form-group {
            background-color: #ffffff;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-group label {
            font-weight: bold;
        }
        .form-control {
            border: 1px solid #ced4da;
            border-radius: 5px;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            font-weight: bold;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        #map {
            height: 300px;
            width: 100%;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <ul class="nav justify-content-center bg-dark py-2">
        <li class="nav-item">
            <a class="link-warning nav-link px-5 mx-5" aria-current="page" href="CustomerHomePage.jsp"> << Go to Home</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="BookingDashboard.jsp">Store</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Search-Booking.jsp">Search & Update</a>
        </li>
        <li class="nav-item">
            <a class="text-white nav-link" href="Booking.jsp">Book</a>
        </li>
    </ul>
    <br/>
    
    <div class="container">
        <div class="form-group p-5 mx-5 mb-4">
            <h3>Register Booking</h3>
            <form method="post" action="BookingController">
                
                <label for="vehicleID">Vehicle ID:</label>
                <input type="text" class="form-control" id="vehicleID" name="vehicleID" value="<%= request.getParameter("vehicleID") %>" readonly />

                <label for="driverID">Driver ID:</label>
                <input type="text" class="form-control" id="driverID" name="driverID" value="<%= request.getParameter("driverID") %>" readonly />

                <label for="customerID">Customer ID:</label>
                <input type="text" class="form-control" id="customerID" name="customerID" value="<%= request.getParameter("customerID") %>" readonly />
                
                <label for="bookingDate">Date:</label>
                <input type="date" class="form-control" id="bookingDate" name="bookingDate" required/>

                <label for="bookingTime">Time:</label>
                <input type="time" class="form-control" id="bookingTime" name="bookingTime" required/>

                <label>Pick-Up Location:</label>
                <input type="text" class="form-control" id="pickupLocation" name="pickupLocation" required/>

                <label>Drop Location:</label>
                <input type="text" class="form-control" id="dropLocation" name="dropLocation" required/>

                <div id="map"></div>

                <label>Distance (km):</label>
                <input type="text" class="form-control" id="distance" name="distance" readonly/>

                <label>Estimated Fare (LKR):</label>
                <input type="text" class="form-control" id="fare" name="fare" readonly/>

                <input type="hidden" name="type" value="add"/>
                <button type="submit" class="btn btn-primary mt-3">Schedule</button>
            </form>
        </div>
    </div>
</div>

<script>
    let map, pickupInput, dropInput;
    let distanceField = document.getElementById("distance");
    let fareField = document.getElementById("fare");
    let pickupLocation, dropLocation;
    let distanceService = new google.maps.DistanceMatrixService();

    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: { lat: 7.8731, lng: 80.7718 }, // Sri Lanka
            zoom: 8
        });

        let pickupAutocomplete = new google.maps.places.Autocomplete(document.getElementById('pickupLocation'));
        let dropAutocomplete = new google.maps.places.Autocomplete(document.getElementById('dropLocation'));

        pickupAutocomplete.addListener('place_changed', function () {
            pickupLocation = pickupAutocomplete.getPlace();
            calculateDistance();
        });

        dropAutocomplete.addListener('place_changed', function () {
            dropLocation = dropAutocomplete.getPlace();
            calculateDistance();
        });
    }

    function calculateDistance() {
        if (pickupLocation && dropLocation) {
            distanceService.getDistanceMatrix({
                origins: [pickupLocation.geometry.location],
                destinations: [dropLocation.geometry.location],
                travelMode: 'DRIVING'
            }, function (response, status) {
                if (status === 'OK' && response.rows[0].elements[0].status !== 'ZERO_RESULTS') {
                    let distanceKm = response.rows[0].elements[0].distance.value / 1000;
                    distanceField.value = distanceKm.toFixed(2);

                    let monthFee = 100; // Default fee per km, you can fetch from DB
                    let totalFare = distanceKm * monthFee;
                    fareField.value = totalFare.toFixed(2);
                } else {
                    alert('Unable to calculate distance');
                }
            });
        }
    }

    window.onload = initMap;
</script>

<script>
    var message = "<%= request.getAttribute("message") %>";
    if (message) {
        alert(message);
    }
</script>
</body>
</html>