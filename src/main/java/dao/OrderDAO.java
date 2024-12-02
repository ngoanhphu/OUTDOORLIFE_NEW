package dao;

import dto.OrderDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.*;
import model.*;

public class OrderDAO {

    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public OrderDAO(Connection con) {
        this.con = con;
    }

    public boolean insertOrder(Order order) {
        boolean result = false;
        try {
            String sql = "INSERT INTO ORDERS (Book_id, TimeStamp, Booker, Quantity, PaymentStatus, ApproveStatus) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = this.con.prepareStatement(sql);
            pst.setInt(1, order.getGearId());
            pst.setTimestamp(2, order.getTimeStamp());
            pst.setInt(3, order.getBooker());
            pst.setInt(4, order.getQuantity());
            pst.setBoolean(5, order.isApproveStatus());
            pst.setBoolean(6, order.isPaymentStatus());
            pst.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL error: " + e.getMessage());
        }
        return result;
    }

    public int insertOrder2(Order order) {
        int generatedId = -1;
        try {
            String sql = "INSERT INTO ORDERS ([TimeStamp]\n"
                    + "      ,[Booker]\n"
                    + "      ,[Campsite_id]\n"
                    + "      ,[Quantity]\n"
                    + "      ,[StartDate]\n"
                    + "      ,[EndDate]\n"
                    + "      ,[ApproveStatus]\n"
                    + "      ,[PaymentStatus]\n"
                    + "      ,[TotalAmount]\n"
                    + "      ,[BookingPrice]) VALUES (GETDATE(), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = this.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, order.getBooker());
            pst.setInt(2, order.getCampsiteId());
            pst.setInt(3, order.getQuantity());
            pst.setTimestamp(4, order.getStartDate());
            pst.setTimestamp(5, order.getEndDate());
            pst.setBoolean(6, order.isApproveStatus());
            pst.setBoolean(7, order.isPaymentStatus());
            pst.setInt(8, order.getTotalAmount());
            pst.setInt(9, order.getBookingPrice());
            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("SQL error: " + e.getMessage());
        }
        return generatedId;
    }

