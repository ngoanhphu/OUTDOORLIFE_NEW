package controller.admin;

import dao.NotificationDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RemoveReportServlet", urlPatterns = {"/removeReportServlet"})
public class RemoveReportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reportIdStr = request.getParameter("reportId");
        NotificationDao dao = new NotificationDao();

        if (reportIdStr != null) {
            int reportId = Integer.parseInt(reportIdStr);
            if (dao.removeReport(reportId)) {
                response.sendRedirect("adminReports?type=pending&page=1");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete report.");
            }
        }
    }
}
