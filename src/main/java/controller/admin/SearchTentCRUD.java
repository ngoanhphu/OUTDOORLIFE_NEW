package controller.admin;



import dao.DBContext;
import dao.GearDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Gear;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SearchControl", urlPatterns = {"/searchTent"})
public class SearchTentCRUD extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // Lấy đối tượng User từ session
        User auth = (User) request.getSession().getAttribute("currentUser");

        // Kiểm tra nếu người dùng đã đăng nhập và là chủ cắm trại
        if (auth != null && auth.isOwner()) {
            // Lấy ID của chủ cắm trại
            int campsiteOwnerId = auth.getId();

            // Lấy thông tin tìm kiếm từ form
            String txtSearch = request.getParameter("txt");

            // Gọi DAO để tìm kiếm Gear của chủ sở hữu
            DBContext db = new DBContext();
            GearDAO dao = new GearDAO(db.getConnection());
            List<Gear> list = dao.searchByNameAndOwnerTent(txtSearch, campsiteOwnerId);

            // Gửi dữ liệu kết quả tìm kiếm sang JSP
            request.setAttribute("gears", list);
            request.getRequestDispatcher("crudTent.jsp").forward(request, response);
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
            Logger.getLogger(controller.others.SearchControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}