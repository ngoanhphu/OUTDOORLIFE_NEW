/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.owner;

import dao.DBContext;
import dao.TentDAO;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Gear;


@WebServlet(name = "UpdateTentControl", urlPatterns = {"/updatetent"})
public class UpdateTentControl extends HttpServlet {

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id"); 
        try {
            DBContext con = new DBContext();
            TentDAO ga = new TentDAO(con.getConnection());
            Gear tent = ga.getGearByID(id);
            request.setAttribute("st", tent);
            
        } catch (Exception ex) {
            
        }
        
        request.getRequestDispatcher("Update_tent.jsp").forward(request, response);
    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String id = request.getParameter("gearId");
            String name = request.getParameter("gearName");
            String price = request.getParameter("gearPrice");
            String decription = request.getParameter("gearDecription");
            String image = request.getParameter("gearImage");
            TentDAO gear = new TentDAO();
            try{
            gear.UpdateGear(id, name, price, decription, image);
        }
            catch(Exception ex){
                
            }
            response.sendRedirect("admintent");
        }
    }
    


