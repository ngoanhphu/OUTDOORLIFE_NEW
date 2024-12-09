<%@ page import="model.Campsite" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Campsite campsite = (Campsite) request.getAttribute("campsite");
    double totalPrice = (request.getAttribute("totalPrice") != null) ? (double) request.getAttribute("totalPrice") : 0;
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Outdoor-Life - Environmental & Nature Website</title>
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

    <!-- Bootstrap Datepicker CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css"
          rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    <style>
        select {
            background-color: #fff !important;
        }
    </style>
</head>

<body>
<jsp:include page="header1.jsp"></jsp:include>
<br>
<br>
<br>

<div class="container my-5 booking-form">
    <h1 class="text-center text-uppercase fw-bold mb-4">Booking Campsite</h1>
    <div class="owner-info text-center mb-4">
        <div class="owner-image-container">
            <img src="img/<%= campsite.getCampImage() %>" alt="Camp Image" class="owner-image rounded-circle shadow">
        </div>
        <h2 class="mt-3 owner-name"><%= campsite.getCampName() %></h2>
        <h6 class="mt-3 campsite-address"><%= campsite.getCampAddress() %></h6>
    </div>
    <form action="bookingservlet" method="post" role="form" class="p-4 shadow-lg rounded-3 bg-white">
        <input type="hidden" name="campId" value="<%= campsite != null ? campsite.getCampId() : "" %>">

        <div class="row mb-4">
            <div class="form-group col-md-6">
                <label for="Phone" class="form-label fw-semibold">Phone</label>
                <input type="text" class="form-control" id="Phone" name="Phone" placeholder="Phone" readonly
                       value="${currentUser.phoneNumber}">
            </div>
            <div class="form-group col-md-6">
                <label for="Email" class="form-label fw-semibold">Email</label>
                <input type="text" class="form-control" id="Email" name="Email" placeholder="Email" readonly
                       value="${currentUser.email}">
            </div>
        </div>

        <div class="row mb-4">
            <div class="form-group col-md-6">
                <label for="CampAddress" class="form-label fw-semibold">Camp Address</label>
                <input type="text" class="form-control" id="CampAddress" name="CampAddress" placeholder="Camp Address"
                       readonly value="<%= campsite != null ? campsite.getCampAddress() : "" %>">
            </div>
            <div class="form-group col-md-6">
                <label for="CampPrice" class="form-label fw-semibold">Camp Price</label>
                <input type="text" class="form-control" id="CampPrice" name="CampPrice" placeholder="Camp Price"
                       readonly value="<%= campsite != null ? campsite.getCampPrice() : "" %>">
            </div>
        </div>

        <div class="row mb-4">
            <div class="form-group col-md-6">
                <label for="CheckIn" class="form-label fw-semibold">Check In</label>
                <input type="date" class="form-control" name="CheckIn" placeholder="Check In" min="${currentDate}"
                       value="${currentDate}" required>
            </div>
            <div class="form-group col-md-6">
                <label for="CheckOut" class="form-label fw-semibold">Check Out</label>
                <input type="date" class="form-control" name="CheckOut" placeholder="Check Out" min="${nextDate}"
                       value="${nextDate}" required>
            </div>
        </div>

        <div class="row mb-4">
            <div class="form-group col-md-6">
                <label for="quantity" class="form-label fw-semibold">Quantity</label>
                <input type="number" class="form-control" id="quantity" name="quantity" placeholder="Quantity" required>
            </div>
            <div class="form-group col-md-6">
                <label for="voucher" class="form-label fw-semibold">Voucher</label>
                <select name="voucher" id="voucher" class="form-control">
                    <option value="">Select voucher</option>
                    <c:forEach items="${vouchers}" var="v">
                        <option value="${v.id}">${v.code} - ${v.percent}% (${v.startDate} - ${v.endDate})</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="text-end">
            <button type="submit" class="btn btn-success btn-lg px-5 py-2">Book Now</button>
        </div>
    </form>
</div>

<script src="js/main.js"></script>
<link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet"/>
<script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<c:if test="${message != null}">
    <script type="text/javascript">
        toastr.success(`${message}`, 'Success', {timeOut: 1000});
    </script>
</c:if>
<c:if test="${error != null}">
    <script type="text/javascript">
        toastr.error(`${error}`, 'Error', {timeOut: 1000});
    </script>
</c:if>
</body>

</html>
