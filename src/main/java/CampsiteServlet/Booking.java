package CampsiteServlet;

import dao.CampsiteDAO;
import dao.VoucherDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import model.Campsite;
import model.Voucher;

@WebServlet(name = "Booking", urlPatterns = {"/booking"})
public class Booking extends HttpServlet {

    private CampsiteDAO campsiteDAO;

    @Override
    public void init() {
        campsiteDAO = new CampsiteDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String campId = request.getParameter("campId");
            if (campId != null) {
                int id = Integer.parseInt(campId);
                Campsite campsite = campsiteDAO.getSingleCampsite(id);
                request.setAttribute("campsite", campsite);

                LocalDate currentDate = LocalDate.now();
                LocalDate nextDate = currentDate.plusDays(1);
                request.setAttribute("currentDate", currentDate.toString());
                request.setAttribute("nextDate", nextDate.toString());

                VoucherDAO vdao = new VoucherDAO();
                List<Voucher> vouchers = vdao.getAllVoucherCanUse();
                request.setAttribute("vouchers", vouchers);

                request.getRequestDispatcher("form_book.jsp").forward(request, response);
            } else {
                response.sendRedirect("campsites");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
