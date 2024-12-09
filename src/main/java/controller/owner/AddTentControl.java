/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.owner;

import dao.DBContext;
import dao.GearDAO;
import java.io.IOException;

import dao.TentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddTentControl", urlPatterns = {"/addtent"})
public class AddTentControl extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ form
        String name = request.getParameter("gearName");
        String price = request.getParameter("gearPrice");
        String description = request.getParameter("gearDescription");
        String image = request.getParameter("gearImage");

        // Lấy thông tin user từ session
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // Kiểm tra nếu người dùng đã đăng nhập và là chủ cắm trại
        if (currentUser != null && currentUser.isOwner()) {
            // Lấy ownerId từ đối tượng User
            int ownerId = currentUser.getId();

            try {
                // Kết nối đến cơ sở dữ liệu
                DBContext con = new DBContext();
                TentDAO gearDAO = new TentDAO(con.getConnection());

                // Thêm gear vào cơ sở dữ liệu, bao gồm ownerId
                gearDAO.insertGear(name, price, description, image, ownerId);

                // Chuyển hướng về trang admin sau khi thêm gear thành công
                response.sendRedirect("viewTent");
            } catch (Exception e) {
                e.printStackTrace();
                // Hiển thị lỗi nếu có
                request.setAttribute("error", "Error adding Tent");
                request.getRequestDispatcher("createTentForm.jsp").forward(request, response);
            }
        } else {
            // Nếu người dùng chưa đăng nhập hoặc không phải owner, chuyển hướng về trang đăng nhập
            response.sendRedirect("login.jsp");
        }
    }
}

