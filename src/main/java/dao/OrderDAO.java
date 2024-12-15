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

    public List<OrderDTO> statisticOrdersByMonth(int year) {
        List<OrderDTO> list = new ArrayList<>();
        try {
            PreparedStatement pst = this.con.prepareStatement("WITH YearMonths AS (\n" +
                    "    SELECT 1 AS MonthNum\n" +
                    "    UNION ALL\n" +
                    "    SELECT MonthNum + 1\n" +
                    "    FROM YearMonths\n" +
                    "    WHERE MonthNum < 12\n" +
                    ")\n" +
                    "SELECT\n" +
                    "    ym.MonthNum AS [Month],\n" +
                    "    ISNULL(COUNT(o.[Orders_id]), 0) AS [OrderCount],\n" +
                    "    ISNULL(SUM(o.[TotalAmount]), 0) AS [MonthlyRevenue]\n" +
                    "FROM\n" +
                    "    YearMonths ym\n" +
                    "LEFT JOIN\n" +
                    "    [dbo].[ORDERS] o\n" +
                    "ON\n" +
                    "    ym.MonthNum = MONTH(o.enddate) AND YEAR(o.enddate) = ? -- Replace @Year with the year parameter\n" +
                    "    \n" +
                    "GROUP BY\n" +
                    "    ym.MonthNum\n" +
                    "ORDER BY\n" +
                    "    ym.MonthNum ASC;\n");
            pst.setInt(1, year);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                OrderDTO order = new OrderDTO();
//                order.setYear(rs.getInt("Year"));
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

    public List<OrderDTO> statisticOrdersByYear() {
        List<OrderDTO> list = new ArrayList<>();
        try {
            // SQL query for getting total orders and revenue for the last 5 years
            String query = "WITH LastFiveYears AS (\n" +
                    "    SELECT YEAR(GETDATE()) AS [Year]\n" +
                    "    UNION ALL\n" +
                    "    SELECT YEAR(DATEADD(YEAR, -1, GETDATE()))\n" +
                    "    UNION ALL\n" +
                    "    SELECT YEAR(DATEADD(YEAR, -2, GETDATE()))\n" +
                    "    UNION ALL\n" +
                    "    SELECT YEAR(DATEADD(YEAR, -3, GETDATE()))\n" +
                    "    UNION ALL\n" +
                    "    SELECT YEAR(DATEADD(YEAR, -4, GETDATE()))\n" +
                    ")\n" +
                    "SELECT\n" +
                    "    ly.[Year],\n" +
                    "    ISNULL(COUNT(o.[Orders_id]), 0) AS [OrderCount],\n" +
                    "    ISNULL(SUM(o.[TotalAmount]), 0) AS [TotalRevenue]\n" +
                    "FROM\n" +
                    "    LastFiveYears ly\n" +
                    "LEFT JOIN\n" +
                    "    [dbo].[ORDERS] o\n" +
                    "ON\n" +
                    "    ly.[Year] = YEAR(o.[EndDate])\n" +
                    "GROUP BY\n" +
                    "    ly.[Year]\n" +
                    "ORDER BY\n" +
                    "    ly.[Year] DESC;";

            PreparedStatement pst = this.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                OrderDTO order = new OrderDTO();
                order.setYear(rs.getInt("Year"));
                order.setNumberOfOrders(rs.getInt("OrderCount"));
                order.setTotalAmount(rs.getInt("TotalRevenue"));
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

    public List<Order> getOrdersByOwnerId(int ownerId) {
        List<Order> ordersList = new ArrayList<>();
        try {
            PreparedStatement pst = this.con.prepareStatement("SELECT o.Orders_id, o.TimeStamp, o.Booker, o.Campsite_id, o.Quantity, " +
                    "o.StartDate, o.EndDate, o.ApproveStatus, o.PaymentStatus, " +
                    "o.TotalAmount, o.BookingPrice " +
                    "FROM [dbo].[ORDERS] o " +
                    "INNER JOIN [dbo].[CAMPSITE] c ON o.Campsite_id = c.Campsite_id " +
                    "WHERE c.Campsite_owner = ?");
            pst.setInt(1, ownerId);
            try (ResultSet resultSet = pst.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setOrdersId(resultSet.getInt("Orders_id"));
                    order.setTimeStamp(resultSet.getTimestamp("TimeStamp"));
                    order.setBooker(resultSet.getInt("Booker"));
                    order.setCampsiteId(resultSet.getInt("Campsite_id"));
                    order.setQuantity(resultSet.getInt("Quantity"));
                    order.setStartDate(resultSet.getTimestamp("StartDate"));
                    order.setEndDate(resultSet.getTimestamp("EndDate"));
                    order.setApproveStatus(resultSet.getBoolean("ApproveStatus"));
                    order.setPaymentStatus(resultSet.getBoolean("PaymentStatus"));
                    order.setTotalAmount(resultSet.getInt("TotalAmount"));
                    order.setBookingPrice(resultSet.getInt("BookingPrice"));
                    int bookerId = resultSet.getInt("Booker"); // Lấy Booker ID từ ResultSet
                    String bookerName = getFullNameById(bookerId); // Gọi hàm getFullNameById để lấy tên đầy đủ
                    order.setBookerName(bookerName); // Gán giá trị vào order
                    ordersList.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordersList;
    }

    public String getFullNameById(int accountId) {
        String fullName = null;
        String sql = "SELECT first_name, last_name FROM ACCOUNT WHERE Account_id = ?";

        try (
                PreparedStatement preparedStatement = this.con.prepareStatement(sql)) {
            preparedStatement.setInt(1, accountId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    fullName = (firstName != null ? firstName : "") +
                            (lastName != null ? " " + lastName : "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fullName;
    }
    public List<Order> getOrderByDateCampsiteAndOwner(String startDate, String endDate, int campsiteId, int ownerId) {
        List<Order> list = new ArrayList<>();
        try {
            // Prepare the SQL query with the new ownerId and campsiteId filters
            String query = "SELECT O.*, " +
                    "       A.first_name + ' ' + A.last_name AS BookerName, " +
                    "       C.Campsite_owner AS OwnerId " +
                    "FROM ORDERS O " +
                    "LEFT JOIN ACCOUNT A ON O.Booker = A.Account_id " +
                    "LEFT JOIN CAMPSITE C ON O.Campsite_id = C.Campsite_id " +
                    "WHERE O.StartDate <= ? AND O.EndDate >= ? " +
                    "  AND (? = 0 OR O.Campsite_id = ?) " +
                    "  AND (? = 0 OR C.Campsite_owner = ?);";

            PreparedStatement pst = this.con.prepareStatement(query);

            // Set parameters for the query
            pst.setString(1, endDate);
            pst.setString(2, startDate);
            pst.setInt(3, campsiteId);
            pst.setInt(4, campsiteId);
            pst.setInt(5, ownerId);
            pst.setInt(6, ownerId);

            // Execute the query
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();

                // Populate the Order object with data from the result set
                order.setOrdersId(rs.getInt("Orders_id"));
                order.setBookerName(rs.getString("BookerName"));
                order.setStartDate(rs.getTimestamp("StartDate"));
                order.setEndDate(rs.getTimestamp("EndDate"));

                // Add the Order object to the list
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        // Return the list of orders
        return list;
    }

    public List<Order> getOrdersByAccountId(int accountId) {
        List<Order> orders = new ArrayList<>();

        String sql = " SELECT o.*, c.Campsite_id, c.Campsite_owner, ow.Owner_id, cust.customer_id " +
                "FROM ORDERS o " +
                "JOIN CUSTOMER cust ON o.Booker = cust.customer_id " +
                "JOIN CAMPSITE c ON o.Campsite_id = c.Campsite_id " +
                "JOIN OWNER ow ON c.Campsite_owner = ow.Owner_id " +
                "WHERE cust.Account_id = ? " +
                "ORDER BY o.TimeStamp DESC";

        try (PreparedStatement preparedStatement = this.con.prepareStatement(sql))  {

            preparedStatement.setInt(1, accountId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setOrdersId(resultSet.getInt("Orders_id"));
                    order.setTimeStamp(resultSet.getTimestamp("TimeStamp"));
                    order.setBooker(resultSet.getInt("Booker"));
                    order.setCampsiteId(resultSet.getInt("Campsite_id"));
                    order.setQuantity(resultSet.getInt("Quantity"));
                    order.setStartDate(resultSet.getTimestamp("StartDate"));
                    order.setEndDate(resultSet.getTimestamp("EndDate"));
                    order.setApproveStatus(resultSet.getBoolean("ApproveStatus"));
                    order.setPaymentStatus(resultSet.getBoolean("PaymentStatus"));
                    order.setTotalAmount(resultSet.getInt("TotalAmount"));
                    order.setBookingPrice(resultSet.getInt("BookingPrice"));
                    order.setOwnerIsRequired(resultSet.getInt("Campsite_owner"));
                    orders.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }



}


