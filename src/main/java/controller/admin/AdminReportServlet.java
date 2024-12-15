package controller.admin;

import dao.ReportTask.ReportDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ReportTask.ReportLocation;
import model.ReportTask.ReportOwner;
import model.ReportTask.Report;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminReportServlet", urlPatterns = {"/adminReports"})
public class AdminReportServlet extends HttpServlet {

    private ReportDao dao = new ReportDao();
    private static final int PAGE_SIZE = 12; // Giới hạn 12 báo cáo mỗi trang

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getParameter("type");
        int page = 1; // Mặc định trang 1
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        try {
            switch (type) {
                case "all":
                    List<Report> allReports = dao.getAllReports();
                    for (Report report : allReports) {
                        int reportDetailId = report.getReportDetailId();
                        if (reportDetailId > 0) {
                            List<String> detailAttributes = dao.getReportDetailColumns(reportDetailId);
                            report.setDetailAttributes(detailAttributes);
                        }
                    }
                    paginateAndSetRequest(request, allReports, page);
                    break;

                case "campsite":
                    List<ReportLocation> reportLocations = dao.getAllReportsLocation();
                    for (Report report : reportLocations) {
                        int reportDetailId = report.getReportDetailId();
                        if (reportDetailId > 0) {
                            List<String> detailAttributes = dao.getReportDetailColumns(reportDetailId);
                            report.setDetailAttributes(detailAttributes);
                        }
                    }
                    paginateAndSetRequest(request, reportLocations, page);
                    break;

                case "owner":
                    List<ReportOwner> reportOwners = dao.getAllOwnerReports();
                    for (Report report : reportOwners) {
                        int reportDetailId = report.getReportDetailId();
                        if (reportDetailId > 0) {
                            List<String> detailAttributes = dao.getReportDetailColumns(reportDetailId);
                            report.setDetailAttributes(detailAttributes);
                        }
                    }
                    paginateAndSetRequest(request, reportOwners, page);
                    break;

                case "pending":
                    List<Report> pendingReports = dao.getAllPendingReports();
                    for (Report report : pendingReports) {
                        int reportDetailId = report.getReportDetailId();
                        if (reportDetailId > 0) {
                            List<String> detailAttributes = dao.getReportDetailColumns(reportDetailId);
                            report.setDetailAttributes(detailAttributes);
                        }
                    }
                    paginateAndSetRequest(request, pendingReports, page);
                    break;

                case "completed":
                    List<Report> completedReports = dao.getAllCompletedReports();
                    for (Report report : completedReports) {
                        int reportDetailId = report.getReportDetailId();
                        if (reportDetailId > 0) {
                            List<String> detailAttributes = dao.getReportDetailColumns(reportDetailId);
                            report.setDetailAttributes(detailAttributes);
                        }
                    }
                    paginateAndSetRequest(request, completedReports, page);
                    break;

                default:
                    System.out.println("No matching report type found.");
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error while fetching data: " + e.getMessage());
        }

        request.getRequestDispatcher("/report/viewReportByAdmin.jsp").forward(request, response);
    }

    private <T> void paginateAndSetRequest(HttpServletRequest request, List<T> reports, int page) {
        if (reports == null) {
            reports = List.of(); // Set danh sách trống nếu null
        }

        int totalItems = reports.size();
        int totalPages = (int) Math.ceil((double) totalItems / PAGE_SIZE);

        if (totalPages <= 0) totalPages = 1; // Tránh totalPages = 0

        int startIndex = Math.max(0, (page - 1) * PAGE_SIZE);
        int endIndex = Math.min(startIndex + PAGE_SIZE, totalItems);

        List<T> paginatedList = new ArrayList<>();
        if (startIndex < endIndex && totalItems > 0) {
            paginatedList = reports.subList(startIndex, endIndex);
        }

        request.setAttribute("paginatedReports", paginatedList);
        request.setAttribute("currentPage1", page);
        request.setAttribute("totalPages1", totalPages);

    }

}
