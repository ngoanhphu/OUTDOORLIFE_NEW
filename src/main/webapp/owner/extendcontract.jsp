<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.*"%>

<%
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Extend Contract</title>
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
        input[type="radio"] {
            margin-right: 10px;
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
        .total-price-container {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Extend Contract</h2>
    <form action="extendContract" method="post" onsubmit="return validateForm()">
        <div class="form-group">
            <label for="duration">Select Extension Duration:</label>
            <input type="radio" id="duration6" name="duration" value="6" data-price="100000" required> 6 Months
            <input type="radio" id="duration12" name="duration" value="12" data-price="180000" required> 12 Months
            <input type="radio" id="duration24" name="duration" value="24" data-price="320000" required> 24 Months
        </div>
        <div class="total-price-container">
            <h3>Total: <span id="total-price">0</span> ₫</h3>
            <input type="hidden" id="total-price-input" name="total-price" value="0">
            <input type="radio" name="paymentMethod" value="VNPay" checked=""/>VNpay<br>
            <input type="radio" name="paymentMethod" value="PayLater"/>Pay later<br>
            <input type="submit" value="Extend Contract">
        </div>
    </form>
</div>

<script>
    document.querySelectorAll('input[name="duration"]').forEach(function (radio) {
        radio.addEventListener('change', function () {
            let price = this.getAttribute('data-price');
            document.getElementById('total-price').innerText = price;
            document.getElementById('total-price-input').value = price;
        });
    });

    function validateForm() {
        const duration = document.querySelector('input[name="duration"]:checked').value;
        if (!duration) {
            alert('Please select a duration.');
            return false;
        }
        return true;
    }
</script>

</body>
</html>