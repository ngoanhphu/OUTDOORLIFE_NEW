///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package controller.owner;
//
//import dao.CampsiteOrderDAO;
//import dao.DBContext;
//import dao.OrderDetailDAO;
//import java.io.IOException;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.util.List;
//import model.CampsiteOrder;
//import model.OrderDetail;
//import model.User;
//
///**
// *
// * @author Admin
// */
//@WebServlet(name = "OrderDetailServlet", urlPatterns = {"/order-detail"})
//public class OrderDetailServlet extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try {
//            String action = request.getParameter("action");
//            String id = request.getParameter("id");
//            User auth = (User) request.getSession().getAttribute("currentUser");
//            DBContext db = new DBContext();
//            if (auth != null) {
//                request.setAttribute("person", auth);
//                CampsiteOrderDAO orderDao = new CampsiteOrderDAO(db.getConnection());
//                CampsiteOrder campsite = orderDao.getCampsiteOrderByOrderId(Integer.parseInt(id));
//                request.setAttribute("campsite", campsite);
//
//                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
//                List<OrderDetail> ods = orderDetailDAO.orderDetailByOrderId(Integer.parseInt(id));
//                request.setAttribute("ods", ods);
//                request.setAttribute("action", action);
//                request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
//            } else {
//                response.sendRedirect("login.jsp");
//            }
//        } catch (Exception e) {
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
