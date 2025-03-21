<%-- 
    Document   : Add-Car
    Created on : Feb 25, 2025, 3:55:39 PM
    Author     : MHM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add-vehicle</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        body {
              background-image: url('img/car/Audi (1).jpg'); 
      background-size: cover;
      background-position: center; 
      background-repeat: no-repeat; 
      height: 100vh; 
      
        background-color: #f8f9fa;
        min-height: 100vh;
        display: flex;
        flex-direction: column;
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
        .form-label {
            font-weight: bold;
        }
        .btn-register {
            width: 100%;
        }
        .form-container {
            background-color:rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
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
            <a class="link-secondary  nav-link " aria-current="page" href="VehicleController">Store</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link " href="Search-Vehicle-Details.jsp">Search vehicle & Update</a>
        </li>
        <li class="nav-item">
            <a class="text-white nav-link" href="Add-Vehicle-Details.jsp">Add</a>
        </li>
    </ul>
    <br/>
    <br/>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="form-container">
                    <h3 class="text-center mb-4">Register vehicle</h3>
                    <form method="post" action="VehicleController">
                       <div class="mb-3">
                            <label for="firstName" class="form-label">Vehicle ID:</label>
                            <input type="text" class="form-control" id="vehicleID" name="vehicleID"/>
                        </div>
                        <div class="mb-3">
                            <label for="firstName" class="form-label">Vehicle Name:</label>
                            <input type="text" class="form-control" id="vehicleName" name="vehicleName"/>
                        </div>
                       
                        
                      
                        <div class="mb-3">
                            <label for="image">Image</label>
                            <input type="file" id="image" name="image" >
                        </div>
                        <div class="mb-3">
                            <label for="category">Category</label>
                            <select id="category" name="category" >
                                <option value="Car">Car</option>
                                <option value="Van">Van</option>
                                <option value="Bus">Bus</option>
                                <option value="SUV">SUV</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="monthFee" class="form-label">Month Fee:</label>
                            <input type="text" class="form-control" id="monthFee" name="monthFee"/>
                        </div>
                        <div class="mb-3">
                                    <label for="driverID">Assign Driver:</label>
                                    <select class="form-control" id="driverID" name="driverID" required>
                                        <option value="">-- Select Driver --</option>

                                        <c:choose>
                                            <c:when test="${not empty sessionScope.driverList}">
                                                <c:forEach var="driver" items="${sessionScope.driverList}">
                                                    <option value="${driver.driverID}">${driver.firstName} ${driver.lastName} (ID: ${driver.driverID})</option>

                                                </c:forEach>
                                            </c:when>
                                        </c:choose>
                                    </select>
                                </div>

                                <!-- Display driver details dynamically -->
                                <div id="driverDetails" style="display:none; border:1px solid #ccc; padding:10px; margin-top:10px;">
                                    <p><strong>Name:</strong> <span id="driverName"></span></p>
                                    <p><strong>License Number:</strong> <span id="licenseNumber"></span></p>
                                </div>


                        <input type="hidden" name="type" value="add"/>
                        <div class="mb-3">
                            <button type="submit" class="btn btn-primary btn-register">Register</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
        <script>
            $(document).ready(function() {
                $("#driverID").change(function() {
                    var driverID = $(this).val();
                    if (driverID) {
                        $.ajax({
                            url: "DriverController",
                            type: "GET",
                            data: { type: "fetchDriver", driverId: driverID },
                            success: function(response) {
                                var driver = JSON.parse(response);
                                if (driver) {
                                    $("#driverName").text(driver.firstName + " " + driver.lastName);
                                    $("#licenseNumber").text(driver.licenseNumber);
                                    $("#driverDetails").show();
                                } else {
                                    $("#driverDetails").hide();
                                }
                            },
                            error: function() {
                                alert("Error fetching driver details.");
                            }
                        });
                    } else {
                        $("#driverDetails").hide();
                    }
                });
            });
        </script>


        <script>
            var message = "<%= request.getAttribute("message") %>";
            if (message) {
                alert(message);
            }
        </script>
    </body>
</html>