<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card border-e shadow rounded-3" style="margin-top: 200px">
                <div class="card-body p-4 p-sm-5">
                    <h5 class="card-title text-center mb-5 fw-light fs-5">Register Account</h5>
                    <form id="form" action="registeracc">
                        <c:if test="${not empty successMessage}">
                            <div class="alert alert-success">
                                    ${successMessage}
                            </div>
                        </c:if>
                        <div id="error" class="text-danger mb-3"></div>

                        <!-- First Name -->
                        <div class="input-group mb-3">
                            <span class="input-group-text bg-primary text-white">
                                <i class="bi bi-person"></i>
                            </span>
                            <input type="text" class="form-control" id="firstname" placeholder="First Name" name="firstname" required>
                        </div>

                        <!-- Last Name -->
                        <div class="input-group mb-3">
                            <span class="input-group-text bg-primary text-white">
                                <i class="bi bi-person-fill"></i>
                            </span>
                            <input type="text" class="form-control" id="lastname" placeholder="Last Name" name="lastname" required>
                        </div>

                        <!-- Email -->
                        <div class="input-group mb-3">
                            <span class="input-group-text bg-primary text-white">
                                <i class="bi bi-envelope"></i>
                            </span>
                            <input type="email" class="form-control" id="email" placeholder="Email" name="email" required>
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text bg-primary text-white">
                                <i class="bi bi-telephone"></i>
                            </span>
                            <input type="tel" class="form-control" id="phonenumber" placeholder="Phone Number" name="phone" pattern="(09|03|07|08|05)[0-9]{8}" required>
                        </div>


                        <!-- Password -->
                        <div class="input-group mb-3">
                            <span class="input-group-text bg-primary text-white">
                                <i class="bi bi-lock"></i>
                            </span>
                            <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
                        </div>

                        <!-- Agree to terms -->
                        <div class="form-check mb-3">
                            <input class="form-check-input" type="checkbox" id="rememberPasswordCheck" name="agreed" required>
                            <label class="form-check-label" for="rememberPasswordCheck">
                                I agree to the <a href="#" class="text-decoration-underline">Terms of Service</a>
                            </label>
                        </div>
                        <!-- Thêm checkbox đăng ký làm owner -->
                        <div class="form-check mb-3">
                            <input class="form-check-input" type="checkbox" value="on" name="isOwner" id="isOwnerCheck">
                            <label class="form-check-label" for="isOwnerCheck">
                                Register as Owner
                            </label>
                        </div>
                        <div class="g-recaptcha mb-3" data-sitekey="6LeBdOMpAAAAAO6dT9JJxUoS5-ZLBelui2f3Fma9"></div>

                        <!-- Submit Button -->
                        <div class="d-grid mb-3">
                            <button class="btn btn-primary btn-lg fw-bold" type="submit">Sign Up</button>
                        </div>
                        <p>If you have an account? <a href="login.jsp" class="link-info">login here</a></p>
                        <!-- Alternative Sign-In -->
                        <p class="text-center mb-2">or sign in with:</p>
                        <div class="d-grid">
                            <button class="btn btn-outline-danger btn-lg fw-bold" type="button">
                                <i class="fab fa-google me-2"></i> Google
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://www.google.com/recaptcha/api.js" async defer></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    window.onload = function () {
        const form = document.getElementById("form");
        const error = document.getElementById("error");

        form.addEventListener("submit", function (event) {
            event.preventDefault();
            const response = grecaptcha.getResponse();
            if (response.length === 0) {
                error.textContent = "Please complete the reCAPTCHA.";
            } else {
                error.textContent = "";  // Clear error message

                var formData = {
                    firstname: $("#firstname").val(),
                    lastname: $("#lastname").val(),
                    email: $("#email").val(),
                    phone: $("#phonenumber").val(),
                    password: $("#password").val(),
                    agreed: $("#rememberPasswordCheck").is(":checked"),
                    isOwner: $("#isOwnerCheck").is(":checked") // Truyền trạng thái checkbox "Register as Owner"
                };

                $.ajax({
                    type: "POST",
                    url: "registeracc",
                    data: formData,
                    success: function (response) {
                        if (response.success) {
                            window.location.href = "verify.jsp";
                        } else {
                            error.textContent = response.message;
                        }
                    },
                    error: function () {
                        error.textContent = "There was an error processing your request.";
                    }
                });
            }
        });
    };
</script>
<jsp:include page="footer.jsp"></jsp:include>
