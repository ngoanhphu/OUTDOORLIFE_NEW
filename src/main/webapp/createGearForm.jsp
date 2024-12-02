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
    .form-group input[type="submit"], .btn {
        background-color: #007bff;
        color: white;
        border: none;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }
    .form-group input[type="submit"]:hover, .btn:hover {
        background-color: #0056b3;
    }
    .btn {
        margin: 5px;
        padding: 0.5rem 1rem;
        border-radius: 0.3rem;
    }
    .gear-list-container {
        margin-top: 20px;
    }
    .gear-item {
        border-bottom: 1px solid #ddd;
        padding: 10px 0;
    }
</style>

<body>
<%--<jsp:include page="headeradmin.jsp"></jsp:include>--%>

<!-- Form for adding new gear -->
<div class="container">
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

<!-- List of gears with Update and Delete actions -->
<div class="container gear-list-container">
    <h2>Gear List</h2>
    <c:forEach var="gear" items="${gears}">
        <div class="gear-item">
            <h3>${gear.name}</h3>
            <p>${gear.description}</p>
            <p>Price: ${gear.price}</p>
            <img src="${gear.image}" alt="${gear.name}" style="max-width: 100px;">

            <div>
                <!-- Update button (open a form or redirect to edit page) -->
                <a href="editGear?id=${gear.gearId}" class="btn">Update</a>

                <!-- Delete button (trigger delete action) -->
                <form action="deleteGear" method="post" style="display:inline;">
                    <input type="hidden" name="gearId" value="${gear.gearId}">
                    <input type="submit" value="Delete" class="btn" onclick="return confirm('Are you sure you want to delete this gear?');">
                </form>
            </div>
        </div>
    </c:forEach>
</div>
</body>
