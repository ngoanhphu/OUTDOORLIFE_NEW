<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.*, javax.servlet.*, model.User" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User Profile - Outdoor-Life</title>
        <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500;600&family=Roboto&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" type="text/css" rel="stylesheet">
        <style>
            .red {
                color: red;
            }
            .green {
                color: green;
            }
            .profile-container {
                max-width: 600px;
                margin: 150px auto;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
        </style>
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
                            <a href="user.jsp" class="nav-item nav-link active">Home</a>
                            <a href="about.html" class="nav-item nav-link">About</a>
                            <a href="service.html" class="nav-item nav-link">Services</a>
                            <a href="campsite.html" class="nav-item nav-link">Campsite</a>
                            <a href="campinggear.html" class="nav-item nav-link">Camping Gear</a>
                            <a href="contact.html" class="nav-item nav-link">Contact</a>
                        </div>
                        <div class="d-flex align-items-center flex-nowrap pt-xl-0" style="margin-left: 15px;">
                            <a href="UserProfile.jsp" class="btn btn-light text-primary me-3">
                                <i class="fas fa-user-circle fa-2x"></i>
                            </a>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
        <!-- Navbar End -->

        <!-- Profile Section Start -->
        <%
    User user = (User) session.getAttribute("currentUser");

    if (user == null) {
        %>
        <h1 class="text-center mt-5">You are not logged into the system. Please return to the home page!</h1>
        <%
            } else {
                String error = (String) request.getAttribute("error");
                String message = (String) request.getAttribute("message");
                error = (error == null) ? "" : error;
                message = (message == null) ? "" : message;
                String firstName = user.getFirstName();
                String lastName = user.getLastName();
                String phoneNumber = user.getPhoneNumber();
                String email = user.getEmail();
        %>
        <div class="container profile-container">
            <div class="text-center">
                <h1>CHANGE THE ACCOUNT INFORMATION</h1>
            </div>
            <div class="red" id="error">
                <%= error %>
            </div>
            <div class="green" id="message">
                <%= message %>
            </div>
            <form class="form" action="ProfileServlet" method="post">
                <div class="row">
                    <div class="col-sm-6">
                        <h3>User Profile</h3>
                        <div class="mb-3">
                            <label for="firstName" class="form-label">First Name</label>
                            <input type="text" class="form-control" id="firstName" name="firstName" value="<%= firstName %>">
                        </div>
                        <div class="mb-3">
                            <label for="lastName" class="form-label">Last Name</label>
                            <input type="text" class="form-control" id="lastName" name="lastName" value="<%= lastName %>">
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <h3>Contact Information</h3>
                        <div class="mb-3">
                            <label for="phoneNumber" class="form-label">Phone Number</label>
                            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="<%= phoneNumber %>">
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" value="<%= email %>" readonly>
                        </div>

                    </div>
                </div>
                <hr>
                <div class="text-center">
                    <a href="changePassword.jsp?user=<%= user.getId() %>" class="btn btn-secondary mb-3">Change Password</a><br>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </form>
        </div>
        <% } %>
        <!-- Profile Section End -->

        <!-- Footer Start -->
        <footer class="bg-dark text-white-50 pt-5 pb-4">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <h5 class="text-primary mb-4">About Us</h5>
                        <p>We are a travel and camping company dedicated to making your outdoor experience enjoyable and unforgettable. Explore our services and gear for your next adventure.</p>
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
                        <p class="mb-2"><i class="bi bi-telephone-fill text-primary me-2"></i>+84 941887045</p>
                        <p class="mb-2"><i class="bi bi-envelope-fill text-primary me-2"></i>phucnhde170706@fpt.edu.vn</p>
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
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/main.js"></script>
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