<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>


<nav class="navbar navbar-expand-lg navbar-light bg-light" >
  <div class="container-fluid" >
      <img style="border-radius:50%;width: 70px;height: 70px; margin:0 10px 0 50px " alt="" src="img/ima (1).webp">
    <p class="fw-bold fs-4 mt-3" style="color: black;">CruzeX</p>
    <button style="margin:0 30px;" class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup" style="margin:0 50px;">
      <div class="navbar-nav" style="#ffff">
          <a class="nav-link active mx-0 mx-md-3 fs-6" aria-current="page" href="CruzeXHome.jsp">Home</a>
          <a class="nav-link mx-0 mx-md-3 fs-6" href="Add-Booking.jsp">Add Booking</a>
        <a class="nav-link mx-0 mx-md-3 fs-6" href="PaymentDashboard.jsp">Make Payment</a>
        <a class="nav-link mx-0 mx-md-3 fs-6" href="VehicleController">View Vehicle</a>
        
        
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
        <img src="img/ima 1.jpg" class="d-block w-100" alt="..." style="height: 100%; object-fit:cover; filter: brightness(0.5);">
      <div class="carousel-caption d-none d-md-block top-0 mt-4">
       
      </div>
    </div>
    <div class="carousel-item" style="height: 450px">
        <img src="img/ima (4).jpg" class="d-block w-100" alt="..." style="height: 100%; object-fit:cover; filter: brightness(0.6);">
      <div class="carousel-caption d-none d-md-block top-0 mt-4">
        
      </div>
    </div>
    <div class="carousel-item" style="height: 450px">
        <img src="img/car/Audi (5).jpg" class="d-block w-100" alt="..." style="height: 100%; object-fit:cover; filter: brightness(0.6);">
      <div class="carousel-caption d-none d-md-block top-0 mt-4">
        
      </div>
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>


















  <div class="row g-0 bg-light position-relative p-5">
    <h5 class="mt-2 mb-3 fs-2">Ask Your Queries</h5>
    <div class="mb-3">
  		<label class="form-label">your name</label>
  		<input type="name" class="form-control" id="exampleFormControlInput1">
	</div>
	<div class="mb-3">
  		<label for="exampleFormControlInput1" class="form-label">Email address</label>
  		<input type="email" class="form-control" id="exampleFormControlInput1" placeholder="name@example.com">
	</div>
	<div class="mb-3">
  		<label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>
  		<textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
	</div>
   
  </div>





</body>
</html>