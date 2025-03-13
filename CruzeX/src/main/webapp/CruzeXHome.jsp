<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Maga City Cab</title>
<link rel="shortcut icon" href="img/ima 3.webp">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<style>
/* Header Styles */
.header-nav {
    background-color: #ffffff;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    position: sticky;
    top: 0;
    z-index: 1000;
}

.navbar-brand img {
    border-radius: 50%;
    width: 50px;
    height: 50px;
    margin-right: 10px;
}

.navbar-brand span {
    font-size: 24px;
    font-weight: initial;
    color: #03509e;
}

.nav-link {
    color: #333 !important;
    font-weight: initial;
    padding: 10px 15px;
    font-size: 16px;
    transition: 0.3s;
}

.nav-link:hover {
    color: #00ab9f !important;
    text-decoration: none;
}

.btn-success {
    background-color: #00ab9f;
    border: none;
    font-weight: initial;
}

.btn-success:hover {
    background-color: #008f86;
    
}
.carousel-caption h5 {
    font-size: 42px ;
    color: #FFFFE4;
    font-weight: initial;
    font-family: serif;
    font-style: normal;
        
        
}


</style>
</head>
<body>

<!-- ################# Header Starts Here ####################### -->
<header class="header-nav">
  <nav class="navbar navbar-expand-lg navbar-light bg-white">
    <div class="container">
      <a class="navbar-brand d-flex align-items-center" href="#">
          <img src="img/x (4).webp" alt="Logo">
        <span>MCC</span>
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNav">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="mainNav">
        <ul class="navbar-nav mx-auto mb-2 mb-lg-0">
          <li class="nav-item"><a class="nav-link" href="#">Home</a></li>
          <li class="nav-item"><a class="nav-link" href="#services">Services</a></li>
          <li class="nav-item"><a class="nav-link" href="#about_us">About Us</a></li>
          <li class="nav-item"><a class="nav-link" href="#gallery">Gallery</a></li>
          <li class="nav-item"><a class="nav-link" href="#logins">Logins</a></li>
        </ul>
        <a class="btn btn-success px-4" href="Login-Customer.jsp">Book</a>
      </div>
    </div>
  </nav>
</header>

              

<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
  <div class="carousel-indicators">
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
    <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
  </div>
  <div class="carousel-inner">
    <div class="carousel-item active" style="height: 450px">
        <img src="img/car/outback.jpg" class="d-block w-100" alt="..." style="height: 100%; object-fit:cover; filter: brightness(0.5);">
      <div class="carousel-caption d-none d-md-block top-0 mt-4">
        
       
            

        
        
      </div>
    </div>
    <div class="carousel-item" style="height: 450px">
        <img src="img/car/Audi (1).jpg" class="d-block w-100" alt="..." style="height: 100%; object-fit:cover; filter: brightness(0.6);">
     
        
        
      </div>
    </div>
    <div class="carousel-item" style="height: 450px">
        <img src="img/car/bmw_x5.jpg" class="d-block w-100" alt="..." style="height: 100%; object-fit:cover; filter: brightness(0.6);">
      
        
        
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
     






 <!-- ################# Our Departments Starts Here#######################--->


    <section id="services" class="key-features department">
        <div class="container">
            <div class="inner-title">

                <h2>Our Key Features</h2>
                <p>Take a look at some of our key features</p>
            </div>

            <div class="row">
                <div class="col-lg-4 col-md-6">
                    <div class="single-key">
                        <i class="fas fa-heartbeat"></i>
                        <img src="img/car/icon/quick.png" width="80" height="80">
                        <h5>Fast & Reliable</h5>
                    </div>
                </div>

                <div class="col-lg-4 col-md-6">
                    <div class="single-key">
                        <i class="fas fa-ribbon"></i>
                        <img src="img/car/icon/encrypted.png" width="80" height="80">
                        <h5>Secure & Safe</h5>
                    </div>
                </div>

                <div class="col-lg-4 col-md-6">
                    <div class="single-key">
                       <i class="fab fa-monero"></i>
                       <img src="img/car/icon/high-five.png" width="80" height="80">
                        <h5>User-Friendly</h5>
                    </div>
                </div>

                <div class="col-lg-4 col-md-6">
                    <div class="single-key">
                        <i class="fas fa-capsules"></i>
                        <img src="img/car/icon/left-and-right-arrows.png" width="80" height="80">
                        <h5>Scalable & Efficient</h5>
                    </div>
                </div>

                <div class="col-lg-4 col-md-6">
                    <div class="single-key">
                        <i class="fas fa-prescription-bottle-alt"></i>
                        <img src="img/car/icon/booking.png" width="80" height="80">
                        <h5>Online Cab Booking</h5>
                    </div>
                </div>



                <div class="col-lg-4 col-md-6">
                    <div class="single-key">
                        <i class="far fa-thumbs-up"></i>
                        <img src="img/car/icon/customer-support.png" width="80" height="80">
                        <h5>User Support & Assistance</h5>

                    </div>
                </div>
            </div>






        </div>

    </section>
 <style>/* ===================================== Key Features CSS ================================== */
