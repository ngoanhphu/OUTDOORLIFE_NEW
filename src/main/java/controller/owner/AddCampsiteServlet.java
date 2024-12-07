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
import jakarta.servlet.http.HttpSession;
import model.Campsite;
import model.User;


@WebServlet(name = "AddCampsideServlet", urlPatterns = {"/add-campsite"})
public class AddCampsiteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddCampsideServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCampsideServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Lấy đối tượng User từ session
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // Kiểm tra nếu người dùng đã đăng nhập và là chủ sở hữu (owner)
        if (currentUser != null && currentUser.isOwner()) {
            // Lấy ownerId từ đối tượng User
            int ownerId = currentUser.getId();

            // Lấy các tham số từ form
            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String address = request.getParameter("address");
            String description = request.getParameter("description");
            String image = request.getParameter("image");
            String limit = request.getParameter("limit");
            String status = request.getParameter("status");

            try {
                // Tạo đối tượng Campsite và thiết lập các thuộc tính
                CampsiteDAO cdao = new CampsiteDAO();
                Campsite campsite = new Campsite();
                campsite.setCampPrice(Integer.parseInt(price));
                campsite.setCampAddress(address);
                campsite.setCampName(name);
                campsite.setCampDescription(description);
                campsite.setCampImage(image);
                campsite.setCampStatus(status.equals("1") ? true : false);
                campsite.setLimite(Integer.parseInt(limit));

                // Gọi phương thức insertCampsite và truyền ownerId
                cdao.insertCampsite(campsite, ownerId);

                // Chuyển hướng tới trang quản lý campsite
                response.sendRedirect("manage-campsite");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Nếu người dùng không phải là chủ sở hữu, chuyển hướng đến trang đăng nhập
            response.sendRedirect("login.jsp");
        }
    }

}
