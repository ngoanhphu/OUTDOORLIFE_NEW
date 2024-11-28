<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    .container {
        padding: 2rem 0;
    }
    .form-container {
        max-width: 600px;
        margin: 0 auto;
        background-color: #fff;
        padding: 2rem;
        border-radius: 0.5rem;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    .form-header {
        text-align: center;
        margin-bottom: 2rem;
    }
    .form-group {
        margin-bottom: 1.5rem;
    }
    .form-group label {
        display: block;
        margin-bottom: 0.5rem;
        font-weight: bold;
    }
    .form-group input, .form-group textarea {
        width: 100%;
        padding: 0.75rem;
        border: 1px solid #ccc;
        border-radius: 0.5rem;
    }
    .form-group textarea {
        resize: vertical;
        height: 100px;
    }
    .form-group input[type="submit"] {
        background-color: #007bff;
        color: white;
        border: none;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    .form-group input[type="submit"]:hover {
        background-color: #0056b3;
    }
</style>
<body>
    <jsp:include page="headeradmin.jsp"></jsp:include>
    <div class="container" style="margin-top: 200px">
        <div class="form-container">
            <div class="form-header">
                <h2>Add Vouchers</h2>
            </div>
            <form action="add-voucher" method="post">
                <div class="form-group">
                    <label for="firstName">Percent:</label>
                    <input type="number" id="firstName" name="percent" min="1" max="100" required>
                </div>
                <div class="form-group">
                    <label for="lastName">Start Date:</label>
                    <input type="date" id="lastName" name="startDate" required value="${currentDate}">
                </div>
                <div class="form-group">
                    <label for="email">End Date:</label>
                    <input type="date" id="email" name="endDate" required value="${currentDate}">
                </div>
                <div class="form-group">
                    <input type="submit" value="Add">
                </div>
            </form>
        </div>
    </div>
                <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>

            <c:if test="${error != null}">
                <script type="text/javascript">
                toastr.error(`${error}`, 'Error', {timeOut: 1000});
                </script>
            </c:if>
</body>
