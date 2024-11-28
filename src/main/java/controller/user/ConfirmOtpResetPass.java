package controller.user;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "ConfirmOtpResetPass", urlPatterns = {"/confirmotp"})
public class ConfirmOtpResetPass extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String code = request.getParameter("authcode");
        String errorMessage = null;
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("authcode");

        if (user != null && code.equals(user.getCode())) {
            response.sendRedirect("TypeNewPassword.jsp");
        } else {
            errorMessage = "Invalid OTP.";
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("verifyChangePass.jsp").forward(request, response);
        }
    }
}
