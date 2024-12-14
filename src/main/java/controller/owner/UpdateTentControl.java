package controller.owner;

import dao.DBContext;
import dao.TentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Gear;

import java.io.File;
import java.io.IOException;

@WebServlet(name = "UpdateTentControl", urlPatterns = {"/updatetent"})
@MultipartConfig
public class UpdateTentControl extends HttpServlet {

    // Đường dẫn lưu trữ ảnh
    private static final String UPLOAD_DIRECTORY = "D:\\OJT\\new_project\\OUTDOORLIFE_NEW\\src\\main\\webapp\\img";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id"); // Lấy Gear ID từ URL
        try {
            DBContext db = new DBContext();
            TentDAO tentDAO = new TentDAO(db.getConnection());
            Gear gear = tentDAO.getTentByID(id); // Lấy thông tin gear từ cơ sở dữ liệu
            request.setAttribute("st", gear); // Đưa gear vào request attribute
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("Update_tent.jsp").forward(request, response); // Forward đến JSP
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("gearId");
        String name = request.getParameter("gearName");
        String price = request.getParameter("gearPrice");
        String description = request.getParameter("gearDescription");

        // Lấy phần tử file từ form
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
            TentDAO tentDAO = new TentDAO(db.getConnection());

            // Cập nhật thông tin tent vào cơ sở dữ liệu
            tentDAO.updateTent(id, name, Integer.parseInt(price), description, image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("admintent"); // Quay lại trang quản lý sau khi cập nhật
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
