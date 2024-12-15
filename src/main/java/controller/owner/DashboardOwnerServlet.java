package controller.owner;

import dao.*;
import dto.OrderDTO;
import dto.OrderRevenueDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Owner;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

@WebServlet(name = "DashboardOwnerServlet", urlPatterns = {"/dashboard-owner"})
public class DashboardOwnerServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, int queryYear)
            throws ServletException, IOException {

        String queryType = request.getParameter("queryType");
        if (queryType != null) {
            request.setAttribute("queryType", queryType);
        }
        else {
            queryType = "monthly";
            request.setAttribute("queryType", queryType);
        }

        DBContext db = new DBContext();
        OwnerDAO ownerDAO = new OwnerDAO(db);
        int ownerId = Integer.parseInt(request.getParameter("ownerId"));
        try {
            Owner owner = ownerDAO.getOwnerByAccountId(ownerId);
            request.setAttribute("owner", owner);
            AccountDAO userDAO = new AccountDAO(db.getConnection());
            User user = userDAO.getAccountById(owner.getAccountId());
            request.setAttribute("ownerAccount", user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (queryType.equals("monthly")) {
            List<OrderRevenueDTO> orderRevenueDTOList = ownerDAO.getOrderRevenueByOwnerId(ownerId, queryYear);
            request.setAttribute("orderRevenueList", orderRevenueDTOList);
        }
        if (queryType.equals("yearly")) {
            List<OrderRevenueDTO> orderRevenueDTOList = ownerDAO.getOrderRevenueInFiveYearsByOwnerId(ownerId);
            request.setAttribute("orderRevenueList", orderRevenueDTOList);
        }

        request.setAttribute("year", queryYear);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            int queryYear = Calendar.getInstance().get(Calendar.YEAR);
            if (request.getParameter("year") != null) {
                queryYear = Integer.parseInt(request.getParameter("year"));
            }
            processRequest(request, response, queryYear);
            request.getRequestDispatcher("dashboard-owner.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int queryYear = Integer.parseInt(request.getParameter("year"));

        processRequest(request, response, queryYear);
        request.getRequestDispatcher("dashboard-owner.jsp").forward(request, response);
    }
}
