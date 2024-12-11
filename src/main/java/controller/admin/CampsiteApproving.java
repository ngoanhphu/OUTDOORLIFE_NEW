package controller.admin;

import dao.CampsiteDAO;
import model.Campsite;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CampsiteApproving", urlPatterns = {"/campsiteApproving"})
public class CampsiteApproving extends HttpServlet {

    private CampsiteDAO campDAO;

    @Override
    public void init() throws ServletException {
        try {
            campDAO = new CampsiteDAO();
        } catch (Exception e) {
            throw new ServletException("Error initializing CampsiteDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        User auth = (User) request.getSession().getAttribute("currentUser");
        if (auth != null && auth.isOwner()) {

            try {
                List<Campsite> campsites = campDAO.getCampsitesWithStatusFalse();
                int totalRecords = campsites.size();
                int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

                int start = (page - 1) * recordsPerPage;
                int end = Math.min(start + recordsPerPage, totalRecords);
                List<Campsite> paginatedCampsites = campsites.subList(start, end);

                request.setAttribute("campsites", paginatedCampsites);
                request.setAttribute("currentPage", page);
                request.setAttribute("totalPages", totalPages);
                request.getRequestDispatcher("/admin/campsiteApproving.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Error retrieving campsites", e);
            }
        }
        else{
                response.sendRedirect("login.jsp");
            }
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int campsiteId = Integer.parseInt(request.getParameter("campsiteId"));
        String action = request.getParameter("action");

        try {
            boolean success;
            if ("approve".equals(action)) {
                success = campDAO.approveCampsite(campsiteId);
            } else if ("disapprove".equals(action)) {
                success = campDAO.disapproveCampsite(campsiteId);
            } else {
                throw new ServletException("Invalid action");
            }

            if (success) {
                response.sendRedirect("campsiteApproving");
            } else {
                throw new ServletException("Error processing campsite action");
            }
        } catch (Exception e) {
            throw new ServletException("Error processing campsite action", e);
        }
    }
}