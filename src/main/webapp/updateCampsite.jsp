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
<div class="container" style="margin-top: 200px">
    <div class="form-container">
        <div class="form-header">
            <h2>Update Campsite</h2>
        </div>
        <form action="update-campsite" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="name">Campsite Name:</label>
                <input type="hidden" id="id" name="id" value="${c.campId}">
                <input type="text" id="name" name="name" required value="${c.campName}">
            </div>
            <div class="form-group">
                <label for="price">Campsite Price:</label>
                <input type="number" id="price" name="price" required value="${c.campPrice}">
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <textarea id="address" name="address" required>${c.campAddress}</textarea>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" required>${c.campDescription}</textarea>
            </div>
            <div class="form-group">
                <label for="image">Image:</label>
                <input type="file" id="image" name="image" accept="image/*">
                <p>Current Image: ${c.campImage}</p> <!-- Display current image -->
            </div>
            <div class="form-group">
                <label for="limit">Limit:</label>
                <input type="number" id="limit" name="limit" required value="${c.limite}">
            </div>
            <div class="form-group">
                <label for="status">Status:</label>
                <select id="status" name="status">
                    <option value="1" ${c.campStatus == true ? 'selected' : ''}>Active</option>
                    <option value="0" ${c.campStatus == false ? 'selected' : ''}>Inactive</option>
                </select>
            </div>
            <div class="form-group">
                <input type="submit" value="Update">
            </div>
        </form>

    </div>
</div>
</body>
