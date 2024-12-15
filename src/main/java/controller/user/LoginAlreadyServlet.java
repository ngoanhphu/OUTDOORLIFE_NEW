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
import model.User;

@WebServlet(name = "loginservlet1", urlPatterns = {"/loginservlet1"})
public class LoginAlreadyServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Xác thực người dùng là quản trị viên
        User user = authenticateUser(email, password, true);
        if (user != null&& user.isAdmin()) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            response.sendRedirect("admin.jsp");
            return; // Kết thúc phương thức sau khi chuyển hướng
        }

        // Nếu không phải quản trị viên, tiếp tục xác thực như người dùng thông thường
        user = authenticateUser(email, password, false);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            response.sendRedirect("Cart.jsp");
        } else {
            request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu có thể sai! Vui lòng đăng nhập lại");
            request.getRequestDispatcher("login1.jsp").forward(request, response);
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

      private User authenticateUser(String email, String password,boolean isAdmin) {
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
