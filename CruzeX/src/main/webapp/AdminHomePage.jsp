<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AdminHomePage</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>


<nav class="navbar navbar-expand-lg navbar-light bg-light" >
  <div class="container-fluid" >
      <img style="border-radius:50%;width: 70px;height: 70px; margin:0 10px 0 50px " alt="" src="img/ima (1).webp">
    <p class="fw-bold fs-4 mt-3" style="color: red;">CruzeX</p>
    <button style="margin:0 30px;" class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup" style="margin:0 50px;">
      <div class="navbar-nav" style="#ffff">
          <a class="nav-link active mx-0 mx-md-3 fs-6" aria-current="page" href="CruzeXHome.jsp">Home</a>
        <a class="nav-link mx-0 mx-md-3  fs-6" href="BookingDashboard.jsp">Manage Booking</a>
        <a class="nav-link mx-0 mx-md-3 fs-6" href="DriverDashboard.jsp">Manage Driver</a>
        <a class="nav-link mx-0 mx-md-3 fs-6" href="VehicleController">Manage Vehicle</a>
      
        <a class="nav-link mx-0 mx-md-3 fs-6" href="CustomerDashboard.jsp">Manage Customer</a>
                
                
        
        
      </div>
      </div>
      </div>
   
</nav>
<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active" style="height: 450px">
        <img src="img/car/Audi (1).jpg" class="d-block w-100" alt="..." style="height: 100%; object-fit:cover; filter: brightness(0.5);">
      <div class="carousel-caption d-none d-md-block top-0 mt-4">
        <h5 class="display-1 fw-bolder text-capitalize">Admin Panel</h5>
        
      </div>
    </div>
   
   
        
      
 </div>
</div>

</body>
</html>