<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Pending Owners</title>
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
    .action-icons {
      cursor: pointer;
      margin: 0 5px;
      font-size: 20px;
    }
    .actions {
      text-align: center;
    }
    .footer {
      background-color: #f0f8ff;
      text-align: center;
      padding: 10px 0;
      position: relative;
      bottom: 0;
      width: 100%;
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
  </style>
</head>
<body>
<div style="margin-bottom: 150px">
  <%@ include file="headeradmin.jsp" %>
</div>
<div class="container">
  <h2>Pending Owners</h2>
  <c:if test="${not empty pendingOwners}">
    <table>
      <thead>
      <tr>
        <th>Account ID</th>
        <th>Occupation</th>
        <th>Address</th>
        <th>Date of Birth</th>
        <th>Gender</th>
        <th>Identification</th>
        <th>Tax Code</th>
        <th>Contract Document</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="owner" items="${pendingOwners}">
        <tr>
          <td>${owner.accountId}</td>
          <td>${owner.occupation}</td>
          <td>${owner.address}</td>
          <td class="date-format">${owner.dob}</td>
          <td>${owner.gender}</td>
          <td>${owner.identification}</td>
          <td>${owner.taxCode}</td>
          <td><a href="${owner.image}" download>Download Contract</a></td>
          <td class="date-format">${owner.startDate}</td>
          <td class="date-format">${owner.endDate}</td>
          <td class="actions">
            <form action="manageOwners" method="post" style="display:inline;">
              <input type="hidden" name="ownerId" value="${owner.accountId}">
              <input type="hidden" name="action" value="approve">
              <span class="action-icons" onclick="this.closest('form').submit();">&#10004;</span>
            </form>
            <form action="manageOwners" method="post" style="display:inline;">
              <input type="hidden" name="ownerId" value="${owner.accountId}">
              <input type="hidden" name="action" value="disapprove">
              <span class="action-icons" onclick="this.closest('form').submit();">&#10008;</span>
            </form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <div class="pagination">
      <c:forEach var="i" begin="1" end="${totalPages}">
        <a href="ownerRegistration?page=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
      </c:forEach>
    </div>
  </c:if>
  <c:if test="${empty pendingOwners}">
    <p>No pending owners found.</p>
  </c:if>
</div>
<%@ include file="footer.jsp" %>
<script>
  document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.date-format').forEach(function(element) {
      const date = new Date(element.textContent);
      const formattedDate = ('0' + date.getDate()).slice(-2) + '/' +
              ('0' + (date.getMonth() + 1)).slice(-2) + '/' +
              date.getFullYear().toString().slice(-2);
      element.textContent = formattedDate;
    });
  });
</script>
</body>
</html>