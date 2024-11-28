/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dao.CommentDAO;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import model.Comment;
import model.User;

/**
 *
 * @author vietn
 */
@WebServlet(name = "CommentServlet", urlPatterns = {"/comment"})
public class CommentServlet extends HttpServlet {
    private CommentDAO commentDAO;

    @Override
    public void init() {
        commentDAO = new CommentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) request.getSession().getAttribute("currentUser");  
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String originPage = request.getParameter("originPage");
        
        Comment comment = new Comment();
        comment.setAccountId(currentUser.getId());
        comment.setContent(request.getParameter("comment"));
        comment.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        comment.setCampsiteAddress(request.getParameter("campAddress"));

        try {
            commentDAO.insertComment(comment);
            response.sendRedirect(request.getContextPath() + "/" + originPage); 
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Chuyển hướng đến trang lỗi nếu có lỗi xảy ra
        }
    }
}
