package dao;

import jakarta.servlet.http.HttpSession;
import model.OwnerContract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerDAO {
    private DBContext db;

    public OwnerDAO(DBContext db) {
        this.db = db;
    }



    public boolean insertOwnerContract(OwnerContract contract, HttpSession session) throws SQLException {

        Integer accountId = (Integer) session.getAttribute("accountId");
        Integer ownerId = (Integer) session.getAttribute("ownerId");

        if (accountId == null || ownerId == null) {
            throw new IllegalArgumentException("Account_id or owner_id is not present in the session");
        }

        String sql = "INSERT INTO OWNER_CONTRACT " +
                "(Account_id, registration_date, status, start_date, end_date, notes, owner_id) " +
                "VALUES (?, GETDATE(), 'waiting', ?, ?, ?, ?)";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, accountId);

            if (contract.getStartDate() != null) {
                pstmt.setDate(2, contract.getStartDate());
            } else {
                pstmt.setNull(2, java.sql.Types.DATE);
            }

            if (contract.getEndDate() != null) {
                pstmt.setDate(3, contract.getEndDate());
            } else {
                pstmt.setNull(3, java.sql.Types.DATE);
            }

            pstmt.setString(4, contract.getNotes());

            pstmt.setInt(5, ownerId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getOwnerIDbyAccountID(int accountId) throws SQLException {
        String sql = "SELECT owner_id FROM OWNER WHERE Account_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("Owner_id");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
