package controller.owner;

import VnpayService.VnpayService;
import dao.AccountDAO;
import dao.DBContext;
import dao.OwnerDAO;
import dao.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Owner;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.sql.Date;

@WebServlet("/extendContract")
public class ExtendContract extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        OwnerDAO ownerDAO = new OwnerDAO(new DBContext());

        try {
            int accountId = (Integer) session.getAttribute("accountId");
            Owner owner = ownerDAO.getOwnerByAccountId(accountId);

            if (owner != null) {
                String durationStr = request.getParameter("duration");
                int duration = Integer.parseInt(durationStr);

                Date currentEndDate = owner.getEndDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentEndDate);
                calendar.add(Calendar.MONTH, duration);
                Date newEndDate = new Date(calendar.getTimeInMillis());

                boolean isUpdated = ownerDAO.updateOwnerEndDate(accountId, newEndDate);
                if (isUpdated) {
                    session.setAttribute("message", "Contract extended successfully.");
                    response.sendRedirect("indexMessage");
                } else {
                    request.setAttribute("errorMessage", "Failed to extend the contract.");
                    request.getRequestDispatcher("extendcontract.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Owner not found.");
                request.getRequestDispatcher("extendcontract.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        OwnerDAO ownerDAO = new OwnerDAO(new DBContext());
        User auth = (User) request.getSession().getAttribute("currentUser");

        if (auth == null) {
            session.setAttribute("message", "You are not logged in!");
            response.sendRedirect("login.jsp");
        } else {
            UserDaoImpl userDAO = new UserDaoImpl();
            boolean isDeactivated = userDAO.isAccountDeactivated(auth.getId());
            if (isDeactivated) {
                session.setAttribute("message", "Your account has been deactivated!");
                response.sendRedirect("loginMessage");
            }

            if (auth != null && auth.isOwner()) {
                try {
                    String status = ownerDAO.getOwnerStatusByAccountId(session);
                    if ("disapproved".equals(status) || "pending".equals(status)) {
                        session.setAttribute("message", "You are not an approved owner!");
                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                    } else {
                        Date endDate = ownerDAO.getOwnerEndDate(session);
                        if (endDate != null) {
                            request.setAttribute("endDate", endDate);
                        } else {
                            request.setAttribute("endDate", "N/A");
                        }
                        request.getRequestDispatcher("extendcontract.jsp").forward(request, response);
                        request.setAttribute("endDate", endDate);
                        request.getRequestDispatcher("extendcontract.jsp").forward(request, response);
                    }
                } catch (SQLException e) {
                    throw new ServletException("Database error", e);
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        }
    }
}