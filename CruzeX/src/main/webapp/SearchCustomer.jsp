<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search and Update Customer</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        /* Custom Styling */
        .container-fluid {
            padding: 20px;
        }
        .left-section {
            float: left;
            width: 50%;
            padding-right: 20px;
        }
        .right-section {
            float: right;
            width: 50%;
        }
        .form-container {
            background-color: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>

<div class="container-fluid">
    <!-- Navigation Bar -->
    <ul class="nav justify-content-center bg-dark py-2">
        <li class="nav-item">
            <a class="link-warning nav-link px-5 mx-5" href="AdminHomePage.jsp"> << Go to Home</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="CustomerDashboard.jsp">Customer List</a>
        </li>
        <li class="nav-item">
            <a class="text-white nav-link" href="SearchCustomer.jsp">Search Customer & Update</a>
        </li>
        <li class="nav-item">
            <a class="link-secondary nav-link" href="Register.jsp">Add Customer</a>
        </li>
    </ul>

    <br/>

    <div class="container">
        <div class="row">
            <!-- Search Customer Section -->
            <div class="col-md-6">
                <div class="form-container">
                    <h3>Search Customer</h3>
                    <br/>
                    <form method="get" action="CustomerController?type=specific">
                        <label for="customerID">Enter Customer ID:</label>
                        <input type="text" class="form-control" name="customerID" id="customerID" required>
                        <input type="hidden" name="type" value="specific">
                        <br/>
                        <button type="submit" class="btn btn-info">Search</button>            
                    </form>
                    <br/>
                    <p class="text-danger">${message}</p>
                </div>
            </div>

            <!-- Edit Customer Section -->
            <div class="col-md-6">
                <div class="form-container">
                    <h3>Edit Customer</h3>
                    <br/>
                    <form method="post" action="CustomerController">
                        <label for="customerID">Customer ID:</label>
                        <input type="text" readonly class="form-control" id="customerID" name="customerID" value="${not empty customer ? customer.customerID : ''}"/>
                        <br/>

                        <label for="customerFullName">Full Name:</label>
                        <input type="text" class="form-control" id="customerFullName" name="customerFullName" value="${not empty customer ? customer.customerFullName : ''}" required/>
                        <br/>

                        <label for="customerPhoneNumber">Phone Number:</label>
                        <input type="text" class="form-control" id="customerPhoneNumber" name="customerPhoneNumber" value="${not empty customer ? customer.customerPhoneNumber : ''}" required/>
                        <br/>

                        <label for="dateOfBirth">Date of Birth:</label>
                        <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value="${not empty customer ? customer.dateOfBirth : ''}" required/>
                        <br/>

                        <label for="customerAddress">Address:</label>
                        <input type="text" class="form-control" id="customerAddress" name="customerAddress" value="${not empty customer ? customer.customerAddress : ''}" required/>
                        <br/>

                        <label for="gender">Gender:</label>
                        <select class="form-control" id="gender" name="gender" required>
                            <option value="Male" ${customer.gender == 'Male' ? 'selected' : ''}>Male</option>
                            <option value="Female" ${customer.gender == 'Female' ? 'selected' : ''}>Female</option>
                            <option value="Other" ${customer.gender == 'Other' ? 'selected' : ''}>Other</option>
                        </select>
                        <br/>

                        <label for="customerEmail">Email:</label>
                        <input type="email" class="form-control" id="customerEmail" name="customerEmail" value="${not empty customer ? customer.customerEmail : ''}" required/>
                        <br/>

                        <label for="customerUsername">Username:</label>
                        <input type="text" class="form-control" id="customerUsername" name="customerUsername" value="${not empty customer ? customer.customerUsername : ''}" required/>
                        <br/>

                        <label for="customerPassword">New Password:</label>
                        <input type="password" class="form-control" id="customerPassword" name="customerPassword" placeholder="Enter new password (optional)"/>
                        <br/>

                        <input type="hidden" name="type" value="update">
                        <button type="submit" class="btn btn-warning">Update</button>            
                    </form> 
                </div>
            </div>
        </div>      
    </div>

</div>

<!-- Alert Message -->
<script>
    var message = "<%= request.getAttribute("message") %>";
    if (message) {
        alert(message);
    }
</script>

</body>
</html>