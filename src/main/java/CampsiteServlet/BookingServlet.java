package CampsiteServlet;

import dao.CampsiteDAO;
import dao.CampsiteOrderDAO;
import dao.VoucherDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CampsiteOrder;
import model.Discount;
import model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Campsite;
import model.Voucher;

@WebServlet(name = "BookingServlet", urlPatterns = {"/bookingservlet"})
public class BookingServlet extends HttpServlet {

    private CampsiteDAO campsiteDAO;
    private CampsiteOrderDAO campsiteOrderDAO;
    private Connection con; // Biến connection

    @Override
    public void init() {
        campsiteDAO = new CampsiteDAO();
        con = (Connection) getServletContext().getAttribute("connection"); // Lấy connection từ ServletContext
        campsiteOrderDAO = new CampsiteOrderDAO(con); // Khởi tạo CampsiteOrderDAO với connection
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String campIdStr = request.getParameter("campId");
        String checkInStr = request.getParameter("CheckIn");
        String checkOutStr = request.getParameter("CheckOut");
        String quantityStr = request.getParameter("quantity");
        String CampPriceStr = request.getParameter("CampPrice");
//        String discountCode = request.getParameter("DiscountCode");
        String voucherId = request.getParameter("voucher");

        if (campIdStr != null && checkInStr != null && checkOutStr != null) {
            int campId = Integer.parseInt(campIdStr);

            try {
                int quantity = Integer.parseInt(quantityStr);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date checkInDate = dateFormat.parse(checkInStr);
                Date checkOutDate = dateFormat.parse(checkOutStr);

                if (checkInDate.compareTo(checkOutDate) > 0) {
                    request.setAttribute("error", "CheckIn date must before or equal to CheckOut date.");
                    request.getRequestDispatcher("booking").forward(request, response);
                    return;
                }

                Campsite campsite = campsiteDAO.getSingleCampsite(campId);
                if (campsite.getLimite() < quantity) {
                    request.setAttribute("error", "This campsite can only be booked for a maximum of " + campsite.getLimite());
                    request.getRequestDispatcher("booking").forward(request, response);
                    return;
                }

                VoucherDAO vdao = new VoucherDAO();
                if (voucherId != null && !voucherId.isEmpty()) {
                    Voucher v = vdao.getVoucherById(Integer.parseInt(voucherId));
                    Date curretnDate = new Date();
                    if (v.getStartDate().compareTo(curretnDate) > 0) {
                        request.setAttribute("error", "The starting date to use this voucher is " + v.getStartDate());
                        request.getRequestDispatcher("booking").forward(request, response);
                        return;
                    }
                }

                CampsiteOrder order = new CampsiteOrder();
                order.setCampsiteId(campId);
                order.setBooker(currentUser.getId());
                order.setStartDate(new Timestamp(checkInDate.getTime()));
                order.setEndDate(new Timestamp(checkOutDate.getTime()));
                order.setApproveStatus(false);
                order.setPaymentStatus(false);
                order.setTimeStamp(new Timestamp(System.currentTimeMillis()));
                order.setQuantity(quantity);

                Timestamp startDate = order.getStartDate();
                Timestamp endDate = order.getEndDate();
                long milliseconds = endDate.getTime() - startDate.getTime();
                int bookingDays = (int) (milliseconds / (1000 * 60 * 60 * 24));

                order.setTotalAmountBooking(quantity * Integer.parseInt(CampPriceStr) * bookingDays);

                if (voucherId != null && !voucherId.isEmpty()) {
                    order.setVoucherId(Integer.parseInt(voucherId));
                } else {
                    order.setVoucherId(null);
                }

                session.setAttribute("CampsiteOrder", order);
                response.sendRedirect("Cart.jsp");

            } catch (Exception ex) {
                Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.sendRedirect("campsites.jsp");
        }
    }
}
