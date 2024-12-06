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

public class TentDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

  public TentDAO(Connection con) {
    this.con = con;
  }

    public TentDAO() {
    }
  
  public List<Gear> searchByName(String txtSearch) throws Exception {
        List<Gear> gears = new ArrayList<>();
        String query = "SELECT g.Gear_id, g.Name, g.Description, g.Image, p.Price "
                + "FROM GEAR g "
                + "JOIN PRICE p ON g.Price_id = p.Price_id "
                + "WHERE g.Name LIKE ?";

        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + txtSearch + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Gear gear = new Gear();
                gear.setGearId(rs.getInt("Gear_id"));
                gear.setGearPrice(rs.getInt("Price"));
                gear.setGearName(rs.getString("Name"));
                gear.setGearDecription(rs.getString("Description"));
                gear.setGearImage(rs.getString("Image"));
                gears.add(gear);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gears;
    }
  
    public List<Gear> getAllTent(int page, int size) {
        List<Gear> gears = new ArrayList<>();
        String query = "SELECT * FROM GEAR WHERE Name LIKE N'%Lều%' "
                + "ORDER BY Gear_id "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement pst = this.con.prepareStatement(query)) {
            int offset = (page - 1) * size;
            pst.setInt(1, offset);
            pst.setInt(2, size);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Gear gear = new Gear();
                    gear.setGearId(rs.getInt("Gear_id"));
                    gear.setGearPrice(rs.getInt("Price"));
                    gear.setGearName(rs.getString("Name"));
                    gear.setGearDecription(rs.getString("Description"));
                    gear.setGearImage(rs.getString("Image"));
                    gears.add(gear);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gears;
    }
    
    public int getTotalItem() {
        try {
            String sql = "SELECT COUNT(*) AS total_items \n"
                    + "FROM GEAR \n"
                    + "WHERE Name LIKE N'Lều%'";
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

    
    public void insertGear(String price, String name, String description, String image) throws Exception {
        String query = "INSERT INTO GEAR \n"
                + "              VALUES (?,?,?,?)";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(price));
            ps.setString(2, name);
            ps.setString(3, description);
            ps.setString(4, image);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Gear getGearByID(String id) throws Exception {
        String query = "select * from GEAR\n"
                + "	where Gear_id = ?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Gear(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    
    public void UpdateGear(String id, String name, String price, String decription, String image) throws Exception {
        String query = "update GEAR\n"
                + "set [name] = ?,\n"
                + "	[Price_id] = ?,\n"
                + "	[Description] = ?,\n"
                + "	[Image] = ?\n"
                + "	where [Gear_id] = ?";
        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, Integer.parseInt(price));
            ps.setString(3, decription);
            ps.setString(4, image);
            ps.setInt(5, Integer.parseInt(id));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertGear(String name, String price, String description, String image, int ownerId) throws SQLException {
        String query = "INSERT INTO GEAR (Campsite_owner, Price, Name, Description, Image) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, ownerId);  // Đảm bảo rằng ownerId là người đăng nhập
            ps.setString(2, price);
            ps.setString(3, name);
            ps.setString(4, description);
            ps.setString(5, image);
            ps.executeUpdate();
        }
    }
    public void updateTent(String id, String name, int price, String description, String image) throws Exception {
        String query = "UPDATE GEAR SET Name = ?, Price = ?, Description = ?, Image = ? WHERE Gear_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setString(3, description);
            ps.setString(4, image);
            ps.setString(5, id);
            ps.executeUpdate();
        }
    }
    public Gear getTentByID(String id) throws Exception {
        String query = "SELECT * FROM GEAR WHERE Gear_id = ?";
        try {
            con = new DBContext().getConnection(); // Kết nối cơ sở dữ liệu
            ps = con.prepareStatement(query); // Chuẩn bị câu lệnh SQL
            ps.setString(1, id); // Gán tham số cho câu lệnh SQL
            rs = ps.executeQuery(); // Thực thi câu lệnh

            if (rs.next()) {
                // Tạo và trả về đối tượng Gear với đầy đủ dữ liệu từ bảng GEAR
                return new Gear(
                        rs.getInt("Gear_id"),          // Cột ID của dụng cụ
                        rs.getInt("Campsite_owner"),   // Cột ID của chủ dụng cụ
                        rs.getInt("Price"),             // Cột giá dụng cụ
                        rs.getString("Name"),          // Cột tên dụng cụ
                        rs.getString("Description"),   // Cột mô tả
                        rs.getString("Image")      // Cột đường dẫn hình ảnh

                );
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ nếu có lỗi SQL
            throw new Exception("Error retrieving Gear by ID: " + e.getMessage());
        } finally {
            // Đóng kết nối và các tài nguyên để tránh rò rỉ
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (con != null) try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return null; // Trả về null nếu không tìm thấy Gear
    }
    public void deleteGear(String id) throws Exception {
        String query = "DELETE FROM GEAR WHERE Gear_id = ?";

        try {
            con = new DBContext().getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getTotalItemByOwner(int campsiteOwnerId) {
        int totalItems = 0;  // Khởi tạo biến lưu số lượng món đồ
        String sql = "SELECT COUNT(*) AS total_items \n" +
                "FROM GEAR \n" +
                "WHERE Campsite_owner = ? \n" +
                "  AND Name LIKE N'%Lều%';\n";

        try (PreparedStatement ps = this.con.prepareStatement(sql)) {
            ps.setInt(1, campsiteOwnerId);  // Sử dụng ID của chủ cắm trại làm điều kiện

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    totalItems = rs.getInt("total_items");  // Lấy kết quả từ cột total_items
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // In ra lỗi nếu có
        }

        return totalItems;  // Trả về tổng số món đồ của chủ cắm trại
    }
    public List<Gear> getTentByOwner(int campsiteOwnerId, int page, int size) {
        List<Gear> gears = new ArrayList<>();
        String query = "SELECT Gear_id, Name, Description, Image, Price, Campsite_owner "
                + "FROM GEAR "
                + "WHERE Campsite_owner = ? AND Name LIKE N'%Lều%' "
                + "ORDER BY Gear_id "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY"; // Phân trang

        try (PreparedStatement pst = this.con.prepareStatement(query)) {
            int offset = (page - 1) * size; // Tính toán offset
            pst.setInt(1, campsiteOwnerId); // Set Campsite_owner
            pst.setInt(2, offset); // Set offset
            pst.setInt(3, size); // Set số bản ghi trên một trang

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Gear gear = new Gear();
                    gear.setGearId(rs.getInt("Gear_id"));
                    gear.setGearName(rs.getString("Name"));
                    gear.setGearDecription(rs.getString("Description"));
                    gear.setGearImage(rs.getString("Image"));
                    gear.setGearPrice(rs.getInt("Price"));
                    gear.setGearOwner(rs.getInt("Campsite_owner"));
                    gears.add(gear); // Thêm Gear vào danh sách
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gears;
    }
    
}
