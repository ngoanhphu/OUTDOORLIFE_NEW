package controller.others;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cart;

@WebServlet(name = "QuantityIncDecServlet", urlPatterns = {"/quantityset"})
public class QuantityIncDecServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");

            if (cart_list == null) {
                out.println("Giỏ hàng trống hoặc chưa được khởi tạo.");
                response.sendRedirect("Cart.jsp");
                return;
            }

            if (action != null && id >= 1) {
                boolean itemFound = false;
                if (action.equals("inc")) {
                    for (Cart c : cart_list) {
                        if (c.getGearId() == id) {
                            int quantity = c.getQuantity();
                            quantity++;
                            c.setQuantity(quantity);
                            itemFound = true;
                            break;
                        }
                    }
                } else if (action.equals("dec")) {
                    for (Cart c : cart_list) {
                        if (c.getGearId() == id && c.getQuantity() > 1) {
                            int quantity = c.getQuantity();
                            quantity--;
                            c.setQuantity(quantity);
                            itemFound = true;
                            break;
                        }
                    }
                }

                if (itemFound) {
                    request.getSession().setAttribute("cart-list", cart_list); // Đảm bảo session được cập nhật
                    response.sendRedirect("Cart.jsp");
                } else {
                    out.println("Không tìm thấy mặt hàng hoặc hành động không hợp lệ.");
                    response.sendRedirect("Cart.jsp");
                }
            } else {
                out.println("Hành động hoặc ID không hợp lệ.");
                response.sendRedirect("Cart.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
