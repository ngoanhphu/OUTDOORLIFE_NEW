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

        request.setAttribute("accounts", paginatedAccounts);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("searchQuery", searchQuery);
        request.getRequestDispatcher("/manageAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDaoImpl userDAO = new UserDaoImpl();
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            User auth = (User) request.getSession().getAttribute("currentUser");

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
        }
        doGet(request, response);
    }
}