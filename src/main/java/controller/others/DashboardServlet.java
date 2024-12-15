/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.others;

import dao.DBContext;
import dao.OrderDAO;
import dao.OwnerDAO;
import dao.UserDaoImpl;
import dto.OrderDTO;
import java.io.IOException;

import dto.OrderRevenueDTO;
import dto.OwnerDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Owner;
import model.User;

import java.util.Calendar;
import java.util.List;


@WebServlet(name = "DashboardServlet", urlPatterns = {"/dashboard"})
public class DashboardServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int queryYear = Calendar.getInstance().get(Calendar.YEAR);
        if (request.getParameter("year") != null) {
            queryYear = Integer.parseInt(request.getParameter("year"));
        }

        String queryType = request.getParameter("queryType");
        if (queryType != null) {
            request.setAttribute("queryType", queryType);
        }
        else {
            queryType = "monthly";
            request.setAttribute("queryType", queryType);
        }


        try {
            DBContext db = new DBContext();
            OrderDAO odao = new OrderDAO(db.getConnection());
            OwnerDAO ownerDAO = new OwnerDAO(db);

            Owner owner = null;
            // Get curent login user
            User loginUser = (User) request.getSession().getAttribute("currentUser");
            if (loginUser != null && loginUser.isOwner()) {
                owner = ownerDAO.getOwnerByAccountId(loginUser.getId());
            }

            if (queryType.equals("monthly")) {
                if (loginUser != null && loginUser.isAdmin()) {
                    List<OrderDTO> lstCountOrderByMonth = odao.statisticOrdersByMonth(queryYear);
                    request.setAttribute("lstCountOrderByMonth", lstCountOrderByMonth);
                }
                else if (loginUser != null &&  loginUser.isOwner()) {
                    List<OrderRevenueDTO> orderRevenueDTOList = ownerDAO.getOrderRevenueByOwnerId(owner.getOwnerId(), queryYear);
                    request.setAttribute("lstCountOrderByMonth", orderRevenueDTOList);
                }
            }

            if (queryType.equals("yearly")) {
                if (loginUser != null && loginUser.isAdmin()) {
                    List<OrderDTO> lstCountOrderByYear = odao.statisticOrdersByYear();
                    request.setAttribute("lstCountOrderByYear", lstCountOrderByYear);
                }
                else if (loginUser != null && loginUser.isOwner()) {
                    List<OrderRevenueDTO> orderRevenueDTOList = ownerDAO.getOrderRevenueInFiveYearsByOwnerId(owner.getOwnerId());

//                    request.setAttribute("orderRevenueList", orderRevenueDTOList);
//                    List<OrderDTO> lstCountOrderByMonth = odao.statisticOrdersByMonth(queryYear);
                    request.setAttribute("lstCountOrderByMonth", orderRevenueDTOList);
                }
            }

            List<OwnerDTO> ownerListWithRevenue = ownerDAO.getOwnersWithAccountInfoAndRevenue();

            request.setAttribute("ownerListWithRevenue", ownerListWithRevenue);

            request.setAttribute("year", queryYear);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("======== Error when query =========");
            System.out.println(e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/OrderRevenueDTO error occurs
     */
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
            processRequest(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/OrderRevenueDTO error occurs
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
