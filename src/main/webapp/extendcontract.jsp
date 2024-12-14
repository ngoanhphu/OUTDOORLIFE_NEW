<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Extend Contract</title>
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
        input[type="radio"] {
            margin-right: 10px;
        }
        input[type="submit"], .btn-home {
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
        input[type="submit"]:hover, .btn-home:hover {
            background-color: #388e3c;
        }
        .total-price-container {
            margin-top: 20px;
        }
        .total-price-container h3 {
            color: #2e7d32;
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
            <h3>Total <span id="total-price"></span> vnd</h3>
            <input type="hidden" id="total-price-input" name="total-price" value="0">
            <input type="radio" name="paymentMethod" value="VNPay" hidden="hidden" checked=""/>VNpay<br>
            <input type="submit" value="Pay with VNPay">
        </div>
    </form>
    <a href="index.jsp" class="btn-home">Return to Homepage</a>
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