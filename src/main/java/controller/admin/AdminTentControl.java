package controller.admin;

import dao.DBContext;

import java.io.IOException;

import dao.TentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.util.List;
import model.Gear;
import model.User;

@WebServlet(name = "AdminTentControl", urlPatterns = {"/admintent"})
public class AdminTentControl extends HttpServlet {

    private final DBContext dbcontext = new DBContext();
    private TentDAO geardao;

    @Override
    public void init() throws ServletException {
        try {
            geardao = new TentDAO(dbcontext.getConnection());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LocalDateTime now = LocalDateTime.now();

        // Lấy thông tin người dùng từ session (User auth)
        User auth = (User) request.getSession().getAttribute("currentUser");

        // Kiểm tra nếu người dùng đã đăng nhập và là chủ cắm trại (isOwner)
        if (auth != null && auth.isOwner()) {
            // Lấy ID của chủ cắm trại từ đối tượng User
            int campsiteOwnerId = auth.getId();  // Thay vì auth.getCampsiteOwnerId(), bạn sử dụng auth.getId()

            // Xử lý phân trang
            int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));

            // Lấy tổng số món đồ cắm trại mà chủ cắm trại này sở hữu
            int totalItems = geardao.getTotalItemByOwner(campsiteOwnerId);

            // Lấy danh sách đồ cắm trại của chủ cắm trại
            List<Gear> gears = geardao.getTentByOwner(campsiteOwnerId, page, 8);

            // Thiết lập các thuộc tính cho view
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", Math.ceil((totalItems / (double) 8)));
            request.setAttribute("itemsPerPage", 8);
            request.setAttribute("gears", gears);

            // Chuyển hướng đến trang crudGear.jsp
            request.getRequestDispatcher("/crudTent.jsp").forward(request, response);
        } else {
            // Nếu người dùng không phải là chủ cắm trại, chuyển hướng đến trang đăng nhập
            response.sendRedirect("login.jsp");
        }
    }
}
