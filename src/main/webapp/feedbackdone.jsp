<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="image/favicon.png" type="image/png">
        <title>OUTDOOR LIFE</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/css/bootstrap.min.css">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="vendors/linericon/style.css">
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
        <link rel="stylesheet" href="vendors/nice-select/css/nice-select.css">
        <link rel="stylesheet" href="vendors/owl-carousel/owl.carousel.min.css">
        <!-- main css -->
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/responsive.css">
        <style>
            body {
                background-color: #f8f9fa;
            }

            .containerfb {
                max-width: 500px;
                margin: 0 auto;
                padding: 20px;
                background-color: #ffffff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                border-radius: 5px;
                margin-top: 50px;
            }

            h1 {
                text-align: center;
                margin-bottom: 30px;
                color: #007bff;
            }

            .rating {
                direction: rtl;
                unicode-bidi: bidi-override;
                text-align: center;
                margin-bottom: 20px;
            }

            .rating input {
                display: none;
            }

            .rating label {
                display: inline-block;
                padding: 10px;
                font-size: 24px;
                color: #dcdcdc;
                cursor: pointer;
            }

            .rating label:before {
                content: '\2605';
            }

            .rating input:checked ~ label,
            .rating input:checked ~ label ~ label {
                color: #ffc107;
            }

            .mb-3 {
                margin-bottom: 20px;
            }

            .form-label {
                font-weight: bold;
                color: #007bff;
            }

            .btn-primary {
                background-color: #007bff;
                border-color: #007bff;
            }

            .btn-primary:hover {
                background-color: #0069d9;
                border-color: #0062cc;
            }

            .comments {
                margin-top: 20px;
            }

            .comment {
                align-items: flex-start;
                margin-bottom: 10px;
            }

            .comment-avatar {
                width: 40px;
                height: 40px;
                border-radius: 50%;
                overflow: hidden;
                margin-right: 10px;
            }

            .comment-avatar img {
                width: 100%;
                height: 100%;
                object-fit: cover;
            }

            .comment-content {
                flex-grow: 1;
                border: 1px solid #ccc;
                padding: 10px;
                border-radius: 5px;
                display: flex;
            }

            .comment-name {
                font-weight: bold;
                margin-bottom: 5px;
            }

            .comment-rating {
                margin-bottom: 5px;
            }

            .comment-text {
                margin-bottom: 0;
            }

            .comment-reply {
                display: flex;
                margin-top: 10px;
                padding-left: 70px;
            }

            .comment-reply-content {
                background-color: #f5f5f5;
                padding: 15px;
                border-radius: 15px;
                width: 380px;
            }

            .comment-reply-content .comment-name {
                font-weight: bold;
                margin-bottom: 5px;
            }

            .reply-text {
                margin: 0;
            }

        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <!--================Breadcrumb Area =================-->
        <section class="breadcrumb_area">
            <div class="overlay bg-parallax" data-stellar-ratio="0.8" data-stellar-vertical-offset="0" data-background=""></div>
            <div class="container">
                <div class="page-cover text-center">
                    <h2 class="page-cover-tittle">Feedback</h2>
                    <ol class="breadcrumb">
                        <li><a href="index.html">Home</a></li>
                        <li class="active">Contact Us</li>
                    </ol>
                </div>
            </div>
        </section>
        <!--================Breadcrumb Area =================-->
        <div class="containerfb">
            <h1>Đánh giá và phản hồi</h1>

           
            <div id="feedback-message" style="margin-top: 20px;">
                <h2> Cảm ơn bạn đã gửi phản hồi!</h2>
            </div>
        </div>
        
         <div class="comments">
                <h2>Các đánh giá khác:</h2>
                <c:forEach var="feedback" items="${listFeedback}">
                    <div class="comment">
                        <div class="comment-content">
                            <div class="comment-avatar">
                                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTA9jyeynlFwVGBRreQHauSuqrkhoKGk7ytIw8OpgZbNA&s" alt="Avatar">
                            </div>
                            <div>
                                <div class="comment-name">${feedback.customerName}</div>

                                <div class="comment-rating">
                                    <span class="badge bg-primary">${feedback.rating} sao</span>
                                </div>
                                <p class="comment-text">${feedback.content}</p>
                            </div>
                        </div>
                        <!-- Show reply comment if it exists -->
                        <!-- Add your code here for showing replies if needed -->
                    </div>
                </c:forEach>
            </div>
               
        <a href="feedback.jsp"><input type="button" id="fb" name="fb" value="Back"></a>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/js/bootstrap.bundle.min.js"></script>
      
    </body>
</html>
