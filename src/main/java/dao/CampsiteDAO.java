package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Campsite;
import model.CampsiteOrder;

public class CampsiteDAO extends DBContext {

    public List<Campsite> getAllRiverCampsite() throws Exception {
        List<Campsite> campsites = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement("SELECT C.*, A.first_name, A.last_name FROM CAMPSITE C JOIN OWNER O ON C.Campsite_owner = O.owner_id JOIN ACCOUNT A ON O.Account_id = A.Account_id WHERE C.Name LIKE N'%Sông%' AND C.Status = 1;\n"); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Campsite campsite = new Campsite();
                campsite.setCampId(rs.getInt("Campsite_id"));
                campsite.setCampPrice(rs.getInt("Price"));
                campsite.setCampOwner(rs.getInt("Campsite_owner"));
                campsite.setCampAddress(rs.getString("Address"));
                campsite.setCampName(rs.getString("Name"));
                campsite.setCampDescription(rs.getString("Description"));
                campsite.setCampImage(rs.getString("Image"));
                campsite.setLimite(rs.getInt("Quantity"));

                // Lấy thêm tên chủ sở hữu
                String ownerName = rs.getString("first_name") + " " + rs.getString("last_name");
                campsite.setCampsiteOwnerName(ownerName);

                campsites.add(campsite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error fetching campsites", e);
        }
        return campsites;
    }

    public List<Campsite> getAllMountainCampsite() throws Exception {
        List<Campsite> campsites = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement("SELECT C.*, A.first_name, A.last_name FROM CAMPSITE C JOIN OWNER O ON C.Campsite_owner = O.owner_id JOIN ACCOUNT A ON O.Account_id = A.Account_id WHERE C.Name LIKE N'%Núi%' AND C.Status = 1;\n"); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Campsite campsite = new Campsite();
                campsite.setCampId(rs.getInt("Campsite_id"));
                campsite.setCampPrice(rs.getInt("Price"));
                campsite.setCampOwner(rs.getInt("Campsite_owner"));
                campsite.setCampAddress(rs.getString("Address"));
                campsite.setCampName(rs.getString("Name"));
                campsite.setCampDescription(rs.getString("Description"));
                campsite.setCampImage(rs.getString("Image"));
                campsite.setLimite(rs.getInt("Quantity"));

                // Lấy thêm tên chủ sở hữu
                String ownerName = rs.getString("first_name") + " " + rs.getString("last_name");
                campsite.setCampsiteOwnerName(ownerName);

                campsites.add(campsite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error fetching campsites", e);
        }
        return campsites;
    }


    public List<Campsite> getAllBeachCampsite() throws Exception {
        List<Campsite> campsites = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement("SELECT C.*, A.first_name, A.last_name FROM CAMPSITE C JOIN OWNER O ON C.Campsite_owner = O.owner_id JOIN ACCOUNT A ON O.Account_id = A.Account_id WHERE C.Name LIKE N'%Biển%' AND C.Status = 1;\n"); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Campsite campsite = new Campsite();
                campsite.setCampId(rs.getInt("Campsite_id"));
                campsite.setCampPrice(rs.getInt("Price"));
                campsite.setCampOwner(rs.getInt("Campsite_owner"));
                campsite.setCampAddress(rs.getString("Address"));
                campsite.setCampName(rs.getString("Name"));
                campsite.setCampDescription(rs.getString("Description"));
                campsite.setCampImage(rs.getString("Image"));
                campsite.setLimite(rs.getInt("Quantity"));

                // Lấy thêm tên chủ sở hữu
                String ownerName = rs.getString("first_name") + " " + rs.getString("last_name");
                campsite.setCampsiteOwnerName(ownerName);

                campsites.add(campsite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error fetching campsites", e);
        }
        return campsites;
    }

