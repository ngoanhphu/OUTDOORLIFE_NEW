package controller.user;

import dao.DBContext;
import dao.OwnerDAO;
import dao.UserDAO;
import dao.UserDaoImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDAO userDAO = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            User currentUser = (User) session.getAttribute("currentUser");

            if (currentUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            User user = userDAO.findByEmail(currentUser.getEmail());
            if (user != null) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("UserProfile.jsp").forward(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("currentUser");

            if (currentUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");

            String error = "";

            if (firstName == null || firstName.trim().isEmpty()
                    || lastName == null || lastName.trim().isEmpty()
                    || phoneNumber == null || phoneNumber.trim().isEmpty()) {
                error = "All fields are required!";
            }

            String url = "";

            if (!error.isEmpty()) {
                request.setAttribute("error", error);
                url = "/UserProfile.jsp";
            } else {
                currentUser.setFirstName(firstName);
                currentUser.setLastName(lastName);
                currentUser.setPhoneNumber(phoneNumber);
                currentUser.setEmail(email);

                boolean updateSuccessful = userDAO.updateInfo(currentUser);
                if (updateSuccessful) {
                    session.setAttribute("currentUser", currentUser);
                    request.setAttribute("successMessage", "Cập nhật thông tin thành công!");
                    url = "/UserProfile.jsp";
                } else {
                    error = "Failed to update profile. Please try again.";
                    request.setAttribute("error", error);
                    url = "/UserProfile.jsp";
                }
            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
