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

@WebServlet(name = "SendOtpServlet", urlPatterns = {"/sendotp"})
public class SendOtpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String errorMessage = null;
        String successMessage = null;
      
        UserDaoImpl guestDao = new UserDaoImpl();
        String code = guestDao.getRandom();

        try {
                boolean emailExists = guestDao.emailExists(email);
                if (!emailExists) {
                    errorMessage = "Email not yet Register, please check again!!!";
                } else {
                    User guest = new User();                 
                    guest.setFirstName(firstName);
                    guest.setLastName(lastName);
                    guest.setEmail(email);
                    guest.setPhoneNumber(phone);
                    guest.setPasswordHash(password);
                    guest.setCode(code);
                    
                    boolean emailSent = guestDao.sendEmail(guest);
                    if (emailSent) {
                        HttpSession session = request.getSession();
                        session.setAttribute("authcode", guest);
                        response.sendRedirect("verifyChangePass.jsp");
                        return;
                    } else {
                        errorMessage = "An error occurred while sending the verification email.";
                    }
                }
            } catch (Exception ex) {
                errorMessage = "An error occurred while checking email.";
            }

        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("successMessage", successMessage);
        request.getRequestDispatcher("forgotpass.jsp").forward(request, response);
    }
}
