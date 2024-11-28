<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "dao.CampsiteDAO"%>
<%@page import = "dao.CommentDAO"%>
<%@page import = "dao.DBContext"%>
<%@page import = "model.*" %>
<%@page import = "java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
User auth = (User) request.getSession().getAttribute("currentUser");
if (auth != null) {
    request.setAttribute("person", auth);
}
DBContext dbContext = new DBContext();
CampsiteDAO cd = new CampsiteDAO();
CommentDAO cm = new CommentDAO();
List<Campsite> campsites = cd.getAllRiverCampsite();
List<Comment> comments = cm.getAllRiverComment();
%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <style>
            .comment {
                border: 1px solid #ccc;
                margin-bottom: 10px;
                padding: 10px;
            }
        </style>
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
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">


        <!-- Customized Bootstrap Stylesheet -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- Template Stylesheet -->
        <link href="css/style.css" rel="stylesheet">
    </head>

    <body>

                <jsp:include page="header.jsp"></jsp:include>

        <!-- Header Start -->
        <div class="container-fluid bg-breadcrumb">
            <div class="container text-center py-5" style="max-width: 900px;">
                <h3 class="text-white display-3 mb-4">CAMPSITE</h3>
                <p class="fs-5 text-white mb-4">CHOOSE THE CAMPSITE TO CHILL</p>
                <ol class="breadcrumb justify-content-center mb-0">
                    <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                    <li class="breadcrumb-item"><a href="#">Pages</a></li>
                    <li class="breadcrumb-item active text-white">Services</li>
                </ol>
            </div>
        </div>
        <!-- Header End -->

        <!-- Services Start -->
        <div class="container-fluid service py-5 bg-light">
            <div class="container py-5">
                <div class="text-center mx-auto pb-5" style="max-width: 800px;">
                    <h5 class="text-uppercase text-primary">Rent</h5>
                </div>
                <div class="row g-4">
                <div class="d-flex flex-wrap">
                    <%
                    if (!campsites.isEmpty()) {
                        for (Campsite c : campsites) { %>
                    <div class="service-item">
                        <div class="card " style="width: 500px; height: 500px; margin: 50px">
                            <div style="height: 65%"> <img class="img-fluid w-100 h-100" src="img/<%= c.getCampImage() %>" alt="Card image cap"></div>
                            <div class="card-body">
                                <div> <h5 class="card-title"><%= c.getCampName() %></h5>
                                <h6 class="category"><%= c.getCampAddress() %></h6>
                                <h6 class="category">Quantity: <%= c.getLimite() %></h6>
                                </div> 
                                <div class="service-link">
                                    <a href="detail1.jsp" class="btn btn-primary mt-3">View Detail</a>
                                    <% if(c.getLimite() > 0){%>
                                    <form action="booking" method="post" style="display: inline;">
                                        <button class="btn btn-secondary mt-3" type="submit" name="campId" value="<%= c.getCampId() %>">Book Now</button>
                                    </form>
                                         <%}
                                    %>
                                </div>
                            </div>
                        </div>
                    </div>
                    <% }
                    } %>                       
                </div>
            </div>

                <form action="comment" method="post" ">
                    <!-- Comment Section Start -->
                    <input type="hidden" name="campAddress" value="Sông">
                    <input type="hidden" name="originPage" value="form1.jsp">
                    <div class="comment-section mt-5">
                        <h5 class="text-uppercase text-primary mb-4">Comments</h5>
                        <div class="comment-box mb-2">
                            <textarea name="comment" id="comment" class="form-control" rows="3" placeholder="Write a comment..."></textarea>
                            <button class="btn btn-primary mt-3" type="submit" name="commentId" ">Post Comment</button>
                        </div>                       
                    </div>
                    <!-- Comment Section End -->
                </form>

                <div class="comment-section mt-5">
                    <h5 class="text-uppercase text-primary mb-4">All Comments</h5>
                    <%
                    if (!comments.isEmpty()) {
                        for (Comment cmt : comments) { %>
                    <div class="row g-4">    
                        <div class="comment-box mb-2">
                            <h7 class="card-title"><%=cmt.getTimeStamp() %></h7>
                            <h6 class="card-title"><%=cmt.getName() %></h6>
                            <h4 class="category"><%=cmt.getContent() %></h4>                          
                        </div>
                    </div>
                    <% }
                } %>                       

                </div>

            </div>
        </div>
        <!-- Services End -->

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

        <!-- Comment Script -->
        <!-- Comment Script -->



    </body>

</html>