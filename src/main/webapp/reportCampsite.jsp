<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Report Owner</title>
</head>
<body>
<h1>Báo cáo thông tin Chủ sở hữu</h1>
<form action="ReportOwnerServlet" method="post">
  <label for="reporter">Reporter ID:</label>
  <input type="number" id="reporter" name="reporter" required><br><br>

  <label for="ownerId">Owner ID:</label>
  <input type="number" id="ownerId" name="ownerId" required><br><br>

  <label for="description">Mô tả thông tin:</label><br>
  <textarea id="description" name="description" rows="4" cols="50" required></textarea><br><br>

  <input type="submit" value="Gửi báo cáo">
</form>
</body>
</html>
