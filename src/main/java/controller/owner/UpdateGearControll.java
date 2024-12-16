package controller.owner;

import dao.DBContext;
import dao.GearDAO;
import dao.OwnerDAO;
import dao.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Gear;
import model.User;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "UpdateGearControl", urlPatterns = {"/update"})
@MultipartConfig
public class UpdateGearControll extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "D:\\INTELIJI\\OUTDOORLIFE\\OUTDOORLIFE_NEW\\src\\main\\webapp\\img";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        OwnerDAO ownerDAO = new OwnerDAO(new DBContext());
        User auth = (User) request.getSession().getAttribute("currentUser");

        if (auth == null) {
            session.setAttribute("message", "You are not logged in!");
            response.sendRedirect("login.jsp");
        } else {
            UserDaoImpl userDAO = new UserDaoImpl();
            boolean isDeactivated = userDAO.isAccountDeactivated(auth.getId());
            if (isDeactivated) {
                session.setAttribute("message", "Your account has been deactivated!");
                response.sendRedirect("loginMessage");
            }

            String id = request.getParameter("id"); // Lấy Gear ID từ URL
            try {
                DBContext db = new DBContext();
                GearDAO gearDAO = new GearDAO(db.getConnection());
                Gear gear = gearDAO.getGearByID(id); // Lấy thông tin gear từ cơ sở dữ liệu
                request.setAttribute("st", gear); // Đưa gear vào request attribute
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("UpdateGear.jsp").forward(request, response); // Forward đến JSP
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("gearId");
        String name = request.getParameter("gearName");
        String price = request.getParameter("gearPrice");
        String description = request.getParameter("gearDescription");

        // Xử lý file tải lên
        Part filePart = request.getPart("gearImage"); // Lấy phần tử file từ form
        String image = extractFileName(filePart); // Lấy tên file

        // Kiểm tra nếu có tệp tải lên thì lưu vào thư mục
        if (image != null && !image.isEmpty()) {
            // Đảm bảo thư mục lưu ảnh tồn tại
            File uploadDir = new File(UPLOAD_DIRECTORY);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            // Lưu tệp vào thư mục
            String filePath = UPLOAD_DIRECTORY + File.separator + image;
            filePart.write(filePath); // Lưu tệp vào đường dẫn
        }

        try {
            DBContext db = new DBContext();
            GearDAO gearDAO = new GearDAO(db.getConnection());

            // Cập nhật thông tin gear vào cơ sở dữ liệu
            gearDAO.updateGear(id, name, Integer.parseInt(price), description, image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("viewOwner"); // Quay lại trang quản lý sau khi cập nhật
    }

    // Hàm lấy tên file từ phần tử "gearImage"
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String content : contentDisp.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return null;
    }
}
