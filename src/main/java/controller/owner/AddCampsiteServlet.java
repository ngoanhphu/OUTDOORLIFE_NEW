/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.owner;

import dao.CampsiteDAO;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import dao.DBContext;
import dao.OwnerDAO;
import dao.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Campsite;
import model.User;
import jakarta.servlet.http.Part;

@WebServlet(name = "AddCampsideServlet", urlPatterns = {"/add-campsite"})
@MultipartConfig
public class AddCampsiteServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "D:\\OJT\\new_project\\OUTDOORLIFE_NEW\\src\\main\\webapp\\img";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddCampsideServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddCampsideServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
            processRequest(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ form
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        String limit = request.getParameter("limit");
        String status = request.getParameter("status");

        // Lấy user từ session
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser != null && currentUser.isOwner()) {
            int ownerId = currentUser.getId();

            try {
                // Xử lý file upload
                Part filePart = request.getPart("image"); // "image" là tên input file
                String fileName = extractFileName(filePart);

                // Đảm bảo thư mục upload tồn tại
                File uploadDir = new File(UPLOAD_DIRECTORY);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                // Lưu file vào thư mục
                String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
                filePart.write(filePath);

                // Lưu đường dẫn file vào database (chỉ lưu tên file hoặc đường dẫn tương đối)
                String imagePath = fileName;

                // Tạo đối tượng Campsite
                CampsiteDAO cdao = new CampsiteDAO();
                Campsite campsite = new Campsite();
                campsite.setCampName(name);
                campsite.setCampPrice(Integer.parseInt(price));
                campsite.setCampAddress(address);
                campsite.setCampDescription(description);
                campsite.setCampImage(imagePath); // Đường dẫn tương đối để hiển thị
                campsite.setCampStatus(status.equals("1"));
                campsite.setLimite(Integer.parseInt(limit));

                // Thêm campsite vào cơ sở dữ liệu
                cdao.insertCampsite(campsite, ownerId);

                // Chuyển hướng về trang quản lý
                response.sendRedirect("manage-campsite");
            } catch (Exception e) {
                e.printStackTrace();
                // Hiển thị lỗi nếu xảy ra
                request.setAttribute("error", "Failed to add campsite: " + e.getMessage());
                request.getRequestDispatcher("addCampsiteForm.jsp").forward(request, response);
            }
        } else {
            // Chuyển hướng đến trang đăng nhập nếu user không hợp lệ
            response.sendRedirect("login.jsp");
        }
    }

    // Hàm lấy tên file từ header
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
