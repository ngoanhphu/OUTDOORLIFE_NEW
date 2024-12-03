/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.owner;

import dao.DBContext;
import dao.GearDAO;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddControl", urlPatterns = {"/add"})
public class AddGearControl extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String price = request.getParameter("gearPrice");
        String name = request.getParameter("gearName");
        String description = request.getParameter("gearDescription");
        String image = request.getParameter("gearImage");
        
        try {
            DBContext con = new DBContext();
        GearDAO gear = new GearDAO(con.getConnection());
            gear.insertGear(price, name, description, image);
        } catch(Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("admin");
    }
}