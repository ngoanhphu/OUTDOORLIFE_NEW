/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.owner;

import dao.*;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.http.HttpSession;
import model.CampsiteOrder;
import model.Order;
import model.User;

/**
 *
 * @author vietn
 */
@WebServlet(name = "CancelOrderServlet", urlPatterns = {"/cancelorder"})
public class CancelOrderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CancelOrderServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CancelOrderServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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

            try (PrintWriter out = response.getWriter()) {
                String id = request.getParameter("id");
                if (id != null) {
                    DBContext db = new DBContext();
                    OrderDAO orderDao = new OrderDAO(db.getConnection());
                    //get order by id
                    Order o = orderDao.getUserById(Integer.parseInt(id));
                    CampsiteOrder campsiteOrder = new CampsiteOrder();
                    campsiteOrder.setCampsiteId(o.getCampsiteId());
                    campsiteOrder.setQuantity(o.getQuantity() * -1);

                    //cancel
                    orderDao.cancelOrder(Integer.parseInt(id));

                    //update quantity campsite
                    CampsiteDAO campsiteDAO = new CampsiteDAO();
                    campsiteDAO.updateQuantityCampsite(campsiteOrder);
                }
                String redirectPage = request.getParameter("redirectPage");
                response.sendRedirect(redirectPage);
            } catch (ClassNotFoundException | SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception ex) {
                Logger.getLogger(CancelOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
