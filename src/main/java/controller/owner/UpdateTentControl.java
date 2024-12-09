/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.owner;

import dao.DBContext;
import dao.TentDAO;
import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Gear;


@WebServlet(name = "UpdateTentControl", urlPatterns = {"/updatetent"})
public class UpdateTentControl extends HttpServlet {
    private Connection con;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id"); // Lấy Gear ID từ URL
        try {
            DBContext db = new DBContext();
            TentDAO gearDAO = new TentDAO(db.getConnection());
            Gear gear = gearDAO.getTentByID(id); // Lấy thông tin gear từ cơ sở dữ liệu
            request.setAttribute("st", gear); // Đưa gear vào request attribute
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("Update_tent.jsp").forward(request, response); // Forward đến JSP
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
            TentDAO gearDAO = new TentDAO(db.getConnection());
            gearDAO.updateTent(id, name, Integer.parseInt(price), description, image); // Gửi dữ liệu mới đến DB
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("admintent");// Quay lại trang quản lý sau khi cập nhật
    }
}



