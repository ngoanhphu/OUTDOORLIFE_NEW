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
    
}
