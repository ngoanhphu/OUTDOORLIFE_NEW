<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.CampsiteDAO" %>
<%@page import="dao.CommentDAO" %>
<%@page import="dao.DBContext" %>
<%@page import="model.*" %>
<%@page import="java.util.*" %>


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
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
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
<div id="container">
    <div class="main_content_area">
        <div class="album py-5 bg-body-tertiary">
            <div class="container">
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                    <%
                        if (campsites != null && !campsites.isEmpty()) {
                            for (Campsite c : campsites) {
                    %>
                    <div class="col">
                        <div class="card shadow-sm">
                            <img class="fixed-rectangle" src="img/<%= c.getCampImage() %>" alt="Camp image">
                            <div class="card-body">
                                <a class="title btn_link venue_title_list" href="#">
                                    <h5 class="card-title"><%= c.getCampName() %></h5>
                                </a>
                                <a class="title2 btn_link" href="#">
                                    <p class="text-muted">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                             fill="currentColor" class="bi bi-geo-alt" viewBox="0 0 16 16">
                                            <path d="M12.166 8.94c-.524 1.062-1.234 2.12-1.96 3.07A32 32 0 0 1 8 14.58a32 32 0 0 1-2.206-2.57c-.726-.95-1.436-2.008-1.96-3.07C3.304 7.867 3 6.862 3 6a5 5 0 0 1 10 0c0 .862-.305 1.867-.834 2.94M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10"/>
                                            <path d="M8 8a2 2 0 1 1 0-4 2 2 0 0 1 0 4m0 1a3 3 0 1 0 0-6 3 3 0 0 0 0 6"/>
                                        </svg>
                                        <%= c.getCampAddress() %>
                                    </p>
                                </a>
                                <div class="title3 mt-2">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                         class="bi bi-body-text" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd"
                                              d="M0 .5A.5.5 0 0 1 .5 0h4a.5.5 0 0 1 0 1h-4A.5.5 0 0 1 0 .5m0 2A.5.5 0 0 1 .5 2h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5m9 0a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5m-9 2A.5.5 0 0 1 .5 4h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5m5 0a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5m7 0a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1-.5-.5m-12 2A.5.5 0 0 1 .5 6h6a.5.5 0 0 1 0 1h-6a.5.5 0 0 1-.5-.5m8 0a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5m-8 2A.5.5 0 0 1 .5 8h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5m7 0a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5m-7 2a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 0 1h-8a.5.5 0 0 1-.5-.5m0 2a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5m0 2a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5"/>
                                    </svg>
                                    <%= c.getCampDescription() %>
                                </div>
                                <div class="title4 mt-2">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-dice-6" viewBox="0 0 16 16">
                                        <path d="M13 1a2 2 0 0 1 2 2v10a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2zM3 0a3 3 0 0 0-3 3v10a3 3 0 0 0 3 3h10a3 3 0 0 0 3-3V3a3 3 0 0 0-3-3z"/>
                                        <path d="M5.5 4a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m8 0a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0 8a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-4a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m-8 4a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0m0-4a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0"/>
                                    </svg>
                                    <%= c.getLimite() %>
                                </div>
                                <button class="btn btn-sm btn-primary btn-add-shortlist btn-outline"
                                        style="background-color: #b6effb; border: 0px; margin-top: 13px;"
                                        type="button" href="#">
                                    <%= c.getCampsiteOwnerName() %>
                                </button>

                                <div class="btn_add_to_cart_list mt-3">
                                    <form action="booking" method="post" style="display: inline;">
                                        <button class="btn btn-sm btn-primary btn-add-shortlist btn-outline"
                                                style="background-color: #B983A9; border: 0px"
                                                type="submit"
                                                name="campId"
                                                value="<%= c.getCampId() %>">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                 fill="currentColor" class="bi bi-cart-check" viewBox="0 0 16 16">
                                                <path d="M11.354 6.354a.5.5 0 0 0-.708-.708L8 8.293 6.854 7.146a.5.5 0 1 0-.708.708l1.5 1.5a.5.5 0 0 0 .708 0z"/>
                                                <path d="M.5 1a.5.5 0 0 0 0 1h1.11l.401 1.607 1.498 7.985A.5.5 0 0 0 4 12h1a2 2 0 1 0 0 4 2 2 0 0 0 0-4h7a2 2 0 1 0 0 4 2 2 0 0 0 0-4h1a.5.5 0 0 0 .491-.408l1.5-8A.5.5 0 0 0 14.5 3H2.89l-.405-1.621A.5.5 0 0 0 2 1zm3.915 10L3.102 4h10.796l-1.313 7zM6 14a1 1 0 1 1-2 0 1 1 0 0 1 2 0m7 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0"/>
                                            </svg>
                                            Book
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    }   else {
                    %><p>No campsites available.</p><%
                    }
                %>
                </div>
            </div>
        </div>

    </div>

    <form action="comment" method="post">
        <!-- Comment Section Start -->
        <input type="hidden" name="campAddress" value="Sông">
        <input type="hidden" name="originPage" value="form1.jsp">
        <div class="comment-section" style=" margin-left: 350px; margin-right: 350px; text-align: center;">
            <h5 class="text-uppercase text-primary mb-4">Comments</h5>
            <div class="comment-box mb-2">
                <textarea name="comment" id="comment" class="form-control" rows="3" placeholder="Write a comment..."
                          style="margin-top: 10px;margin-bottom: 10px;width: 1050px;"></textarea>
                <button class="btn btn-primary mt-3" type="submit" name="commentId">Post Comment</button>
            </div>
        </div>
        <!-- Comment Section End -->
    </form>

    <div class="comment-section" style="margin-left: 400px; margin-top: 100px; margin-right: 400px; text-align: center;">
        <h5 class="text-uppercase text-primary mb-4">All Comments</h5>
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3" style="margin-left: 10px; margin-right: 10px; margin-top: 30px;">
            <%
                if (!comments.isEmpty()) {
                    for (Comment cmt : comments) {
            %>
            <div class="col-12" style="width: 100%">
                <div class="card shadow-sm comment">
                    <div class="card-body">
                        <h6 class="card-title text-primary"><%= cmt.getName() %></h6>
                        <small class="text-muted"><%= cmt.getTimeStamp() %></small>
                        <p class="card-text mt-2"><%= cmt.getContent() %></p>
                    </div>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>

</div>
<!-- Services End -->

<!-- Copyright Start -->
<div class="container-fluid copyright py-4">
    <div class="container">
        <div class="row g-4 align-items-center">
            <div class="col-md-4 text-center text-md-start mb-md-0">
                <span class="text-body"><a href="#"><i
                        class="fas fa-copyright text-light me-2"></i>Outdoor-Life</a></span>
            </div>
            <div class="col-md-4 text-center">
                <div class="d-flex align-items-center justify-content-center">
                    <a href="https://www.facebook.com/Phuc250703/" class="btn-hover-color btn-square text-white me-2"><i
                            class="fab fa-facebook-f"></i></a>
                    <a href="https://www.facebook.com/Phuc250703/" class="btn-hover-color btn-square text-white me-2"><i
                            class="fab fa-twitter"></i></a>
                    <a href="https://www.facebook.com/Phuc250703/" class="btn-hover-color btn-square text-white me-2"><i
                            class="fab fa-instagram"></i></a>
                    <a href="https://www.facebook.com/Phuc250703/" class="btn-hover-color btn-square text-white me-2"><i
                            class="fab fa-pinterest"></i></a>
                    <a href="#https://www.facebook.com/Phuc250703/"
                       class="btn-hover-color btn-square text-white me-0"><i class="fab fa-linkedin-in"></i></a>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Copyright End -->

<!-- Back to Top -->
<a href="#" class="btn btn-primary btn-primary-outline-0 btn-md-square back-to-top"><i class="fa fa-arrow-up"></i></a>
</body>

</html>