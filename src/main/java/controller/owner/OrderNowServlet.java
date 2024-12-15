package controller.owner;

import dao.DBContext;
import dao.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.OwnerDAO;
import dao.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Order;
import model.User;

@WebServlet(name = "OrderNowServlet", urlPatterns = {"/ordernow"})
public class OrderNowServlet extends HttpServlet {

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

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                LocalDateTime now = LocalDateTime.now();

                if (auth != null) {
                    String productId = request.getParameter("id");
                    int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                    if (productQuantity <= 0) {
                        productQuantity = 1;
                    }

                    Order order = new Order();
                    order.setGearId(Integer.parseInt(productId));
                    order.setBooker(auth.getId());
                    Timestamp timestamp = Timestamp.valueOf(now);
                    order.setTimeStamp(timestamp);
                    order.setQuantity(productQuantity);

                    DBContext db = new DBContext();
                    OrderDAO orderDao = new OrderDAO(db.getConnection());
                    boolean result = orderDao.insertOrder(order);

                    if (result) {
                        // Xóa sản phẩm đã đặt hàng khỏi giỏ hàng
                        ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                        if (cart_list != null) {
                            cart_list.removeIf(c -> c.getGearId() == Integer.parseInt(productId));
                        }
                        response.sendRedirect("orders.jsp");
                    } else {
                        out.println("Đặt hàng thất bại");
                    }
                } else {
                    response.sendRedirect("login1.jsp");
                }

            } catch (Exception ex) {
                Logger.getLogger(OrderNowServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
