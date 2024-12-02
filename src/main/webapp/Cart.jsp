<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.DBContext"%>
<%@page import="dao.*"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>

<%
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);
    User auth = (User) request.getSession().getAttribute("currentUser");
    if (auth != null) {
        request.setAttribute("person", auth);
    }

    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    double total = 0;
    DBContext dbContext = new DBContext();
    if (cart_list != null) {
        GearDAO pDao = new GearDAO(dbContext.getConnection());
        cartProduct = pDao.getCartGears(cart_list);
        total = pDao.getTotalCartPrice(cart_list);
        request.setAttribute("total", total);
        request.setAttribute("cart_list", cart_list);
        request.setAttribute("cartProduct", cartProduct);
    }
    int totalInt = (int) total;
    session.setAttribute("totalInt", totalInt);
    int discount = 0;
    int billTotal = 0;
    CampsiteOrder campsiteOrder = (CampsiteOrder) session.getAttribute("CampsiteOrder");
    if(campsiteOrder != null && campsiteOrder.getVoucherId() != null){
        VoucherDAO vdao = new VoucherDAO();
        Voucher v = vdao.getVoucherById(campsiteOrder.getVoucherId());
        discount = v.getPercent();
        billTotal = campsiteOrder.getTotalAmountBooking() * (100 - discount) /100;
    }
%>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-12 col-xl-12 mx-auto">
            <div class="card border-e shadow rounded-3" style="margin-top: 200px">
                <div class="card-body p-4 p-sm-5">
                    <div>
                        <h3>Campsite booking information:</h3>
                        <c:if test="${sessionScope.CampsiteOrder != null}">
                            <div style="padding-left: 20px; padding-bottom: 20px">
                                Id: ${sessionScope.CampsiteOrder.campsiteId}<br>
                                Start Date : ${sessionScope.CampsiteOrder.startDate}<br>
                                End Date : ${sessionScope.CampsiteOrder.endDate}<br>
                                Quantity: ${sessionScope.CampsiteOrder.quantity}<br>
                                <span style="color: red">Campsite Total: ${sessionScope.CampsiteOrder.totalAmountBooking}</span>
                            </div>
                        </c:if><c:if test="${sessionScope.CampsiteOrder == null}">
                        <div style="padding-left: 20px; padding-bottom: 20px">Please booking <a href="campsite.jsp">campsite</a></div>
                    </c:if>
                    </div>
                    <h3>Gear order information:</h3>
                    <div class="total-price-container">
                        <!--<h3>Total: <%= total > 0 ? dcf.format(total) + "₫" : "0 ₫" %> </h3>-->
                        <!--<a class="mx-3 btn btn-primary" href="checkout">Order All</a>-->
                    </div>
                    <form id="cart-form" action="partialCheckout" method="post">
                        <table class="table table-light">
                            <thead>
                            <tr>
                                <th scope="col">Select</th>
                                <th scope="col">Name</th>
                                <th scope="col">Category</th>
                                <th scope="col">Price</th>
                                <th scope="col">Order now</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Cancel</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                if (cart_list != null && cartProduct != null) {
                                    for (Cart c : cartProduct) {
                            %>
                            <tr>
                                <td><input type="checkbox" name="selectedItems" value="<%= c.getGearId() %>" class="form-check-input" onclick="calculateSelectedTotal()"></td>
                                <td><%=c.getGearName()%></td>
                                <td><%=c.getGearDecription()%></td>
                                <td class="gear-price"><%= dcf.format(c.getGearPrice()) %></td>
                                <td>
                                    <button type="button" class="btn btn-primary btn-sm" onclick="orderNow('<%= c.getGearId()%>')">Order</button>
                                </td>
                                <td>
                                    <div class="form-group d-flex justify-content-between">
                                        <a class="btn btn-sm btn-incre" href="quantityset?action=inc&id=<%=c.getGearId()%>">
                                            <i class="fas fa-plus-square"></i>
                                        </a>
                                        <input type="text" name="quantity" class="form-control" value="<%=c.getQuantity()%>" readonly>
                                        <a class="btn btn-sm btn-decre" href="quantityset?action=dec&id=<%=c.getGearId()%>">
                                            <i class="fas fa-minus-square"></i>
                                        </a>
                                    </div>
                                </td>
                                <td><a href="remove?id=<%=c.getGearId()%>" class="btn btn-sm btn-danger">Remove</a></td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                            </tbody>
                        </table>
                        <div class="total-price-container">
                            <div style="margin-left: 10px; margin-top: 5px;">
                                <a href="viewgear.jsp" class="btn btn-success btn-sm">Add Gear</a>
                            </div>
                            <p style="color: red">Selected Gears Total: <span id="selected-total-price">0</span> ₫</p>
                            <h3>Sub Total: <span id="sub-total">${sessionScope.CampsiteOrder != null ? sessionScope.CampsiteOrder.totalAmountBooking : 0}</span> ₫</h3>
                            <h3>Discount: <span id="discount-price">- <%= discount%></span>%</h3>
                            <h3 style="color: red">Total: <span id="bill-total"><%=billTotal%></span> ₫</h3>
                            <input type="radio" name="paymentMethod" value="VNPay" checked=""/>VNpay<br>
                            <input type="radio" name="paymentMethod" value="PayLater"/>Pay later<br>
                            <button type="summit" class="mx-3 btn btn-primary" onclick="submitSelectedItems()">Order Selected</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function calculateSelectedTotal() {
        let total = 0;
        document.querySelectorAll('input[name="selectedItems"]:checked').forEach(function (checkbox) {
            let priceElement = checkbox.closest('tr').querySelector('.gear-price');
            let price = parseFloat(priceElement.innerText.replace(/[^0-9.-]+/g, ""));

            let quantityElement = checkbox.closest('tr').querySelector('.form-control');
            let quantity = parseInt(quantityElement.value);

            let subtotal = price * quantity;
            total += subtotal;
        });
        document.getElementById('selected-total-price').innerText = total;

        var totalCampsite = ${sessionScope.CampsiteOrder != null ?sessionScope.CampsiteOrder.totalAmountBooking : 0};
        document.getElementById('sub-total').innerText = total + totalCampsite;
        document.getElementById('bill-total').innerText = (total + totalCampsite) * (100 - <%=discount%>)/100;
    }

    function orderNow(gearId) {
        let quantityElement = document.querySelector('input[name="quantity"]');
        let quantity = parseInt(quantityElement.value); // Lấy số lượng từ input

        // Cài đặt logic để gửi yêu cầu đặt hàng tới servlet
        let form = document.createElement('form');
        form.setAttribute('method', 'post');
        form.setAttribute('action', 'ordernow');

        let idInput = document.createElement('input');
        idInput.setAttribute('type', 'hidden');
        idInput.setAttribute('name', 'id');
        idInput.setAttribute('value', gearId);
        form.appendChild(idInput);

        let quantityInput = document.createElement('input');
        quantityInput.setAttribute('type', 'hidden');
        quantityInput.setAttribute('name', 'quantity');
        quantityInput.setAttribute('value', quantity);
        form.appendChild(quantityInput);

        document.body.appendChild(form);
        form.submit();
    }


</script>
<link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css" rel="stylesheet" />
<script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<c:if test="${message != null}">
    <script type="text/javascript">
        toastr.success(${message}, 'Success', {timeOut: 1000});
    </script>
</c:if>
<c:if test="${error != null}">
    <script type="text/javascript">
        toastr.error(${error}, 'Error', {timeOut: 1000});
    </script>
</c:if>
</body>