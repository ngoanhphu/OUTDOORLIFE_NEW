<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
    /* Reset CSS */
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    /* General Page Style */
    body {
        font-family: Arial, Helvetica, sans-serif;
        background: linear-gradient(to right, #6a11cb, #2575fc);
        color: #fff;
    }

    /* Header Styles */
    .header {
        text-align: center;
        padding: 15px 0;
        background: #333;
        color: white;
    }

    /* Main container */
    .container {
        max-width: 700px;
        margin: 20px auto;
        padding: 20px;
        background: white;
        border-radius: 8px;
        box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.3);
        color: #333;
        text-align: center;
    }

    /* Title Styles */
    .title {
        font-size: 24px;
        margin-bottom: 20px;
        color: #333;
    }

    /* Form Container */
    .form-container {
        display: flex;
        justify-content: space-around;
        gap: 20px;
        flex-wrap: wrap;
        margin: 20px 0;
    }

    /* Button Styles */
    .btn {
        padding: 15px 30px;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
        transition: transform 0.2s ease, background-color 0.2s ease;
    }

    .btn:hover {
        transform: scale(1.1);
    }

    .btn-campsite {
        background-color: #ff5722;
        color: #fff;
    }

    .btn-owner {
        background-color: #3f51b5;
        color: #fff;
    }

    .btn-home {
        background-color: #4caf50;
        color: #fff;
    }

</style>

<body>
<div class="header">
    <h1>My Application - Báo Cáo Hệ Thống</h1>
</div>

<!-- Main Content Section -->
<div class="container">
    <h2 class="title">Chọn loại báo cáo của bạn</h2>
    <form method="POST" action="reportServlet" class="form-container">
        <button type="submit" name="action" value="campsite" class="btn btn-campsite">Report Campsite</button>
        <button type="submit" name="action" value="owner" class="btn btn-owner">Report Owner</button>
    </form>
    <button href="index.jsp" name="home" value="home" class="btn btn-home">Back Home</button>
</div>
</body>
