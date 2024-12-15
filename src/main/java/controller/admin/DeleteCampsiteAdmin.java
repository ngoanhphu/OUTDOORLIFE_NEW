package controller.admin;

import dao.CampsiteDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;

@WebServlet(name = "DeleteCampsiteControl", urlPatterns = {"/deleteCampsiteAdmin"})
public class DeleteCampsiteAdmin extends HttpServlet {

    private Connection con;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id"); // Lấy id từ request
        CampsiteDAO campsiteDAO = new CampsiteDAO(con); // Tạo đối tượng DAO để xóa

        try {
            // Gọi phương thức xóa vĩnh viễn từ DAO
            campsiteDAO.deleteCampsiteAdmin(Integer.parseInt(id));
        } catch (Exception ex) {
            // Log lỗi hoặc xử lý thông báo lỗi nếu cần
            ex.printStackTrace();
        }

        // Chuyển hướng lại đến trang danh sách campsite
        response.sendRedirect("all-campsites"); // Trang này sẽ hiển thị lại danh sách sau khi xóa
    }
}
