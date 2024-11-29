package controller.others;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import model.User;
import dao.UserDaoImpl;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "RegisterAcc", urlPatterns = {"/registeracc"})
public class RegisterAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String agreed = request.getParameter("agreed");
        String isOwner=request.getParameter("isOwner");
        JSONObject jsonResponse = new JSONObject();
        UserDaoImpl guestDao = new UserDaoImpl();
        String code = guestDao.getRandom();

        if (agreed != null && isOwner==null) {
            try {
                boolean emailExists = guestDao.emailExists(email);
                if (emailExists) {
                    jsonResponse.put("success", false);
                    jsonResponse.put("message", "Email is already registered. Please use a different email.");
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
                        jsonResponse.put("success", true);
                    } else {
                        jsonResponse.put("success", false);
                        jsonResponse.put("message", "An error occurred while sending the verification email.");
                    }
                }
            } catch (Exception ex) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "An error occurred while checking email.");
            }
        } else if(agreed != null && isOwner!=null) {
            try {
                boolean emailExists = guestDao.emailExists(email);
                if (emailExists) {
                    jsonResponse.put("success", false);
                    jsonResponse.put("message", "Email is already registered. Please use a different email.");
                } else {
                    User guest = new User();
                    guest.setFirstName(firstName);
                    guest.setLastName(lastName);
                    guest.setEmail(email);
                    guest.setPhoneNumber(phone);
                    guest.setPasswordHash(password);
                    guest.setCode(code);
                    guest.setOwner(true);


                    boolean emailSent = guestDao.sendEmail(guest);
                    if (emailSent) {
                        HttpSession session = request.getSession();
                        session.setAttribute("authcode", guest);
                        jsonResponse.put("success", true);
                    } else {
                        jsonResponse.put("success", false);
                        jsonResponse.put("message", "An error occurred while sending the verification email.");
                    }
                }
            } catch (Exception ex) {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "An error occurred while checking email.");
            }
        }
        else {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Please accept the Terms of Service.");
        }

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());
    }
}
