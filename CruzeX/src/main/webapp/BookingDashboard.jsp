<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
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
        .btn-edit {
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
            <a class="text-white nav-link" href="BookingDashboard.jsp">Home</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Search-Booking.jsp">Search Specific & Update</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Booking.jsp">Booking</a>
        </li>
    </ul>
    <br/>
    <!-- Display message (e.g., confirmation) -->
    <p>${message}</p>
    <br/>
    <!-- Booking Table -->
    <div class="container">
        <table class="table table-striped">
            <thead>
                <tr class="table-dark">
                    <th>Booking ID</th>
                    <th>Customer ID</th>
                    <th>Booking Date</th>
                    <th>Booking Time</th>
                    <th>Driver Name</th>
                    <th>Address</th>
                    <th>Destination</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="booking" items="${bookingList}">
                    <tr>
                        <td>${booking.bookingID}</td>
                        <td>${booking.customerID}</td>
                        <td>${booking.bookingDate}</td>
                        <td>${booking.bookingTime}</td>
                        <td>${booking.driverName}</td>
                        <td>${booking.address}</td>
                        <td>${booking.destination}</td>
                        <td>
                            <form method="post" action="appointmentController">
                                <input type="hidden" name="bookingID" value="${booking.bookingID}"/>
                                <input type="hidden" name="type" value="delete"/>
                                <button type="submit" class="btn btn-danger">Remove</button>
                            </form>
                            <!-- Edit Booking -->
                            <a href="EditBooking.jsp?bookingID=${booking.bookingID}" class="btn btn-warning btn-edit">Edit</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<br/>
</body>
</html>
