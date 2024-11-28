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

<!DOCTYPE html>
<html>
    <body>
        <c:if test="${action != null && action eq 'admin'}">
             <jsp:include page="headeradmin.jsp"></jsp:include>
            </c:if>
        <c:if test="${action == null}">
        <jsp:include page="header.jsp"></jsp:include>
        </c:if>
            <div class="container">
                <div class="row">
                    <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 mx-auto">
                        <div class="card border-e shadow rounded-3" style="margin-top: 200px">
                            <div class="card-body p-4 p-sm-5">
                                <div class="container">
                                    <div class="card-header my-3">Campsite Information</div>
                                    <div>
                                        <div style="padding-left: 20px; padding-bottom: 20px">
                                            Name: ${campsite.campsiteName}<br>
                                        Start Date : ${campsite.startDate}<br>
                                        End Date : ${campsite.endDate}<br>
                                        Quantity: ${campsite.quantity}<br>
                                        Booking Price : ${campsite.totalAmountBooking}<br>
                                        Total Amount (booking + gear - discount): ${campsite.totalAmount}
                                    </div>
                                </div>
                                <div class="card-header my-3">All Gears</div>
                                <table class="table table-light">
                                    <thead>
                                        <tr>
                                            <th scope="col">Image</th>
                                            <th scope="col">Gear Name</th>
                                            <th scope="col">Description</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">Sub Amount</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${ods}" var="o">
                                            <tr>
                                                <td>
                                                    <img src="img/${o.gear.gearImage}" alt="Card image cap" style="width: 100px"/></td>
                                                <td>${o.gear.gearName}</td>
                                                <td>${o.gear.gearDecription}</td>
                                                <td>${o.quantity}</td>
                                                <td>${o.subAmount}</td>


                                            </tr>
                                        </c:forEach>
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
