package controller.user.ReportTask;

import com.google.gson.Gson;
import dao.CampsiteDAO;
import dao.DBContext;
import dao.OwnerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Campsite;
import model.Owner;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "GetCampsiteDetailServlet", urlPatterns = {"/getCampsiteDetail"})
public class GetCampsiteDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reportType = request.getParameter("reportType");

        if ("campsite".equals(reportType)) {
            int campsiteId = Integer.parseInt(request.getParameter("campsiteId"));
            CampsiteDAO campsiteDAO = new CampsiteDAO();

            Campsite campsite = campsiteDAO.getSingleCampsite(campsiteId); // Giả định có service hoặc DAO gọi DB

            if (campsite != null) {
                request.setAttribute("campsiteReportForm", campsite);
                request.setAttribute("reportType", reportType);
                request.getRequestDispatcher("/report/reportForm.jsp").forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
        } else if ("owner".equals(reportType)) {
            int ownerId = Integer.parseInt(request.getParameter("ownerId"));
            OwnerDAO ownerDAO = new OwnerDAO(new DBContext());
            try {
                Owner owner = ownerDAO.getOwnerByOwnerId(ownerId);
                if (owner != null) {
                    request.setAttribute("ownerReportForm", owner);
                    request.setAttribute("reportType", reportType);
                    request.getRequestDispatcher("/report/reportForm.jsp").forward(request, response);
                } else {
                    response.sendRedirect("error.jsp");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

}
