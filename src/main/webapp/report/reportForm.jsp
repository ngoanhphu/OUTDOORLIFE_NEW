<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if (session.getAttribute("reportType") == null) {
        session.setAttribute("reportType", "defaultType");
    }
    String formReportType = request.getParameter("reportType");
    if (formReportType != null && !formReportType.isEmpty()) {
        session.setAttribute("reportType", formReportType);
    }
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Submit Report</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f4f9;
        }

        .container {
            max-width: 800px;
            background: #ffffff;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            margin-top: 30px;
        }

        h1 {
            font-size: 2rem;
            margin-bottom: 1.5rem;
            color: #333;
            text-align: center;
        }

        label {
            font-weight: bold;
            color: #555;
        }

        .form-control {
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .form-check-input:checked {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            font-size: 1rem;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .report-image {
            border-radius: 10px;
            margin-bottom: 15px;
            max-height: 150px;
            object-fit: cover;
        }

        .report-details {
            margin-bottom: 20px;
        }

        .form-check {
            margin-bottom: 10px;
        }
    </style>
</head>

<body>
<div class="container">
    <h1>Submit Your Report</h1>
    <form action="createReportServlet" method="post">
        <c:choose>
            <c:when test="${reportType == 'campsite'}">
                <input type="hidden" name="campsiteId" value="${campsiteReportForm.campId}" />
                <img class="report-image" src="img/${campsiteReportForm.campImage}" alt="Camp Image">
                <div class="report-details">
                    <h5>${campsiteReportForm.campName}</h5>
                    <p>Address: ${campsiteReportForm.campAddress}</p>
                    <p>Price: ${campsiteReportForm.campPrice}</p>
                    <p>Description: ${campsiteReportForm.campDescription}</p>
                </div>
            </c:when>
            <c:when test="${reportType == 'owner'}">
                <input type="hidden" name="ownerId" value="${ownerReportForm.ownerId}" />
                <img class="report-image" src="img/${ownerReportForm.image}" alt="Owner Image">
                <div class="report-details">
                    <h5>${ownerReportForm.ownerName}</h5>
                    <p>DOB: ${ownerReportForm.dob}</p>
                    <p>Address: ${ownerReportForm.address}</p>
                    <p>Occupation: ${ownerReportForm.occupation}</p>
                </div>
            </c:when>
            <c:otherwise>
                <p class="text-danger">Invalid report type.</p>
            </c:otherwise>
        </c:choose>

        <div class="form-group mb-3">
            <label for="reportType">Report Type:</label>
            <input type="text" name="reportType" id="reportType" class="form-control"
                   value="${reportType == 'campsite' ? 'Campsite Issue' : (reportType == 'owner' ? 'Owner Issue' : '')}" readonly />
        </div>

        <div class="form-group">
            <label>Report Reasons:</label>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="fraudulent" id="fraudulent">
                <label class="form-check-label" for="fraudulent">Fraudulent</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="malicious" id="malicious">
                <label class="form-check-label" for="malicious">Malicious</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="scam" id="scam">
                <label class="form-check-label" for="scam">Scam</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="racist" id="racist">
                <label class="form-check-label" for="racist">Racist</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="environment" id="environment">
                <label class="form-check-label" for="environment">Environment</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="security" id="security">
                <label class="form-check-label" for="security">Security</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="quality" id="quality">
                <label class="form-check-label" for="quality">Quality</label>
            </div>
        </div>

        <div class="form-group mb-3">
            <label for="reportComment">Additional Comments:</label>
            <textarea name="reportComment" id="reportComment" class="form-control" placeholder="Type your detailed report here..." rows="4" required></textarea>
        </div>

        <div class="text-center">
            <input type="submit" value="Submit Report" class="btn btn-primary" />
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    document.querySelector('form').addEventListener('submit', function (e) {
        const checkboxes = document.querySelectorAll('input[type="checkbox"]');
        const isChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);
        if (!isChecked) {
            e.preventDefault();
            alert('Please select at least one report reason.');
        }
    });
</script>
</body>

</html>
