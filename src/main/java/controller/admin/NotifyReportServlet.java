package controller.admin;

import dao.NotificationDao;
import dao.ReportTask.ReportDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Notification;
import model.ReportTask.Report;
import org.json.JSONObject;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "NotifyReportServlet", urlPatterns = {"/notifyReport"})
public class NotifyReportServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reportIdStr = request.getParameter("reportId");
        String description = request.getParameter("description");
        NotificationDao dao = new NotificationDao();
        String reportType = request.getParameter("type");

        if (reportIdStr != null && description != null) {
            int reportId = Integer.parseInt(reportIdStr);
            if (dao.sendNotification(reportId, description)) {
                response.sendRedirect("adminReports?type="+reportType+"&page=1");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to process notification.");
            }
        }
    }

}
