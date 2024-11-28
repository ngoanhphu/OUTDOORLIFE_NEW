<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
    <div class="container">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                <div class="card border-e shadow rounded-3" style="margin-top: 200px">
                    <div class="card-body p-4 p-sm-5">
                        <h5 class="card-title text-center mb-5 fw-light fs-5">Register Account</h5>
                        <form id="form">
                        <c:if test="${not empty successMessage}">
                            <div class="alert alert-success">
                                ${successMessage}
                            </div>
                        </c:if>
                        <div id="error" style="color:red;"></div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="firstname" placeholder="firstName" name="firstname" required>
                            <label for="firstname">First Name</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="lastname" placeholder="lastName" name="lastname" required>
                            <label for="lastname">Last Name</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
                            <label for="email">Email</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input type="tel" class="form-control" id="phonenumber" name="phone" placeholder="phoneNumber" pattern="(09|03|07|08|05)[0-9]{8}" required>
                            <label for="phonenumber">Phone</label>
                        </div>                             
                        <div class="form-floating mb-3">
                            <input type="password" name="password" class="form-control" id="password" placeholder="Password" required>
                            <label for="password">Password</label>
                        </div>

                        <div class="form-check mb-3">
                            <input class="form-check-input" type="checkbox" value="on" name="agreed" id="rememberPasswordCheck" required>
                            <label class="form-check-label" for="rememberPasswordCheck">
                                Agree with us <a href="#" class="term-service">Terms of service</a>
                            </label>
                        </div>
                        <div class="g-recaptcha mb-3" data-sitekey="6LeBdOMpAAAAAO6dT9JJxUoS5-ZLBelui2f3Fma9"></div>
                        <div class="d-grid">
                            <button class="btn btn-primary btn-login text-uppercase fw-bold" type="submit">Sign up</button>
                        </div>
                        <hr class="my-4">
                        <p>Already have an account? <a href="login.jsp" class="link-info">Sign in</a></p>
                        <div class="d-grid mb-2">
                            <button class="btn btn-google btn-login text-uppercase fw-bold" type="button">
                                <i class="fab fa-google me-2"></i> Sign in with Google
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
                    agreed: $("#rememberPasswordCheck").is(":checked")
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
                                