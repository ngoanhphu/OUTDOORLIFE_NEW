<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.DBContext"%>
<%@page import="dao.GearDAO"%>
<%@page import="dao.OrderDAO"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="dao.CampsiteDAO"%>
<%@page import="dao.CampsiteOrderDAO"%>


<%
        DecimalFormat dcf = new DecimalFormat("#.##");
        request.setAttribute("dcf", dcf);
        User auth = (User) request.getSession().getAttribute("currentUser");
        List<Order> orders = null;
        List<CampsiteOrder> camporders = null;
        DBContext db = new DBContext();
        
        if (auth != null) {
            request.setAttribute("person", auth);
            OrderDAO orderDao  = new OrderDAO(db.getConnection());
                orders = orderDao.userOrdersPayment(auth.getId());
            CampsiteOrderDAO camporderDao  = new CampsiteOrderDAO(db.getConnection());
                camporders = camporderDao.userOrdersPayment(auth.getId());
        }else{
                response.sendRedirect("login1.jsp");
        }
        ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
        if (cart_list != null) {
                request.setAttribute("cart_list", cart_list);
        }
	
%>
<body 
    <jsp:include page="header1.jsp"></jsp:include>
        <div class="container">
            <div class="row">
                <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 mx-auto">
                    <div class="card border-e shadow rounded-3" style="margin-top: 200px">
                        <div class="card-body p-4 p-sm-5">
                            <div class="container">
                                <div class="card-header my-3">GEAR ORDERS</div>
                                <table class="table table-light">
                                    <thead>
                                        <tr>
                                            <th scope="col">Time Order</th>
                                            <th scope="col">Name</th>
                                            <th scope="col">Description</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">Price</th>
                                            <th scope="col">Cancel</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                    <%
                                    if(orders != null){
                                            for(Order o:orders){%>
                                    <tr>
                                        <td><%=o.getTimeStamp() %></td>
                                        <td><%=o.getGearName() %></td>
                                        <td><%=o.getGearDecription() %></td>   
                                        <td><%=o.getQuantity() %></td> 
                                        <td><%=o.getGearPrice() %></td>
                                        <td><a class="btn btn-sm btn-danger" href="cancelorder?id=<%=o.getOrdersId()%>&redirectPage=orders.jsp">Cancel Order</a></td>
                                    </tr>
                                    <%}
                            }
                                    %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 mx-auto">
                <div class="card border-e shadow rounded-3" style="margin-top: 200px">
                    <div class="card-body p-4 p-sm-5">
                        <div class="container">
                            <div class="card-header my-3">CAMP ORDERS</div>
                            <table class="table table-light">
                                <thead>
                                    <tr>
                                        <th scope="col">Time Order</th>
                                        <th scope="col">Time Start</th>
                                        <th scope="col">Time End</th>
                                        <th scope="col">Days Book</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Description</th>
                                        <th scope="col">Number of people</th>
                                        <th scope="col">Price</th>
                                        <th scope="col">Cancel</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <%
                                    if(camporders != null){
                                            for(CampsiteOrder c:camporders){%>
                                    <tr>
                                        <td><%=c.getTimeStamp() %></td>
                                        <td><%=c.getStartDate() %></td>
                                        <td><%=c.getEndDate() %></td>
                                        <td><%=c.getBookingDays() %></td>
                                        <td><%=c.getCampName() %></td>
                                        <td><%=c.getCampDescription() %></td>                                         
                                        <td><%=c.getQuantity() %></td>                                     
                                        <td><%=c.getCampPrice() %></td>
                                        <td><a class="btn btn-sm btn-danger" href="cancelorder?id=<%=c.getOrderId()%>&redirectPage=orderscamp.jsp">Cancel Order</a></td>
                                    </tr>
                                    <%}
                                    }
                                    %>
                                </tbody>

                            </table>
                        </div>
                        <button href ="vnpay_pay.jsp" class="mx-3 btn btn-primary">Paynow</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>                            