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
            String sql = "SELECT COUNT(*) AS total_items FROM [ACCOUNT]";
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
        String query = "SELECT G.* FROM [ACCOUNT] G ORDER BY G.[Account_id] DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
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


    public boolean updateAccount(User user) {
        String sql = "UPDATE [ACCOUNT] SET first_name = ?, last_name = ?, Gmail = ?, phone_number = ?, passwordHash = ?, isAdmin = ?, isOwner = ? WHERE Account_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhoneNumber());
            ps.setString(5, user.getPasswordHash());
            ps.setBoolean(6, user.isAdmin());
            ps.setBoolean(7, user.isOwner());
            ps.setInt(8, user.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAccount(int accountId) {
        String sql = "DELETE FROM [ACCOUNT] WHERE Account_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getAccountById(int accountId) {
        String sql = "SELECT * FROM [ACCOUNT] WHERE Account_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("Account_id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setEmail(rs.getString("Gmail"));
                    user.setPhoneNumber(rs.getString("phone_number"));
                    user.setPasswordHash(rs.getString("passwordHash"));
                    user.setAdmin(rs.getBoolean("isAdmin"));
                    user.setOwner(rs.getBoolean("isOwner"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> searchAccounts(String keyword) {
        List<User> accounts = new ArrayList<>();
        String sql = "SELECT * FROM [ACCOUNT] WHERE first_name LIKE ? OR last_name LIKE ? OR Gmail LIKE ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            String searchKeyword = "%" + keyword + "%";
            ps.setString(1, searchKeyword);
            ps.setString(2, searchKeyword);
            ps.setString(3, searchKeyword);
            try (ResultSet rs = ps.executeQuery()) {
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

    public int getOwnerIdByAccountId(int accountId) throws SQLException {
        String query = "SELECT owner_id FROM [dbo].[OWNER] WHERE Account_id = ?";
        try (PreparedStatement stmt = this.con.prepareStatement(query)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("owner_id");
                }
            }
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }

    public boolean isAccountDeactivated(int accountId) {
        String sql = "SELECT deactivated FROM [ACCOUNT] WHERE Account_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("deactivated") == 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
