    <%-- 
    Document   : campinggear
    Created on : Jun 6, 2024, 4:31:09 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<style>
        h1.mb-0 {
            color: green;
        }
    </style>
<div class="container-fluid donation py-5">
    <div class="container py-5">
        <div class="text-center mx-auto pb-5" style="max-width: 800px; margin-top: 50px">
            <h5 class="text-uppercase text-primary">  </h5>
            <h1 class="mb-0">Choose Your Gear</h1>
        </div>
        <div class="row g-6">
            <div class="col-lg-6">
                <div class="donation-item">
                    <img src="img/leu1_1.jpg" class="img-fluid w-100" alt="Lều cắm trại">
                    <div class="donation-content d-flex flex-column">
                        <h5 class="text-uppercase text-primary mb-4">Tent</h5>
                        <p class="text-white mb-4">Use the tent for sleep or to do a storage if you want</p>
                        <div class="donation-btn d-flex align-items-center justify-content-start">
                            <a class="btn-hover-bg btn btn-primary text-white py-2 px-4" href="/login_war/tentcontrol?action=viewtent">View Tent</a>

                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="donation-item">
                    <img src="img/gearalll.jpg" class="img-fluid w-100" alt="Dụng cụ cắm trại">
                    <div class="donation-content d-flex flex-column">
                        <h5 class="text-uppercase text-primary mb-4">Gear</h5>
                        <p class="text-white mb-4">Gear can help you to easy have the camping day</p>
                        <div class="donation-btn d-flex align-items-center justify-content-start">
                            <a class="btn-hover-bg btn btn-primary text-white py-2 px-4" href="/login_war/gearcontroller?action=viewgear">View Gear</a>
                        </div>  
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Copyright Start -->
        <div class="container-fluid copyright py-4">
            <div class="container">
                <div class="row g-4 align-items-center">
                    <div class="col-md-4 text-center text-md-start mb-md-0">
                        <span class="text-body"><a href="#"><i class="fas fa-copyright text-light me-2"></i>Outdoor-Life</a></span>
                    </div>
                    <div class="col-md-4 text-center">
                        <div class="d-flex align-items-center justify-content-center">
                            <a href="https://www.facebook.com/Phuc250703/" class="btn-hover-color btn-square text-white me-2"><i class="fab fa-facebook-f"></i></a>
                            <a href="https://www.facebook.com/Phuc250703/" class="btn-hover-color btn-square text-white me-2"><i class="fab fa-twitter"></i></a>
                            <a href="https://www.facebook.com/Phuc250703/" class="btn-hover-color btn-square text-white me-2"><i class="fab fa-instagram"></i></a>
                            <a href="https://www.facebook.com/Phuc250703/" class="btn-hover-color btn-square text-white me-2"><i class="fab fa-pinterest"></i></a>
                            <a href="#https://www.facebook.com/Phuc250703/" class="btn-hover-color btn-square text-white me-0"><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Copyright End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-primary btn-primary-outline-0 btn-md-square back-to-top"><i class="fa fa-arrow-up"></i></a>   

        
        <!-- JavaScript Libraries -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="lib/easing/easing.min.js"></script>
        <script src="lib/waypoints/waypoints.min.js"></script>
        <script src="lib/counterup/counterup.min.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="lib/lightbox/js/lightbox.min.js"></script>
        

        <!-- Template Javascript -->
        <script src="js/main.js"></script>
