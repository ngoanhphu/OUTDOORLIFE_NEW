package controller.owner;

import dao.DBContext;
import dao.GearDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Gear;

@WebServlet(name = "GearController", urlPatterns = {"/gearcontroller"})
public class GearController extends HttpServlet {

    private final DBContext dbcontext = new DBContext();
    private GearDAO geardao;

    @Override
    public void init() throws ServletException {
        try {
            geardao = new GearDAO(dbcontext.getConnection());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        
        int totalItems = geardao.getTotalItem();
        List<Gear> gears = geardao.getAllGears(page, 8);

        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", Math.ceil((totalItems / (double) 8)));
        request.setAttribute("itemsPerPage", 8);
        request.setAttribute("gears", gears);

        request.getRequestDispatcher("/viewgear.jsp").forward(request, response);

        
    }
}
