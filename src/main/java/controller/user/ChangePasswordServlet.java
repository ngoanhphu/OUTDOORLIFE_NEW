package controller.user;

import dao.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.User;

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("currentUser");
        String email = user.getEmail(); // Assuming email is stored in session
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
            
        System.out.println(email + " " + currentPassword + " " + newPassword + " " + confirmPassword);
        if (email == null || currentPassword == null || newPassword == null || confirmPassword == null) {
            request.setAttribute("message", "nullValue");
        } else if (newPassword.equals(confirmPassword)) {
            try {
                boolean isChanged = userDao.changePassword(email, currentPassword, newPassword);
                if (isChanged) {
                    request.setAttribute("message", "success");
                } else {
                    request.setAttribute("message", "failure");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("message", "error");
            }
        } else {
            request.setAttribute("message", "mismatch");
        }
        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
    }
 }