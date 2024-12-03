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
import java.sql.Connection;
import model.Gear;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdateGearControl", urlPatterns = {"/update"})
public class UpdateGearControll extends HttpServlet {
    private Connection con;
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id"); 
        try {
            DBContext con = new DBContext();
            GearDAO ga = new GearDAO(con.getConnection());
            Gear gear = ga.getGearByID(id);
            request.setAttribute("st", gear);
            
        } catch (Exception ex) {
            
        }
        
        request.getRequestDispatcher("UpdateGear.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String id = request.getParameter("gearId");
            String name = request.getParameter("gearName");
            String price = request.getParameter("gearPrice");
            String decription = request.getParameter("gearDecription");
            String image = request.getParameter("gearImage");
            GearDAO gear = new GearDAO(con);
            try{
            gear.UpdateGear(id, name, price, decription, image);
        }
            catch(Exception ex){
                
            }
            response.sendRedirect("admin");
        }
    }