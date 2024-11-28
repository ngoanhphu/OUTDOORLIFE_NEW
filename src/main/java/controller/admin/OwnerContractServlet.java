package controller.admin;

import dao.DBContext;
import dao.OwnerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OwnerContract;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/owner/OwnerContractServlet")
public class OwnerContractServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OwnerDAO dao;

    public void init() throws ServletException {
        try {
            DBContext db = new DBContext();
            dao = new OwnerDAO(db);
        } catch (Exception e) {
            throw new ServletException("Failed to initialize OwnerDAO", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Create OwnerContract object from form data
            OwnerContract contract = new OwnerContract();

            contract.setAccountId(Integer.parseInt(request.getParameter("accountId")));

            String registrationDateStr = request.getParameter("registrationDate");
            if (registrationDateStr != null && !registrationDateStr.isEmpty()) {
                contract.setRegistration(Date.valueOf(registrationDateStr));
            }

            contract.setStatus(request.getParameter("status"));

            String startDateStr = request.getParameter("startDate");
            if (startDateStr != null && !startDateStr.isEmpty()) {
                contract.setStartDate(Date.valueOf(startDateStr));
            }

            String endDateStr = request.getParameter("endDate");
            if (endDateStr != null && !endDateStr.isEmpty()) {
                contract.setEndDate(Date.valueOf(endDateStr));
            }

            contract.setNotes(request.getParameter("notes"));

            String ownerIdStr = request.getParameter("ownerId");
            if (ownerIdStr != null && !ownerIdStr.isEmpty()) {
                contract.setOwnerId(Integer.parseInt(ownerIdStr));
            }

            // Insert contract into database
            boolean success = insertOwnerContract(contract);

            // Redirect based on success
            if (success) {
                response.sendRedirect(request.getContextPath() + "/success.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    private boolean insertOwnerContract(OwnerContract contract) throws SQLException {
        return dao.insertOwnerContract(contract);
    }
}