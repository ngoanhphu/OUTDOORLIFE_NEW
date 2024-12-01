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
            <div class="form-group full-width">
                <label for="downloadDoc">Download Contract Document:</label>
                <a href="<c:url value='/resources/contract.doc'/>" download>Download contract.doc</a>
            </div>
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
                <label for="fileInput">Upload Signed Contract Document:</label>
                <input type="file" id="fileInput" name="fileInput" accept=".doc" required>
            </div>
            <div class="form-group">
                <label for="startDate">Start Date:</label>
                <input type="date" id="startDate" name="startDate" required>
            </div>
            <div class="form-group full-width">
                <label for="duration">Contract Duration:</label>
                <input type="radio" id="duration3" name="duration" value="3" required> 3 months
                <input type="radio" id="duration6" name="duration" value="6" required> 6 months
                <input type="radio" id="duration12" name="duration" value="12" required> 12 months
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
    function validateForm() {
        const dob = new Date(document.getElementById('dob').value);
        const startDate = new Date(document.getElementById('startDate').value);
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

        const duration = document.querySelector('input[name="duration"]:checked').value;
        const endDate = new Date(startDate);
        endDate.setMonth(endDate.getMonth() + parseInt(duration));

        const endDateInput = document.createElement('input');
        endDateInput.type = 'hidden';
        endDateInput.name = 'endDate';
        endDateInput.value = endDate.toISOString().split('T')[0];
        document.querySelector('form').appendChild(endDateInput);

        return true;
    }
</script>

</body>
</html>