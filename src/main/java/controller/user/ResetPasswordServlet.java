package controller.user;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.UserDaoImpl;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

@WebServlet(name = "ResetPasswordServlet", urlPatterns = {"/resetpassword"})
public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String errorMessage = null;
        String successMessage = null;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("authcode");

        if (user != null) {
            if (newPassword.equals(confirmPassword)) {
                UserDaoImpl userDao = new UserDaoImpl();
                boolean status = false;
                try {
                    // Hash mật khẩu trước khi lưu vào cơ sở dữ liệu
                    String hashedPassword = hashPassword(newPassword);
                    status = userDao.updatePassword(user.getEmail(), hashedPassword);
                } catch (Exception ex) {
                    Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (status) {
                    successMessage = "Password has been successfully reset. You can now log in.";
                    session.removeAttribute("authcode");
                } else {
                    errorMessage = "An error occurred while updating the password.";
                }
            } else {
                errorMessage = "Passwords do not match.";
            }
        } else {
            errorMessage = "Session expired or invalid.";
        }

        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("successMessage", successMessage);
        request.getRequestDispatcher("TypeNewPassword.jsp").forward(request, response);
    }

    private String hashPassword(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(password.getBytes());

        // Convert byte array to hexadecimal string
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
}
