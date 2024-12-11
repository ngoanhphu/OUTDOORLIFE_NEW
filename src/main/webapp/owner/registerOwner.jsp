<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Owner Registration</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #e8f5e9;
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
            color: #2e7d32;
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
        input[type="submit"], .btn-download, .btn-upload {
            width: 100%;
            padding: 10px;
            background-color: #4caf50;
            border: none;
            border-radius: 4px;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
            margin-top: 10px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
        }
        input[type="submit"]:hover, .btn-download:hover, .btn-upload:hover {
            background-color: #388e3c;
        }
        .form-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
        }
        .form-group.full-width {
            grid-column: span 3;
        }
        .form-group.inline {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .file-input-wrapper {
            position: relative;
            overflow: hidden;
            display: inline-block;
        }
        .file-input-wrapper input[type="file"] {
            font-size: 100px;
            position: absolute;
            left: 0;
            top: 0;
            opacity: 0;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Owner Registration</h2>
    <form action="registerOwner" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
        <div class="form-grid">
            <div class="form-group inline full-width">
                <a href="<c:url value='E:\Downloads\doc\up\contract.doc'/>" class="btn-download" download>Download Contract</a>
                <div class="file-input-wrapper">
                    <button class="btn-upload">Upload Signed Contract Document</button>
                    <input type="file" id="fileInput" name="fileInput" accept=".doc" required>
                </div>
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

        const identification = document.getElementById('identification').value;
        if (!/^\d{9}$/.test(identification) && !/^\d{12}$/.test(identification)) {
            alert('Identification number must be 9 or 12 digits.');
            return false;
        }

        const taxCode = document.getElementById('taxCode').value;
        if (!/^\d{10}$/.test(taxCode) && !/^\d{13}$/.test(taxCode)) {
            alert('Tax code must be 10 or 13 digits.');
            return false;
        }

        return true;
    }
</script>

</body>
</html>