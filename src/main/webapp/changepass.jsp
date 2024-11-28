<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="changepass.css"/>
</head>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password</title>
    <link rel="stylesheet" href="login.css">
</head>
<body>
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="card w-50">
            <div class="card-body">
                <div class="logo-container mb-4">
                    <img src="img/logo.jpg"
                         alt="Login image" class="logo-circle">
                </div>
                <h3 class="text-center mb-4">Change Password</h3>
                <form>
                    <div class="form-outline mb-4">
                        <input type="password" id="current-password" class="form-control form-control-lg" placeholder=" " required>
                        <label for="current-password">Current Password</label>
                    </div>
                    <div class="form-outline mb-4">
                        <input type="password" id="new-password" class="form-control form-control-lg" placeholder=" " required>
                        <label for="new-password">New Password</label>
                    </div>
                    <div class="form-outline mb-4">
                        <input type="password" id="confirm-password" class="form-control form-control-lg" placeholder=" " required>
                        <label for="confirm-password">Confirm New Password</label>
                    </div>
                    <div class="d-grid">
                        <button type="submit" class="btn btn-info btn-lg btn-block">Change Password</button>
                    </div>
                    <div class="text-center mt-3">
                        <a href="login.jsp" class="link-info">Back to Login</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
<jsp:include page="footer.jsp"></jsp:include>