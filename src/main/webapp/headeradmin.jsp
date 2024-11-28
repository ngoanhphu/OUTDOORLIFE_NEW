<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">


    <head>
        <meta charset="utf-8">
        <title>Environs - Environmental & Nature Website Template</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500;600&family=Roboto&display=swap" rel="stylesheet"> 

        <!-- Icon Font Stylesheet -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">

        <link href="addtocart.css" rel="stylesheet">
        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" tyle="text/css" rel="stylesheet">
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
    </head>

    <body>

        <!-- Spinner Start -->
        <div id="spinner" class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
            <div class="spinner-grow text-primary" role="status"></div>
        </div>
        <!-- Spinner End -->
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
                    <a href="index.jsp" class="navbar-brand ms-3">
                        <h1 class="text-primary display-5">Outdoor-Life</h1>
                    </a>
                    <button class="navbar-toggler py-2 px-3 me-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                        <span class="fa fa-bars text-primary"></span>
                    </button>
                    <div class="collapse navbar-collapse bg-light" id="navbarCollapse">
                        <div class="navbar-nav ms-auto">
                            <c:if test = "${not empty currentUser && currentUser.staff}">
                                <a href="schedule-rent" class="nav-item nav-link">Schedule Rent</a>
                            </c:if>
                            <c:if test = "${not empty currentUser && currentUser.admin}">
                                <a href="dashboard" class="nav-item nav-link active">Dashboard</a>
                                <a href="admin" class="nav-item nav-link">Manage Gears</a>
                                <a href="admintent" class="nav-item nav-link">Manage Tents</a>
                                <a href="manage-account" class="nav-item nav-link">Manage Accounts</a>
                                <a href="manage-campsite" class="nav-item nav-link">Manage Campsites</a>
                                 <a href="show-feedback" class="nav-item nav-link">Manage Feedbacks</a>
                            </c:if>
                            <a href="manage-order" class="nav-item nav-link">Manage Orders</a>
                            <c:if test = "${not empty currentUser && currentUser.staff}">
                                <a href="manage-voucher" class="nav-item nav-link">Manage Vouchers</a>
                            </c:if>

                            <!--                            <a href="service.jsp" class="nav-item nav-link">Services</a>
                                                        <a href="campinggear.jsp" class="nav-item nav-link">Camping gear</a>
                            <%--<c:if test = "${not empty currentUser }">--%>
                           <a href="#" class="nav-item nav-link" onclick="openOrderPopup()">Order History</a>
                            <%--</c:if>--%>
                           <a href="contact.jsp" class="nav-item nav-link">Contact</a>
                            <li class="nav-item"><a class="nav-link" href="Cart.jsp">Cart <span class="badge-custom">${cart_list.size()}</span> </a></li>-->
                        </div>

                        <c:choose>
                            <c:when test = "${not empty currentUser }">
                                <div class="d-flex align-items-center flex-nowrap pt-xl-0" style="margin-left: 15px;">
                                    <!-- Add dropdown menu for profile and logout -->
                                    <div class="dropdown">
                                        <button class="btn btn-light text-primary me-3 dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-expanded="false">
                                            <i class="fas fa-user-circle fa-2x"></i>
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                            <li><a class="dropdown-item" href="UserProfile.jsp">Profile</a></li>
                                            <li><a class="dropdown-item" href="logout">Logout</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </c:when>


                            <c:otherwise>

                                <div class="d-flex align-items-center flex-nowrap pt-xl-0" style="margin-left: 15px;">
                                    <a href="login.jsp" class="btn-hover-bg btn btn-primary text-white py-2 px-4 me-3">Login</a>
                                </div>
                            </c:otherwise>

                        </c:choose>
                    </div>
                </nav>
            </div>
        </div>
        <!-- Navbar End -->


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

    </body>
    <script>
        function openOrderPopup() {
            var modal = new bootstrap.Modal(document.getElementById('orderModal'), {
                keyboard: false
            });
            modal.show();
        }
    </script>

</html>