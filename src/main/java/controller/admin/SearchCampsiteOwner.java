package controller.admin;

import dao.CampsiteDAO;
import model.Campsite;
import model.User;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SearchCampsiteCRUD", urlPatterns = {"/searchCampsite"})
public class SearchCampsiteOwner extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Lấy thông tin người dùng từ session
        User auth = (User) request.getSession().getAttribute("currentUser");

        // Kiểm tra xem người dùng đã đăng nhập và có quyền là chủ campsite
        if (auth != null && auth.isOwner()) {
            // Lấy ID của chủ campsite
            int campsiteOwnerId = auth.getId();

            // Lấy thông tin từ form tìm kiếm
            String txtSearch = request.getParameter("txt");

            // Sử dụng DAO để tìm kiếm Campsite
            CampsiteDAO dao = new CampsiteDAO();
            List<Campsite> campsites = dao.searchCampsiteByNameAndOwner(txtSearch, campsiteOwnerId);

            // Gửi danh sách Campsite sang trang JSP
            request.setAttribute("campsites", campsites);
            request.getRequestDispatcher("crudCampsite.jsp").forward(request, response);
        } else {
            // Nếu không hợp lệ, chuyển hướng về trang đăng nhập hoặc báo lỗi
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(SearchCampsiteOwner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}