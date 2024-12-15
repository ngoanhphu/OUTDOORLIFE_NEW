<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Report Success</title>
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f4f4f9;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
      text-align: center;
    }

    .container {
      max-width: 500px;
      background: #ffffff;
      border-radius: 10px;
      padding: 20px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }

    h1 {
      font-size: 2rem;
      color: #28a745;
      margin-bottom: 1rem;
    }

    p {
      font-size: 1rem;
      color: #555;
      margin-bottom: 1.5rem;
    }

    a {
      color: #007bff;
      text-decoration: none;
      font-weight: bold;
    }

    a:hover {
      text-decoration: underline;
    }
  </style>
  <script>
    // Tự động chuyển hướng sau 5 giây
    setTimeout(function () {
      window.location.href = "user.jsp"; // Đường dẫn đến trang Home
    }, 5000);
  </script>
</head>

<body>
<div class="container">
  <h1>Report Submitted Successfully!</h1>
  <p>Your report has been submitted. You will be redirected to the homepage in 5 seconds.</p>
  <p>If you are not redirected, click <a href="user.jsp">here</a> to go to the homepage.</p>
</div>
</body>

</html>
