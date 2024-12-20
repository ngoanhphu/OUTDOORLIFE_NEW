package controller.owner;

import VnpayService.VnpayService;
import dao.DBContext;
import dao.OwnerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Owner;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.sql.Date;

@WebServlet("/owner/extendContract")
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
                String paymentMethod = request.getParameter("paymentMethod");

                Date currentEndDate = owner.getEndDate();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentEndDate);
                calendar.add(Calendar.MONTH, duration);
                Date newEndDate = new Date(calendar.getTimeInMillis());

                if ("VNPay".equals(paymentMethod)) {
                    long price = Long.parseLong(request.getParameter("total-price"));
                    String url = VnpayService.paymentUrl(request, price);
                    session.setAttribute("newEndDate", newEndDate);
                    response.sendRedirect(url);
                } else {
                    boolean isUpdated = ownerDAO.updateOwnerEndDate(accountId, newEndDate);
                    if (isUpdated) {
                        response.sendRedirect("success.jsp");
                    } else {
                        request.setAttribute("errorMessage", "Failed to extend the contract.");
                        request.getRequestDispatcher("extendcontract.jsp").forward(request, response);
                    }
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

        try {
            String status = ownerDAO.getOwnerStatusByAccountId(session);
            if ("disapproved".equals(status) || "pending".equals(status)) {
                session.setAttribute("message", "You are not an approved owner!");
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                String responseCode = request.getParameter("vnp_ResponseCode");
                int accountId = (Integer) session.getAttribute("accountId");
                Date newEndDate = (Date) session.getAttribute("newEndDate");

                if ("00".equals(responseCode)) {
                    try {
                        boolean isUpdated = ownerDAO.updateOwnerEndDate(accountId, newEndDate);
                        if (isUpdated) {
                            response.sendRedirect("success.jsp");
                        } else {
                            request.setAttribute("errorMessage", "Failed to extend the contract.");
                            request.getRequestDispatcher("extendcontract.jsp").forward(request, response);
                        }
                    } catch (SQLException e) {
                        throw new ServletException("Database error", e);
                    }
                } else {
                    request.setAttribute("errorMessage", "Payment failed.");
                    request.getRequestDispatcher("extendcontract.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}