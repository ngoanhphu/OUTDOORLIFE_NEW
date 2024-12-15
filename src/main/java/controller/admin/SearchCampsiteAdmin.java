package controller.admin;

import dao.CampsiteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Campsite;
import model.User;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchCampsiteServlet", urlPatterns = {"/search-campsite-admin"})
public class SearchCampsiteAdmin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchQuery = request.getParameter("search"); // Lấy tham số tìm kiếm từ form

        if (searchQuery == null) {
            searchQuery = ""; // Nếu không có tìm kiếm, đặt là chuỗi rỗng
        }

        // Gọi DAO để tìm kiếm campsite với owner
        CampsiteDAO campsiteDAO = new CampsiteDAO();
        List<Object[]> campsitesWithOwner = campsiteDAO.getCampsitesBySearchWithOwner(searchQuery); // Gọi hàm tìm kiếm với owner

        // Truyền dữ liệu vào request và chuyển hướng đến JSP
        request.setAttribute("campsites", campsitesWithOwner); // Truyền danh sách kết quả vào JSP
        request.setAttribute("search", searchQuery); // Truyền tham số tìm kiếm vào JSP

        request.getRequestDispatcher("/allCampsites.jsp").forward(request, response); // Chuyển hướng đến trang JSP
    }
}
