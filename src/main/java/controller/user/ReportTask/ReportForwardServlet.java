package controller.user.ReportTask;

import controller.user.PartialCheckoutServlet;
import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import model.Campsite;
import model.Order;
import model.Owner;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ReportForwardServlet", urlPatterns = {"/reportForward"})
public class ReportForwardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("currentUser");
            if (currentUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            int accountId = currentUser.getId();
            DBContext db = new DBContext();
            AccountDAO acc= new AccountDAO(db.getConnection());
            int customer_id = acc.getCustomerIdByAccountId(accountId);
            OrderDAO orderDAO = new OrderDAO(db.getConnection());
            OwnerDAO ownerDAO = new OwnerDAO(new DBContext());
            List<Order> orders = orderDAO.getOrdersByAccountId(customer_id);
            if (orders != null && !orders.isEmpty()) {
                // Nếu đã từng đặt hàng, gửi thông tin orders đến trang lựa chọn giữa campsites và owners
                // Lấy các campsiteId duy nhất từ orders
                Set<Integer> campsiteIds = new HashSet<>();
                Set<Integer> ownerIds = new HashSet<>();
                for (Order order : orders) {
                    campsiteIds.add(order.getCampsiteId());
                    int ownerId = ownerDAO.getOwnerIdByCampsiteIdInTableCampsite(order.getCampsiteId());
                    ownerIds.add(ownerId);
                }
                session.setAttribute("ownerIds", ownerIds);
                session.setAttribute("campsiteIds", campsiteIds);
                request.getRequestDispatcher("/report/selectReportOption.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/report/reportNoneOrder.jsp").forward(request, response);
            }
        } catch (Exception e) {
            Logger.getLogger(ReportForwardServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
