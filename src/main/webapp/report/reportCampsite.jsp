<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="model.Order" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Report Template</title>
    <meta charset="utf-8">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <style>
        /* Container Styles */
        .album .container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center; /* Căn giữa các thẻ */
            align-items: flex-start; /* Căn các thẻ thẳng hàng trên cùng */
            gap: 1.5rem; /* Khoảng cách giữa các thẻ */
        }

        /* Campsite Item */
        .campsite-item {
            width: calc(33.33% - 2rem); /* Đảm bảo mỗi thẻ chiếm 1/3 chiều rộng */
            max-width: 300px; /* Giới hạn kích thước tối đa */
            min-width: 250px; /* Giới hạn kích thước tối thiểu */
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            display: flex;
            flex-direction: column;
            align-items: center; /* Đảm bảo căn giữa nội dung bên trong */
            text-align: center;
            transition: transform 0.3s, box-shadow 0.3s;
        }

        .campsite-item:hover {
            transform: translateY(-5px);
            box-shadow: 0px 8px 12px rgba(0, 0, 0, 0.2);
        }

        /* Campsite Image */
        .fixed-rectangle {
            width: 100%; /* Đảm bảo ảnh chiếm toàn chiều rộng thẻ */
            height: 150px; /* Chiều cao cố định */
            object-fit: cover; /* Đảm bảo hình ảnh không bị méo */
            border-bottom: 1px solid #ddd;
        }

        /* Campsite Text */
        .campsite-item div {
            padding: 1rem;
            font-size: 0.9rem;
            line-height: 1.5;
            color: #555;
        }

        /* Responsive Adjustments */
        @media (max-width: 768px) {
            .campsite-item {
                width: calc(50% - 2rem); /* Giảm xuống 2 cột trên màn hình nhỏ */
            }
        }

        @media (max-width: 480px) {
            .campsite-item {
                width: calc(100% - 2rem); /* 1 cột trên màn hình rất nhỏ */
            }
        }
    </style>
</head>

<body>
<div class="container-fluid bg-breadcrumb">
    <div class="container text-center py-5" style="max-width: 900px;">
        <h3 class="text-white display-3 mb-4">REPORT</h3>
        <p class="fs-5 text-white mb-4">HELP US UPGRADE TEMPLATE</p>
        <ol class="breadcrumb justify-content-center mb-0">
            <li class="breadcrumb-item"><a href="user.jsp">Home</a></li>
        </ol>
    </div>
</div>

<div id="container">
    <div class="main_content_area">
        <div class="album py-5 bg-body-tertiary">
            <div class="container">
                <div class="card-header my-3">Campsite Booked Information</div>
                <c:forEach var="campsite" items="${campsitesReport}">
                    <div class="campsite-item" onclick="selectCampsite(${campsite.campId})">
                        <img class="fixed-rectangle" src="img/${campsite.campImage}" alt="Camp image">
                        <div>
                            <strong>${campsite.campName}</strong><br>
                            Giá: ${campsite.campPrice}<br>
                            Địa chỉ: ${campsite.campAddress}<br>
                            Mô tả: ${campsite.campDescription}
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function selectCampsite(campsiteId) {
        console.log("Campsite_id: ", campsiteId);
        const reportType = 'campsite';
        window.location.href = "/login_war/getCampsiteDetail?campsiteId=" + campsiteId + "&reportType=" + reportType;
    }
</script>
</body>

</html>
