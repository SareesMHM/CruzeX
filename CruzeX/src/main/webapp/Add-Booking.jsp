<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://api.mapbox.com/mapbox-gl-js/v2.14.1/mapbox-gl.js"></script>
    <link href="https://api.mapbox.com/mapbox-gl-js/v2.14.1/mapbox-gl.css" rel="stylesheet"/>
    
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

                <label>Pick-Up Location (Lat, Lng):</label>
                <input type="text" class="form-control" id="pickupLatLng" name="pickupLocation" readonly required />

                <label>Drop Location (Lat, Lng):</label>
                <input type="text" class="form-control" id="dropLatLng" name="dropLocation" readonly required/>


                <div id="map"></div>

                <label>Distance (km):</label>
                <input type="text" class="form-control" id="distance" name="distance" readonly/>

                <label>Estimated Fare (LKR):</label>
                <input type="text" class="form-control" id="fare" name="fare" readonly/>
                
                <label>Tax (7%):</label>
                <input type="text" class="form-control" id="tax" name="tax" readonly />

                <label>Discount (7%):</label>
                <input type="text" class="form-control" id="discount" name="discount" readonly />

                <label>Total Fare (LKR):</label>
                <input type="text" class="form-control" id="totalFare" name="totalFare" readonly />


                <input type="hidden" name="type" value="add"/>
                <button type="submit" class="btn btn-primary mt-3">Schedule</button>
            </form>
        </div>
    </div>
</div>

<!--<script>
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

//    function calculateDistance() {
//        if (pickupLocation && dropLocation) {
//            distanceService.getDistanceMatrix({
//                origins: [pickupLocation.geometry.location],
//                destinations: [dropLocation.geometry.location],
//                travelMode: 'DRIVING'
//            }, function (response, status) {
//                if (status === 'OK' && response.rows[0].elements[0].status !== 'ZERO_RESULTS') {
//                    let distanceKm = response.rows[0].elements[0].distance.value / 1000;
//                    distanceField.value = distanceKm.toFixed(2);
//
//                    let monthFee = 100; // Default fee per km, you can fetch from DB
//                    let totalFare = distanceKm * monthFee;
//                    fareField.value = totalFare.toFixed(2);
//                } else {
//                    alert('Unable to calculate distance');
//                }
//            });
//        }
//    }

        function calculateDistance() {
    if (pickupLocation && dropLocation) {
        let pickupLatLng = pickupLocation.geometry.location;
        let dropLatLng = dropLocation.geometry.location;

        document.getElementById("pickupLatLng").value = pickupLatLng.lat() + ", " + pickupLatLng.lng();
        document.getElementById("dropLatLng").value = dropLatLng.lat() + ", " + dropLatLng.lng();

        distanceService.getDistanceMatrix({
            origins: [pickupLatLng],
            destinations: [dropLatLng],
            travelMode: 'DRIVING'
        }, function (response, status) {
            if (status === 'OK' && response.rows[0].elements[0].status !== 'ZERO_RESULTS') {
                let distanceKm = response.rows[0].elements[0].distance.value / 1000;
                document.getElementById("distance").value = distanceKm.toFixed(2);

                let perKmRate = 100; // Ideally, fetch from DB
                let totalFare = distanceKm * perKmRate;
                document.getElementById("fare").value = totalFare.toFixed(2);
            } else {
                alert('Unable to calculate distance');
            }
        });
    }
}


    window.onload = initMap;
</script>-->

<script>
        mapboxgl.accessToken = 'pk.eyJ1Ijoic2FyZWVzIiwiYSI6ImNtN3VwM2Y2cjAyeDQybHF1c3plcmdpdjIifQ.Of51-3X4TCDP6_wKDIqqLA'; // Replace with your token

let map = new mapboxgl.Map({
    container: 'map',
    style: 'mapbox://styles/mapbox/streets-v11',
    center: [79.8603,6.8857],  
    zoom: 12
});

let pickupMarker, dropMarker, pickupCoords, dropCoords;

// Function to add a marker when the user selects a location
function addMarker(lngLat, type) {
    let marker = new mapboxgl.Marker().setLngLat(lngLat).addTo(map);
    if (type === 'pickup') {
        if (pickupMarker) pickupMarker.remove();
        pickupMarker = marker;
        pickupCoords = lngLat;
        document.getElementById("pickupLatLng").value = lngLat.lat + ", " + lngLat.lng;
    } else {
        if (dropMarker) dropMarker.remove();
        dropMarker = marker;
        dropCoords = lngLat;
        document.getElementById("dropLatLng").value = lngLat.lat + ", " + lngLat.lng;
    }
    calculateDistance();
}

// Add click event to select pickup/drop points
map.on('click', function (e) {
    if (!pickupCoords) {
        addMarker(e.lngLat, 'pickup');
    } else {
        addMarker(e.lngLat, 'drop');
    }
});

// Function to calculate distance using Mapbox Directions API
function calculateDistance() {
    
//    if (!pickupCoords || !dropCoords) {
//        console.error("Error: Pickup or drop location is missing!");
//        alert("Please select both pickup and drop locations.");
//        return;
//    }

    console.log("Using coordinates for request:", pickupCoords, dropCoords);
    if (pickupCoords && dropCoords) {
        let url = "https://api.mapbox.com/directions/v5/mapbox/driving/"+pickupCoords.lng+","+pickupCoords.lat+";"+dropCoords.lng+","+dropCoords.lat+"?access_token="+mapboxgl.accessToken+"&geometries=geojson";
        fetch(url)
            .then(response => response.json())
            .then(data => {
                console.log("Mapbox API Response:", data); // Debugging
                if (data.routes.length > 0) {
                    let distanceKm = data.routes[0].distance / 1000;
                    document.getElementById("distance").value = distanceKm.toFixed(2);

                    let perKmRate = <%= request.getParameter("monthFee") != null ? request.getParameter("monthFee") : "100" %>;
                    let fare = distanceKm * perKmRate;
                    document.getElementById("fare").value = fare.toFixed(2);

                    // Calculate tax (7% of base fare)
                    let tax = fare * 0.07;
                    document.getElementById("tax").value = tax.toFixed(2);

                    // Calculate discount (7% of base fare)
                    let discount = fare * 0.07;
                    document.getElementById("discount").value = discount.toFixed(2);

                    // Calculate total fare: baseFare + tax - discount
                    let totalFare = fare + tax - discount;
                    document.getElementById("totalFare").value = totalFare.toFixed(2);
                } else {
                    alert("No route found. Try different locations.");
                }
            })
            .catch(error => {
                console.error("Error fetching directions:", error);
                alert("Unable to calculate distance. Check console for errors.");
            });
    }
}


</script>

<script>
    var message = "<%= request.getAttribute("message") %>";
    if (message) {
        alert(message);
    }
</script>
</body>
</html>