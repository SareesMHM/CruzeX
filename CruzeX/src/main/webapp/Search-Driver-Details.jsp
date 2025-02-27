<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <meta charset="UTF-8">
    <title>Search and Update Driver</title>
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
                <a class="link-secondary  nav-link " aria-current="page" href="DeiverDashboard.jsp">Store</a>
            </li>
            <li class="nav-item">
                <a class="text-white nav-link " href="Search-Driver-Details.jsp">Search Driver & Update</a>
            </li>
            <li class="nav-item">
                <a class="link-secondary nav-link" href="Add-Driver-Details.jsp">Add</a>
            </li>
        </ul>
        <br/>
        <br/>
        <!-- Main Content -->
        <div class="container">
            <div class="left-section">
                <!-- Search Driver Section -->
                <h3>Search Driver</h3>
                <br/>
                <form method="get" action="driverController">
                    Enter Driver ID: <input type="text" name="driverId">
                    <input type="hidden" name="type" value="specific">
                    <button type="submit" class="btn btn-info">Search</button>            
                </form>
                <br/>
                <p>${searchResult}</p>
            </div>
            <div class="right-section">
                <!-- Edit Driver Section -->
                <h3>Edit Driver</h3>
                <br/>
                <form method="post" action="driverController">
                    <label for="driverId">Driver ID:</label>
                    <input type="text" readonly class="form-control" id="driverId" name="driverId" value="${not empty driver ? driver.driverID : ''}"/>
                    <br/>
                    <label for="firstName">First Name:</label>
                    <input type="text" class="form-control" id="firstName" name="firstName"  value="${not empty driver ? driver.firstName : ''}"/>
                    <br/>
                    <label for="lastName">Last Name:</label>
                    <input type="text" class="form-control" id="lastName" name="lastName"  value="${not empty driver ? driver.lastName : ''}"/>
                    <br/>
                    <label for="licenseNumber">License Number:</label>
                    <input type="text" class="form-control" id="licenseNumber" name="licenseNumber"  value="${not empty driver ? driver.licenseNumber : ''}"/>
                    <br/>
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email" name="email"  value="${not empty driver ? driver.email : ''}"/>
                    <input type="hidden" name="type" value="update"/>
                    <br/>
                    <button type="submit" class="btn btn-warning">Update</button>            
                </form> 
            </div>
        </div>      
    </div>
</body>
</html>
