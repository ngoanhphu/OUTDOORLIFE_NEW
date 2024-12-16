<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Accounts</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            color: #333;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            flex: 1;
        }
        h2 {
            text-align: center;
            color: #4CAF50;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        .pagination {
            text-align: center;
            margin: 20px 0;
        }
        .pagination a {
            margin: 0 5px;
            padding: 8px 16px;
            text-decoration: none;
            color: #4CAF50;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .pagination a.active {
            background-color: #4CAF50;
            color: white;
            border: 1px solid #4CAF50;
        }
        .pagination a:hover {
            background-color: #ddd;
        }
        .btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-align: center;
        }
        .btn:hover {
            background-color: #45a049;
        }
        .form-container {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        .form-container input, .form-container select {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .alert {
            color: red;
            text-align: center;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<%@ include file="headeradmin.jsp" %>
<div class="container" style="margin-top: 300px">
    <h2>Manage Accounts</h2>
    <form method="get" action="manage-account">
        <input type="text" name="search" placeholder="Search by ID, Email, or Phone" value="${searchQuery}" />
        <button type="submit" class="btn">Search</button>
    </form>
    <c:if test="${not empty message}">
        <div class="alert">${message}</div>
    </c:if>
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Role</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="a" items="${accounts}">
            <tr>
                <td>${a.id}</td>
                <td>${a.firstName}</td>
                <td>${a.lastName}</td>
                <td>${a.email}</td>
                <td>${a.phoneNumber}</td>
                <td>
                    <c:choose>
                        <c:when test="${a.admin}">Admin</c:when>
                        <c:when test="${a.owner}">Owner</c:when>
                        <c:otherwise>User</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <button class="btn" onclick="editAccount(${a.id}, '${a.firstName}', '${a.lastName}', '${a.email}', '${a.phoneNumber}', ${a.admin}, ${a.owner})">Edit</button>
                    <form method="post" action="manage-account" style="display:inline;">
                        <input type="hidden" name="action" value="deactivate">
                        <input type="hidden" name="id" value="${a.id}">
                        <button type="submit" class="btn">Deactivate</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">
        <c:forEach var="i" begin="1" end="${totalPages}">
            <a href="manage-account?page=${i}&search=${searchQuery}" class="${i == currentPage ? 'active' : ''}">${i}</a>
        </c:forEach>
    </div>
</div>

<!-- New Deactivated Accounts Section -->
<div class="container">
    <h2>Deactivated Accounts</h2>
    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Role</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="a" items="${deactivatedAccounts}">
            <tr>
                <td>${a.id}</td>
                <td>${a.firstName}</td>
                <td>${a.lastName}</td>
                <td>${a.email}</td>
                <td>${a.phoneNumber}</td>
                <td>
                    <c:choose>
                        <c:when test="${a.admin}">Admin</c:when>
                        <c:when test="${a.owner}">Owner</c:when>
                        <c:otherwise>User</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form method="post" action="manage-account" style="display:inline;">
                        <input type="hidden" name="action" value="reactivate">
                        <input type="hidden" name="id" value="${a.id}">
                        <button type="submit" class="btn">Reactivate</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="form-container" id="editForm">
    <h2>Edit Account</h2>
    <form method="post" action="manage-account">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" id="accountId">
        <label for="firstName">First Name:</label>
        <input type="text" name="firstName" id="firstName" required>
        <label for="lastName">Last Name:</label>
        <input type="text" name="lastName" id="lastName" required>
        <label for="email">Email:</label>
        <input type="email" name="email" id="email" required>
        <label for="phoneNumber">Phone Number:</label>
        <input type="text" name="phoneNumber" id="phoneNumber" required>
        <input type="hidden" name="isAdmin" id="isAdmin">
        <input type="hidden" name="isOwner" id="isOwner">
        <button type="submit" class="btn">Update</button>
        <button type="button" class="btn" onclick="closeForm()">Cancel</button>
    </form>
</div>

<%@ include file="footer.jsp" %>

<script>
    function editAccount(id, firstName, lastName, email, phoneNumber, isAdmin, isOwner) {
        document.getElementById('accountId').value = id;
        document.getElementById('firstName').value = firstName;
        document.getElementById('lastName').value = lastName;
        document.getElementById('email').value = email;
        document.getElementById('phoneNumber').value = phoneNumber;
        document.getElementById('isAdmin').value = isAdmin;
        document.getElementById('isOwner').value = isOwner;
        document.getElementById('editForm').style.display = 'block';
    }

    function closeForm() {
        document.getElementById('editForm').style.display = 'none';
    }
</script>
</body>
</html>