/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.owner;

import dao.CampsiteDAO;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Campsite;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateCampsiteServlet", urlPatterns = {"/update-campsite"})
public class UpdateCampsiteServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateCampsiteServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCampsiteServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int campsiteId = Integer.parseInt(request.getParameter("id"));
        CampsiteDAO campsiteDAO = new CampsiteDAO();
        Campsite campsite = campsiteDAO.getCampsiteById(campsiteId);  // Lấy thông tin campsite theo id

        if (campsite != null) {
            request.setAttribute("c", campsite);  // Đưa dữ liệu campsite vào request
            request.getRequestDispatcher("/updateCampsite.jsp").forward(request, response);  // Chuyển tiếp đến trang form edit
        } else {
            response.sendRedirect("manage-campsite");  // Nếu không tìm thấy campsite, điều hướng về trang quản lý campsite
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        String limit = request.getParameter("limit");
        String status = request.getParameter("status");
        try {
            CampsiteDAO cdao = new CampsiteDAO();
            Campsite campsite = new Campsite();
            campsite.setCampId(Integer.parseInt(id));
            campsite.setCampPrice(Integer.parseInt(price));
            campsite.setCampAddress(address);
            campsite.setCampName(name);
            campsite.setCampDescription(description);
            campsite.setCampImage(image);
            campsite.setCampStatus(status.equals("1") ? true : false);
            campsite.setLimite(Integer.parseInt(limit));
            cdao.updateCampsite(campsite);
        } catch (Exception ex) {

        }
        response.sendRedirect("manage-campsite");
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


