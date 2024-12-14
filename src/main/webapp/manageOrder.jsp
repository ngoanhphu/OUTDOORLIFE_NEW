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
            <div class="row">
                <table class="table table-light">
                    <thead>
                        <tr>
                            <th scope="col">Time Order</th>
                            <th scope="col">Booker</th>
                            <th scope="col">Time Start</th>
                            <th scope="col">Time End</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Total Price</th>
                            <th scope="col">Approved Status</th>
                            <th scope="col">Detail Booking</th>
                            <th scope="col">Approved</th>
                            <th scope="col">Payment</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="a" items="${orders}">
                        <tr>
                            <td>${a.timeStamp}</td>
                            <td>${a.bookerName}</td>
                            <td>${a.startDate}</td>
                            <td>${a.endDate}</td>
                            <td>
                                ${a.quantity}
                            </td>
                            <td>
                                ${a.totalAmount}
                            </td>
                            <td>
                                <c:if test="${a.approveStatus == true}">Success
                                </c:if>
                                <c:if test="${a.approveStatus == false}">Not Yet
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${a.paymentStatus == true}">Completed
                                </c:if>
                                <c:if test="${a.paymentStatus == false}">Not Yet
                                </c:if>
                            </td>
                            <td><a a href="order-detail?id=${a.ordersId}&action=admin" class="btn btn-sm btn-success">View Detail</a></td>
                            <td>
                                <c:if test="${a.approveStatus == false && not empty currentUser && currentUser.owner}">
                                    <a a href="approve-order?id=${a.ordersId}&action=status" class="btn btn-sm btn-primary">Approve Order</a>
                                </c:if>
                            </td>
                            <td><c:if test="${a.paymentStatus == false && not empty currentUser && currentUser.owner}">
                                    <a a href="approve-order?id=${a.ordersId}&action=payment" class="btn btn-sm btn-warning">Approve Payment</a></c:if></td>

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
        //phan trang
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
                window.location.href = 'manage-order?page=' + page;
            }
        });
    </script>
</body>