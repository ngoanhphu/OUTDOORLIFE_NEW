package dao;

import jakarta.servlet.http.HttpSession;
import model.Owner;
import model.OwnerContract;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class OwnerDAO {
    private DBContext db;

    public OwnerDAO(DBContext db) {
        this.db = db;
    }



    public boolean insertOwner(OwnerContract contract, HttpSession session) throws SQLException {

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

    public boolean addOwner(Owner owner, HttpSession session) throws SQLException {

        Integer accountId = (Integer) session.getAttribute("accountId");

        if (accountId == null) {
            throw new IllegalArgumentException("Account_id or owner_id is not present in the session");
        }

        String sql = "INSERT INTO OWNER (Account_id, owner_since, occupation, address, date_of_birth, gender, region, identification, tax_code, attached_files, status, start_date, end_date, notes) " +
                "VALUES (?, GETDATE(), ?, ?, ?, ?, ?, ?, ?, ?, 'pending', ?, ?, ?)";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, accountId);
            pstmt.setString(2, owner.getOccupation());
            pstmt.setString(3, owner.getAddress());
            pstmt.setDate(4, owner.getDob());
            pstmt.setString(5, owner.getGender());
            pstmt.setString(6, owner.getRegion());
            pstmt.setInt(7, owner.getIdentification());
            pstmt.setInt(8, owner.getTaxCode());
            pstmt.setString(9, owner.getImage());
            pstmt.setDate(10, owner.getStartDate());
            pstmt.setDate(11, owner.getEndDate());
            pstmt.setString(12, owner.getNotes());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isOwnerExist(int accountId) throws SQLException {
        String sql = "SELECT 1 FROM OWNER WHERE Account_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Owner> getPendingOwners() throws SQLException {
        String sql = "SELECT * FROM OWNER WHERE status='pending'";
        List<Owner> pendingOwners = new ArrayList<>();

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Owner owner = new Owner();
                owner.setAccountId(rs.getInt("Account_id"));
                owner.setOccupation(rs.getString("occupation"));
                owner.setAddress(rs.getString("address"));
                owner.setDob(rs.getDate("date_of_birth"));
                owner.setGender(rs.getString("gender"));
                owner.setRegion(rs.getString("region"));
                owner.setIdentification(rs.getInt("identification"));
                owner.setTaxCode(rs.getInt("tax_code"));
                owner.setImage(rs.getString("attached_files"));
                owner.setStartDate(rs.getDate("start_date"));
                owner.setEndDate(rs.getDate("end_date"));
                owner.setNotes(rs.getString("notes"));
                owner.setStatus(rs.getString("status"));
                pendingOwners.add(owner);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pendingOwners;
    }

    public boolean approveOwner(int ownerId) throws SQLException {
        String sql = "UPDATE OWNER SET status = 'approved', owner_since = GETDATE() WHERE Account_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ownerId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean disapproveOwner(int ownerId) throws SQLException {
        String sql1 = "UPDATE a SET a.isOwner = 0 FROM ACCOUNT a INNER JOIN OWNER o ON a.Account_id = o.Account_id WHERE o.Account_id = ?";
        String sql2 = "UPDATE OWNER SET status = 'disapproved' WHERE Account_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt1 = conn.prepareStatement(sql1);
             PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
            pstmt1.setInt(1, ownerId);
            pstmt2.setInt(1, ownerId);
            int rowsAffected1 = pstmt1.executeUpdate();
            int rowsAffected2 = pstmt2.executeUpdate();
            return rowsAffected1 > 0 && rowsAffected2 > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Owner getOwnerByAccountId(int accountId) throws SQLException {
        String sql = "SELECT * FROM OWNER WHERE Account_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Owner owner = new Owner();
                    owner.setOwnerId(rs.getInt("owner_id"));
                    owner.setAccountId(rs.getInt("Account_id"));
                    owner.setOwnerSince(rs.getDate("owner_since"));
                    owner.setOccupation(rs.getString("occupation"));
                    owner.setAddress(rs.getString("address"));
                    owner.setDob(rs.getDate("date_of_birth"));
                    owner.setGender(rs.getString("gender"));
                    owner.setRegion(rs.getString("region"));
                    owner.setIdentification(rs.getInt("identification"));
                    owner.setTaxCode(rs.getInt("tax_code"));
                    owner.setImage(rs.getString("attached_files"));
                    owner.setStatus(rs.getString("status"));
                    owner.setStartDate(rs.getDate("start_date"));
                    owner.setEndDate(rs.getDate("end_date"));
                    owner.setNotes(rs.getString("notes"));
                    return owner;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String getOwnerStatusByAccountId(HttpSession session) throws SQLException {
        Integer accountId = (Integer) session.getAttribute("accountId");

        if (accountId == null) {
            throw new IllegalArgumentException("Account_id is not present in the session");
        }

        String sql = "SELECT status FROM OWNER WHERE Account_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("status");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean updateOwnerEndDate(int accountId, Date newEndDate) throws SQLException {
        String sql = "UPDATE OWNER SET end_date = ? WHERE Account_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, newEndDate);
            pstmt.setInt(2, accountId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isOwnerEndDateValid(HttpSession session) throws SQLException {
        Integer accountId = (Integer) session.getAttribute("accountId");

        if (accountId == null) {
            throw new IllegalArgumentException("Account_id is not present in the session");
        }

        String sql = "SELECT end_date FROM OWNER WHERE Account_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, accountId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Date endDate = rs.getDate("end_date");
                    if (endDate != null && endDate.before(new Date(System.currentTimeMillis()))) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
