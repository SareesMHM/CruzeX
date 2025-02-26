<%-- 
    Document   : Add-Car
    Created on : Feb 25, 2025, 3:55:39 PM
    Author     : MHM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add-vehicle</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        body {
              background-image: url('img/ima (1).webp'); 
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
            <a class="link-secondary  nav-link " aria-current="page" href="DoctorDashboard">Store</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link " href="Search-Doctor-Details.jsp">Search vehicle & Update</a>
        </li>
        <li class="nav-item">
            <a class="text-white nav-link" href="Add-Doctor-Details.jsp">Add</a>
        </li>
    </ul>
    <br/>
    <br/>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="form-container">
                    <h3 class="text-center mb-4">Register vehicle</h3>
                    <form method="post" action="doctorController">
                        <div class="mb-3">
                            <label for="firstName" class="form-label">vehicle Name:</label>
                            <input type="text" class="form-control" id="firstName" name="firstName"/>
                        </div>
                       
                        
                      
                        <div class="mb-3">
                                                        <label for="image">Image</label>
                                                        <input type="file" id="image" name="image" >
                                                    </div>
                        <div class="mb-3">
                                                        <label for="category">Category</label>
                                                        <select id="category" name="category" >
                                                            <option value="Collected Items">Car</option>
                                                            <option value="Recycling Items">Van</option>
                                                            <option value="Recycling Items">Bus</option>
                                                            <option value="Recycling Items">SUV</option>
                                                        </select>
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
    </body>
</html>
