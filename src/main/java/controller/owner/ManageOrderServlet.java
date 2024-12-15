package controller.owner;

import dao.*;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.http.HttpSession;
import model.CampsiteOrder;
import model.Order;
import model.Owner;
import model.User;

@WebServlet(name = "ManageOrderServlet", urlPatterns = {"/manage-order"})
public class ManageOrderServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
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
        DBContext db = new DBContext();
        CampsiteOrderDAO campsiteOrderDAO = new CampsiteOrderDAO(db.getConnection());
        OrderDAO orderDAO = new OrderDAO(db.getConnection());
        AccountDAO accountDAO = new AccountDAO(db.getConnection());

        if (auth != null) {
            int accountId = auth.getId();
            int ownerID = accountDAO.getOwnerIdByAccountId(accountId);
            int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));

            int totalItems = campsiteOrderDAO.getTotalItem();
            List<Order> orders = orderDAO.getOrdersByOwnerId(ownerID);

            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", Math.ceil((totalItems / (double) 8)));
            request.setAttribute("itemsPerPage", 8);
            request.setAttribute("orders", orders);

            request.getRequestDispatcher("/manageOrder.jsp").forward(request, response);
        }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManageOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ManageOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
