<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Pending Campsites</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f0f8ff;
      color: #333;
      margin: 0;
      padding: 0;
    }
    .container {
      width: 80%;
      margin: 0 auto;
      padding: 20px;
      background-color: #fff;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      border-radius: 8px;
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
    .action-buttons {
      display: flex;
      gap: 10px;
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
<div class="container">
  <h2>Pending Campsites</h2>
  <c:if test="${not empty campsites}">
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Price</th>
        <th>Owner</th>
        <th>Address</th>
        <th>Name</th>
        <th>Description</th>
        <th>Image</th>
        <th>Status</th>
        <th>Limit</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="campsite" items="${campsites}">
        <tr>
          <td>${campsite.campId}</td>
          <td>${campsite.campPrice}</td>
          <td>${campsite.campOwner}</td>
          <td>${campsite.campAddress}</td>
          <td>${campsite.campName}</td>
          <td>${campsite.campDescription}</td>
          <td>${campsite.campImage}</td>
          <td>${campsite.campStatus ? 'Approved' : 'Pending'}</td>
          <td>${campsite.limite}</td>
          <td>
            <div class="action-buttons">
              <form action="campsiteApproving" method="post" style="display:inline;">
                <input type="hidden" name="campsiteId" value="${campsite.campId}">
                <input type="hidden" name="action" value="approve">
                <button type="submit">Approve</button>
              </form>
              <form action="campsiteApproving" method="post" style="display:inline;">
                <input type="hidden" name="campsiteId" value="${campsite.campId}">
                <input type="hidden" name="action" value="disapprove">
                <button type="submit">Disapprove</button>
              </form>
            </div>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <div class="pagination">
      <c:forEach var="i" begin="1" end="${totalPages}">
        <a href="campsiteApproving?page=${i}" class="${i == currentPage ? 'active' : ''}">${i}</a>
      </c:forEach>
    </div>
  </c:if>
  <c:if test="${empty campsites}">
    <p>No pending campsites found.</p>
  </c:if>
</div>
</body>
</html>