    public List<Order> userOrders(int id) {
        List<Order> list = new ArrayList<>();
        try {
            PreparedStatement pst = this.con.prepareStatement("SELECT * FROM ORDERS WHERE Booker = ? AND Book_id <> 0 ORDER BY Orders_id DESC");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                int pId = rs.getInt("Book_id");
                GearDAO gearDAO = new GearDAO(this.con);
                Gear gear = gearDAO.getSingleGear(pId);
                order.setOrdersId(rs.getInt("Orders_id"));
                order.setGearId(pId);
                order.setGearName(gear.getGearName());
                order.setGearDecription(gear.getGearDecription());
                order.setGearPrice(gear.getGearPrice() * rs.getInt("Quantity"));
                order.setTimeStamp(rs.getTimestamp("TimeStamp"));
                order.setQuantity(rs.getInt("Quantity"));
                list.add(order);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Order getUserById(int id) {
        try {
            PreparedStatement pst = this.con.prepareStatement("SELECT * FROM ORDERS WHERE Orders_id = ?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrdersId(rs.getInt("Orders_id"));
                order.setQuantity(rs.getInt("Quantity"));
                order.setCampsiteId(rs.getInt("Campsite_id"));
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Order> userOrdersPayment(int id) {
        List<Order> list = new ArrayList<>();
        try {
            PreparedStatement pst = this.con.prepareStatement("SELECT * FROM ORDERS WHERE Booker = ? AND (PaymentStatus IS NULL OR PaymentStatus <> 1)  AND Book_id <> 0 ORDER BY Orders_id DESC");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                int pId = rs.getInt("Book_id");
                GearDAO gearDAO = new GearDAO(this.con);
                Gear gear = gearDAO.getSingleGear(pId);
                order.setOrdersId(rs.getInt("Orders_id"));
                order.setGearId(pId);
                order.setGearName(gear.getGearName());
                order.setGearDecription(gear.getGearDecription());
                order.setGearPrice(gear.getGearPrice() * rs.getInt("Quantity"));
                order.setTimeStamp(rs.getTimestamp("TimeStamp"));
                order.setQuantity(rs.getInt("Quantity"));
                list.add(order);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void cancelOrder(int id) {
        try {
            cancelOrderDetail(id);

            query = "DELETE FROM ORDERS WHERE Orders_id = ?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }

    public void cancelOrderDetail(int id) {
        PreparedStatement pst = null;
        try {
            String query = "DELETE FROM [ORDER_DETAIL] WHERE Orders_id = ?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void approveOrder(int id, String action) {
        try {
            if (action.equals("status")) {
                query = "Update ORDERS set ApproveStatus = 1 WHERE Orders_id = ?";
            } else {

                query = "Update ORDERS set PaymentStatus = 1 WHERE Orders_id = ?";
            }
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
    }

    public List<OrderDTO> statisticOrdersByMonth() {
        List<OrderDTO> list = new ArrayList<>();
        try {
            PreparedStatement pst = this.con.prepareStatement("	WITH LastSixMonths AS (\n"
                    + "    SELECT \n"
                    + "        YEAR(GETDATE()) AS [Year],\n"
                    + "        MONTH(GETDATE()) AS [Month]\n"
                    + "    UNION ALL\n"
                    + "    SELECT \n"
                    + "        YEAR(DATEADD(MONTH, -1, GETDATE())) AS [Year],\n"
                    + "        MONTH(DATEADD(MONTH, -1, GETDATE())) AS [Month]\n"
                    + "    UNION ALL\n"
                    + "    SELECT \n"
                    + "        YEAR(DATEADD(MONTH, -2, GETDATE())) AS [Year],\n"
                    + "        MONTH(DATEADD(MONTH, -2, GETDATE())) AS [Month]\n"
                    + "    UNION ALL\n"
                    + "    SELECT \n"
                    + "        YEAR(DATEADD(MONTH, -3, GETDATE())) AS [Year],\n"
                    + "        MONTH(DATEADD(MONTH, -3, GETDATE())) AS [Month]\n"
                    + "    UNION ALL\n"
                    + "    SELECT \n"
                    + "        YEAR(DATEADD(MONTH, -4, GETDATE())) AS [Year],\n"
                    + "        MONTH(DATEADD(MONTH, -4, GETDATE())) AS [Month]\n"
                    + "    UNION ALL\n"
                    + "    SELECT \n"
                    + "        YEAR(DATEADD(MONTH, -5, GETDATE())) AS [Year],\n"
                    + "        MONTH(DATEADD(MONTH, -5, GETDATE())) AS [Month]\n"
                    + ")\n"
                    + "SELECT \n"
                    + "    LSM.[Year],\n"
                    + "    LSM.[Month],\n"
                    + "    ISNULL(COUNT(O.[Orders_id]), 0) AS [OrderCount],\n"
                    + "	 ISNULL(SUM(O.[TotalAmount]), 0) AS [MonthlyRevenue]\n"
                    + "FROM \n"
                    + "    LastSixMonths LSM\n"
                    + "LEFT JOIN \n"
                    + "    [dbo].[ORDERS] O\n"
                    + "ON \n"
                    + "    LSM.[Year] = YEAR(O.[TimeStamp]) AND\n"
                    + "    LSM.[Month] = MONTH(O.[TimeStamp])\n"
                    + "GROUP BY \n"
                    + "    LSM.[Year], \n"
                    + "    LSM.[Month]\n"
                    + "ORDER BY \n"
                    + "    LSM.[Year], \n"
                    + "    LSM.[Month];");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                OrderDTO order = new OrderDTO();
                order.setYear(rs.getInt("Year"));
                order.setMonth(rs.getInt("Month"));
                order.setNumberOfOrders(rs.getInt("OrderCount"));
                order.setTotalAmount(rs.getInt("MonthlyRevenue"));
                list.add(order);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Order> getOrderByDate(String startDate, String endDate) {
        List<Order> list = new ArrayList<>();
        try {
            PreparedStatement pst = this.con.prepareStatement("select ORDERS.*, ACCOUNT.first_name + ' ' + ACCOUNT.last_name as name from ORDERS\n"
                    + "left join ACCOUNT on ORDERS.Booker = ACCOUNT.Account_id\n"
                    + "where StartDate <= ? and EndDate >= ?");
            pst.setString(1, endDate);
            pst.setString(2, startDate);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();

                order.setOrdersId(rs.getInt("Orders_id"));
                order.setBookerName(rs.getString("name"));
                order.setStartDate(rs.getTimestamp("StartDate"));
                order.setEndDate(rs.getTimestamp("EndDate"));
                list.add(order);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Order> getOrderByDateAndCampsite(String startDate, String endDate, int campsiteId) {
        List<Order> list = new ArrayList<>();
        try {
            PreparedStatement pst = this.con.prepareStatement("select ORDERS.*, ACCOUNT.first_name + ' ' + ACCOUNT.last_name as name from ORDERS\n"
                    + "left join ACCOUNT on ORDERS.Booker = ACCOUNT.Account_id\n"
                    + "where StartDate <= ? and EndDate >= ? and (? = 0 or Campsite_id = ?)");
            pst.setString(1, endDate);
            pst.setString(2, startDate);
            pst.setInt(3, campsiteId);
            pst.setInt(4, campsiteId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();

                order.setOrdersId(rs.getInt("Orders_id"));
                order.setBookerName(rs.getString("name"));
                order.setStartDate(rs.getTimestamp("StartDate"));
                order.setEndDate(rs.getTimestamp("EndDate"));
                list.add(order);

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

}
