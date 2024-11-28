<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    .container {
        padding: 2rem 0;
    }
    .card {
        background-color: #fff;
        border: none;
        border-radius: 0.5rem;
        margin-bottom: 1.5rem;
        display: flex;
        flex-direction: column;
        height: 100%;
    }
    .card-header {
        padding: 1rem 1.5rem;
        margin-bottom: 1rem;
        background-color: #f8f9fa;
        border-radius: 0.5rem 0.5rem 0 0;
        text-align: center;
        font-size: 1.25rem;
        font-weight: bold;
    }
    .card-body {
        padding: 1.5rem;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }
    .card-img-top {
        height: 200px;
        object-fit: cover;
        border-radius: 0.5rem 0.5rem 0 0;
    }
    .card-title {
        font-size: 1.25rem;
        margin: 0.5rem 0;
    }
    .price, .category {
        font-size: 1rem;
        margin: 0.25rem 0;
    }
    .btn {
        width: 80%;
    }
    .row {
        margin-bottom: 1.5rem; /* Adjust this value to increase/decrease spacing between rows */
    }
    .create-button-container {
        display: flex;
        justify-content: center;
        margin-bottom: 2rem;
    }
    .btn-create {
        background-color: #007bff;
        color: white;
        padding: 0.75rem 1.5rem;
        font-size: 1rem;
        border: none;
        border-radius: 0.5rem;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    .btn-create:hover {
        background-color: #0056b3;
    }
</style>

<body>
    <jsp:include page="headeradmin.jsp"></jsp:include>
        <div class="container" style="margin-top: 200px">
            <!-- Create Gear Button -->
                 <div class="create-button-container">
                            <button class="btn-create" onclick="window.location.href = 'add-voucher'">Create Voucher</button>
                        </div>

            <!--            <div class="search-container" style="margin-bottom: 16px">
                            <form action="search" method="post" class="form-inline my-2 my-lg-0">
                                <div class="input-group input-group-sm">
                                    <input name="txt" type="text" placeholder="Search...">
                                    <div class="input-group-append">
                                        <button type="submit" class="btn btn-secondary btn-number">
                                            <i class="fa fa-search"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>-->

            <div class="row">
                <table class="table table-light">
                    <thead>
                        <tr>
                            <th scope="col">Code</th>
                            <th scope="col">Percent</th>
                            <th scope="col">Start Date</th>
                            <th scope="col">End Date</th>
                            <th scope="col">Is Used</th>
                            <th scope="col">Action</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="a" items="${vouchers}">
                        <tr>
                            <td>${a.code}</td>
                             <td>${a.percent}</td>
                            <td>${a.startDate}</td>
                            <td>${a.endDate}</td>
                            <td>${a.isUsed}</td>
                            <td>
                                <a a href="update-voucher?id=${a.id}" class="btn btn-sm btn-primary">Edit</a>
                            </td>
                            <td>
                                <a a href="delete-voucher?id=${a.id}" class="btn btn-sm btn-danger">Delete</a>
                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>

        <div class="d-flex justify-content-center mt-2">
            <div id="pagination"></div>
        </div>
    </div>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/twbs-pagination/1.4.2/jquery.twbsPagination.min.js"></script>

    <script type="text/javascript">
        // Setup variables for pagination
        let itemsPerPage = 8;
        let totalPages = ${totalPages};
        let currentPage = ${currentPage};

        // Initialize pagination plugin
        $('#pagination').twbsPagination({
            startPage: currentPage,
            totalPages: totalPages,
            visiblePages: 10,
            initiateStartPageClick: false,
            onPageClick: function (event, page) {
                window.location.href = 'manage-voucher?page=' + page;
            }
        });
    </script>
</body>