package controller.others;

import dao.DBContext;
import dao.GearDAO;
import java.io.IOException;
import java.io.PrintWriter;

import dao.OwnerDAO;
import dao.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.Gear;
import model.User;

@WebServlet(name = "CartServlet", urlPatterns = {"/cartservlet"})
public class CartServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartServlet at " + request.getContextPath() + "</h1>");
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

            response.setContentType("text/html;charset=UTF-8");

            try (PrintWriter out = response.getWriter()) {
                ArrayList<Cart> cartList = new ArrayList();
                int id = Integer.parseInt(request.getParameter("id"));
                DBContext con = new DBContext();
                GearDAO gear = new GearDAO(con.getConnection());
                Gear g = gear.getGearByIDPrice(id + "");
                Cart c = new Cart();
                c.setQuantity(1);
                c.setGearId(id);
                c.setGearPrice(c.getQuantity() * g.getGearPrice());

                ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
                if (cart_list == null) {
                    cartList.add(c);
                    session.setAttribute("cart-list", cartList);
                } else {
                    cartList = cart_list;
                    boolean exist = false;
                    for (Cart cm : cart_list) {
                        if (cm.getGearId() == id) {
                            exist = true;
                            break;
                        }
                    }
                    if (!exist) {
                        cartList.add(c);
                    }
                }

                // Lấy giá trị của tham số redirectPage
                String redirectPage = request.getParameter("redirectPage");
                if (redirectPage == null || redirectPage.isEmpty()) {
                    // Đặt giá trị mặc định nếu cần thiết
                    redirectPage = "viewgear.jsp";
                }
                response.sendRedirect(redirectPage);
            } catch (Exception ex) {
                Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
