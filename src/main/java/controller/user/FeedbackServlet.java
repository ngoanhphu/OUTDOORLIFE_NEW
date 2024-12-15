package controller.user;

import dao.DBContext;
import dao.FeedbackDAO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.OwnerDAO;
import dao.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Feedback;
import model.User;
@WebServlet(name = "FeedbackServlet", urlPatterns = {"/feedback"})
public class FeedbackServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

            String campsiteId = request.getParameter("campsiteId");


            request.setAttribute("campsiteId", campsiteId);
            request.getRequestDispatcher("feedback.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String rating = request.getParameter("rating");
        String content = request.getParameter("content");
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String campsiteId = request.getParameter("campsiteId");

        // In ra để kiểm tra
        System.out.println("Campsite ID: " + campsiteId);

        HttpSession session = request.getSession();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        String url = "error.jsp";

        try {

            User user = (User) session.getAttribute("currentUser");
            if (user != null) {
                boolean result = feedbackDAO.insertFeedback(Integer.parseInt(rating), date, user.getId(), Integer.parseInt(campsiteId), content);

                if (result) {
                    List<Feedback> listFeedback = feedbackDAO.getAllFeedback();
                    System.out.println("list"+listFeedback);
                    request.setAttribute("listFeedback", listFeedback);
                    // request.setAttribute("message", "Cảm ơn bạn đã đánh giá phản hồi"); // Thêm thông báo cảm ơn
                    url = "feedbackdone.jsp";
                    request.getRequestDispatcher(url). forward(request, response);
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
