<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.http.*, javax.servlet.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Staff Profile - Outdoor-Life</title>
    
    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500;600&family=Roboto&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" type="text/css" rel="stylesheet">
    

</head>
<body>
    <!-- Navbar start -->
    <div class="container-fluid fixed-top px-0">
        <div class="container px-0">
            <div class="topbar">
                <div class="row align-items-center justify-content-center">
                    <div class="col-md-8">
                        <div class="topbar-info d-flex flex-wrap">
                            <a href="#" class="text-light me-4"><i class="fas fa-envelope text-white me-2"></i>phucnhde170706@fpt.edu.vn</a>
                            <a href="#" class="text-light"><i class="fas fa-phone-alt text-white me-2"></i>0941887045</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="topbar-icon d-flex align-items-center justify-content-end">
                            <a href="https://www.facebook.com/Phuc250703/" class="btn-square text-white me-2"><i class="fab fa-facebook-f"></i></a>
                            <a href="https://www.facebook.com/Phuc250703/" class="btn-square text-white me-2"><i class="fab fa-twitter"></i></a>
                            <a href="https://www.facebook.com/Phuc250703/" class="btn-square text-white me-2"><i class="fab fa-instagram"></i></a>
                            <a href="https://www.facebook.com/Phuc250703/" class="btn-square text-white me-2"><i class="fab fa-pinterest"></i></a>
                            <a href="https://www.facebook.com/Phuc250703/" class="btn-square text-white me-0"><i class="fab fa-linkedin-in"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <nav class="navbar navbar-light bg-light navbar-expand-xl">
                <a href="index.html" class="navbar-brand ms-3">
                    <h1 class="text-primary display-5">Outdoor-Life</h1>
                </a>
                <button class="navbar-toggler py-2 px-3 me-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="fa fa-bars text-primary"></span>
                </button>
                <div class="collapse navbar-collapse bg-light" id="navbarCollapse">
                    <div class="navbar-nav ms-auto">
                        <a href="user.jsp" class="nav-item nav-link">Home</a>
                        <a href="about.html" class="nav-item nav-link">About</a>
                        <a href="service.html" class="nav-item nav-link">Services</a>
                        <a href="campsite.html" class="nav-item nav-link">Campsite</a>
                        <a href="campinggear.html" class="nav-item nav-link">Camping Gear</a>
                        <a href="contact.html" class="nav-item nav-link">Contact</a>
                    </div>
                    <div class="d-flex align-items-center flex-nowrap pt-xl-0" style="margin-left: 15px;">
                        <a href="profile.jsp" class="btn btn-light text-primary me-3">
                            <i class="fas fa-user-circle fa-2x"></i>
                        </a>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <!-- Navbar End -->

    <!-- Profile Section Start -->
    <div class="container profile py-5">
        <div class="row justify-content-center">
            <div class="col-lg-8">
                <div class="card shadow-lg">
                    <div class="card-header bg-primary text-white text-center py-4">
                        <h2 class="mb-0">Admin Profile</h2>
                    </div>
                    <div class="card-body text-center">
                        <div class="profile-avatar mb-4">
                            <img src="img/avatar.png" alt="Admin Avatar" class="rounded-circle" width="150">
                        </div>
                        <h3 class="mb-3"><%= session.getAttribute("name") %></h3>
                        <p class="text-muted mb-3"><i class="fas fa-envelope me-2"></i><%= session.getAttribute("email") %></p>
                        <p class="text-muted mb-3"><i class="fas fa-phone me-2"></i><%= session.getAttribute("phone") %></p>
                        <p class="text-muted mb-3"><i class="fas fa-calendar me-2"></i><%= session.getAttribute("dob") %></p>
                        <p class="text-muted mb-3"><i class="fas fa-id-card me-2"></i><%= session.getAttribute("cccd") %></p>
                        <p class="text-muted mb-4"><i class="fas fa-map-marker-alt me-2"></i><%= session.getAttribute("address") %></p>
                        <a href="change-password.jsp" class="btn btn-warning mb-3"><i class="fas fa-key me-2"></i>Change Password</a>
                        <a href="logout.jsp" class="btn btn-danger"><i class="fas fa-sign-out-alt me-2"></i>Logout</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Profile Section End -->

    <!-- Footer start -->
    <footer class="container-fluid bg-dark text-white py-5">
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <h5 class="text-primary mb-4">Outdoor-Life</h5>
                    <p class="mb-4">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vehicula euismod diam a laoreet.</p>
                    <ul class="list-unstyled">
                        <li class="mb-2"><a href="#" class="text-white">Privacy Policy</a></li>
                        <li class="mb-2"><a href="#" class="text-white">Terms of Use</a></li>
                    </ul>
                </div>
                <div class="col-md-3">
                    <h5 class="text-primary mb-4">Quick Links</h5>
                    <ul class="list-unstyled">
                        <li class="mb-2"><a href="#" class="text-white">Home</a></li>
                        <li class="mb-2"><a href="#" class="text-white">About</a></li>
                        <li class="mb-2"><a href="#" class="text-white">Services</a></li>
                        <li class="mb-2"><a href="#" class="text-white">Campsite</a></li>
                        <li class="mb-2"><a href="#" class="text-white">Camping Gear</a></li>
                        <li class="mb-2"><a href="#" class="text-white">Contact</a></li>
                    </ul>
                </div>
                <div class="col-md-3">
                    <h5 class="text-primary mb-4">Contact Us</h5>
                    <p class="mb-2"><i class="bi bi-geo-alt-fill text-primary me-2"></i>123 Street, New York, USA</p>
                    <p class="mb-2"><i class="bi bi-telephone-fill text-primary me-2"></i>+123 456 7890</p>
                    <p class="mb-2"><i class="bi bi-envelope-fill text-primary me-2"></i>info@example.com</p>
                </div>
                <div class="col-md-3">
                    <h5 class="text-primary mb-4">Subscribe</h5>
                    <form>
                        <div class="mb-3">
                            <input type="email" class="form-control" placeholder="Email Address">
                        </div>
                        <button type="submit" class="btn btn-primary">Subscribe Now</button>
                    </form>
                </div>
            </div>
        </div>
    </footer>
    <!-- Footer end -->

    <!-- Back to Top Start -->
    <a href="#" class="btn-scroll-top"><i class="bi bi-arrow-up-short"></i></a>
    <!-- Back to Top End -->

       <!-- JavaScript Libraries -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>
</html>