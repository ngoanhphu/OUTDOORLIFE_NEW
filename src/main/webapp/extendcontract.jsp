<%@ include file="headeradmin.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Extend Contract</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <style>
    body {
      background-color: #e8f5e9;
      margin: 0;
      padding: 0;
      display: flex;
      flex-direction: column;
      min-height: 100vh;
      font-family: Arial, sans-serif;
    }
    .content {
      flex: 1;
      display: flex;
      justify-content: center;
      align-items: center;
    }
    .container {
      background-color: #fff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      width: 90%;
      max-width: 800px;
      margin: 20px;
    }
    h2 {
      text-align: center;
      color: #2e7d32;
      margin-bottom: 20px;
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
    .end-date {
      font-size: 1.5em;
      color: #d32f2f;
      font-weight: bold;
      text-align: center;
      margin-bottom: 20px;
    }
    footer {
      background-color: #f1f1f1;
      padding: 10px 0;
      text-align: center;
      width: 100%;
    }
    @media (max-width: 600px) {
      .container {
        padding: 10px;
      }
      h2 {
        font-size: 1.5em;
      }
      .end-date {
        font-size: 1.2em;
      }
    }
  </style>
</head>
<body>
<div class="content">
  <div class="container">
    <h2><i class="fas fa-file-contract"></i> Extend Contract</h2>
    <p class="end-date"><i class="fas fa-calendar-alt"></i> Your contract ends on <span id="end-date"><%= request.getAttribute("endDate") %></span></p>
    <form action="extendContract" method="post" onsubmit="return validateForm()">
      <div class="form-group">
        <label for="duration"><i class="fas fa-clock"></i> Select Extension Duration:</label>
        <input type="radio" id="duration6" name="duration" value="6" data-price="640000" required> 6 Months
        <input type="radio" id="duration12" name="duration" value="12" data-price="1100000" required> 12 Months
        <input type="radio" id="duration24" name="duration" value="24" data-price="2000000" required> 24 Months
      </div>
      <div class="total-price-container">
        <h3>Total: <span id="total-price">0</span> vnd</h3>
        <input type="hidden" id="total-price-input" name="total-price" value="0">
        <input type="submit" value="Proceed">
      </div>
    </form>
    <a href="index.jsp" class="btn-home"><i class="fas fa-home"></i> Return to Homepage</a>
  </div>
</div>
<%@ include file="footer.jsp" %>
<script>
  document.addEventListener('DOMContentLoaded', function() {
    const endDateElement = document.getElementById('end-date');
    const endDate = new Date(endDateElement.textContent);
    const options = { day: 'numeric', month: 'long', year: 'numeric' };
    endDateElement.textContent = endDate.toLocaleDateString('en-US', options);
  });

  document.querySelectorAll('input[name="duration"]').forEach(function (radio) {
    radio.addEventListener('change', function () {
      let price = this.getAttribute('data-price');
      document.getElementById('total-price').innerText = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(price);
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