package controller.owner;

import dao.DBContext;
import dao.NotificationDao;
import dao.OwnerDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Notification;
import model.Owner;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "NotificationToOwnerServlet", urlPatterns = {"/notifyToOwner"})
public class NotificationToOwnerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        int currentId = user.getId();
        OwnerDAO ownerDAO = new OwnerDAO(new DBContext());

        try {
            Owner owner = ownerDAO.getOwnerByAccountId(currentId);
            int ownerId = owner.getOwnerId();
            System.out.println(ownerId);

            NotificationDao dao = new NotificationDao();
            int unreadCount = dao.getUnreadCount(ownerId);
            List<Notification> notifications = dao.getNotifications(ownerId);

            session.setAttribute("unreadCount", unreadCount);
            session.setAttribute("notifications", notifications);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        request.getRequestDispatcher("schedule-rent").forward(request, response);
        response.sendRedirect("schedule-rent");
    }

}

