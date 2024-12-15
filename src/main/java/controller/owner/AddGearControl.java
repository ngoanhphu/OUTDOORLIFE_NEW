package controller.owner;

import dao.DBContext;
import dao.GearDAO;
import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.User;

@WebServlet(name = "AddControl", urlPatterns = {"/addGear"})
@MultipartConfig // Cho phép servlet xử lý multipart/form-data
public class AddGearControl extends HttpServlet {

    private static final String UPLOAD_DIRECTORY = "D:\\OJT\\new_project\\OUTDOORLIFE_NEW\\src\\main\\webapp\\img";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ form
        String name = request.getParameter("gearName");
        String price = request.getParameter("gearPrice");
        String description = request.getParameter("gearDescription");

        // Lấy thông tin user từ session
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // Kiểm tra nếu người dùng đã đăng nhập và là chủ cắm trại
        if (currentUser != null && currentUser.isOwner()) {
            int ownerId = currentUser.getId();

            try {
                // Xử lý file upload
                Part filePart = request.getPart("gearImage");
                String fileName = extractFileName(filePart);

                // Đảm bảo thư mục upload tồn tại
                File uploadDir = new File(UPLOAD_DIRECTORY);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs(); // Tạo thư mục nếu chưa tồn tại
                }

                // Lưu file vào thư mục
                String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
                filePart.write(filePath);

                // Đường dẫn file lưu vào database
                String imagePath = fileName; // Đường dẫn tương đối

                // Kết nối đến cơ sở dữ liệu
                DBContext con = new DBContext();
                GearDAO gearDAO = new GearDAO(con.getConnection());

                // Thêm gear vào cơ sở dữ liệu
                gearDAO.insertGear(name, price, description, imagePath, ownerId);

                // Chuyển hướng về trang admin sau khi thêm gear thành công
                response.sendRedirect("viewOwner");
            } catch (Exception e) {
                e.printStackTrace();
                // Hiển thị lỗi nếu có
                request.setAttribute("error", "Error adding gear");
                request.getRequestDispatcher("createGearForm.jsp").forward(request, response);
            }
        } else {
            // Nếu người dùng chưa đăng nhập hoặc không phải owner, chuyển hướng về trang đăng nhập
            response.sendRedirect("login.jsp");
        }
    }

    // Lấy tên file từ phần header
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

