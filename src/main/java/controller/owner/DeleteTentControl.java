/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.owner;

import dao.GearDAO;
import java.io.IOException;

import dao.TentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DeleteTentControl", urlPatterns = {"/deletetent"})
public class DeleteTentControl extends HttpServlet {
    private Connection con;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id"); //get id tu jsp
        TentDAO gear = new TentDAO(con);
        try {
            gear.deleteGear(id);
        } catch (Exception ex) {

        }
        response.sendRedirect("viewTent");
    }
}
