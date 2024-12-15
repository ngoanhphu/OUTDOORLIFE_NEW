package controller.user.ReportTask;

import dao.CampsiteDAO;
import dao.ReportTask.ReportDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Campsite;
import model.ReportTask.ReportLocation;
import model.ReportTask.ReportOwner;
import model.User;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@WebServlet(name = "CreateReportServlet", urlPatterns = {"/createReportServlet"})
public class CreateReportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            ReportDao rp = new ReportDao();
            HttpSession session = request.getSession();
            String reportType = (String) session.getAttribute("reportType");
            System.out.println("Session reportType in CreateReportServlet: " + reportType); // Debugging
            User currentUser = (User) session.getAttribute("currentUser");
            int reporterId = currentUser.getId();
            if ("campsite".equals(reportType)) {
                int campsiteId = Integer.parseInt(request.getParameter("campsiteId"));
                boolean fraudulent = request.getParameter("fraudulent") != null;
                boolean malicious = request.getParameter("malicious") != null;
                boolean scam = request.getParameter("scam") != null;
                boolean racist = request.getParameter("racist") != null;
                boolean environment = request.getParameter("environment") != null;
                boolean security = request.getParameter("security") != null;
                boolean quality = request.getParameter("quality") != null;
                String another1 = request.getParameter("anotherReason1");
                String another2 = request.getParameter("anotherReason2");
                String another3 = request.getParameter("anotherReason3");
                String description = request.getParameter("reportComment");
                int reportDetailId = rp.insertReportDetail(reportType, fraudulent, malicious, scam, racist, environment, security, quality, another1, another2, another3);

                ReportLocation reportLocation = new ReportLocation();
//                reportLocation.setReporter(reporterId);
//                reportLocation.setCampsiteId(campsiteId);
//                reportLocation.setReportDetailId(reportDetailId);
                reportLocation.setDescription(description);
                reportLocation.setReportDate(timestamp);
                rp.insertReportLocation(reportLocation, reporterId, campsiteId, reportDetailId);
            } else if ("owner".equals(reportType)) {
                int ownerId = Integer.parseInt(request.getParameter("ownerId"));
                boolean fraudulent = request.getParameter("fraudulent") != null;
                boolean malicious = request.getParameter("malicious") != null;
                boolean scam = request.getParameter("scam") != null;
                boolean racist = request.getParameter("racist") != null;
                boolean environment = request.getParameter("environment") != null;
                boolean security = request.getParameter("security") != null;
                boolean quality = request.getParameter("quality") != null;
                String another1 = request.getParameter("another1");
                String another2 = request.getParameter("another2");
                String another3 = request.getParameter("another3");
                String description = request.getParameter("reportComment");

                int reportDetailId = rp.insertReportDetail(reportType, fraudulent, malicious, scam, racist, environment, security, quality, another1, another2, another3);

                ReportOwner reportOwner = new ReportOwner();
//                reportOwner.setReporter(reporterId);
//                reportOwner.setOwnerId(ownerId);
//                reportOwner.setReportDetailId(reportDetailId);
                reportOwner.setReportDate(timestamp);
                reportOwner.setDescription(description);

                rp.insertReportOwner(reportOwner, reporterId, ownerId, reportDetailId);
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/report/reportSuccess.jsp").forward(request, response);
    }
}
