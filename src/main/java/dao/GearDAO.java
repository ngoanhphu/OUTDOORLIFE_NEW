package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import model.Cart;
import model.Gear;
import model.User;

public class GearDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private HttpServletRequest request;
    public GearDAO(HttpServletRequest request) {
        this.request = request;
    }
    public GearDAO(Connection con) { 
        this.con = con;
    }

    public List<Gear> getGearsByCampsiteOwner(int campsiteOwnerId) throws SQLException {
        List<Gear> gears = new ArrayList<>();
        String sql = "SELECT * FROM GEAR WHERE Campsite_owner = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, campsiteOwnerId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Gear gear = new Gear();
            gear.setGearId(rs.getInt("Gear_id"));
            gear.setCampsiteOwner(rs.getInt("Campsite_owner"));
            gear.setPrice(rs.getInt("Price"));
            gear.setName(rs.getString("Name"));
            gear.setDescription(rs.getString("Description"));
            gear.setImage(rs.getString("Image"));
            gears.add(gear);
        }
        return gears;
    }

//    public List<Cart> getCartGears(ArrayList<Cart> cartList) {
//        List<Cart> gears = new ArrayList<>();
//        try {
//            if (cartList.size() > 0) {
//                for (Cart item : cartList) {
//                    PreparedStatement pst = this.con.prepareStatement("SELECT G.*, P.Price FROM GEAR G INNER JOIN PRICE P ON G.Price_id = P.Price_id WHERE G.Gear_id = ?");
//                    pst.setInt(1, item.getGearId());
//                    ResultSet rs = pst.executeQuery();
//                    while (rs.next()) {
//                        Cart c = new Cart();
//                        c.setGearId(rs.getInt("Gear_id"));
//                        c.setGearPrice(rs.getInt("Price"));
//                        c.setGearName(rs.getString("Name"));
//                        c.setGearDecription(rs.getString("Description"));
//                        c.setGearImage(rs.getString("Image"));
//                        c.setQuantity(item.getQuantity());
//                        gears.add(c);
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return gears;
//    }
//
//    public int getTotalCartPrice(ArrayList<Cart> cartList) {
//        int sum = 0;
//        try {
//            if (cartList.size() > 0) {
//                for (Cart item : cartList) {
//                    PreparedStatement pst = this.con.prepareStatement("SELECT P.Price FROM GEAR G INNER JOIN PRICE P ON G.Price_id = P.Price_id WHERE G.Gear_id = ?");
//                    pst.setInt(1, item.getGearId());
//                    ResultSet rs = pst.executeQuery();
//                    while (rs.next()) {
//                        sum += rs.getInt("Price") * item.getQuantity();
//                    }
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//        return sum;
//    }
//
//    public Gear getSingleGear(int id) {
//        Gear row = null;
//        try (PreparedStatement pst = this.con.prepareStatement("SELECT * FROM GEAR G INNER JOIN PRICE P ON G.Price_id = P.Price_id WHERE G.Gear_id = ?")) {
//            pst.setInt(1, id);
//            try (ResultSet rs = pst.executeQuery()) {
//                if (rs.next()) {
//                    row = new Gear();
//                    row.setGearId(rs.getInt("Gear_id"));
//                    row.setGearName(rs.getString("Name"));
//                    row.setGearDecription(rs.getString("Description"));
//                    row.setGearPrice(rs.getInt("Price"));
//                    row.setGearImage(rs.getString("Image"));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//        return row;
//    }
public int getTotalItemByOwner(int campsiteOwnerId) {
    int totalItems = 0;  // Khởi tạo biến lưu số lượng món đồ
    String sql = "SELECT COUNT(*) AS total_items \n" +
                  "FROM GEAR \n" +
                  "WHERE Campsite_owner = ? \n" +
                  "  AND Name NOT LIKE N'%Lều%';\n";

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




    public List<Gear> getGearsByOwner(int campsiteOwnerId, int page, int size) {
        List<Gear> gears = new ArrayList<>();
        String query = "SELECT Gear_id, Name, Description, Image, Price, Campsite_owner "
                + "FROM GEAR "
                + "WHERE Campsite_owner = ? AND Name NOT LIKE N'%Lều%' "
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
                    gear.setName(rs.getString("Name"));
                    gear.setDescription(rs.getString("Description"));
                    gear.setImage(rs.getString("Image"));
                    gear.setPrice(rs.getInt("Price"));
                    gear.setCampsiteOwner(rs.getInt("Campsite_owner"));
                    gears.add(gear); // Thêm Gear vào danh sách
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gears;
    }







    //    public List<Gear> searchByName(String txtSearch) throws Exception {
//        List<Gear> gears = new ArrayList<>();
//        String query = "SELECT g.Gear_id, g.Name, g.Description, g.Image, p.Price "
//                + "FROM GEAR g "
//                + "JOIN PRICE p ON g.Price_id = p.Price_id "
//                + "WHERE g.Name LIKE ?";
//
//        try {
//            con = new DBContext().getConnection();
//            ps = con.prepareStatement(query);
//            ps.setString(1, "%" + txtSearch + "%");
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Gear gear = new Gear();
//                gear.setGearId(rs.getInt("Gear_id"));
//                gear.setGearPrice(rs.getInt("Price"));
//                gear.setGearName(rs.getString("Name"));
//                gear.setGearDecription(rs.getString("Description"));
//                gear.setGearImage(rs.getString("Image"));
//                gears.add(gear);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return gears;
//    }
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

    public Gear getGearByID(String id) throws Exception {
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

//
//    public Gear getGearByIDPrice(String id) throws Exception {
//        String query = "select G.*, P.Price from GEAR G left join PRICE P on G.Price_id = P.Price_id\n"
//                + "	where Gear_id = ?";
//        try {
//            con = new DBContext().getConnection();
//            ps = con.prepareStatement(query);
//            ps.setString(1, id);
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                return new Gear(rs.getInt(1),
//                        rs.getInt("Price"),
//                        rs.getString(3),
//                        rs.getString(4),
//                        rs.getString(5));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//
//    }

    public void updateGear(String id, String name, int price, String description, String image) throws Exception {
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


}
