/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.owner;

import dao.VoucherDAO;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import model.Voucher;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddVoucherServlet", urlPatterns = {"/add-voucher"})
public class AddVoucherServlet extends HttpServlet {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
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
        LocalDate currentDate = LocalDate.now();
        request.setAttribute("currentDate", currentDate.toString());
        request.getRequestDispatcher("/addVoucher.jsp").forward(request, response);
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
        processRequest(request, response);
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
            response.setContentType("text/html;charset=UTF-8");
            String percent = request.getParameter("percent");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            VoucherDAO vdao = new VoucherDAO();
            List<String> codes = vdao.getAllVoucherCode();
            String code = generateUniqueVoucherCode(codes);

            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            Date start = formatter.parse(startDate);
            Date end = formatter.parse(endDate);

            if (start.compareTo(end) > 0) {
                request.setAttribute("error", "Start date must before or equal to end date.");
                doGet(request, response);
                return;
            }

            Voucher v = new Voucher(code, Integer.parseInt(percent), start, end, false);
            vdao.insertVoucher(v);
            response.sendRedirect("manage-voucher");

        } catch (Exception e) {
            request.setAttribute("error", "Fail: " + e);
            doGet(request, response);
        }
    }

    public static String generateUniqueVoucherCode(List<String> codes) {
        String voucherCode;
        do {
            voucherCode = generateVoucherCode(8);
        } while (codes.contains(voucherCode));

        return voucherCode;
    }

    public static String generateVoucherCode(int length) {
        StringBuilder voucherCode = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            voucherCode.append(CHARACTERS.charAt(index));
        }

        return voucherCode.toString();
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
