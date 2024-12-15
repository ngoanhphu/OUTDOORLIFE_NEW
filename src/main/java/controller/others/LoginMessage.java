package controller.others;

import dao.UserDaoImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet(urlPatterns = {"/loginMessage"})
public class LoginMessage extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User auth = (User) session.getAttribute("currentUser");

        if (auth == null) {
            session.setAttribute("message", "You are not logged in!");
            response.sendRedirect("login.jsp");
            return;
        }

        UserDaoImpl userDAO = new UserDaoImpl();
        boolean isDeactivated = userDAO.isAccountDeactivated(auth.getId());

        if (isDeactivated) {
            session.setAttribute("message", "Your account has been deactivated!");
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
