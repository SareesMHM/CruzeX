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
      <img style="border-radius:50%;width: 70px;height: 70px; margin:0 10px 0 50px " alt="" src="img/x (4).webp">
    <p class="fw-bold fs-4 mt-3" style="color: black;">MCC</p>
    <button style="margin:0 30px;" class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup" style="margin:0 50px;">
      <div class="navbar-nav" style="#ffff">
          <a class="nav-link active mx-0 mx-md-3 fs-6" aria-current="page" href="CruzeXHome.jsp">Home</a>
          <a class="nav-link mx-0 mx-md-3 fs-6" href="Add-Booking.jsp">Add Booking</a>
        <a class="nav-link mx-0 mx-md-3 fs-6" href="PaymentDashboard.jsp">Make Payment</a>
        <a class="nav-link mx-0 mx-md-3 fs-6" href="VehicleController?type=customer">View Vehicle</a>
        <a class="nav-link mx-0 mx-md-3 fs-6" href="QueryForm.jsp">Ask Your Queries</a>
        
        
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









<style>
/* Navbar Styling */
.navbar {
    background-color: #fff;
    box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    padding: 0.75rem 1.5rem;
}

.navbar img {
    height: 60px;
    width: 60px;
    object-fit: cover;
    border-radius: 50%;
    margin-right: 15px;
}

.navbar .navbar-nav .nav-link {
    font-weight: 500;
    color: #333;
    transition: all 0.3s ease-in-out;
}

.navbar .navbar-nav .nav-link:hover,
.navbar .navbar-nav .nav-link.active {
    color: #dc3545; /* Bootstrap's red */
    font-weight: 600;
}

/* Brand name */
.navbar p {
    font-size: 1.8rem;
    color: #dc3545;
    margin-bottom: 0;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

/* Carousel */
.carousel-item {
    position: relative;
    height: 450px;
    overflow: hidden;
}

.carousel-item img {
    object-fit: cover;
    width: 100%;
    height: 100%;
    filter: brightness(0.4);
}

.carousel-caption {
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    text-shadow: 2px 2px 5px rgba(0,0,0,0.6);
}

.carousel-caption h5 {
    font-size: 3rem;
    color: white;
    font-weight: bold;
    animation: fadeInUp 1s ease-out;
}

/* Animations */
@keyframes fadeInUp {
  from { opacity: 0; transform: translateY(20px); }
  to   { opacity: 1; transform: translateY(0); }
}

/* Responsive fix */
@media (max-width: 768px) {
    .carousel-caption h5 {
        font-size: 2rem;
    }

    .navbar p {
        font-size: 1.3rem;
    }
}
</style>
</body>
</html>