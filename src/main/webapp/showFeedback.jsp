

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback Page</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="vendors/linericon/style.css">
        <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="vendors/owl-carousel/owl.carousel.min.css">
        <link rel="stylesheet" type="text/css" href="vendors/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
        <link rel="stylesheet" type="text/css" href="vendors/nice-select/css/nice-select.css">
        <link rel="stylesheet" type="text/css" href="vendors/owl-carousel/owl.carousel.min.css">
        <!-- main css -->
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/responsive.css">
        <style>
            /* CSS styles go here */
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }

            h1 {
                text-align: center;
            }

            .containersfb {
                max-width: 1200px;
                margin: 20px auto;
                padding: 20px;
                background-color: #f4f4f4;
                border: 1px solid #ccc;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ccc;
            }

            th {
                background-color: #f2f2f2;
                font-weight: bold;
            }

            .actions {
                margin-top: 10px;
            }

            .actions button {
                margin-right: 10px;
            }
            /*            img {
                            height: 100px;
                        }*/

            .actions{
                width: 250px;
            }

            .button {
                display: inline-block;
                padding: 10px 10px;
                background-color: lightblue;
                color: #fff;
                font-size: 16px;
                text-decoration: none;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            .button-update{
                margin-top: 20px;

            }

            .button:hover {
                background-color: #ccc;
            }

            .button:focus {
                outline: none;
            }

            .button:active {
                background-color: #3e8e41;
                transform: translateY(1px);
            }

            .button-delete{
                background-color: lightcoral;
            }

            .popup {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0, 0, 0, 0.4);
            }

            .popup-content {
                display: block;
                background-color: #fefefe;
                margin: 15% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 80%;
                max-width: 600px;
            }

            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
                cursor: pointer;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }


            .warning-message {
                font-weight: bold;
                color: red;
                text-align: center;
                margin-bottom: 20px;
            }

            .delete-pop-up-actions{
                display: flex;
                justify-content: center;
            }

            .button-gap{
                margin-right: 20px;
            }

            .btn-delete-all-item{
                margin: 10px 0 0 0;
            }
        </style>
    </head>
    <body>
       
            
        
         <jsp:include page="headeradmin.jsp"></jsp:include>
         <div class="container" style="margin-top: 200px">
        <!--================Breadcrumb Area =================-->
        <section class="breadcrumb_area">
            <div class="overlay bg-parallax" data-stellar-ratio="0.8" data-stellar-vertical-offset="0" data-background=""></div>
            <div class="container">
                <div class="page-cover text-center">
                    <h2 class="page-cover-tittle">FEEDBACK</h2>
                    
                </div>
            </div>
        </section>
        <!--================Breadcrumb Area =================-->

        <div class="containersfb">
            <form action="searchFeedback" >
                <label>Search Feedback: </label><input type="text" name="txtSearch" value="${param.txtSearch}"/>
                <button class="btn btn-primary">Search</button>
            </form>
            <form action="filterFeedback" method="GET" style="margin-top: 10px">
                <label>Filter by Rating: </label>
                <input type="number" min="1" max="5" name="txtFilter" onkeydown="return false;" id="filter" required=""/>
                <button class="btn btn-secondary">Filter</button>
            </form>
            <h2>Feedback</h2>
            <table>
                <tr>                                       
                    <th>Account Name</th>
                    <th>Feedback Date</th>

                    <th>Content</th>  
                    <th>Rating</th>


                    <th>Campsite Name</th>
                    <th style="text-align: center">Actions</th>
                </tr>
                <c:forEach items="${sessionScope.LIST_ADMIN_FFEDBACK}" var="feedback">
                    <tr>
                        <td>${feedback.accountName}</td>
                        <td>${feedback.feedbackDate}</td>
                        <td>${feedback.content}</td>
                        <td>${feedback.rating}</td>

                        <td>${feedback.campsiteName}</td>

                        <td class="actions" style="text-align: center">
                            <!--<button class="button btn-secondary" onclick="openPopupReply()">Reply</button>-->
                            <a href="delete-feedback?id=${feedback.feedbackId}" class=" btn btn-sm btn-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>

        </div>

        <div id="popupDelete" class="popup">
            <div id="popupContent" class="popup-content">
                <span class="close" onclick="closePopup()">&times;</span>

                <h2>Reply</h2>

                <div class="form-group delete-pop-up-actions">                       

                    <form action="ReplyFeedback" method="POST">
                        <input type="hidden" name="txtId" id="txtId" />
                        <textarea rows="10" cols="80" name="content"></textarea>
                        <br>
                        <button style="float: right"type="submit" class="button button-delete">Reply</button>
                    </form>
                </div>

            </div>
        </div>
</div>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script>

//            function openPopupReply() {
//                document.getElementById('popupReply').style.display = 'block';
//            }
//
//            function closePopup() {
//                document.getElementById('popupReply').style.display = 'none';
//            }

                    function openPopupDelete(id) {
                        document.getElementById('txtId').value = id;
                        console.log(document.getElementById('txtId').value)
                        document.getElementById('popupDelete').style.display = 'block';
                    }

                    function closePopup() {
                        document.getElementById('popupDelete').style.display = 'none';
                    }
                    document.getElementById("filter").addEventListener("keydown", function (event) {
                        if (event.key === "ArrowUp" || event.key === "ArrowDown") {
                            event.preventDefault(); // Prevent the default behavior of the arrow keys
                        }
                    });
                    if (${requestScope.REPLY_SUCUESS != null}) {
                        swal("Reply Success");
                    }
        </script>
    </body>
</html>
