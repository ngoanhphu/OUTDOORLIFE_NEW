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
        String id = request.getParameter("id"); // Lấy Gear ID từ URL
        try {
            DBContext db = new DBContext();
            GearDAO gearDAO = new GearDAO(db.getConnection());
            Gear gear = gearDAO.getGearByID(id); // Lấy thông tin gear từ cơ sở dữ liệu
            request.setAttribute("st", gear); // Đưa gear vào request attribute
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("UpdateGear.jsp").forward(request, response); // Forward đến JSP
    }




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("gearId");
        String name = request.getParameter("gearName");
        String price = request.getParameter("gearPrice");
        String description = request.getParameter("gearDescription");
        String image = request.getParameter("gearImage");

        try {
            DBContext db = new DBContext();
            GearDAO gearDAO = new GearDAO(db.getConnection());
            gearDAO.updateGear(id, name, Integer.parseInt(price), description, image); // Gửi dữ liệu mới đến DB
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("viewOwner"); // Quay lại trang quản lý sau khi cập nhật
    }



}