package controller.admin;

import dao.UserDaoImpl;
import model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/manage-account")
public class ManageAccountsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDaoImpl userDAO = new UserDaoImpl();
        int page = 1;
        int recordsPerPage = 8;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        String searchQuery = request.getParameter("search");
        List<User> allAccounts;
        if (searchQuery != null && !searchQuery.isEmpty()) {
            allAccounts = userDAO.searchAccounts(searchQuery);
        } else {
            allAccounts = userDAO.getAllAccount();
        }

        int totalRecords = allAccounts.size();
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        int start = (page - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, totalRecords);
        List<User> paginatedAccounts = allAccounts.subList(start, end);

        String searchDeactivatedQuery = request.getParameter("searchDeactivated");
        List<User> deactivatedAccounts;
        if (searchDeactivatedQuery != null && !searchDeactivatedQuery.isEmpty()) {
            deactivatedAccounts = userDAO.searchDeactivatedAccounts(searchDeactivatedQuery);
        } else {
            deactivatedAccounts = userDAO.getDeactivatedAccounts();
        }

        request.setAttribute("accounts", paginatedAccounts);
        request.setAttribute("deactivatedAccounts", deactivatedAccounts);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchQuery", searchQuery);
        request.setAttribute("searchDeactivatedQuery", searchDeactivatedQuery);
        request.getRequestDispatcher("/manageAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDaoImpl userDAO = new UserDaoImpl();
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));
        User auth = (User) request.getSession().getAttribute("currentUser");

        if ("update".equals(action)) {
            if (auth != null && auth.getId() == id) {
                request.setAttribute("message", "You can't edit your own account!");
            } else {
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String email = request.getParameter("email");
                String phoneNumber = request.getParameter("phoneNumber");
                boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));
                boolean isOwner = Boolean.parseBoolean(request.getParameter("isOwner"));

                User user = new User(id, firstName, lastName, email, phoneNumber, null, isAdmin, isOwner, null);
                boolean success = userDAO.updateAccount(user);

                if (success) {
                    request.setAttribute("message", "Account updated successfully.");
                } else {
                    request.setAttribute("message", "Failed to update account.");
                }
            }
        } else if ("deactivate".equals(action)) {
            User userToDeactivate = userDAO.findById(id);
            if (auth != null && auth.getId() == id) {
                request.setAttribute("message", "You can't deactivate your own account!");
            } else if (userToDeactivate != null && userToDeactivate.isAdmin()) {
                request.setAttribute("message", "Admin accounts cannot be deactivated!");
            } else {
                boolean success = userDAO.deactivateAccount(id);
                if (success) {
                    request.setAttribute("message", "Account deactivated successfully.");
                } else {
                    request.setAttribute("message", "Failed to deactivate account.");
                }
            }
        } else if ("reactivate".equals(action)) {
            boolean success = userDAO.reactivateAccount(id);
            if (success) {
                request.setAttribute("message", "Account reactivated successfully.");
            } else {
                request.setAttribute("message", "Failed to reactivate account.");
            }
        }
        doGet(request, response);
    }
}