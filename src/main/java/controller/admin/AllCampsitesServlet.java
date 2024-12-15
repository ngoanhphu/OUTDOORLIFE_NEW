package controller.admin;

import dao.CampsiteDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Campsite;

/**
 * Servlet để lấy danh sách tất cả các campsite
 */
@WebServlet(name = "AllCampsitesServlet", urlPatterns = {"/all-campsites"})
public class AllCampsitesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CampsiteDAO campsiteDAO = new CampsiteDAO();

        // Lấy danh sách campsite và thông tin owner
        List<Object[]> campsites = campsiteDAO.getAllCampsitesWithOwner();

        // Truyền dữ liệu vào request để hiển thị trên JSP
        request.setAttribute("campsites", campsites);

        // Forward đến trang quản lý campsite
        request.getRequestDispatcher("/allCampsites.jsp").forward(request, response);
    }
}
