<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <title>Search and Update Vehicle</title>
    <style>
        /* Custom Styles */
        .left-section {
            float: left;
            width: 50%;
        }
        .right-section {
            float: right;
            width: 50%;
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <!-- Navigation Bar -->
        <ul class="nav justify-content-center bg-dark py-2">
            <li class="nav-item">
                <a class="link-warning nav-link px-5 mx-5" aria-current="page" href="AdminHomePage.jsp"> << Go to Home</a>
            </li>
            <li class="nav-item">
                <a class="link-secondary nav-link" aria-current="page" href="VehicleDashboard.jsp">Store</a>
            </li>
            <li class="nav-item">
                <a class="text-white nav-link" href="Search-Vehicle-Details.jsp">Search Vehicle & Update</a>
            </li>
            <li class="nav-item">
                <a class="link-secondary nav-link" href="Add-Vehicle-Details.jsp">Add</a>
            </li>
        </ul>
        <br/>
        <br/>
        <!-- Main Content -->
        <div class="container">
            <div class="left-section">
                <!-- Search Vehicle Section -->
                <h3>Search Vehicle</h3>
                <br/>
                <form method="get" action="VehicleController">
                    Enter Vehicle ID: <input type="text" name="vehicleId">
                    <input type="hidden" name="type" value="specific">
                    <button type="submit" class="btn btn-info">Search</button>            
                </form>
                <br/>
                <p>${searchResult}</p>
            </div>
            <div class="right-section">
                <!-- Edit Vehicle Section -->
                <h3>Edit Vehicle</h3>
                <br/>
                <form method="post" action="VehicleController">
                    <label for="vehicleId">Vehicle ID:</label>
                    <input type="text" readonly class="form-control" id="vehicleId" name="vehicleId" value="${not empty vehicle ? vehicle.vehicleId : ''}"/>
                    <br/>
                    <label for="vehicleName">Vehicle Name:</label>
                    <input type="text" class="form-control" id="vehicleName" name="vehicleName" value="${not empty vehicle ? vehicle.vehicleName : ''}"/>
                    <br/>
                    <label for="category">Category:</label>
                    <input type="text" class="form-control" id="category" name="category" value="${not empty vehicle ? vehicle.category : ''}"/>
                    <br/>
                    <label for="image">Image URL:</label>
                    <input type="text" class="form-control" id="image" name="image" value="${not empty vehicle ? vehicle.image : ''}"/>
                    <input type="file" name="type" value="update"/>
                    <br/>
                     <label for="category">Month Fee:</label>
                    <input type="text" class="form-control" id="monthFee" name="monthFee" value="${not empty vehicle ? vehicle.monthFee : ''}"/>
                    <br/>
                    <button type="submit" class="btn btn-warning">Update</button>            
                </form> 
            </div>
        </div>      
    </div>
</body>
</html>
