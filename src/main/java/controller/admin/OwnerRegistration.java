package controller.admin;

import dao.OwnerDAO;
import dao.DBContext;
import model.Owner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/ownerRegistration")
public class OwnerRegistration extends HttpServlet {
    private OwnerDAO ownerDAO;

    @Override
    public void init() throws ServletException {
        ownerDAO = new OwnerDAO(new DBContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Owner> pendingOwners = ownerDAO.getPendingOwners();
            request.setAttribute("pendingOwners", pendingOwners);
            request.getRequestDispatcher("/admin/ownerRegistration.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}