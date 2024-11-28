<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Owner Contract Registration</title>
</head>
<body>
<h2>Owner Contract Registration</h2>
<form action="OwnerContractServlet" method="post">
    <div>
        <label for="accountId">Account ID:</label>
        <input type="number" id="accountId" name="accountId" required>
    </div>
    <div>
        <label for="startDate">Start Date:</label>
        <input type="date" id="startDate" name="startDate">
    </div>
    <div>
        <label for="endDate">End Date:</label>
        <input type="date" id="endDate" name="endDate">
    </div>
    <div>
        <label for="notes">Notes:</label>
        <textarea id="notes" name="notes" rows="4" cols="50"></textarea>
    </div>
    <div>
        <label for="ownerId">Owner ID:</label>
        <input type="number" id="ownerId" name="ownerId">
    </div>
    <div>
        <input type="submit" value="Register Contract">
    </div>
</form>
</body>
</html>