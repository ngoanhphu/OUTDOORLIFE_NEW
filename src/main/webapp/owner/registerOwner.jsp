<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Owner Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 800px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }
        input[type="text"],
        input[type="date"],
        input[type="number"],
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .image-preview {
            display: block;
            margin-top: 10px;
            max-width: 100%;
            max-height: 200px;
        }
        .form-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
        }
        .form-group.full-width {
            grid-column: span 3;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Owner Registration</h2>
    <form action="registerOwner" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
        <div class="form-grid">
            <div class="form-group">
                <label for="occupation">Occupation:</label>
                <input type="text" id="occupation" name="occupation" required>
            </div>
            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required>
            </div>
            <div class="form-group">
                <label for="taxCode">Tax Code:</label>
                <input type="number" id="taxCode" name="taxCode" required>
            </div>
            <div class="form-group">
                <label for="dob">Date of Birth:</label>
                <input type="date" id="dob" name="dob" required>
            </div>
            <div class="form-group">
                <label for="region">Region:</label>
                <input type="text" id="region" name="region" required>
            </div>
            <div class="form-group">
                <label for="identification">Identification:</label>
                <input type="number" id="identification" name="identification" required>
            </div>
            <div class="form-group">
                <label for="gender">Gender:</label>
                <select id="gender" name="gender" required>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="none">None of above</option>
                </select>
            </div>
            <div class="form-group full-width">
                <label for="fileInput">Image:</label>
                <input type="file" id="fileInput" name="fileInput" accept="image/*" required>
                <img id="imagePreview" class="image-preview" src="#" alt="Image Preview" style="display: none;">
            </div>
            <div class="form-group">
                <label for="startDate">Start Date:</label>
                <input type="date" id="startDate" name="startDate" required>
            </div>
            <div class="form-group">
                <label for="endDate">End Date:</label>
                <input type="date" id="endDate" name="endDate" required>
            </div>
            <div class="form-group full-width">
                <label for="notes">Notes:</label>
                <textarea id="notes" name="notes" rows="4" cols="50"></textarea>
            </div>
            <div class="form-group full-width">
                <input type="submit" value="Register Contract">
            </div>
        </div>
    </form>
</div>

<script>
    const fileInput = document.getElementById('fileInput');
    const imagePreview = document.getElementById('imagePreview');

    fileInput.addEventListener('change', (event) => {
        const file = event.target.files[0];
        if (file) {
            if (file.size > 4 * 1024 * 1024) {
                alert('File size exceeds 4MB. Please choose a smaller file.');
                fileInput.value = '';
                imagePreview.style.display = 'none';
                return;
            }
            const reader = new FileReader();
            reader.onload = (e) => {
                imagePreview.src = e.target.result;
                imagePreview.style.display = 'block';
            };
            reader.readAsDataURL(file);
        }
    });

    function validateForm() {
        const dob = new Date(document.getElementById('dob').value);
        const startDate = new Date(document.getElementById('startDate').value);
        const endDate = new Date(document.getElementById('endDate').value);
        const today = new Date();
        let age = today.getFullYear() - dob.getFullYear();
        const monthDiff = today.getMonth() - dob.getMonth();
        const dayDiff = today.getDate() - dob.getDate();

        if (monthDiff < 0 || (monthDiff === 0 && dayDiff < 0)) {
            age--;
        }

        if (age < 18) {
            alert('You must be at least 18 years old.');
            return false;
        }

        if (startDate <= today) {
            alert('Start date must be after today\'s date.');
            return false;
        }

        const oneMonthLater = new Date(startDate);
        oneMonthLater.setMonth(oneMonthLater.getMonth() + 1);

        if (endDate <= oneMonthLater) {
            alert('End date must be at least one month after the start date.');
            return false;
        }

        return true;
    }
</script>

</body>
</html>