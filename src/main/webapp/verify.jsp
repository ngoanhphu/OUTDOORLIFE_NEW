<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <div class="card shadow-lg border-0 rounded-4" style="margin-top: 150px;">
                <div class="card-body p-4">
                    <h3 class="text-center text-primary mb-4">Verify Your OTP</h3>
                    <form action="verifycode" method="post">

                        <!-- Hiển thị thông báo lỗi hoặc thành công -->
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger text-center">
                                    ${errorMessage}
                            </div>
                        </c:if>
                        <c:if test="${not empty successMessage}">
                            <div class="alert alert-success text-center">
                                    ${successMessage}
                            </div>
                        </c:if>

                        <!-- Trường nhập OTP -->
                        <div class="input-group mb-4">
                            <span class="input-group-text bg-primary text-white">
                                <i class="bi bi-shield-lock"></i>
                            </span>
                            <input type="text" class="form-control" id="otp" placeholder="Enter 6-digit OTP" name="authcode" pattern="[0-9]{6}" required>
                        </div>

                        <!-- Nút xác minh -->
                        <div class="d-grid">
                            <button class="btn btn-primary btn-lg fw-bold" type="submit">Verify OTP</button>
                        </div>

                        <!-- Liên kết hỗ trợ -->
                        <div class="text-center mt-3">
                            <p class="small text-muted">Didn't receive an OTP? <a href="resendotp.jsp" class="text-primary">Resend OTP</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
