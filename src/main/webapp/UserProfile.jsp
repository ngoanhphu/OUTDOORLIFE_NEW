<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.*, javax.servlet.*, model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile - Outdoor-Life</title>
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500;600&family=Roboto&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" type="text/css" rel="stylesheet">
    <style>
        .red {
            color: red;
        }
        .green {
            color: green;
        }
        .profile-container {
            max-width: 600px;
            margin: 150px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<!-- Navbar start -->
<div class="container-fluid fixed-top px-0">
    <div class="container px-0">
        <div class="topbar">
            <div class="row align-items-center justify-content-center">
                <div class="col-md-8">
                    <div class="topbar-info d-flex flex-wrap">
                        <a href="#" class="text-light me-4"><i class="fas fa-envelope text-white me-2"></i>@@@@@@fpt.edu.vn</a>
                        <a href="#" class="text-light"><i class="fas fa-phone-alt text-white me-2"></i>@@@@@@</a>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="topbar-icon d-flex align-items-center justify-content-end">
                        <a href="@@@@@" class="btn-square text-white me-2"><i class="fab fa-facebook-f"></i></a>
                        <a href="@@@@@" class="btn-square text-white me-2"><i class="fab fa-twitter"></i></a>
                        <a href="@@@@@" class="btn-square text-white me-2"><i class="fab fa-instagram"></i></a>
                        <a href="@@@@@" class="btn-square text-white me-2"><i class="fab fa-pinterest"></i></a>
                        <a href="https://www.facebook.com/Phuc250703/" class="btn-square text-white me-0"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <nav class="navbar navbar-light bg-light navbar-expand-xl">
            <a href="index.html" class="navbar-brand ms-3">
                <h1 class="text-primary display-5">Outdoor-Life</h1>
            </a>
            <button class="navbar-toggler py-2 px-3 me-3" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="fa fa-bars text-primary"></span>
            </button>
            <div class="collapse navbar-collapse bg-light" id="navbarCollapse">
                <div class="navbar-nav ms-auto">
                    <a href="user.jsp" class="nav-item nav-link active">Home</a>
                    <a href="about.html" class="nav-item nav-link">About</a>
                    <a href="service.html" class="nav-item nav-link">Services</a>
                    <a href="campsite.html" class="nav-item nav-link">Campsite</a>
                    <a href="campinggear.html" class="nav-item nav-link">Camping Gear</a>
                    <a href="contact.html" class="nav-item nav-link">Contact</a>
                </div>
                <div class="d-flex align-items-center flex-nowrap pt-xl-0" style="margin-left: 15px;">
                    <a href="UserProfile.jsp" class="btn btn-light text-primary me-3">
                        <i class="fas fa-user-circle fa-2x"></i>
                    </a>
                </div>
            </div>
        </nav>
    </div>
</div>
<!-- Navbar End -->

<!-- Profile Section Start -->
<div class="profile-container">
    <%
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
    %>
    <h1>You are not logged in</h1>
    <p class="text-center">Please return to the <a href="index.html">home page</a> to log in.</p>
    <% } else { %>
    <h1>Update Your Profile</h1>
    <form action="ProfileServlet" method="post">
        <div class="mb-3">
            <label for="firstName" class="form-label">First Name</label>
            <input type="text" class="form-control" id="firstName" name="firstName" value="<%= user.getFirstName() %>">
        </div>
        <div class="mb-3">
            <label for="lastName" class="form-label">Last Name</label>
            <input type="text" class="form-control" id="lastName" name="lastName" value="<%= user.getLastName() %>">
        </div>
        <div class="mb-3">
            <label for="phoneNumber" class="form-label">Phone Number</label>
            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="<%= user.getPhoneNumber() %>">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="<%= user.getEmail() %>" readonly>
        </div>
        <div class="text-center">
            <a href="changePassword.jsp?user=<%= user.getId() %>" class="btn btn-secondary me-2">Change Password</a>
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form>
    <% } %>
</div>
<!-- Profile Section End -->

<!-- Footer Start -->
<footer>
    <div class="container text-center">
        <p>&copy; 2024 Outdoor-Life. All Rights Reserved.</p>
    </div>
</footer>
<!-- Footer End -->

<!-- Scroll to Top Button -->
<a href="#" class="btn-scroll-top d-flex"><i class="fas fa-chevron-up"></i></a>

<script src="js/bootstrap.bundle.min.js"></script>
<script>
    const scrollTopBtn = document.querySelector('.btn-scroll-top');
    window.addEventListener('scroll', () => {
        scrollTopBtn.style.display = window.scrollY > 200 ? 'flex' : 'none';
    });
</script>
</body>
</html>
