package controller.user;

import VnpayService.VnpayService;
import VnpayService.Config;
import dao.CampsiteDAO;
import dao.DBContext;
import dao.GearDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.VoucherDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import model.Campsite;
import model.CampsiteOrder;
import model.Cart;
import model.Order;
import model.OrderDetail;
import model.User;
import model.Voucher;

@WebServlet(name = "PartialCheckoutServlet", urlPatterns = {"/partialCheckout"})
public class PartialCheckoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String priceStr = request.getParameter("price");
            String responseCode = request.getParameter("vnp_ResponseCode");

            HttpSession session = request.getSession();
            DBContext db = new DBContext();
            OrderDAO oDao = new OrderDAO(db.getConnection());
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            CampsiteDAO campsiteDAO = new CampsiteDAO();

            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            ArrayList<Cart> cart_list_selected = (ArrayList<Cart>) request.getSession().getAttribute("cart_list_selected");
            CampsiteOrder campsiteOrder = (CampsiteOrder) session.getAttribute("CampsiteOrder");
            User auth = (User) request.getSession().getAttribute("currentUser");

            if (responseCode != null && responseCode.equals("00")) {
                //success
                Order order = new Order(auth.getId(), campsiteOrder.getCampsiteId(), campsiteOrder.getStartDate(), campsiteOrder.getEndDate(), false, true, campsiteOrder.getQuantity(),
                        Integer.parseInt(priceStr), campsiteOrder.getTotalAmountBooking());
                int orderId = oDao.insertOrder2(order);
                //create order detail
                for (Cart cart : cart_list_selected) {
                    OrderDetail od = new OrderDetail(orderId, cart.getQuantity(), cart.getGearId(), cart.getGearPrice());
                    orderDetailDAO.insertOrderDetail(od);
                }

                //update quantity of campsite
                campsiteDAO.updateQuantityCampsite(campsiteOrder);

                //update status voucher
                VoucherDAO vdao = new VoucherDAO();
                if (campsiteOrder.getVoucherId() != null) {
                    Voucher v = vdao.getVoucherById(campsiteOrder.getVoucherId());
                    v.setIsUsed(true);
                    vdao.updateVoucher(v);
                }

                //clear cart
                if (cart_list != null) {
                    cart_list.removeAll(cart_list_selected);
                    session.setAttribute("cart_list", cart_list);
                }

                session.removeAttribute("cart_list_selected");
                response.sendRedirect("orderscamp.jsp");
            } else {
                //fail
                request.setAttribute("error", "Payment fail!");
                request.getRequestDispatcher("Cart.jsp").forward(request, response);
            }
        } catch (Exception e) {
            Logger.getLogger(PartialCheckoutServlet.class.getName()).log(Level.SEVERE, null, e);
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
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            DBContext db = new DBContext();
            OrderDAO oDao = new OrderDAO(db.getConnection());
            OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
            CampsiteDAO campsiteDAO = new CampsiteDAO();

//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//            Timestamp now = new Timestamp(System.currentTimeMillis());
            String[] selectedItems = request.getParameterValues("selectedItems");
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            ArrayList<Cart> cart_list_selected = new ArrayList<>();
            User auth = (User) request.getSession().getAttribute("currentUser");
            CampsiteOrder campsiteOrder = (CampsiteOrder) session.getAttribute("CampsiteOrder");
            int total = 0;
            if (auth != null && campsiteOrder != null) {
                if (selectedItems != null && cart_list != null) {
                    for (String itemId : selectedItems) {
                        int gearId = Integer.parseInt(itemId);
                        for (Cart c : cart_list) {
                            if (c.getGearId() == gearId) {
                                cart_list_selected.add(c);
//                            Order order = new Order();
//                            order.setGearId(c.getGearId());
//                            order.setBooker(auth.getId());
//                            order.setTimeStamp(now);
//                            order.setQuantity(c.getQuantity());
//
//                            DBContext db = new DBContext();
//                            OrderDAO oDao = new OrderDAO(db.getConnection());
//                            boolean result = oDao.insertOrder(order);
//                            if (!result) {
//                                break;
//                            }
                            }
                        }
                        for (Cart cart : cart_list_selected) {
                            System.out.println(cart);
                        }
                    }
                }
//                cart_list.clear();
//                response.sendRedirect("orders.jsp");

                session.setAttribute("cart_list_selected", cart_list_selected);
                DBContext dbContext = new DBContext();
                GearDAO pDao = new GearDAO(dbContext.getConnection());
                total = pDao.getTotalCartPrice(cart_list_selected);

                int billTotal = total + campsiteOrder.getTotalAmountBooking();
                VoucherDAO vdao = new VoucherDAO();
                Voucher v = null;
                if (campsiteOrder.getVoucherId() != null) {
                    v = vdao.getVoucherById(campsiteOrder.getVoucherId());
                    billTotal = billTotal * (100 - v.getPercent()) / 100;
                }

                String paymentMethod = request.getParameter("paymentMethod");
                if (paymentMethod.equals("VNPay")) {
                    //Option1 // go to vnpay
                    String url = VnpayService.paymentUrl(request, (long) billTotal);
                    response.sendRedirect(url);
                } else {
                    //Option2//order
                    //create order
                    Order order = new Order(auth.getId(), campsiteOrder.getCampsiteId(), campsiteOrder.getStartDate(), campsiteOrder.getEndDate(), false, false, campsiteOrder.getQuantity(),
                            billTotal, campsiteOrder.getTotalAmountBooking());
                    int orderId = oDao.insertOrder2(order);
                    //create order detail
                    for (Cart cart : cart_list_selected) {
                        OrderDetail od = new OrderDetail(orderId, cart.getQuantity(), cart.getGearId(), cart.getGearPrice());
                        orderDetailDAO.insertOrderDetail(od);
                    }

                    //update quantity of campsite
                    campsiteDAO.updateQuantityCampsite(campsiteOrder);

                    //update status voucher
                    if (v != null) {
                        v.setIsUsed(true);
                        vdao.updateVoucher(v);
                    }

                    //clear cart
                    if (cart_list != null) {
                        cart_list.removeAll(cart_list_selected);
                        session.setAttribute("cart_list", cart_list);
                    }

                    session.removeAttribute("cart_list_selected");
                    response.sendRedirect("orderscamp.jsp");
                }
            } else {
                if (auth == null) {
                    response.sendRedirect("login1.jsp");
                } else {
                    response.sendRedirect("Cart.jsp");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PartialCheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Partial checkout for selected items";
    }
}