.inner-title {
    text-align: center;
    margin-bottom: 50px;
}

.inner-title h2 {
    font-size: 36px;
    font-weight: initial;
    color: #03509e;
    margin-bottom: 10px;
}

.inner-title p {
    font-size: 16px;
    color: #666;
}
     .key-features .kvxol {
  padding: 0px; }
.key-features .single-key {
  background-color: #FFF;
  margin-bottom: 30px;
  text-align: center;
  padding: 30px; }
  .key-features .single-key i {
    background-color: #FFF;
    padding: 20px;
    font-size: 42px;
    color: #00ab9f;
    margin-bottom: 30px;
    border-radius: 50px; }
  .key-features .single-key p {
    font-family: "mouse-300", Arial, Helvetica, sans-serif;
    font-size: 15px; }
.key-features .ky-1 {
  background-color: #03509e;
  color: #FFF;
  margin-bottom: 0px; }
.key-features .ky-2 {
  background-color: #0085bc;
  color: #FFF;
  margin-bottom: 0px; }

.department {
  padding: 80px 50px;
  background-color: #FFF; }
  @media screen and (max-width: 940px) {
    .department {
      padding: 50px 10px; } }
  header {
  box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
  position: relative;
  width: 100%;
  z-index: 999; }
  header .header-nav {
    background-color: #FFF;
    width: 100%; }
    header .header-nav .nav-item ul li {
      float: left;
      font-family: "mouse-500", Arial, Helvetica, sans-serif;
      font-size: 16px;
      padding: 28px; }
      @media screen and (max-width: 998px) {
        header .header-nav .nav-item ul li {
          padding: 30px 18px; } }
      @media screen and (max-width: 767px) {
        header .header-nav .nav-item ul li {
          float: none;
          padding: 14px;
          border-top: 1px solid #CCC; } }
    header .header-nav .nav-img img {
      width: 200px;
      padding: 0px;
      margin-top: 21px; }
      @media screen and (max-width: 767px) {
        header .header-nav .nav-img img {
          margin-top: 0px;
          padding: 10px; } }
      @media screen and (max-width: 1199px) {
        header .header-nav .nav-img img {
          margin-left: 10px; } }
    header .header-nav .appoint {
      padding-top: 21px; }
  header .scroll-to-fixed-fixed {
    background-color: #FFF;
    box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12); }

.small-menu {
  float: right;
  color: #00ab9f;
  font-size: 32px;
  margin-top: 17px;
  margin-right: 12px; }
  @media screen and (max-width: 767px) {
    .small-menu {
      margin-top: 11px; } }
</style>




<!--  ************************* About Us Starts Here ************************** -->
<section id="about_us" class="about-us py-5">
    <div class="container">
        <div class="section-title text-center mb-5">
            <h2>About Us</h2>
        </div>
        <div class="row align-items-center">
            <div class="col-sm-6 image-bg">
                <!-- Background image via CSS -->
            </div>
            <div class="col-sm-6 abut-yoiu text-center">
                <h3>About Our Cab</h3>
                   <p class="mt-3 px-3">
                    At Maga City Cab, we are revolutionizing the way people experience transportation. As a cutting-edge vehicle
                    reservation and management platform, we aim to streamline the booking process for both customers and
                    service providers, ensuring a seamless and efficient journey from start to finish.<br><br>

                    With a commitment to innovation and user-friendly technology, Maga City Cab offers a robust and secure system
                    that enables users to book rides effortlessly while giving service providers powerful tools to manage
                    operations effectively. Our system integrates authentication, booking management, billing, and vehicle
                    tracking into a single, easy-to-use platform.<br><br>

                    Driven by a passion for efficiency and customer satisfaction, Maga City Cab is dedicated to making travel more
                    convenient, reliable, and accessible for everyone. Whether you?re a daily commuter, a business traveler,
                    or a fleet operator, Maga City Cab is here to transform your transportation experience.<br><br>

                    <strong>Your journey, simplified.</strong>
                </p>
            </div>
        </div>
    </div>
</section>

<style>
/* ====================================== About Us Style ===================================== */
.about-us .section-title h2 {
    font-size: 36px;
    font-weight: initial;
    color: #03509e;
    margin-bottom: 30px;
}

.about-us .image-bg {
    background-image: url("img/xxx.webp");
    background-size: cover;
    background-position: center;
    height: 100%;
    min-height: 600px;
}

.about-us .abut-yoiu {
    padding: 30px;
    background-color: #FFF;
    min-height: 400px;
}

.about-us h3 {
    font-size: 28px;
    font-weight: lighter;
    color: #00ab9f;
    margin-bottom: 20px;
}
</style>

     <!--  ************************* Gallery Starts Here ************************** -->
        <div id="gallery" class="gallery">    
           <div class="container">
              <div class="inner-title">

                <h2>Our Gallery</h2>
                <p>View Our Gallery</p>
            </div>
              <div class="row">
                

        <div class="gallery-filter d-none d-sm-block">
            <button class="btn btn-default filter-button" data-filter="all">All</button>
            <button class="btn btn-default filter-button" data-filter="hdpe">CAR</button>
            <button class="btn btn-default filter-button" data-filter="sprinkle">VAN</button>
            <button class="btn btn-default filter-button" data-filter="spray"> SUV</button>
            <button class="btn btn-default filter-button" data-filter="irrigation">BUS</button>
        </div>
        <br/>



            <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
                <img src="img/car/gallery/BMW (1).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
                <img src="img/car/gallery/BMW (2).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
                <img src="img/car/gallery/BMW (3).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
                <img src="img/car/gallery/BMW (4).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
                <img src="img/car/gallery/BMW (5).jpg" class="img-responsive">
            </div>

            <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter sprinkle">
                <img src="img/car/gallery/VAN  (1).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter sprinkle">
                <img src="img/car/gallery/VAN  (2).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter sprinkle">
                <img src="img/car/gallery/VAN  (3).jpg" class="img-responsive">
            </div>

            

            <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter irrigation">
                <img src="img/car/gallery/BUS (1).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter irrigation">
                <img src="img/car/gallery/BUS (2).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter irrigation">
                <img src="img/car/gallery/BUS (3).jpg" class="img-responsive">
            </div>

            
          

            <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter spray">
                <img src="img/car/gallery/SUV (1).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter spray">
                <img src="img/car/gallery/SUV (2).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter spray">
                <img src="img/car/gallery/SUV (3).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter spray">
                <img src="img/car/gallery/SUV (4).jpg" class="img-responsive">
            </div>

        </div>
    </div>
       
       
       </div>
     <style>/* ===================================== Gallery Style  ================================== */
.gallery-filter {
  width: 100%;
  text-align: center; }
  .gallery-filter .btn {
    background-color: #FFF; }

.gallery {
  padding: 80px 50px;
  background-color: #FAFAFA; }
  @media screen and (max-width: 940px) {
    .gallery {
      padding: 50px 10px; } }

.gallery-title {
  font-size: 36px;
  color: #00ab9f;
  text-align: center;
  font-weight: 500;
  margin-bottom: 70px; }

.gallery-title:after {
  content: "";
  position: absolute;
  width: 7.5%;
  left: 46.5%;
  height: 45px;
  border-bottom: 1px solid #00ab9f; }

.filter-button {
  font-size: 18px;
  border: 1px solid #00ab9f;
  border-radius: 5px;
  text-align: center;
  color: #00ab9f;
  margin-bottom: 30px; }

.filter-button:hover {
  font-size: 18px;
  border: 1px solid #00ab9f;
  border-radius: 5px;
  text-align: center;
  color: #ffffff;
  background-color: #00ab9f; }

.btn-default:active .filter-button:active {
  background-color: #00ab9f;
  color: white; }

.port-image {
  width: 100%; }

.gallery_product {
  margin-bottom: 30px; }</style>
     <!-- JavaScript for Filter Buttons -->
<script>
    document.addEventListener("DOMContentLoaded", function() {
        // Show all images on page load
        document.querySelectorAll(".gallery_product").forEach(item => item.style.display = "block");

        document.querySelectorAll(".filter-button").forEach(button => {
            button.addEventListener("click", function() {
                // Remove 'active' class from all buttons and add to the clicked one
                document.querySelectorAll(".filter-button").forEach(btn => btn.classList.remove("active"));
                this.classList.add("active");

                // Get filter category
                let filter = this.getAttribute("data-filter");

                // Show/hide images based on the filter
                document.querySelectorAll(".gallery_product").forEach(item => {
                    if (filter === "all") {
                        item.style.display = "block";
                    } else if (item.classList.contains(filter)) {
                        item.style.display = "block";
                    } else {
                        item.style.display = "none";
                    }
                });
            });
        });
    });
</script>
        <!-- ######## Gallery End ####### -->
        


</body>
</html>