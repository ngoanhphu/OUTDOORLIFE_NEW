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
                <button class="btn-create" onclick="window.location.href = 'addStaff.jsp'">Create Staff</button>
            </div>

            <!--        <div class="search-container">
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
                            <th scope="col">Id</th>
                            <th scope="col">First Name</th>
                            <th scope="col">Last Name</th>
                            <th scope="col">Email</th>
                            <th scope="col">Phone</th>
                            <th scope="col">Role</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="a" items="${accounts}">
                        <tr>
                            <td>${a.id}</td>
                            <td>${a.firstName}</td>
                            <td>${a.lastName}</td>
                            <td>${a.email}</td>
                            <td>
                                ${a.phoneNumber}
                            </td>
                            <c:if test="${a.admin}">
                                <td>
                                    Admin
                                </td>
                            </c:if>
                            <c:if test="${a.user}">
                                <td>
                                    User
                                </td>
                            </c:if>
                            <c:if test="${a.staff}">
                                <td>
                                    Staff
                                </td>
                            </c:if>
                            <td><c:if test="${a.staff}"><a a href="#" onclick="showMess(${a.id})"  class="btn btn-sm btn-danger">Remove</a> </c:if></td>
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
                        window.location.href = 'manage-account?page=' + page;
                    }
                });
    </script>

    <script>
        function showMess(gearId) {
            var option = confirm('Are you sure to delete');
            if (option === true) {
                window.location.href = 'delete-staff?id=' + gearId;
            }
        }
    </script>
</body>