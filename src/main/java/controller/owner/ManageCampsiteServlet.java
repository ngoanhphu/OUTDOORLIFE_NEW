/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.owner;

import dao.CampsiteDAO;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Campsite;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ManageCampsiteServlet", urlPatterns = {"/manage-campsite"})
public class ManageCampsiteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User auth = (User) request.getSession().getAttribute("currentUser");

        if (auth != null && auth.isOwner()) {
            int ownerId = auth.getId(); // ID chủ sở hữu
            int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
            int size = 9; // Số lượng campsite mỗi trang

            CampsiteDAO campsiteDAO = new CampsiteDAO();
            List<Campsite> campsites = campsiteDAO.getCampsitesByOwner(ownerId, page, size);

            request.setAttribute("currentPage", page);
            request.setAttribute("campsites", campsites);
            request.setAttribute("totalPages", Math.ceil(campsiteDAO.getTotalCampsitesByOwner(ownerId) / (double) size));

            request.getRequestDispatcher("/crudCampsite.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

}
