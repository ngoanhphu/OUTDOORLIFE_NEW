<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<style>
    /* Global Styles */
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f6f9;
        margin: 0;
        padding: 0;
    }

    .container {
        padding: 3rem 2rem;
        margin-top: 100px;
    }
    .container px-0{
        margin-top: -45px;
    }

    /* Button Styles */
    .btn-create {
        font-size: 18px;
        padding: 12px 24px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .btn-create:hover {
        background-color: #45a049;
    }

    .btn {
        padding: 0.5rem 1rem;
        font-size: 1rem;
        border-radius: 8px;
        cursor: pointer;
        text-decoration: none;
        display: inline-block;
        text-align: center;
    }

    .btn-primary {
        background-color: #007bff;
        color: white;
    }

    .btn-primary:hover {
        background-color: #0056b3;
    }

    .btn-danger {
        background-color: #dc3545;
        color: white;
    }

    .btn-danger:hover {
        background-color: #c82333;
    }

    /* Layout Styles */
    .row {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-between;
        gap: 1.5rem;
    }

    .col-md-3 {
        flex: 1 1 calc(25% - 1.5rem);
        max-width: calc(25% - 1.5rem);
        box-sizing: border-box;
    }

    /* Card Styles */
    .card {
        background-color: white;
        border-radius: 8px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        overflow: hidden;
        transition: transform 0.3s, box-shadow 0.3s;
    }

    .card:hover {
        transform: translateY(-5px);
        box-shadow: 0 6px 15px rgba(0, 0, 0, 0.2);
    }

    .card-img-top {
        width: 100%;
        height: 200px;
        object-fit: cover;
    }

    .card-body {
        padding: 1.5rem;
        text-align: center;
    }

    .card-title {
        font-size: 1.25rem;
        font-weight: bold;
        margin-bottom: 1rem;
    }

    .price {
        font-size: 1.2rem;
        font-weight: bold;
        color: #28a745;
    }

    .address {
        font-size: 1rem;
        color: #555;
        margin-bottom: 1rem;
    }

    .mt-3 {
        margin-top: 1rem;
    }

    /* Pagination */
    .pagination {
        display: flex;
        justify-content: center;
        margin-top: 2rem;
    }

    /* Nếu adminheader cố định ở đầu trang, thêm một margin-top cho phần tạo khu cắm trại */
    .headeradmin {
        margin-top: -150px;
        position: fixed;
        width: 100%;
        top: 0;
        left: 0;
        z-index: 999; /* Đảm bảo adminheader không che mất các phần tử khác */
    }

    .create-button-container text-center {
        margin-top: 100px; /* Tùy chỉnh giá trị margin-top phù hợp để tránh bị che */
    }
    .btn-create{
        margin-top: 40px;
    }
</style>

<body>

<div class="headeradmin" <jsp:include page="headeradmin.jsp"></jsp:include>
</div>
<div class="container">
    <!-- Create Campsite Button -->
    <div class="create-button-container text-center">
        <button class="btn-create" onclick="window.location.href='addCampsite.jsp'">Create Campsite</button>
    </div>
    <!-- Search Form -->
    <div class="search-container">
        <form action="searchCampsite" method="post" class="form-inline">
            <div class="input-group">
                <button type="submit" class="btn btn-secondary">
                    <i class="fa fa-search"></i>
                </button>
                <input name="txt" type="text" placeholder="Search..." class="form-control" />
            </div>
        </form>
    </div>
    <!-- Display Campsites -->
    <div class="row">
        <c:forEach var="g" items="${campsites}">
            <div class="col-md-3">
                <div class="card">
                    <img class="card-img-top" src="img/${g.campImage}" alt="Campsite image">
                    <div class="card-body">
                        <h3 class="card-title">
                            <i class="fas fa-campground"></i> ${g.campName}
                        </h3>
                        <h5 class="price">
                            <i class="fas fa-money-bill-wave"></i> ${g.campPrice} VND
                        </h5>
                        <h5 class="address">
                            <i class="fas fa-map-marker-alt"></i> ${g.campAddress}
                        </h5>
                        <p>
                            <i class="fas fa-info-circle"></i> ${g.campDescription}
                        </p>
                        <p>
                            <i class="fas fa-calendar-day"></i> ${g.limite}
                        </p>
                        <p>
                            <c:if test="${g.campStatus}">
                                <i class="fas fa-check-circle text-success"></i> Active
                            </c:if>
                            <c:if test="${!g.campStatus}">
                                <i class="fas fa-times-circle text-danger"></i> Inactive
                            </c:if>
                        </p>
                        <div class="mt-3">
                            <a href="update-campsite?id=${g.campId}" class="btn btn-primary">
                                <i class="fas fa-edit"></i> Update
                            </a>
                            <c:if test="${g.campStatus}">
                                <a href="#" onclick="showMess(${g.campId})" class="btn btn-danger">
                                    <i class="fas fa-trash"></i> Delete
                                </a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <!-- Pagination -->
    <div class="pagination">
        <div id="pagination"></div>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"></script>
<script>
    // Pagination Setup
    const itemsPerPage = 9;
    const totalPages = ${totalPages};
    const currentPage = ${currentPage};

    $('#pagination').twbsPagination({
        startPage: currentPage,
        totalPages: totalPages,
        visiblePages: 10,
        initiateStartPageClick: false,
        onPageClick: function (event, page) {
            window.location.href = 'manage-campsite?page=' + page;
        }
    });

    // Confirmation for Delete
    function showMess(campId) {
        const option = confirm('Are you sure to delete?');
        if (option) {
            window.location.href = 'delete-campsite?id=' + campId;
        }
    }
</script>
</body>
