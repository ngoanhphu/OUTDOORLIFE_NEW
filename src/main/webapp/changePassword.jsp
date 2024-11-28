<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="login.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    <style>
        .password-container {
            position: relative;
        }
        .success-message {
            color: green;
            font-weight: bold;
        }
        .error-message {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="card w-50">
            <div class="card-body">
                <div class="logo-container mb-4">
                    <img src="img/logo.jpg" alt="Login image" class="logo-circle">
                </div>
                <h3 class="text-center mb-4">Change Password</h3>
                <% 
                    String message = (String) request.getAttribute("message");
                    if (message != null) {
                        if (message.equals("success")) { %>
                            <p class="success-message">Password changed successfully.</p>
                        <% } else if (message.equals("failure")) { %>
                            <p class="error-message">Password change failed. Incorrect current password.</p>
                        <% } else if (message.equals("mismatch")) { %>
                            <p class="error-message">Passwords do not match.</p>
                        <% } else if (message.equals("error")) { %>
                            <p class="error-message">An error occurred. Please try again.</p>
                        <% }
                    }
                %>
                <form action="ChangePasswordServlet" method="post">
                    <div class="form-outline mb-4 password-container">
                        <input type="password" id="current-password" name="currentPassword" class="form-control form-control-lg" placeholder=" " required>
                        <label for="current-password">Current Password</label>
                    </div>
                    <div class="form-outline mb-4 password-container">
                        <input type="password" id="new-password" name="newPassword" class="form-control form-control-lg" placeholder=" " required>
                        <label for="new-password">New Password</label>
                    </div>
                    <div class="form-outline mb-4 password-container">
                        <input type="password" id="confirm-password" name="confirmPassword" class="form-control form-control-lg" placeholder=" " required>
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
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>