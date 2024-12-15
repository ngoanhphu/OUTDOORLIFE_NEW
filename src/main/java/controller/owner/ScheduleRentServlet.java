/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.owner;

import dao.CampsiteDAO;
import dao.DBContext;
import dao.OrderDAO;
import java.io.IOException;

import dao.OwnerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import model.Campsite;
import model.Order;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ScheduleRentServlet", urlPatterns = {"/schedule-rent"})
public class ScheduleRentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String campsiteIdSelected = request.getParameter("campsite");
            int campsiteId = campsiteIdSelected == null || campsiteIdSelected.isEmpty() ? 0 : Integer.parseInt(campsiteIdSelected);
            String dateStr = request.getParameter("txt");

            // Parse the date if it's not null and in a valid format
            LocalDate date;
            if (dateStr != null && !dateStr.isEmpty()) {
                date = LocalDate.parse(dateStr);
            } else {
                date = LocalDate.now();  // Default to today's date if parameter is null or empty
            }

            // Add 6 days to the parsed or current date
            LocalDate datePlus7Days = date.plusDays(6);
            request.setAttribute("date", date.toString());

            DBContext db = new DBContext();

            // Get ownerId from session
            HttpSession session = request.getSession();
            int ownerId = (int) session.getAttribute("accountId"); // Lấy ownerId từ session

            OrderDAO orderDao = new OrderDAO(db.getConnection());
            List<Order> orders = orderDao.getOrderByDateCampsiteAndOwner(date.toString(),datePlus7Days.toString(),campsiteId,ownerId);
            request.setAttribute("orders", orders);
            System.out.println("list order" +orders );

            // Get list campsite
            CampsiteDAO campsiteDAO = new CampsiteDAO();
            List<Campsite> campsites = campsiteDAO.getCampsitesByOwnerId(ownerId);
            request.setAttribute("campsites", campsites);
            request.setAttribute("campsite", campsiteIdSelected);
            System.out.println("list camp"+campsites);

            request.getRequestDispatcher("scheduleRent.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        response.sendRedirect("notifyToOwner");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        OwnerDAO ownerDAO = new OwnerDAO(new DBContext());

        try {
            if (!ownerDAO.isOwnerEndDateValid(session)) {
                session.setAttribute("message", "Your owner's contract is over!");
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                processRequest(request, response);
//                request.getRequestDispatcher("scheduleRent.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
