<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.DBContext"%>
<%@page import="dao.CampsiteDAO"%>
<%@page import="dao.CampsiteOrderDAO"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>

<%
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);
    User auth = (User) request.getSession().getAttribute("currentUser");
    List<CampsiteOrder> orders = null;
    DBContext db = new DBContext();
    if (auth != null) {
        request.setAttribute("person", auth);
        CampsiteOrderDAO orderDao = new CampsiteOrderDAO(db.getConnection());
        orders = orderDao.userOrders(auth.getId());
    } else {
        response.sendRedirect("login1.jsp");
    }
%>
<!DOCTYPE html>
<html>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 mx-auto">
            <div class="card border-e shadow rounded-3" style="margin-top: 200px">
                <div class="card-body p-4 p-sm-5">
                    <div class="container">
                        <div class="card-header my-3">All Orders</div>
                        <table class="table table-light">
                            <thead>
                            <tr>
                                <th scope="col">Time Order</th>
                                <th scope="col">Time Start</th>
                                <th scope="col">Time End</th>
                                <th scope="col">Days Book</th>
                                <th scope="col">Name</th>
                                <th scope="col">Description</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Total Price</th>
                                <th scope="col">Approved</th>
                                <th scope="col">Payment</th>
                                <th scope="col">Action</th>
                                <th scope="col">Detail</th>
                                <th scope="col">Feedback</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% if (orders != null) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                for (CampsiteOrder o : orders) {
                                    String startDateStr = sdf.format(o.getStartDate());
                                    String endDateStr = sdf.format(new Date(o.getEndDate().getTime())); // Chuyển đổi Timestamp thành Date và format lại
                            %>
                            <tr>
                                <td><%= o.getTimeStamp() %></td>
                                <td><%= startDateStr %></td>
                                <td><%= endDateStr %></td>
                                <td><%= o.getBookingDays() %></td>
                                <td><%= o.getCampName() %></td>
                                <td><%= o.getCampDescription() %></td>
                                <td><%= o.getQuantity() %></td>
                                <td><%= o.getTotalAmount() %></td>
                                <td>
                                    <% if (o.isApproveStatus()) { %>
                                    Success
                                    <% } else { %>
                                    Not yet
                                    <% } %>
                                </td>
                                <td>
                                    <% if (o.isPaymentStatus()) { %>
                                    Completed
                                    <% } else { %>
                                    Not yet
                                    <% } %>
                                </td>
                                <td>
                                    <%
                                        try {
                                            Date endDate = sdf.parse(endDateStr);
                                            Date now = new Date();
                                            if (endDate.before(now)) {
                                                if (o.isApproveStatus() & o.isPaymentStatus() ){
                                    %>
                                    <a class="btn btn-sm btn-success" href="#">Thank You</a>
                                    <%
                                    }
                                    else {
                                    %>
                                    <a class="btn btn-sm btn-secondary" href="#">Out of date</a>
                                    <%

                                        }
                                    } else {
                                        if (!o.isPaymentStatus() ){
                                    %>
                                    <a class="btn btn-sm btn-danger" href="cancelorder?id=<%= o.getOrderId() %>&redirectPage=orderscamp.jsp">Cancel</a>
                                    <%
                                                }}
                                        } catch (ParseException e) {
                                            out.print("Định dạng ngày không hợp lệ");
                                        }
                                    %>
                                </td>
                                <td>
                                    <a class="btn btn-sm btn-success" href="order-detail?id=<%= o.getOrderId() %>">Detail</a>
                                </td>
                                <td>
                                    <% if (o.isApproveStatus() && o.isPaymentStatus()) { %>
<%--                                    <a class="btn btn-sm btn-info" href="feedback.jsp?orderId=<%= o.getOrderId() %>&campsiteId=<%= o.getCampsiteId() %>">Give Feedback</a>--%>
                                    <a class="btn btn-sm btn-info" href="feedback?orderId=<%= o.getOrderId() %>&campsiteId=<%= o.getCampsiteId() %>">Give Feedback</a>

                                    <% } else { %>
                                    <span>N/A</span>
                                    <% } %>
                                </td>


                            </tr>
                            <% }
                            } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
