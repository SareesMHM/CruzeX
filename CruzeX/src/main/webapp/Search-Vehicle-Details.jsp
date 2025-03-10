<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search and Update Vehicle</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <!-- Navigation Bar -->
    <ul class="nav justify-content-center bg-dark py-2">
        <li class="nav-item">
            <a class="link-warning nav-link px-5 mx-5" href="AdminHomePage.jsp"><< Go to Home</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="VehicleDashboard.jsp">Store</a>
        </li>
        <li class="nav-item">
            <a class="text-white nav-link" href="Search-Vehicle-Details.jsp">Search Vehicle & Update</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Add-Vehicle-Details.jsp">Add</a>
        </li>
    </ul>

    <div class="container mt-5">
        <div class="row">
            <!-- Search Section -->
            <div class="col-md-6">
                <h3>Search Vehicle</h3>
                <form method="get" action="VehicleController">
                    <div class="mb-3">
                        <label for="vehicleId">Enter Vehicle ID:</label>
                        <input type="text" class="form-control" id="vehicleId" name="vehicleId" required>
                        <input type="hidden" name="type" value="getSpecificVehicle">
                    </div>
                    <button type="submit" class="btn btn-info">Search</button>
                </form>
                <br/>
                <p class="text-muted">${searchResult}</p>
            </div>

            <!-- Edit Section -->
            <div class="col-md-6">
                <h3>Edit Vehicle</h3>
                <form method="post" action="VehicleController">
                    <div class="mb-3">
                        <label for="vehicleId">Vehicle ID:</label>
                        <input type="text" readonly class="form-control" id="vehicleId" name="vehicleId" 
                               value="${vehicle != null ? vehicle.vehicleID : ''}"/>
                    </div>

                    <div class="mb-3">
                        <label for="vehicleName">Vehicle Name:</label>
                        <input type="text" class="form-control" id="vehicleName" name="vehicleName" 
                               value="${vehicle != null ? vehicle.vehicleName : ''}"/>
                    </div>

                    <div class="mb-3">
                        <label for="category">Category:</label>
                        <input type="text" class="form-control" id="category" name="category" 
                               value="${vehicle != null ? vehicle.category : ''}"/>
                    </div>

                    <div class="mb-3">
                        <label for="image">Image URL:</label>
                        <input type="file" class="form-control" id="image" name="image" 
                               value="${vehicle != null ? vehicle.image : ''}"/>
                    </div>

                    <div class="mb-3">
                        <label for="monthFee">Per 1Km:</label>
                        <input type="number" class="form-control" id="monthFee" name="monthFee" 
                               value="${vehicle != null ? vehicle.monthFee : ''}"/>
                    </div>

                    <div class="mb-3">
                        <label for="driverID">Driver ID:</label>
                        <input type="number" class="form-control" id="driverID" name="driverID" 
                               value="${vehicle != null ? vehicle.driverID : ''}"/>
                    </div>

                    <input type="hidden" name="type" value="updateVehicle">
                    <button type="submit" class="btn btn-warning">Update</button>
                    
                </form>
            </div>
        </div>
    </div>

    <!-- Show Message Alert -->
    <%
        String message = (String) request.getAttribute("message");
        if (message != null && !message.trim().isEmpty()) {
    %>
        <script>
            alert("<%= message %>");
        </script>
    <%
        }
    %>
</div>
</body>
</html>