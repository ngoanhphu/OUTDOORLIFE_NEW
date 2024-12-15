<%@ page import="java.util.List" %>
<%@ page import="model.ReportTask.Report" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Report Viewer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .sidebar {
            height: 100vh;
            background-color: #343a40;
            color: #fff;
            padding-top: 20px;
        }
        .sidebar a {
            color: #fff;
            text-decoration: none;
            margin: 10px 0;
            display: block;
            padding: 10px;
            border-radius: 5px;
            transition: background-color 0.2s ease;
        }
        .sidebar a.active, .sidebar a:hover {
            background-color: #495057;
        }
        .report-attributes {
            font-size: 14px;
            color: #6c757d;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-2 sidebar">
            <h4 class="text-center">Admin Menu</h4>
            <%
                String reportType = request.getParameter("type");
                if (reportType == null) reportType = "all"; // Default to "all" if no type is passed
            %>
            <a href="/login_war/adminReports?type=all&page=1" class="<%= "all".equals(reportType) ? "active" : "" %>">All Reports</a>
            <a href="/login_war/adminReports?type=pending&page=1" class="<%= "pending".equals(reportType) ? "active" : "" %>">Pending Reports</a>
            <a href="/login_war/adminReports?type=campsite&page=1" class="<%= "campsite".equals(reportType) ? "active" : "" %>">Campsite Reports</a>
            <a href="/login_war/adminReports?type=owner&page=1" class="<%= "owner".equals(reportType) ? "active" : "" %>">Report Owner</a>
            <a href="/login_war/adminReports?type=completed&page=1" class="<%= "completed".equals(reportType) ? "active" : "" %>">Completed Reports</a>
        </div>

        <!-- Content Area -->
        <div class="col-md-10">
            <div class="container mt-4">
                <h2 class="mb-4 text-primary">Admin Report Viewer</h2>

                <!-- Report Cards -->
                <div class="row">
                    <%
                        List<?> reports = (List<?>) request.getAttribute("paginatedReports");
                        if (reports != null && !reports.isEmpty()) {
                            for (Object report : reports) {
                                if (report instanceof Report) {
                                    Report r = (Report) report;
                    %>
                    <div class="col-md-4 mb-3">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title text-primary">Reporter id: <%= r.getReporter() %></h5>
                                <p class="card-text"><strong>Description:</strong> <%= r.getDescription() %></p>
                                <p class="card-text"><strong>Date:</strong> <%= r.getReportDate() %></p>
                                <p class="card-text"><strong>Status:</strong>
                                    <span class="badge bg-<%= r.getStatus().equals("Pending") ? "warning" : "success" %>">
                                        <%= r.getStatus() %>
                                    </span>
                                </p>
                                <p class="report-attributes"><strong>Problems:</strong>
                                    <%
                                        List<String> detailAttributes = r.getDetailAttributes();
                                        if (detailAttributes != null && !detailAttributes.isEmpty()) {
                                            for (String attr : detailAttributes) {
                                    %>
                                    <span class="badge bg-secondary"><%= attr %></span>
                                    <%
                                            }
                                        } else {
                                            out.print("None");
                                        }
                                    %>
                                </p>
                                <!-- Nút Warning & Remove Logic -->
                                <%
                                    if ("Pending".equals(r.getStatus())) {
                                %>
                                <form action="notifyReport" method="post">
                                    <input type="hidden" name="reportId" value="<%= r.getId() %>">
                                    <input type="text" name="description" class="form-control" placeholder="Enter description" required>
                                    <button class="btn btn-warning mt-2" type="submit">Send Warning</button>
                                </form>
                                <form action="removeReportServlet" method="post" class="mt-2">
                                    <input type="hidden" name="reportId" value="<%= r.getId() %>">
                                    <button class="btn btn-danger" type="submit">Remove Report</button>
                                </form>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                    <%
                                }
                            }
                        } else {
                            out.print("<p class='text-center'>No reports available.</p>");
                        }
                    %>
                </div>

                <!-- Pagination -->
                <nav class="mt-4">
                    <ul class="pagination justify-content-center">
                        <%
                            Integer currentPage = (Integer) request.getAttribute("currentPage1");
                            Integer totalPages = (Integer) request.getAttribute("totalPages1");

                            if (currentPage == null) currentPage = 1;
                            if (totalPages == null) totalPages = 1;

                            for (int i = 1; i <= totalPages; i++) {
                        %>
                        <li class="page-item <%= currentPage == i ? "active" : "" %>">
                            <a class="page-link" href="/login_war/adminReports?type=<%= request.getParameter("type") %>&page=<%= i %>"><%= i %></a>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</div>

</body>
</html>
