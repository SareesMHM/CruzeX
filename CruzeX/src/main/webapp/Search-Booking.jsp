<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <title>Search and Update Booking</title>
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
    <!-- Navigation Bar -->
    <ul class="nav justify-content-center bg-dark py-2">
        <li class="nav-item">
            <a class="link-warning nav-link px-5 mx-5" aria-current="page" href="CustomerHomePage.jsp"> << Go to Home</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="BookingController">Store</a>
        </li>
        <li class="nav-item">
            <a class="text-white nav-link" href="Search-Booking.jsp">Search Specific & Update</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Add-Booking.jsp">Booking</a>
        </li>
    </ul>
    <br/>

    <!-- Form Section -->
    <div class="container form-group p-5 mx-5 mb-4 bg-light">
        <div class="row">
            <!-- Search Booking -->
            <div class="col-md-6">
                <h3>Search Booking</h3>
                <form method="get" action="BookingController">
                    <div class="form-group">
                        Enter Booking ID: 
                        <input type="text" class="form-control" name="bookingID">
                        <input type="hidden" name="type" value="specific">
                        <button type="submit" class="btn btn-info mt-2">Search</button>
                    </div>
                </form>
                <br/>
                <!-- Display Search Result Message -->
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
                    <input type="text" class="form-control" id="customerID" name="customerID" value="${not empty booking ? booking.customerID : ''}"/>
                    <br/>
                    <label for="bookingDate">Booking Date:</label>
                    <input type="date" class="form-control" id="bookingDate" name="bookingDate" value="${not empty booking ? booking.bookingDate : ''}"/>
                    <br/>
                    <label for="bookingTime">Booking Time:</label>
                    <input type="time" class="form-control" id="bookingTime" name="bookingTime" value="${not empty booking ? booking.bookingTime : ''}"/>
                    <br/>
                    <label for="driverName">Driver Name:</label>
                    <input type="text" class="form-control" id="driverName" name="driverName" value="${not empty booking ? booking.driverName : ''}"/>
                    <br/>
                    <label for="address">Address:</label>
                    <input type="text" class="form-control" id="address" name="address" value="${not empty booking ? booking.address : ''}"/>
                    <br/>
                    <label for="destination">Destination:</label>
                    <input type="text" class="form-control" id="destination" name="destination" value="${not empty booking ? booking.destination : ''}"/>
                    <input type="hidden" name="type" value="update"/>
                    <br/>
                    <button type="submit" class="btn btn-warning">Update</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
