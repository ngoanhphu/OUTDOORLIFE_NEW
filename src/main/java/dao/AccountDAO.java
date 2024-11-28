/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Gear;
import model.User;

/**
 *
 * @author Admin
 */
public class AccountDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public AccountDAO(Connection con) {
        this.con = con;
    }

    public int getTotalItem() {
        try {
            String sql = "SELECT COUNT(*) AS total_items \n"
                    + "FROM [ACCOUNT] \n";
            PreparedStatement ps = this.con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("total_items");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<User> getAllAccount(int page, int size) {
        List<User> accounts = new ArrayList<>();
        String query = "SELECT G.* FROM [ACCOUNT] G"
                + " ORDER BY G.[Account_id] desc "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement pst = this.con.prepareStatement(query)) {
            int offset = (page - 1) * size;
            pst.setInt(1, offset);
            pst.setInt(2, size);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("Account_id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setEmail(rs.getString("Gmail"));
                    user.setPhoneNumber(rs.getString("phone_number"));
                    user.setPasswordHash(rs.getString("passwordHash"));
                    user.setAdmin(rs.getBoolean("isAdmin"));
                    user.setOwner(rs.getBoolean("isOwner"));
                    accounts.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
