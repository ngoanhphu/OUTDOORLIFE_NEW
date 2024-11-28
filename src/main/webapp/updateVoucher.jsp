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
                    <h2>Update Voucher</h2>
                </div>
                <form action="update-voucher" method="post">
                    <div class="form-group">
                        <label for="name">Code:</label>
                        <input type="hidden" id="id" name="id" value="${c.id}">
                    <input type="text" id="name" name="code" readonly value="${c.code}">
                </div>
                <div class="form-group">
                    <label for="price">Percent:</label>
                    <input type="number" id="price" name="percent" required value="${c.percent}" min="0" max="100">
                </div>
                <div class="form-group">
                    <label for="address">Start Date:</label>
                    <input type="date" id="price" name="startDate" required value="${c.startDate}">
                </div>
                <div class="form-group">
                    <label for="description">End Date:</label>
                    <input type="date" id="price" name="endDate" required value="${c.endDate}">
                </div>
                <div class="form-group">
                    <label for="status">Is Used:</label>
                    <select id="status" name="isUsed">
                        <option value="0" ${c.isUsed == false ? 'selected':''}>Not Used</option>
                        <option value="1" ${c.isUsed == true ? 'selected':''}>Is Used</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="submit" value="Update">
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