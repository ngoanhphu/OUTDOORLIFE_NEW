/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.owner;

import dao.VoucherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Voucher;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateVoucherServlet", urlPatterns = {"/update-voucher"})
public class UpdateVoucherServlet extends HttpServlet {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateVoucherServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateVoucherServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        try {
            VoucherDAO vdao = new VoucherDAO();
            Voucher v = vdao.getVoucherById(Integer.parseInt(id));
            request.setAttribute("c", v);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        request.getRequestDispatcher("updateVoucher.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        String percent = request.getParameter("percent");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String isUsed = request.getParameter("isUsed");
        try {
            VoucherDAO vdao = new VoucherDAO();
            Voucher v = vdao.getVoucherById(Integer.parseInt(id));

            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            Date start = formatter.parse(startDate);
            Date end = formatter.parse(endDate);
            if (start.compareTo(end) > 0) {
                request.setAttribute("error", "Start date must before or equal to end date.");
                doGet(request, response);
                return;
            }

            boolean status = isUsed.equals("1") ? true : false;
            v.setPercent(Integer.parseInt(percent));
            v.setStartDate(start);
            v.setEndDate(end);
            v.setIsUsed(status);

            vdao.updateVoucher(v);
            response.sendRedirect("manage-voucher");
        } catch (Exception ex) {
            request.setAttribute("error", "Fail " + ex);
            doGet(request, response);
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
