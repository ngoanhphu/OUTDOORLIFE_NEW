<%@page contentType="text/html" pageEncoding="UTF-8"%>
<head>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="login.css"/>
</head>
<section class="vh-100">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6 text-black d-flex flex-column align-items-center justify-content-center">
                <div class="logo-container mb-4">
                    <img src="img/logo.jpg"
                         alt="Login image" class="logo-circle">
                </div>

                <div class="d-flex align-items-center h-custom-2 px-5 ms-xl-4 mt-5 pt-5 pt-xl-0 mt-xl-n5">

                    <form action="loginservlet1" method="post" style="width: 23rem;">

                        <h1 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px; color: #007bff; font-size: 2rem; text-shadow: 2px 2px 4px rgba(0,0,0,0.2);">SIGN IN</h1>

                        <c:if test="${not empty errorMessage}">
                            <p style="color:red">${errorMessage}</p>
                        </c:if>
                        
                        <div class="form-floating mb-3">
                            <input type="email" class="form-control" id="floatingEmail" placeholder="Email address" name="email" required>
                            <label for="floatingEmail">Email address</label>
                        </div>


                        <div class="form-floating mb-3">
                            <a href="login.jsp"></a>
                            <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" required>
                            <label for="floatingPassword">Password</label>
                        </div>

                        <div class="d-grid">
                            <button class="btn btn-primary btn-login text-uppercase fw-bold" type="submit">Sign in</button>
                        </div>

                        <p class="small mb-5 pb-lg-2"><a class="text-muted" href="forgotpass.jsp">Forgot password?</a></p>
                        <p>Don't have an account? <a href="Register.jsp" class="link-info">Register here</a></p>


                    </form>

                </div>
            </div>
            <div class="col-sm-6 px-0 d-none d-sm-block">
                <img src="img/camping.jpg"
                     alt="Login image" class="w-100 vh-100" style="object-fit: cover; object-position: left;">
            </div>



        </div>
    </div>
</section>

<jsp:include page="footer.jsp"></jsp:include>
