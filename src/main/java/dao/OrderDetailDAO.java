/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Campsite;
import model.CampsiteOrder;
import model.Gear;
import model.OrderDetail;

/**
 *
 * @author Admin
 */
public class OrderDetailDAO extends DBContext {

    public void insertOrderDetail(OrderDetail c) {
        String query = "INSERT INTO [ORDER_DETAIL] (Orders_id, Quantity, Gear_id, SubAmount) VALUES (?,?,?,?)";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, c.getOrderId());
            ps.setInt(2, c.getQuantity());
            ps.setInt(3, c.getGearId());
            ps.setInt(4, c.getSubAmount());
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<OrderDetail> orderDetailByOrderId(int orderId) {
        List<OrderDetail> ods = new ArrayList<>();
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement("select g.*,od.Quantity, od.SubAmount from ORDER_DETAIL od\n"
                    + "left join GEAR g on od.Gear_id = g.Gear_id\n"
                    + "where od.Orders_id = ?");
            pst.setInt(1, orderId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                OrderDetail order = new OrderDetail();
                Gear gear = new Gear();
                gear.setGearId(rs.getInt("Gear_id"));
                gear.setGearPrice(rs.getInt("Price"));
                gear.setGearName(rs.getString("Name"));
                gear.setGearDecription(rs.getString("Description"));
                gear.setGearImage(rs.getString("Image"));
                order.setGear(gear);
                order.setQuantity(rs.getInt("Quantity"));
                order.setSubAmount(rs.getInt("SubAmount"));
                ods.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return ods;
    }
}
