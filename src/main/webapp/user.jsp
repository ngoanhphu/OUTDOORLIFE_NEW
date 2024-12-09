<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>

    <body>
    <div class="container">


        <!-- Carousel Start -->
        <div class="container-fluid carousel-header vh-100 px-0">
            <div id="carouselId" class="carousel slide" data-bs-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-bs-target="#carouselId" data-bs-slide-to="0" class="active"></li>
                    <li data-bs-target="#carouselId" data-bs-slide-to="1"></li>
                    <li data-bs-target="#carouselId" data-bs-slide-to="2"></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="carousel-item active">
                        <img src="img/banner 1.jpg" class="img-fluid" alt="Image">
                        <div class="carousel-caption">
                            <div class="p-3" style="max-width: 900px;">
                                <h4 class="text-white text-uppercase fw-bold mb-4" style="letter-spacing: 3px;">Chill and happy</h4>
                                <h1 class="display-1 text-capitalize text-white mb-4">Outdoor-life</h1>
                                <p class="mb-5 fs-5">Make you happy, make you love the life after work
                                </p>
                                <div class="d-flex align-items-center justify-content-center">
                                    <a class="btn-hover-bg btn btn-primary text-white py-3 px-5" href="#">Join With Us</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="img/banner 2.jpg" class="img-fluid" alt="Image">
                        <div class="carousel-caption">
                            <div class="p-3" style="max-width: 900px;">
                                <h4 class="text-white text-uppercase fw-bold mb-4" style="letter-spacing: 3px;">Chill and happy</h4>
                                <h1 class="display-1 text-capitalize text-white mb-4">Outdoor-life</h1>
                                <p class="mb-5 fs-5">Make you happy, make you love the life after work 
                                </p>
                                <div class="d-flex align-items-center justify-content-center">
                                    <a class="btn-hover-bg btn btn-primary text-white py-3 px-5" href="#">Join With Us</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="img/banner 3.jpg" class="img-fluid" alt="Image">
                        <div class="carousel-caption">
                            <div class="p-3" style="max-width: 900px;">
                                <h4 class="text-white text-uppercase fw-bold mb-4" style="letter-spacing: 3px;">Chill and happy</h4>
                                <h1 class="display-1 text-capitalize text-white mb-4">Outdoor-life</h1>
                                <p class="mb-5 fs-5">Make you happy, make you love the life after work  
                                </p>
                                <div class="d-flex align-items-center justify-content-center">
                                    <a class="btn-hover-bg btn btn-primary text-white py-3 px-5" href="#">Join With Us</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselId" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselId" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
        <!-- Carousel End -->





        <!-- About Start -->
        <div class="container-fluid about  py-5">
            <div class="container py-5">
                <div class="row g-5">
                    <div class="col-xl-5">
                        <div class="h-100">
                            <img src="img/about-1.jpg" class="img-fluid w-100 h-100" alt="Image">
                        </div>
                    </div>
                    <div class="col-xl-7">
                        <h5 class="text-uppercase text-primary">About Us</h5>
                        <h1 class="mb-4">Our main goal is make you have a chilling time</h1>
                        <p class="fs-5 mb-4">For us, helping you have the best preparation and experience after stressful days is a noble task and a great goal that we aim for. Surely we will bring you the best experience, the best service and the best moments.

                        </p>
                        <div class="tab-class bg-secondary p-4">
                            <ul class="nav d-flex mb-2">
                                <li class="nav-item mb-3">
                                    <a class="d-flex py-2 text-center bg-white active" data-bs-toggle="pill" href="#tab-1">
                                        <span class="text-dark" style="width: 150px;">About</span>
                                    </a>
                                </li>
                                <li class="nav-item mb-3">
                                    <a class="d-flex py-2 mx-3 text-center bg-white" data-bs-toggle="pill" href="#tab-2">
                                        <span class="text-dark" style="width: 150px;">Mission</span>
                                    </a>
                                </li>
                                <li class="nav-item mb-3">
                                    <a class="d-flex py-2 text-center bg-white" data-bs-toggle="pill" href="#tab-3">
                                        <span class="text-dark" style="width: 150px;">Vision</span>
                                    </a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div id="tab-1" class="tab-pane fade show p-0 active">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="d-flex">
                                                <div class="text-start my-auto">
                                                    <h5 class="text-uppercase mb-3"></h5>
                                                    <p class="mb-4">we are students from FPT. We want to build this website to help every body at Da Nang can choose the campsite to help them relax after tired times. 
                                                    </p>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="tab-2" class="tab-pane fade show p-0">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="d-flex">
                                                <div class="text-start my-auto">
                                                    <h5 class="text-uppercase mb-3"></h5>
                                                    <p class="mb-4">help user can view and choose the campsite gear. Bring them the best service in the world and make everybody relax
                                                    </p>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="tab-3" class="tab-pane fade show p-0">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="d-flex">
                                                <div class="text-start my-auto">
                                                    <h5 class="text-uppercase mb-3"></h5>
                                                    <p class="mb-4">with out missin. My team dont want this website just can use at Da Nang. we want it can use large more and more in Viet Nam. To help everyone in Viet Nam can find the campsite they want and relax for their life.
                                                    </p>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- About End -->


        <!-- Services Start -->
        <div class="container-fluid service py-5 bg-light">
            <div class="container py-5">
                <div class="text-center mx-auto pb-5" style="max-width: 800px;">
                    <h5 class="text-uppercase text-primary">CAMPSITE</h5>
                    <h1 class="mb-0">topographic</h1>
                </div>
                <div class="row g-4">
                    <div class="col-md-6 col-lg-6 col-xl-4">
                        <div class="service-item">
                            <img src="img/diahinh1.jpg" class="img-fluid w-100" alt="Image">
                            <div class="service-link">
                                <a href="form1.jsp" class="h4 mb-0">River</a>
                            </div>
                        </div>
                        <p class="my-4">Campsites around the river. Make you have more and more the experience with your friends or family. Cool river bring you good relax
                        </p>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-4">
                        <div class="service-item">
                            <img src="img/diahinh2.jpg" class="img-fluid w-100" alt="Image">
                            <div class="service-link">
                                <a href="form2.jsp" class="h4 mb-0"> Beach</a>
                            </div>
                        </div>
                        <p class="my-4">A place where you can immerse yourself in the sea water. There are rich seafood and interesting entertainment services such as boating and beach volleyball
                        </p>
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-4">
                        <div class="service-item">
                            <img src="img/diahinh3.jpg" class="img-fluid w-100" alt="Image">
                            <div class="service-link">
                                <a href="form3.jsp" class="h4 mb-0">Mountain</a>
                            </div>
                        </div>
                        <p class="my-4">You can enjoy the atmosphere away from the city at an altitude of a few hundred meters. You can grill meat or organize parties among the mountains without worrying about being disturbed
                        </p>
                        <button class="button">
                            <span>Add to cart</span>
                            <svg fill="#fff" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><g stroke-width="0" id="SVGRepo_bgCarrier"></g><g stroke-linejoin="round" stroke-linecap="round" id="SVGRepo_tracerCarrier"></g><g id="SVGRepo_iconCarrier"> <defs>  </defs> <g id="cart"> <circle r="1.91" cy="20.59" cx="10.07" class="cls-1"></circle> <circle r="1.91" cy="20.59" cx="18.66" class="cls-1"></circle> <path d="M.52,1.5H3.18a2.87,2.87,0,0,1,2.74,2L9.11,13.91H8.64A2.39,2.39,0,0,0,6.25,16.3h0a2.39,2.39,0,0,0,2.39,2.38h10" class="cls-1"></path> <polyline points="7.21 5.32 22.48 5.32 22.48 7.23 20.57 13.91 9.11 13.91" class="cls-1"></polyline> </g> </g></svg>
                        </button>
                    </div>



                </div>
            </div>
        </div>
        <!-- Services End -->


        <!-- Donation Start -->
        <div class="container-fluid donation py-5">
            <div class="container py-5">
                <div class="text-center mx-auto pb-5" style="max-width: 800px;">
                    <h5 class="text-uppercase text-primary">Camping gear</h5>
                    <h1 class="mb-0">Choose your gear </h1>
                </div>
                <div class="row g-6">
                    <div class="col-lg-6">
                        <div class="donation-item">
                            <img src="img/leu1.jpg" class="img-fluid w-100" alt="Image">
                            <div class="donation-content d-flex flex-column">
                                <h5 class="text-uppercase text-primary mb-4">Tent</h5>
                                <p class="text-white mb-4">use the tent for sleep or to do a storage if you want</p>
                                <div class="donation-btn d-flex align-items-center justify-content-start">
                                    <a class="btn-hover-bg btn btn-primary text-white py-2 px-4" href="#">View</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="donation-item">
                            <img src="img/leu2.jpg" class="img-fluid w-100" alt="Image">
                            <div class="donation-content d-flex flex-column">
                                <h5 class="text-uppercase text-primary mb-4">Gear</h5>
                                <p class="text-white mb-4">Gear can help you to easy have the camping day</p>
                                <div class="donation-btn d-flex align-items-center justify-content-start">
                                    <a class="btn-hover-bg btn btn-primary text-white py-2 px-4" href="#">View</a>
                                </div>  
                            </div>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="d-flex align-items-center justify-content-center">
                            <a class="btn-hover-bg btn btn-primary text-white py-2 px-4" href="#">View all camping gear</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Donation End -->

        <!-- Blog Start -->
        <div class="container-fluid blog py-5 mb-5">
            <div class="container py-5">
                <div class="text-center mx-auto pb-5" style="max-width: 800px;">
                    <h5 class="text-uppercase text-primary">Recommended campsite</h5>
                    <h1 class="mb-0">Highest score campsite user vote
                    </h1>
                </div>
                <div class="row g-4">
                    <div class="col-lg-6 col-xl-3">
                        <div class="blog-item">
                            <div class="blog-img">
                                <img src="img/dexuat1.jpg" class="img-fluid w-100" alt="">
                                <div class="blog-info">
                                    <div class="d-flex">
                                        <span class="me-3"> 100 <i class="fa fa-heart"></i></span>
                                        <a href="#" class="text-white">1 <i class="fa fa-comment"></i></a>
                                    </div>
                                </div>
                                <div class="search-icon">
                                    <a href="dexuat1.jpg" data-lightbox="Blog-1" class="my-auto"><i class="fas fa-search-plus btn-primary text-white p-3"></i></a>
                                </div>
                            </div>
                            <div class="text-dark border p-4 ">
                                <h4 class="mb-4">Roc rach glamping</h4>
                                <p class="mb-4">chill chill chill chill chill chill chill chill chill chill chill chill chill chill chill chill chill chill chill chill chill chill chill chill</p>
                                <a class="btn-hover-bg btn btn-primary text-white py-2 px-4" href="#">Read More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-xl-3">
                        <div class="blog-item">
                            <div class="blog-img">
                                <img src="img/dexuat2.jpg" class="img-fluid w-100" alt="">
                                <div class="blog-info">
                                    <div class="d-flex">
                                        <span class="me-3"> 100 <i class="fa fa-heart"></i></span>
                                        <a href="#" class="text-white">0 <i class="fa fa-comment"></i></a>
                                    </div>
                                </div>
                                <div class="search-icon">
                                    <a href="img/dexuat2.jpg" data-lightbox="Blog-2" class="my-auto"><i class="fas fa-search-plus btn-primary text-white p-3"></i></a>
                                </div>
                            </div>
                            <div class="text-dark border p-4 ">
                                <h4 class="mb-4">Lang van</h4>
                                <p class="mb-4">chill chill chill chill chill chill chill chillchill chill chill chill chill chill chill chillchill chill chill chill chill chill chill chill</p>
                                <a class="btn-hover-bg btn btn-primary text-white py-2 px-4" href="#">Read More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-xl-3">
                        <div class="blog-item">
                            <div class="blog-img">
                                <img src="img/dexuat3.jpg" class="img-fluid w-100" alt="">
                                <div class="blog-info">
                                    <div class="d-flex">
                                        <span class="me-3"> 100 <i class="fa fa-heart"></i></span>
                                        <a href="#" class="text-white">0 <i class="fa fa-comment"></i></a>
                                    </div>
                                </div>
                                <div class="search-icon">
                                    <a href="img/dexuat3.jpg" data-lightbox="Blog-3" class="my-auto"><i class="fas fa-search-plus btn-primary text-white p-3"></i></a>
                                </div>
                            </div>
                            <div class="text-dark border p-4 ">
                                <h4 class="mb-4">Hoa bac</h4>
                                <p class="mb-4">chill chill chill chill chill chill chill chillchill chill chill chill chill chill chill chill</p>
                                <a class="btn-hover-bg btn btn-primary text-white py-2 px-4" href="#">Read More</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-xl-3">
                        <div class="blog-item">
                            <div class="blog-img">
                                <img src="img/dexuat4.jpg" class="img-fluid w-100" alt="">
                                <div class="blog-info">
                                    <div class="d-flex">
                                        <span class="me-3"> 100 <i class="fa fa-heart"></i></span>
                                        <a href="#" class="text-white">0 <i class="fa fa-comment"></i></a>
                                    </div>
                                </div>
                                <div class="search-icon">
                                    <a href="img/dexuat4.jpg" data-lightbox="Blog-4" class="my-auto"><i class="fas fa-search-plus btn-primary text-white p-3"></i></a>
                                </div>
                            </div>
                            <div class="text-dark border p-4 ">
                                <h4 class="mb-4">Da lat</h4>
                                <p class="mb-4">chill chill chill chill chill chill chill chill chill chill chill chill chill chill chill chill</p>
                                <a class="btn-hover-bg btn btn-primary text-white py-2 px-4" href="#">Read More</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Blog End -->





        <!-- Copyright Start -->
        <div class="container-fluid copyright py-4">
            <div class="container">
                <div class="row g-4 align-items-center">
                    <div class="col-md-4 text-center text-md-start mb-md-0">
                        <span class="text-body"><a href="#"><i class="fas fa-copyright text-light me-2"></i>Outdoor-Life</a></span>
                    </div>
                    <div class="col-md-4 text-center">
                        <div class="d-flex align-items-center justify-content-center">
                            <a href="@@@@@" class="btn-hover-color btn-square text-white me-2"><i class="fab fa-facebook-f"></i></a>
                            <a href="@@@@@@" class="btn-hover-color btn-square text-white me-2"><i class="fab fa-twitter"></i></a>
                            <a href="@@@@@" class="btn-hover-color btn-square text-white me-2"><i class="fab fa-instagram"></i></a>
                            <a href="@@@@@" class="btn-hover-color btn-square text-white me-2"><i class="fab fa-pinterest"></i></a>
                            <a href="@@@@@" class="btn-hover-color btn-square text-white me-0"><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Copyright End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-primary btn-primary-outline-0 btn-md-square back-to-top"><i class="fa fa-arrow-up"></i></a>   

         <!-- modal start -->
        <div class="modal fade" id="orderModal" tabindex="-1" aria-labelledby="orderModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="orderModalLabel">Choose Order Option</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p>Choose where you want to go:</p>
                        <div class="d-grid gap-2">
                            <a href="orders.jsp" class="btn btn-primary">Gear Order</a>
                            <a href="orderscamp.jsp" class="btn btn-primary">Camp Order</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- modal End -->

<!--         JavaScript Libraries
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/counterup/counterup.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="lib/lightbox/js/lightbox.min.js"></script>


         Template Javascript
        <script src="js/main.js"></script>-->
    <script>
                                function openOrderPopup() {
                                    var modal = new bootstrap.Modal(document.getElementById('orderModal'), {
                                        keyboard: false
                                    });
                                    modal.show();
                                }
    </script>
    </body>

<!--</html>-->