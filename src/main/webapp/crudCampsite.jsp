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
            <button class="btn-create" onclick="window.location.href='addCampsite.jsp'">Create Campsite</button>
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
            <c:forEach var="g" items="${campsites}">
                <div class="col-md-3 d-flex">
                    <div class="card border-e shadow rounded-3 w-100">
                        <img class="card-img-top" src="img/${g.campImage}" alt="Card image cap" />
                        <div class="card-body">
                            <h5 class="card-title">${g.campName}</h5>
                            <h6 class="price">${g.campPrice}</h6>
                            <h6 class="category">${g.campDescription}</h6>
                            <div class="mt-3 d-flex justify-content-between">
                                <a href="update-campsite?id=${g.campId}" class="btn btn-primary">Update</a>
                                <c:if test="${g.campStatus == true}">
                                <a href="#" onclick="showMess(${g.campId})" class="btn btn-primary">Delete</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
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
                window.location.href = 'manage-campsite?page=' + page;
            }
        });
    </script>
    
    <script>
        function showMess(gearId) {
            var option = confirm('Are you sure to delete');
            if (option === true) {
                window.location.href = 'delete-campsite?id=' + gearId;
            }
        }
    </script>
</body>