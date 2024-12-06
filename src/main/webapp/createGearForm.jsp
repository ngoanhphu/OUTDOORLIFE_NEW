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
<%--    <jsp:include page="headeradmin.jsp"></jsp:include>--%>
    <div class="container" style="margin-top: 200px">
        <div class="form-container">
            <div class="form-header">
                <h2>Add New Gear</h2>
            </div>
            <form action="addGear" method="post">
                <div class="form-group">
                    <label for="gearName">Gear Name:</label>
                    <input type="text" id="gearName" name="gearName" required>
                </div>
                <div class="form-group">
                    <label for="gearPrice">Gear Price:</label>
                    <input type="number" id="gearPrice" name="gearPrice" required>
                </div>
                <div class="form-group">
                    <label for="gearDescription">Description:</label>
                    <textarea id="gearDescription" name="gearDescription" required></textarea>
                </div>
                <div class="form-group">
                    <label for="gearImage">Image URL:</label>
                    <input type="text" id="gearImage" name="gearImage" required>
                </div>
                <div class="form-group">
                    <input type="submit" value="Add Gear">
                </div>
            </form>
        </div>
    </div>
</body>