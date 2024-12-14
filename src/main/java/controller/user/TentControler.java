/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dao.DBContext;
import dao.TentDAO;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Gear;


@WebServlet(name = "TentControler", urlPatterns = {"/tentcontrol"})
public class TentControler extends HttpServlet {

    private final DBContext dbcontext = new DBContext();
    private TentDAO tentdao;

    @Override
    public void init() throws ServletException {
        try {
            tentdao = new TentDAO(dbcontext.getConnection());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));

        int totalItems = tentdao.getTotalItem();
        List<Gear> gears = tentdao.getAllTent(page, 8);

        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", Math.ceil((totalItems / (double) 8)));
        request.setAttribute("itemsPerPage", 8);
        request.setAttribute("gears", gears);

        request.getRequestDispatcher("/viewtent.jsp").forward(request, response);
    }

}


