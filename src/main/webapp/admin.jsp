<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="headerAdmin.jsp"></jsp:include>

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