//package controller.user.ReportTask;
//
//import dao.*;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import model.Campsite;
//import model.Order;
//import model.Owner;
//import model.User;
//import java.io.IOException;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@WebServlet(name = "ReportServlet", urlPatterns = {"/reportServlet"})
//public class ReportServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            HttpSession session = request.getSession();
//            User currentUser = (User) session.getAttribute("currentUser");
//            if (currentUser == null) {
//                response.sendRedirect("login.jsp");
//                return;
//            }
//            Order order = (Order) session.getAttribute("ordersReport");
//            int campsiteId = order.getCampsiteId();
//            CampsiteDAO campsiteDAO = new CampsiteDAO();
//            Campsite camp = campsiteDAO.getCampsiteById(campsiteId);
//            DBContext db = new DBContext();
//            OwnerDAO ownerDAO = new OwnerDAO(new DBContext());
//            int ownerId = ownerDAO.getOwnerIdByCampsiteIdInTableCampsite(campsiteId);
//            Owner owner = ownerDAO.getOwnerByOwnerId(ownerId);
//            request.setAttribute("campReport", camp);
//            request.setAttribute("ownerReport", owner);
//
//            String action = request.getParameter("action"); // Lấy giá trị của tham số "action"
//            if ("campsite".equals(action)) {
//                // Nếu nhấn nút "Report Campsite"
//                response.sendRedirect("reportCampsite.jsp");
//            } else if ("owner".equals(action)) {
//                // Nếu nhấn nút "Report Owner"
//                response.sendRedirect("reportOwner.jsp");
//            } else {
//                // Xử lý nếu không có nút hợp lệ
//                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
//            }
//
//        }   catch (Exception e) {
//            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, e);
//            }
//    }
//}
package controller.user.ReportTask;

import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Campsite;
import model.Order;
import model.Owner;
import model.User;
import java.io.IOException;
import java.util.List;
import java.util.Set; // Thêm import
import java.util.HashSet; // Thêm import
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

@WebServlet(name = "ReportServlet", urlPatterns = {"/reportServlet"})
public class ReportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("currentUser");
            if (currentUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            Set<Integer> savedCampsiteIds = (Set<Integer>) session.getAttribute("campsiteIds");
            Set<Integer> savedOwnerIds = (Set<Integer>) session.getAttribute("ownerIds");
            CampsiteDAO campsiteDAO = new CampsiteDAO();
            OwnerDAO ownerDAO = new OwnerDAO(new DBContext());
            List<Campsite> campsiteList = new ArrayList<>();
            List<Owner> ownerList = new ArrayList<>();
            String action = request.getParameter("action");

            if ("campsite".equals(action)) {
                for (Integer id : savedCampsiteIds) {
                    Campsite camp = campsiteDAO.getCampsiteById(id);
                    if (camp != null) {
                        campsiteList.add(camp);
                    }
                }
                request.setAttribute("campsitesReport", campsiteList);
                request.getRequestDispatcher("/report/reportCampsite.jsp").forward(request, response);
                }  else if ("owner".equals(action)) {
                for (Integer id : savedOwnerIds) {
                    Owner owner = ownerDAO.getOwnerByOwnerId(id);
                    if (owner != null) {
                        ownerList.add(owner);
                    }
                }
                    request.setAttribute("ownerReportReport", ownerList);
                    request.getRequestDispatcher("/report/reportOwner.jsp").forward(request, response);
                }

        } catch (Exception e) {
            Logger.getLogger(ReportServlet.class.getName()).log(Level.SEVERE, null, e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Server Error");
        }
    }
}
