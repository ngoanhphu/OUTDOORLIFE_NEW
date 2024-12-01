<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import = "dao.TentDAO"%>
<%@page import = "dao.DBContext"%>
<%@page import = "java.util.List"%>
<%@page import = "model.Gear" %>
<%@page import = "model.Cart" %>
<%@page import = "java.util.*"%>
<%


ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
    request.setAttribute("cart_list", cart_list);
    
}
%>

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
</style>
<body>
    <jsp:include page="header.jsp"></jsp:include>  
        <div class="container" style="margin-top: 200px">
            <div class="search-container">
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
            </div>
            <div class="row">
            <c:forEach var="g" items="${gears}">
                <div class="col-md-3 d-flex">
                    <div class="card border-e shadow rounded-3 w-100">
                        <img class="card-img-top" src="img/${g.gearImage}" alt="Card image cap" />
                        <div class="card-body">
                            <h5 class="card-title">${g.gearName}</h5>
                            <h6 class="price">${g.gearPrice}</h6>
                            <h6 class="category">${g.gearDecription}</h6>
                            <div class="mt-3 d-flex justify-content-between" style="width: 125%">
                                <a href="cartservlet?id=${g.gearId}&redirectPage=/login_war/gearcontroller?action=viewgear" class="btn btn-primary">Add to Cart</a>
                                <!--<a href="ordernow?quantity=1&id=${g.gearId}" class="btn btn-primary">Order Now</a>-->
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
                window.location.href = 'gearcontroller?page=' + page;
            }
        });

    </script>
</body>