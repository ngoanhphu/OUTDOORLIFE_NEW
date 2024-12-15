<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>Tìm Kiếm Báo Cáo</title>
</head>
<body>
<h2>Chọn địa điểm hoặc chủ sở hữu để báo cáo</h2>
<form method="GET" action="searchReportResults.jsp">
  <label>Nhập ID Campsite:</label>
  <input type="text" name="campsiteId">
  <label>Hoặc ID Chủ sở hữu:</label>
  <input type="text" name="ownerId">
  <button type="submit">Tìm Kiếm</button>
</form>
</body>
</html>
