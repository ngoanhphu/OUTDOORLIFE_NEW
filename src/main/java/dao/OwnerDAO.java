package dao;

import model.OwnerContract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OwnerDAO {
    private DBContext db;

    public OwnerDAO(DBContext db) {
        this.db = db;
    }

    public boolean insertOwnerContract(OwnerContract contract) throws SQLException {
        String sql = "INSERT INTO OWNER_CONTRACT " +
                "(Account_id, registration_date, status, start_date, end_date, notes, owner_id) " +
                "VALUES (?, GETDATE(), 'waiting', ?, ?, ?, ?)";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, contract.getAccountId());

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

            pstmt.setInt(5, contract.getOwnerId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
