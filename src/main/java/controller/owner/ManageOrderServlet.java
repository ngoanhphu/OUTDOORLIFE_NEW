/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.owner;

import dao.CampsiteOrderDAO;
import dao.DBContext;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CampsiteOrder;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ManageOrderServlet", urlPatterns = {"/manage-order"})
public class ManageOrderServlet extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        DBContext db = new DBContext();
        CampsiteOrderDAO campsiteOrderDAO = new CampsiteOrderDAO(db.getConnection());
        // Lấy số trang từ tham số yêu cầu, nếu không có tham số thì mặc định là trang 1.
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));

        int totalItems = campsiteOrderDAO.getTotalItem();//lấy tổng dnah sách đơn hàng
        List<CampsiteOrder> orders = campsiteOrderDAO.getAllOrders(page, 8);
        //Lấy danh sách các đơn hàng của trang hiện tại với 8 mục mỗi trang.

        request.setAttribute("currentPage", page);
        //tinh tong so trang
        request.setAttribute("totalPages", Math.ceil((totalItems / (double) 8)));
        
        request.setAttribute("itemsPerPage", 8);
        request.setAttribute("orders", orders);

        request.getRequestDispatcher("/manageOrder.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManageOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManageOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
