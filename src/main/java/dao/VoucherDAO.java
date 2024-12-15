/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Voucher;

/**
 *
 * @author Admin
 */
public class VoucherDAO extends DBContext {

    public List<Voucher> getAllVoucher(int page, int size) throws Exception {
        List<Voucher> vouchers = new ArrayList<>();
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM VOUCHER ORDER BY id desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
            int offset = (page - 1) * size;
            pst.setInt(1, offset);
            pst.setInt(2, size);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Voucher v = new Voucher(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getDate(5),
                        rs.getBoolean(6));
                vouchers.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vouchers;
    }

    public int getTotalItem() {
        try {
            String sql = "SELECT COUNT(*) AS total_items \n"
                    + "FROM [VOUCHER] \n";
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("total_items");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Voucher getVoucherById(int id) throws Exception {
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM VOUCHER WHERE id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                return new Voucher(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getDate(5),
                        rs.getBoolean(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Voucher> getAllVoucherCanUse() throws Exception {
        List<Voucher> vouchers = new ArrayList<>();
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM VOUCHER Where isUsed = 0 and endDate >= ?");
            LocalDate currentDate = LocalDate.now();
            Date sqlDate = Date.valueOf(currentDate);

            pst.setDate(1, sqlDate);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Voucher v = new Voucher(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getDate(5),
                        rs.getBoolean(6));
                vouchers.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vouchers;
    }

    public void insertVoucher(Voucher c) {
        String query = "INSERT INTO VOUCHER \n"
                + "              VALUES (?,?,?,?,0)";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, c.getCode());
            ps.setInt(2, c.getPercent());
            ps.setDate(3, new Date(c.getStartDate().getTime()));
            ps.setDate(4, new Date(c.getEndDate().getTime()));
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateVoucher(Voucher c) {
        String query = "update VOUCHER\n"
                + " set startDate = ?,\n"
                + "	endDate = ?,\n"
                + "	isUsed = ?, vPercent = ?"
                + "	where id = ?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDate(1, new Date(c.getStartDate().getTime()));
            ps.setDate(2, new Date(c.getEndDate().getTime()));
            ps.setBoolean(3, c.isIsUsed());
            ps.setInt(4, c.getPercent());
            ps.setInt(5, c.getId());
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteVoucher(int id) {
        String query = "delete from VOUCHER where id = ?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(VoucherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<String> getAllVoucherCode() throws Exception {
        List<String> codes = new ArrayList<>();
        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT code FROM VOUCHER");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                codes.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codes;
    }


}
