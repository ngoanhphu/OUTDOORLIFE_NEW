package controller.others;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import dao.UserDaoImpl;

@WebServlet(name = "VerifyCode", urlPatterns = {"/verifycode"})
public class VerifyCode extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("authcode");
        String code = request.getParameter("authcode");

        if (code.equals(user.getCode())) {
            UserDaoImpl guestDao = new UserDaoImpl();
            boolean status = guestDao.insertGuest(user.getFirstName(), user.getLastName(), user.getEmail(),user.getPhoneNumber() , user.getPasswordHash(),user.isOwner());
            if (status) {
                session.removeAttribute("authcode");
                    request.setAttribute("successMessage", "Registration successful! You can now log in.");
            } else {
                request.setAttribute("errorMessage", "An error occurred during registration.");
            }
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Incorrect verification code.");
            request.getRequestDispatcher("verify.jsp").forward(request, response);
        }
    }
}
