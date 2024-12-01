//package controller.user;
//
//import dao.DBContext;
//import dao.OrderDAO;
//import java.io.IOException;
//import java.io.PrintWriter;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import model.Cart;
//import model.Order;
//import model.User;
//
//@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
//public class CheckoutServlet extends HttpServlet {
//
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CheckoutServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CheckoutServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try (PrintWriter out = response.getWriter()) {
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//            Timestamp now = new Timestamp(System.currentTimeMillis());
//
//            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
//            User auth = (User) request.getSession().getAttribute("currentUser");
//
//            if (cart_list != null && auth != null) {
//                for (Cart c : cart_list) {
//                    Order order = new Order();
//                    order.setGearId(c.getGearId());
//                    order.setBooker(auth.getId());
//                    order.setTimeStamp(now);
//                    order.setQuantity(c.getQuantity());
//
//                    DBContext db = new DBContext();
//                    OrderDAO oDao = new OrderDAO(db.getConnection());
//                    boolean result = oDao.insertOrder(order);
//                    if (!result) {
//                        break;
//                    }
//                }
//                cart_list.clear();
//                response.sendRedirect("orders.jsp");
//            } else {
//                if (auth == null) {
//                    response.sendRedirect("login1.jsp");
//                } else {
//                    response.sendRedirect("Cart.jsp");
//                }
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(CheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//}
