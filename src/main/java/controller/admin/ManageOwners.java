package controller.admin;

import dao.OwnerDAO;
import dao.DBContext;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/manageOwners")
public class ManageOwners extends HttpServlet {
    private OwnerDAO ownerDAO;

    @Override
    public void init() throws ServletException {
        ownerDAO = new OwnerDAO(new DBContext());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int ownerId = Integer.parseInt(request.getParameter("ownerId"));

        try {
            if ("approve".equals(action)) {
                ownerDAO.approveOwner(ownerId);
            } else if ("disapprove".equals(action)) {
                ownerDAO.disapproveOwner(ownerId);
            }
            response.sendRedirect("ownerRegistration");
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}