    public boolean isDateAvailable(int campId, java.sql.Date startDate, java.sql.Date endDate) throws Exception {
        String query = "SELECT * FROM ORDERS WHERE Campsite_id = ? AND ((StartDate <= ? AND EndDate >= ?) OR (StartDate <= ? AND EndDate >= ?))";
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, campId);
            pst.setDate(2, endDate);
            pst.setDate(3, startDate);
            pst.setDate(4, startDate);
            pst.setDate(5, endDate);
            ResultSet rs = pst.executeQuery();
            return !rs.next();
        }
    }

    public void bookCampsite(CampsiteOrder order) throws Exception {
        String query = "INSERT INTO ORDERS (Campsite_id, Booker, StartDate, EndDate, ApproveStatus, PaymentStatus, TimeStamp, Quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, order.getCampsiteId());
            pst.setInt(2, order.getBooker());
            pst.setTimestamp(3, order.getStartDate());
            pst.setTimestamp(4, order.getEndDate());
            pst.setBoolean(5, order.isApproveStatus());
            pst.setBoolean(6, order.isPaymentStatus());
            pst.setTimestamp(7, order.getTimeStamp());
            pst.setInt(8, order.getQuantity());
            pst.executeUpdate();
        }
    }

    public Campsite getSingleCampsite(int id) {
        Campsite row = null;
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement("SELECT * FROM CAMPSITE C WHERE C.Campsite_id = ?")) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    row = new Campsite();
                    row.setCampId(rs.getInt("Campsite_id"));
                    row.setCampName(rs.getString("Name"));
                    row.setCampOwner(rs.getInt("Campsite_owner"));
                    row.setCampAddress(rs.getString("Address"));
                    row.setCampDescription(rs.getString("Description"));
                    row.setCampPrice(rs.getInt("Price"));
                    row.setCampImage(rs.getString("Image"));
                    row.setLimite(rs.getInt("Quantity"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return row;
    }

    public int getTotalItem() {
        try {
            String sql = "SELECT COUNT(*) AS total_items \n"
                    + "FROM [CAMPSITE] \n";
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

    public List<Campsite> getAllCampside(int page, int size) {
        List<Campsite> campsites = new ArrayList<>();
        String query = "SELECT G.*, P.Price FROM [CAMPSITE] G"
                + " INNER JOIN PRICE P ON G.Price_id = P.Price_id ORDER BY G.[Campsite_id] desc "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            int offset = (page - 1) * size;
            pst.setInt(1, offset);
            pst.setInt(2, size);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Campsite campsite = new Campsite();
                    campsite.setCampId(rs.getInt("Campsite_id"));
                    campsite.setCampPrice(rs.getInt("Price"));
                    campsite.setCampAddress(rs.getString("Address"));
                    campsite.setCampName(rs.getString("Name"));
                    campsite.setCampDescription(rs.getString("Description"));
                    campsite.setCampImage(rs.getString("Image"));
                    campsite.setCampStatus(rs.getBoolean("Status"));
                    campsite.setLimite(rs.getInt("Limite"));
                    campsites.add(campsite);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return campsites;
    }

    public List<Campsite> getAllCampside() {
        List<Campsite> campsites = new ArrayList<>();
        String query = "SELECT G.*, P.Price FROM [CAMPSITE] G"
                + " INNER JOIN PRICE P ON G.Price_id = P.Price_id ORDER BY G.[Campsite_id] desc ";

        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Campsite campsite = new Campsite();
                    campsite.setCampId(rs.getInt("Campsite_id"));
                    campsite.setCampPrice(rs.getInt("Price"));
                    campsite.setCampAddress(rs.getString("Address"));
                    campsite.setCampName(rs.getString("Name"));
                    campsite.setCampDescription(rs.getString("Description"));
                    campsite.setCampImage(rs.getString("Image"));
                    campsite.setCampStatus(rs.getBoolean("Status"));
                    campsite.setLimite(rs.getInt("Limite"));
                    campsites.add(campsite);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return campsites;
    }

    public void insertCampsite(Campsite c, int ownerId) {
        String query = "INSERT INTO CAMPSITE (Campsite_owner, Price, Address, Name, Description, Status, Image, Quantity) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            // Thiết lập các giá trị
            ps.setInt(1, ownerId);  // Đặt ownerId lấy từ session
            ps.setInt(2, c.getCampPrice());
            ps.setString(3, c.getCampAddress());
            ps.setString(4, c.getCampName());
            ps.setString(5, c.getCampDescription());
            ps.setBoolean(6, c.isCampStatus());
            ps.setString(7, c.getCampImage());
            ps.setInt(8, c.getLimite());

            // Thực thi câu lệnh chèn
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(CampsiteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public Campsite getCampsiteByID(String id) {
        String query = "select * from CAMPSITE\n"
                + "	where Campsite_id = ?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Campsite campsite = new Campsite();
                campsite.setCampId(rs.getInt("Campsite_id"));
                campsite.setCampPrice(rs.getInt("Price_id"));
                campsite.setCampAddress(rs.getString("Address"));
                campsite.setCampName(rs.getString("Name"));
                campsite.setCampDescription(rs.getString("Description"));
                campsite.setCampImage(rs.getString("Image"));
                campsite.setCampStatus(rs.getBoolean("Status"));
                campsite.setLimite(rs.getInt("Limite"));
                return campsite;
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void updateCampsite(Campsite c) {
        String query = "UPDATE CAMPSITE "
                + "SET [Price] = ?, "
                + "    [Address] = ?, "
                + "    [Name] = ?, "
                + "    [Description] = ?, "
                + "    [Status] = ?, "
                + "    [Image] = ?, "
                + "    [Quantity] = ? "
                + "WHERE [Campsite_id] = ?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, c.getCampPrice());
            ps.setString(2, c.getCampAddress());
            ps.setString(3, c.getCampName());
            ps.setString(4, c.getCampDescription());
            ps.setInt(5, c.isCampStatus() == true ? 1 : 0);
            ps.setString(6, c.getCampImage());
            ps.setInt(7, c.getLimite());
            ps.setInt(8, c.getCampId());
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void updateQuantityCampsite(CampsiteOrder c) {
        String query = "update CAMPSITE\n"
                + " set "
                + "	[Quantity] = [Quantity] - ?\n"
                + "	where [Campsite_id] = ?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, c.getQuantity());
            ps.setInt(2, c.getCampsiteId());
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteCampsite(String id) {
        String query = "update CAMPSITE\n"
                + "set [Status] = 0\n"
                + "	where [Campsite_id] = ?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Campsite> getCampsitesByOwner(int ownerId, int page, int size) {
        List<Campsite> campsites = new ArrayList<>();
        String query = "SELECT [Campsite_id], [Campsite_owner], [Price], [Address], [Name], [Description], [Status], [Image], [Quantity] "
                + "FROM [CAMPSITE] "
                + "WHERE [Campsite_owner] = ? "
                + "ORDER BY [Campsite_id] DESC "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            // Tính toán offset
            int offset = (page - 1) * size;
            pst.setInt(1, ownerId); // Gán giá trị ownerId cho tham số đầu tiên
            pst.setInt(2, offset);  // Gán giá trị offset cho tham số thứ hai
            pst.setInt(3, size);    // Gán giá trị size cho tham số thứ ba

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Campsite campsite = new Campsite();
                    campsite.setCampId(rs.getInt("Campsite_id"));
                    campsite.setCampOwner(rs.getInt("Campsite_owner"));
                    campsite.setCampPrice(rs.getInt("Price"));
                    campsite.setCampAddress(rs.getString("Address"));
                    campsite.setCampName(rs.getString("Name"));
                    campsite.setCampDescription(rs.getString("Description"));
                    campsite.setCampStatus(rs.getBoolean("Status"));
                    campsite.setCampImage(rs.getString("Image"));
                    campsite.setLimite(rs.getInt("Quantity"));
                    campsites.add(campsite);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return campsites;
    }
    public int getTotalCampsitesByOwner(int ownerId) {
        int totalCampsites = 0;
        String query = "SELECT COUNT(*) FROM [CAMPSITE] WHERE Campsite_owner = ?";

        // Kết nối tới cơ sở dữ liệu và thực hiện truy vấn
        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
            // Gán giá trị ownerId vào câu lệnh SQL
            pst.setInt(1, ownerId);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    totalCampsites = rs.getInt(1); // Lấy kết quả trả về từ COUNT(*)
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return totalCampsites;
    }

    public Campsite getCampsiteById(int campsiteId) {
        String query = "SELECT * FROM CAMPSITE WHERE Campsite_id = ?";
        Campsite campsite = null;
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, campsiteId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    campsite = new Campsite();
                    campsite.setCampId(rs.getInt("Campsite_id"));
                    campsite.setCampPrice(rs.getInt("Price"));
                    campsite.setCampAddress(rs.getString("Address"));
                    campsite.setCampName(rs.getString("Name"));
                    campsite.setCampDescription(rs.getString("Description"));
                    campsite.setCampImage(rs.getString("Image"));
                    campsite.setCampStatus(rs.getBoolean("Status"));
                    campsite.setLimite(rs.getInt("Quantity"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return campsite;
    }
    public void updateCampsite(int campsiteId, String name, int price, String address, String description, boolean status, String image, int limite) throws Exception {
        String query = "UPDATE CAMPSITE\n"
                + "SET [Price] = ?,\n"
                + "    [Address] = ?,\n"
                + "    [Name] = ?,\n"
                + "    [Description] = ?,\n"
                + "    [Status] = ?,\n"
                + "    [Image] = ?,\n"
                + "    [Limite] = ?\n"
                + "WHERE [Campsite_id] = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setString(3, address);
            ps.setString(4, description);
            ps.setInt(5, status ? 1 : 0);  // Trạng thái: 1 cho active, 0 cho inactive
            ps.setString(6, image);
            ps.setInt(7, limite);
            ps.setInt(8, campsiteId);  // ID của trại cắm trại cần cập nhật

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
