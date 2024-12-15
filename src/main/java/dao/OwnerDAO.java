package dao;

import dto.OrderRevenueDTO;
import dto.OwnerDTO;
import jakarta.servlet.http.HttpSession;
import model.Campsite;
import model.Owner;
import model.OwnerContract;
import model.User;

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

    public Connection getConnection() throws Exception {
        return db.getConnection();
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

    public boolean updateOwnerStatus(HttpSession session) throws SQLException {
        Integer accountId = (Integer) session.getAttribute("accountId");

        if (accountId == null) {
            throw new IllegalArgumentException("Account_id is not present in the session");
        }

        String sql1 = "UPDATE ACCOUNT SET isOwner = 0 WHERE Account_id = ?";
        String sql2 = "UPDATE OWNER SET status = 'disapproved' WHERE Account_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt1 = conn.prepareStatement(sql1);
             PreparedStatement pstmt2 = conn.prepareStatement(sql2)) {
            pstmt1.setInt(1, accountId);
            pstmt2.setInt(1, accountId);

            int rowsAffected1 = pstmt1.executeUpdate();
            int rowsAffected2 = pstmt2.executeUpdate();

            return rowsAffected1 > 0 && rowsAffected2 > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public List<OwnerDTO> getOwnersWithAccountInfoAndRevenue() throws SQLException {
        String sql = "SELECT "
                + "o.owner_id, "
                + "o.Account_id, "
                + "o.owner_since, "
                + "o.occupation, "
                + "o.address AS owner_address, "
                + "o.date_of_birth AS owner_dob, "
                + "o.gender AS owner_gender, "
                + "o.region AS owner_region, "
                + "o.identification, "
                + "o.tax_code, "
                + "o.attached_files, "
                + "o.status AS owner_status, "
                + "o.start_date, "
                + "o.end_date, "
                + "CAST(o.notes AS NVARCHAR)  as notes, "
                + "a.first_name, "
                + "a.last_name, "
                + "a.Gmail, "
                + "a.phone_number, "
                + "a.isAdmin, "
                + "a.isOwner, "
                + "COALESCE(SUM(ord.totalAmount), 0) AS total_revenue "  // Total revenue from orders associated with the campsite
                + "FROM OWNER o "
                + "INNER JOIN ACCOUNT a ON o.Account_id = a.Account_id "
                + "LEFT JOIN CAMPSITE cs ON cs.Campsite_owner = o.owner_id "
                + "LEFT JOIN [ORDERS]" +
                " ord ON ord.campsite_id = cs.campsite_id "  // Join with ORDER table to calculate total revenue
                + "GROUP BY "
                + "o.owner_id, "
                + "o.Account_id, "
                + "o.owner_since, "
                + "o.occupation, "
                + "o.address, "
                + "o.date_of_birth, "
                + "o.gender, "
                + "o.region, "
                + "o.identification, "
                + "o.tax_code, "
                + "o.attached_files, "
                + "o.status, "
                + "o.start_date, "
                + "o.end_date, "
                + "CAST(o.notes AS NVARCHAR), "
                + "a.first_name, "
                + "a.last_name, "
                + "a.Gmail, "
                + "a.phone_number, "
                + "a.isAdmin, "
                + "a.isOwner";

        List<OwnerDTO> owners = new ArrayList<>();
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                OwnerDTO ownerDTO = new OwnerDTO();
                Owner owner = new Owner();
                owner.setOwnerId(rs.getInt("owner_id"));
                owner.setAccountId(rs.getInt("Account_id"));
                owner.setOwnerSince(rs.getDate("owner_since"));
                owner.setOccupation(rs.getString("occupation"));
                owner.setAddress(rs.getString("owner_address"));
                owner.setDob(rs.getDate("owner_dob"));
                owner.setGender(rs.getString("owner_gender"));
                owner.setRegion(rs.getString("owner_region"));
                owner.setIdentification(rs.getInt("identification"));
                owner.setTaxCode(rs.getInt("tax_code"));
                owner.setImage(rs.getString("attached_files"));
                owner.setStatus(rs.getString("owner_status"));
                owner.setStartDate(rs.getDate("start_date"));
                owner.setEndDate(rs.getDate("end_date"));
                owner.setNotes(rs.getString("notes"));

                User account = new User();
                account.setFirstName(rs.getString("first_name"));
                account.setLastName(rs.getString("last_name"));
                account.setEmail(rs.getString("Gmail"));
                account.setPhoneNumber(rs.getString("phone_number"));
                account.setAdmin(rs.getBoolean("isAdmin"));
                account.setOwner(rs.getBoolean("isOwner"));

                ownerDTO.setUser(account);
                ownerDTO.setOwner(owner);
                ownerDTO.setTotalRevenue(rs.getBigDecimal("total_revenue"));  // Get the total revenue
                owners.add(ownerDTO);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return owners;
    }

    public List<OrderRevenueDTO> getOrderRevenueByOwnerId(int ownerId, int queryYear) {
        List<OrderRevenueDTO> orderRevenueDTOs = new ArrayList<>();
        String sql =
                "WITH YearMonths AS ( " +
                        "    SELECT 1 AS MonthNum " +
                        "    UNION ALL " +
                        "    SELECT MonthNum + 1 " +
                        "    FROM YearMonths " +
                        "    WHERE MonthNum < 12 " +
                        ") " +
                        "SELECT " +
                        "    ym.MonthNum AS [Month], " +
                        "    ISNULL(COUNT(o.[Orders_id]), 0) AS [TotalOrders], " +
                        "    ISNULL(SUM(o.[TotalAmount]), 0) AS [TotalRevenue] " +
                        "FROM " +
                        "    YearMonths ym " +
                        "LEFT JOIN " +
                        "    [dbo].[ORDERS] o " +
                        "ON " +
                        "    ym.MonthNum = MONTH(o.enddate) AND YEAR(o.enddate) = ? " +  // Replace with the year parameter
                        "    AND o.Campsite_id IN (SELECT Campsite_id FROM [dbo].[CAMPSITE] WHERE Campsite_owner = ?) " +  // Replace with the ownerId parameter
                        "GROUP BY " +
                        "    ym.MonthNum " +
                        "ORDER BY " +
                        "    ym.MonthNum ASC;";
        try (Connection conn = db.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, queryYear); // Replace ownerId with the actual value
            pstmt.setInt(2, ownerId); // Replace ownerId with the actual value
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                OrderRevenueDTO orderRevenueDTO = new OrderRevenueDTO();
                orderRevenueDTO.setMonth(rs.getInt("Month"));
                orderRevenueDTO.setNumberOfOrders(rs.getInt("TotalOrders"));
                orderRevenueDTO.setTotalAmount(rs.getInt("TotalRevenue"));

                orderRevenueDTOs.add(orderRevenueDTO);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return orderRevenueDTOs;
    }

    public List<OrderRevenueDTO> getOrderRevenueInFiveYearsByOwnerId(int ownerId) {
        List<OrderRevenueDTO> orderRevenueDTOs = new ArrayList<>();
        String sql =
                "WITH LastFiveYears AS ( " +
                        "    SELECT YEAR(GETDATE()) AS [Year] " +
                        "    UNION ALL " +
                        "    SELECT YEAR(GETDATE()) - 1 " +
                        "    UNION ALL " +
                        "    SELECT YEAR(GETDATE()) - 2 " +
                        "    UNION ALL " +
                        "    SELECT YEAR(GETDATE()) - 3 " +
                        "    UNION ALL " +
                        "    SELECT YEAR(GETDATE()) - 4 " +
                        ") " +
                        "SELECT " +
                        "    lf.[Year], " +
                        "    ISNULL(COUNT(o.[Orders_id]), 0) AS [TotalOrders], " +
                        "    ISNULL(SUM(o.[TotalAmount]), 0) AS [TotalRevenue] " +
                        "FROM " +
                        "    LastFiveYears lf " +
                        "LEFT JOIN " +
                        "    [dbo].[ORDERS] o " +
                        "ON " +
                        "    lf.[Year] = YEAR(o.enddate) " +
                        "    AND o.Campsite_id IN (SELECT Campsite_id FROM [dbo].[CAMPSITE] WHERE Campsite_owner = ?) " +
                        "GROUP BY " +
                        "    lf.[Year] " +
                        "ORDER BY " +
                        "    lf.[Year] ASC;";

        try (Connection conn = db.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ownerId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                OrderRevenueDTO orderRevenueDTO = new OrderRevenueDTO();
                orderRevenueDTO.setYear(rs.getInt("Year"));
                orderRevenueDTO.setNumberOfOrders(rs.getInt("TotalOrders"));
                orderRevenueDTO.setTotalAmount(rs.getInt("TotalRevenue"));

                orderRevenueDTOs.add(orderRevenueDTO);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return orderRevenueDTOs;
    }

    public List<Campsite> getCampsitesWithStatusFalse() throws Exception {
        List<Campsite> campsites = new ArrayList<>();
        String query = "SELECT * FROM CAMPSITE WHERE Status = 0 AND (adminApproved = 0 OR adminApproved IS NULL)";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Campsite campsite = new Campsite();
                campsite.setCampId(rs.getInt("Campsite_id"));
                campsite.setCampPrice(rs.getInt("Price"));
                campsite.setCampOwner(rs.getInt("Campsite_owner"));
                campsite.setCampAddress(rs.getString("Address"));
                campsite.setCampName(rs.getString("Name"));
                campsite.setCampDescription(rs.getString("Description"));
                campsite.setCampImage(rs.getString("Image"));
                campsite.setCampStatus(rs.getBoolean("Status"));
                campsite.setLimite(rs.getInt("Quantity"));
                campsites.add(campsite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campsites;
    }

    public boolean approveCampsite(int campsiteId) throws SQLException {
        String query = "UPDATE CAMPSITE SET Status = 1, adminApproved = 1 WHERE Campsite_id = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, campsiteId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean disapproveCampsite(int campsiteId) throws SQLException {
        String query = "UPDATE CAMPSITE SET Status = 0, adminApproved = 1 WHERE Campsite_id = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, campsiteId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
