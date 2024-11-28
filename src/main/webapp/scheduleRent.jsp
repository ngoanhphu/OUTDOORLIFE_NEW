<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
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
            <h3>Schedule Rent</h3>


            <div class="search-container" style="margin-bottom: 16px">
                <form action="schedule-rent" method="get" class="form-inline my-2 my-lg-0">
                    <div class="input-group input-group-sm">
                        Campsite: <select style="margin-right: 20px;margin-left: 6px" name="campsite">
                            <option value="">All</option>
                        <c:forEach items="${campsites}" var="c">
                            <option value="${c.campId}" <c:if test="${c.campId == campsite}">selected</c:if>>${c.campName}</option>
                        </c:forEach>
                    </select>
                    Date: <input style="margin-right: 6px;margin-left: 6px" name="txt" type="date" placeholder="Search..." value="${date}">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>
            </form>
        </div>

        <div class="row">
            <table class="table table-light table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Booker</th>
                            <%//hien thi ket qua việc tìm kiếm  
                                 // Lấy giá trị từ request attribute
                               Object dateObject = request.getAttribute("date");
                                LocalDate startDate = null;
                                String dateString = "";
                                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                               // Kiểm tra nếu dateObject không null và là một chuỗi
                               if (dateObject != null && dateObject instanceof String) {
                                    dateString = (String) dateObject;
                    
                                   // Chuyển đổi từ chuỗi sang LocalDate
                                   startDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    
                                  
                    
                                   // Hiển thị 7 ngày kế tiếp
                                   for (int i = 0; i < 7; i++) {
                                       LocalDate currentDate = startDate.plusDays(i);
                                       String formattedDate = currentDate.format(formatter);
                            %>
                        <th scope="col"><%= formattedDate %></th>
                            <%
                      }}
                            %>
                    </tr>
                </thead>
                <tbody>
                    <% 
                               // Truy vấn và lặp qua danh sách các đơn hàng (ví dụ: từ một danh sách đơn hàng đã lấy được)
                               List<Order> orders = (List<Order>) request.getAttribute("orders");
                               startDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            
                               // Lặp qua danh sách các đơn hàng
                               for (Order order : orders) {
                    %>
                    <tr>
                        <!--open order detail-->
                        <td><a href="order-detail?id=<%= order.getOrdersId()%>&action=admin"><%= order.getBookerName() %></a></td>
                            <% 
                                // Lặp lại qua các ngày
                                for (int i = 0; i < 7; i++) {
                                    LocalDate currentDate = startDate.plusDays(i);
                                    String formattedDate = currentDate.format(formatter);
                                      // Chuyển đổi từ Timestamp sang LocalDate
                                                LocalDate orderStartDate = order.getStartDate().toLocalDateTime().toLocalDate();
                                                LocalDate orderEndDate = order.getEndDate().toLocalDateTime().toLocalDate();
                                                // Kiểm tra nếu đơn hàng này nằm trong khoảng thời gian từ startDate đến endDate
                                                if (orderStartDate.compareTo(currentDate) <= 0 && orderEndDate.compareTo(currentDate) >= 0) {
                            %>
                        <td style="background-color: green"><%= order.getBookerName() %></td>
                        <% 
                                } else {
                        %>
                        <td></td> <!-- Hoặc có thể hiển thị một dấu gạch nếu không có đơn hàng -->
                        <% 
                                }
                            } // end for loop
                        %>
                    </tr>
                    <% 
                        } // end for loop (orders)
                    %>
                </tbody>
            </table>

        </div>


    </div>
</body>