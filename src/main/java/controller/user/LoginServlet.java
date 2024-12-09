package controller.user;

import dao.DBContext;
import dao.OwnerDAO;
import dao.UserDaoImpl;
import java.io.IOException;
import java.security.MessageDigest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.SQLException;

import model.User;

@WebServlet(name = "loginservlet", urlPatterns = {"/loginservlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String email = request.getParameter("email");
        String password = request.getParameter("password");
        OwnerDAO ownerDAO = new OwnerDAO(new DBContext());

        // Xác thực người dùng là quản trị viên
        User user = authenticateUser(email, password);
        if (user != null&& user.isAdmin()) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            response.sendRedirect("dashboard");
            return; // Kết thúc phương thức sau khi chuyển hướng
        }else if (user != null && user.isOwner()) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            session.setAttribute("accountId", user.getId());

            try {
                boolean ownerExists = ownerDAO.isOwnerExist(user.getId());
                if (ownerExists) {

                    response.sendRedirect("schedule-rent");

                } else {
                    response.sendRedirect("owner/registerOwner.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
            return;
        }

        // Nếu không phải quản trị viên, tiếp tục xác thực như người dùng thông thường
        else if (user != null) {
            HttpSession session = request.getSession();
            user.setPhoneNumber(user.getPhoneNumber());
            user.setEmail(user.getEmail());
            session.setAttribute("currentUser", user);
            response.sendRedirect("user.jsp");
        } else {
            request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu có thể sai! Vui lòng đăng nhập lại");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
}

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    // Method to hash password using SHA-256
    private String hashPassword(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

      private User authenticateUser(String email, String password) {
        UserDaoImpl userDAO = new UserDaoImpl();
        User user = userDAO.findByEmail(email);
        if (user != null) {
            try {
                String passwordHashed = hashPassword(password);
                if (user.getPasswordHash().equals(passwordHashed)) {
                    return user;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
