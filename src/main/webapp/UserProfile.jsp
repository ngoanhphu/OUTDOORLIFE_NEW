<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.*, javax.servlet.*, model.User" %>
<jsp:include page="header.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card border-e shadow rounded-3" style="margin-top: 200px">
                <div class="card-body p-4 p-sm-5">
                    <h5 class="card-title text-center mb-5 fw-light fs-5">Update Your Profile</h5>

                    <!-- Hiển thị thông báo thành công -->
                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success text-center">
                                ${successMessage}
                        </div>
                    </c:if>

                    <!-- Hiển thị thông báo lỗi -->
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger text-center">
                                ${error}
                        </div>
                    </c:if>

                    <%
                        User user = (User) session.getAttribute("currentUser");
                        if (user == null) {
                    %>
                    <div class="alert alert-warning text-center">
                        You are not logged in. Please <a href="index.html" class="text-decoration-underline">log in</a>.
                    </div>
                    <% } else { %>
                    <form action="ProfileServlet" method="post">
                        <!-- First Name -->
                        <div class="input-group mb-3">
                            <span class="input-group-text bg-primary text-white">
                                <i class="bi bi-person"></i>
                            </span>
                            <input type="text" class="form-control" id="firstName" name="firstName" value="<%= user.getFirstName() %>" required>
                        </div>

                        <!-- Last Name -->
                        <div class="input-group mb-3">
                            <span class="input-group-text bg-primary text-white">
                                <i class="bi bi-person-fill"></i>
                            </span>
                            <input type="text" class="form-control" id="lastName" name="lastName" value="<%= user.getLastName() %>" required>
                        </div>

                        <!-- Phone Number -->
                        <div class="input-group mb-3">
                            <span class="input-group-text bg-primary text-white">
                                <i class="bi bi-telephone"></i>
                            </span>
                            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="<%= user.getPhoneNumber() %>" required>
                        </div>

                        <!-- Email -->
                        <div class="input-group mb-3">
                            <span class="input-group-text bg-primary text-white">
                                <i class="bi bi-envelope"></i>
                            </span>
                            <input type="email" class="form-control" id="email" name="email" value="<%= user.getEmail() %>" readonly>
                        </div>

                        <!-- Submit and Change Password -->
                        <div class="d-grid gap-2">
                            <a href="changePassword.jsp?user=<%= user.getId() %>" class="btn btn-secondary">Change Password</a>
                            <button type="submit" class="btn btn-primary">Update Profile</button>
                        </div>
                    </form>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>
