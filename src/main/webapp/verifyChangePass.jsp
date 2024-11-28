<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card border-e shadow rounded-3" style="margin-top: 200px;">
                <div class="card-body p-4 p-sm-5">
                    <h5 class="card-title text-center mb-5 fw-light fs-5">Verify OTP & Reset Password</h5>
                    <form action="confirmotp" method="post">
                        <c:if test="${not empty errorMessage}">
                            <p style="color:red">${errorMessage}</p>
                        </c:if>
                        <c:if test="${not empty successMessage}">
                            <p style="color:green">${successMessage}</p>
                        </c:if>

                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="otp" placeholder="OTP" name="authcode" required>
                            <label for="otp">Enter OTP</label>
                        </div>

                        <div class="d-grid">
                            <button class="btn btn-primary btn-login text-uppercase fw-bold" type="submit">Reset Password</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

