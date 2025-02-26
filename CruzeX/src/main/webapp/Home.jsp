<%-- 
    Document   : Home
    Created on : Feb 25, 2025, 12:12:56 PM
    Author     : MHM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CruzeX</title>
        <link rel="shortcut icon" href="img/ima (1).webp">
        <link rel="Stylesheet" href="img/bootstrap.min.css.map">
        <link rel="Stylesheet" href="img/bootstrap.min.css">
        <link rel="Stylesheet" href="img/animate.css">
        <link rel="Stylesheet" href="img/fontawsom-all.min.css">
        <link rel="Stylesheet" href="img/style.css">
        
    </head>
    
    <body>
        <header id="menu-jk">
    
        <div id="nav-head" class="header-nav">
            <div class="container">
                <div class="row">
                    <div class="col-lg-2 col-md-3  col-sm-12" style="color:#000;font-weight:bold; font-size:42px; margin-top: 1% !important;">
                       <a data-toggle="collapse" data-target="#menu" href="#menu" ><i class="fas d-block d-md-none small-menu fa-bars"></i></a>
                    </div>
                    <div id="menu" class="col-lg-8 col-md-9 d-none d-md-block nav-item">
                        <ul>
                            <li><a href="#">Home</a></li>
                            <li><a href="#services">Services</a></li>
                            <li><a href="#about_us">About Us</a></li>
                            <li><a href="#gallery">Gallery</a></li>
                            <li><a href="#contact_us">Contact Us</a></li>
                            <li><a href="#logins">Logins</a></li>  
							
                        </ul>
                    </div>
                    <div class="col-sm-2 d-none d-lg-block appoint">
                        <a class="btn btn-success" href="hms/user-login.php">Book</a>
                    </div>
                </div>

            </div>
        </div>
    </header>
         <div class="slider-detail">

        <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            </ol>
 <div class="carousel-inner">
                <div class="carousel-item ">
                    <img class="d-block w-100" src="img/ima (4).jpg" alt="Second slide">
                    <div class="carousel-cover"></div>
                    <div class="carousel-caption vdg-cur d-none d-md-block">
                        <h5 class="animated bounceInDown">Vehicle reservation System</h5>
</div>
                </div>
                
                <div class="carousel-item active">
                    <img class="d-block w-100" src="img/ima 1.jpg" alt="Third slide">
                      <div class="carousel-cover"></div>
                    <div class="carousel-caption vdg-cur d-none d-md-block">
                        <h5 class="animated bounceInDown">Vehicle reservation System</h5>
</div>
              
                </div>
                
            </div>
            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>


    </div>
         <!--  ************************* Logins ************************** -->
		
    
    
     <section id="logins" class="our-blog container-fluid">
        <div class="container">
        <div class="inner-title">
<h2>Logins</h2>
            </div>
            <div class="col-sm-12 blog-cont">
                <div class="row no-margin">
                    <div class="col-sm-4 blog-smk">
                        <div class="blog-single">
 <img src="assets/images/customer.jpg" alt="">

                            <div class="blog-single-det">
                                <h6>Customer Login</h6>
                                <a href="cineliex/user-login.php" target="_blank">
                                    <button class="btn btn-success btn-sm">Click Here</button>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4 blog-smk">
                        <div class="blog-single">

                                <img src="assets/images/staff.png" alt="">

                            <div class="blog-single-det">
                                <h6>Staff login</h6>
                                <a href="cineliex/dashboard.php" target="_blank">
                                    <button class="btn btn-success btn-sm">Click Here</button>
                                </a>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-sm-4 blog-smk">
                        <div class="blog-single">

                                <img src="assets/images/admin.png" alt="">

                            <div class="blog-single-det">
                                <h6>Admin Login</h6>
                    
                                <a href="cineliex/admin/index.php" target="_blank">
                                    <button class="btn btn-success btn-sm">Click Here</button>
                                </a>
                            </div>
                        </div>
                    </div>
                    
                    
                    

                    
                    
                </div>
            </div>
            
        </div>
    </section>  
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
            <button class="btn btn-default filter-button" data-filter="hdpe">Audi</button>
            <button class="btn btn-default filter-button" data-filter="sprinkle">BMW</button>
            <button class="btn btn-default filter-button" data-filter="spray"> Events</button>
            <button class="btn btn-default filter-button" data-filter="irrigation">Parking</button>
			<button class="btn btn-default filter-button" data-filter="ir">Snacks</button>
        </div>
        <br/>



            <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
                <img src="img/car/Audi (1).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
                <img src="img/car/Audi (2).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
                <img src="img/car/Audi (3).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter hdpe">
                <img src="img/car/Audi (4).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter sprinkle">
            <img src="img/car/bmw (1).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter sprinkle">
            <img src="img/car/bmw (2).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter sprinkle">
            <img src="img/car/bmw (3).jpg" class="img-responsive">
            </div>
        <div class="gallery_product col-lg-4 col-md-4 col-sm-4 col-xs-6 filter sprinkle">
            <img src="img/car/bmw (4).jpg" class="img-responsive">
            </div>
				   

            

        </div>
    </div>
       
       
       </div>

    <style>
        /* Your existing styles here */

        #zoomedImage {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.8);
            justify-content: center;
            align-items: center;
        }

        #zoomedImage img {
            max-width: 100%;
            max-height: 100%;
            cursor: zoom-out;
        }
    </style>
</head>
<body>

<!-- Your existing gallery HTML here -->

<div id="zoomedImage"></div>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
    $(document).ready(function () {
        $(".gallery_product img").on("dblclick", function () {
            var imageUrl = $(this).attr("src");
            $("#zoomedImage").html('<img src="' + imageUrl + '" alt="Zoomed Image">').fadeIn();
        });

        $("#zoomedImage").on("click", function () {
            $(this).fadeOut();
        });
    });
</script>
        
        
    </body>
</html>
