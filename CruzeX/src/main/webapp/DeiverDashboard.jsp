<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Driver List</title>
    
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
    </style>
</head>
<body>
<div class="container-fluid">
    <ul class="nav justify-content-center bg-dark py-2">
        <li class="nav-item">
            <a class="link-warning nav-link px-5 mx-5" aria-current="page" href="AdminHomePage.jsp"> << Go to Home</a>
        </li>
        <li class="nav-item">
            <a class="text-white nav-link" aria-current="page" href="DriverDashboard.jsp">Store</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Search-Driver-Details.jsp">Search Specific & Update</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Add-Driver-Details.jsp">Add</a>
        </li>
    </ul>
    <br/>
    <p>${message}</p>
    <br/>
    <div class="container">
        <table class="table table-striped">
            <thead>
                <tr class="table-dark">
                    <th>Driver ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>License Number</th>
                    <th>Email</th>
                    <th>Remove from List</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="driver" items="${driverList}">
                    <tr>
                        <td>${driver.driverID}</td>
                        <td>${driver.firstName}</td>
                        <td>${driver.lastName}</td>
                        <td>${driver.licenseNumber}</td>
                        <td>${driver.email}</td>
                        <td>
                            <form method="post" action="driverController">
                                <input type="hidden" name="driverId" value="${driver.driverID}"/>
                                <input type="hidden" name="type" value="delete"/>
                                <button type="submit" class="btn btn-danger">Remove</button>
                            </form>
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
