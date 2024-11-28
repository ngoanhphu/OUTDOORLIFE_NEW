package controller;

import dao.FeedbackDAO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Feedback;
import model.User;

public class FeedbackServlet extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String rating = request.getParameter("rating");
        String content = request.getParameter("content");
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        HttpSession session = request.getSession();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        String url = "error.jsp";

        try {
            User user = (User) session.getAttribute("currentUser");
            if (user != null) {
                boolean result = feedbackDAO.insertFeedback(new Feedback(0, user.getId(), date, content, rating));

                if (result) {
                    List<Feedback> listFeedback = feedbackDAO.getAllFeedback();
                    request.setAttribute("listFeedback", listFeedback);
                   // request.setAttribute("message", "Cảm ơn bạn đã đánh giá phản hồi"); // Thêm thông báo cảm ơn
                    url = "feedbackdone.jsp";
                   request.getRequestDispatcher(url). forward(request, response);
                    return;
                }
            } else {
                // Chuyển hướng người dùng tới trang đăng nhập nếu chưa đăng nhập
                url = "login.jsp";
                 response.sendRedirect(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }
       public static void main(String[] args) {
        // Tạo một danh sách giả đầy đủ các đối tượng Feedback
        List<Feedback> feedbackList = new ArrayList<>();
        
        // Thêm các đối tượng Feedback vào danh sách (giả định)
        feedbackList.add(new Feedback(1, 1, "2024-07-11", "Nội dung đánh giá 1", "5"));
        feedbackList.add(new Feedback(2, 2, "2024-07-10", "Nội dung đánh giá 2", "4"));
        feedbackList.add(new Feedback(3, 3, "2024-07-09", "Nội dung đánh giá 3", "3"));
        
        // In ra thông tin của từng đối tượng Feedback
        for (Feedback feedback : feedbackList) {
            System.out.println("ID: " + feedback.getFeedbackId());
            System.out.println("UserID: " + feedback.getAccountId());
            System.out.println("Date: " + feedback.getFeedbackDate());
            System.out.println("Content: " + feedback.getContent());
            System.out.println("Rating: " + feedback.getRating());
            System.out.println("-------------------------");
        }
    }
}
