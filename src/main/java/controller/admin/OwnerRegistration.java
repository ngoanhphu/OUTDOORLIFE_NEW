package controller.admin;

import dao.OwnerDAO;
import dao.DBContext;
import model.Owner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/ownerRegistration")
public class OwnerRegistration extends HttpServlet {
    private OwnerDAO ownerDAO;

    @Override
    public void init() throws ServletException {
        ownerDAO = new OwnerDAO(new DBContext());
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
        if (auth != null && auth.isAdmin()) {
            try {
                List<Owner> pendingOwners = ownerDAO.getPendingOwners();
                int totalRecords = pendingOwners.size();
                int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

                int start = (page - 1) * recordsPerPage;
                int end = Math.min(start + recordsPerPage, totalRecords);
                List<Owner> paginatedOwners = pendingOwners.subList(start, end);

                request.setAttribute("pendingOwners", paginatedOwners);
                request.setAttribute("currentPage", page);
                request.setAttribute("totalPages", totalPages);
                request.getRequestDispatcher("/ownerRegistration.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new ServletException("Database error", e);
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
