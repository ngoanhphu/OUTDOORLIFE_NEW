package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Campsite;
import model.CampsiteOrder;
import model.Discount;
import model.User;

public class CampsiteOrderDAO {

    private Connection con;

    public CampsiteOrderDAO(Connection con) {
        this.con = con;
    }

    public List<CampsiteOrder> userOrders(int id) {
        List<CampsiteOrder> list = new ArrayList<>();
        try {
            PreparedStatement pst = this.con.prepareStatement("SELECT * FROM ORDERS WHERE Booker = ? AND Campsite_id <> 0 ORDER BY Orders_id DESC");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CampsiteOrder order = new CampsiteOrder();
                int pId = rs.getInt("Campsite_id");
                CampsiteDAO campsiteDAO = new CampsiteDAO();
                Campsite camp = campsiteDAO.getSingleCampsite(pId);
                order.setOrderId(rs.getInt("Orders_id"));
                order.setCampsiteId(pId);
                order.setCampName(camp.getCampName());
                order.setCampDescription(camp.getCampDescription());

                order.setTimeStamp(rs.getTimestamp("TimeStamp"));
                order.setStartDate(rs.getTimestamp("StartDate"));
                order.setEndDate(rs.getTimestamp("EndDate"));
                order.setQuantity(rs.getInt("Quantity"));

                Timestamp startDate = rs.getTimestamp("StartDate");
                Timestamp endDate = rs.getTimestamp("EndDate");
                long milliseconds = endDate.getTime() - startDate.getTime();
                int bookingDays = (int) (milliseconds / (1000 * 60 * 60 * 24));

//                order.setCampPrice(camp.getCampPrice() * rs.getInt("Quantity") * bookingDays);
                order.setTotalAmountBooking(rs.getInt("BookingPrice"));
                order.setTotalAmount(rs.getInt("TotalAmount"));
                order.setBookingDays(bookingDays);
                order.setApproveStatus(rs.getBoolean("ApproveStatus"));
                order.setPaymentStatus(rs.getBoolean("PaymentStatus"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public CampsiteOrder getCampsiteOrderByOrderId(int orderId) {
        try {
            PreparedStatement pst = this.con.prepareStatement("SELECT o.*, c.Name FROM ORDERS o\n" +
"left join CAMPSITE c on o.Campsite_id = c.Campsite_id\n" +
"WHERE o.Orders_id = ? ORDER BY o.Orders_id DESC");
            pst.setInt(1, orderId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CampsiteOrder order = new CampsiteOrder();
                int pId = rs.getInt("Campsite_id");
                CampsiteDAO campsiteDAO = new CampsiteDAO();
                Campsite camp = campsiteDAO.getSingleCampsite(pId);
                order.setOrderId(rs.getInt("Orders_id"));
                order.setCampsiteId(pId);
                order.setCampName(camp.getCampName());
                order.setCampDescription(camp.getCampDescription());

                order.setTimeStamp(rs.getTimestamp("TimeStamp"));
                order.setStartDate(rs.getTimestamp("StartDate"));
                order.setEndDate(rs.getTimestamp("EndDate"));
                order.setQuantity(rs.getInt("Quantity"));

                Timestamp startDate = rs.getTimestamp("StartDate");
                Timestamp endDate = rs.getTimestamp("EndDate");
                long milliseconds = endDate.getTime() - startDate.getTime();
                int bookingDays = (int) (milliseconds / (1000 * 60 * 60 * 24));

//                order.setCampPrice(camp.getCampPrice() * rs.getInt("Quantity") * bookingDays);
                order.setTotalAmountBooking(rs.getInt("BookingPrice"));
                order.setTotalAmount(rs.getInt("TotalAmount"));
                order.setBookingDays(bookingDays);
                order.setApproveStatus(rs.getBoolean("ApproveStatus"));
                order.setPaymentStatus(rs.getBoolean("PaymentStatus"));
                order.setCampsiteName(rs.getString("Name"));
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<CampsiteOrder> userOrdersPayment(int id) {
        List<CampsiteOrder> list = new ArrayList<>();
        try {
            PreparedStatement pst = this.con.prepareStatement("SELECT * FROM ORDERS WHERE Booker = ? AND PaymentStatus <> 1 AND Campsite_id <> 0 ORDER BY Orders_id DESC");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CampsiteOrder order = new CampsiteOrder();
                int pId = rs.getInt("Campsite_id");
                CampsiteDAO campsiteDAO = new CampsiteDAO();
                Campsite camp = campsiteDAO.getSingleCampsite(pId);
                order.setOrderId(rs.getInt("Orders_id"));
                order.setCampsiteId(pId);
                order.setCampName(camp.getCampName());
                order.setCampDescription(camp.getCampDescription());

                order.setTimeStamp(rs.getTimestamp("TimeStamp"));
                order.setStartDate(rs.getTimestamp("StartDate"));
                order.setEndDate(rs.getTimestamp("EndDate"));
                order.setQuantity(rs.getInt("Quantity"));

                Timestamp startDate = rs.getTimestamp("StartDate");
                Timestamp endDate = rs.getTimestamp("EndDate");
                long milliseconds = endDate.getTime() - startDate.getTime();
                int bookingDays = (int) (milliseconds / (1000 * 60 * 60 * 24));

                order.setCampPrice(camp.getCampPrice() * rs.getInt("Quantity") * bookingDays);
                order.setBookingDays(bookingDays);
                order.setApproveStatus(rs.getBoolean("ApproveStatus"));
                order.setPaymentStatus(rs.getBoolean("PaymentStatus"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Discount getDiscountByCode(String discountCode) throws SQLException {
        try {
            PreparedStatement pst = this.con.prepareStatement("SELECT * FROM Discount WHERE code = ?");
            pst.setString(1, discountCode);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Discount discount = new Discount();
                discount.setCode(rs.getInt("code"));
                discount.setPercentDiscount(rs.getDouble("percent_discount"));
                discount.setAmountDiscount(rs.getDouble("amount_discount"));
                discount.setExpiryDate(rs.getDate("expiry_date"));
                discount.setUsageCount(rs.getInt("usage_count"));
                return discount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        return null;
    }

    private double applyDiscount(double originalPrice, Discount discount) {
        if (discount.getPercentDiscount() != null) {
            originalPrice *= (1 - (discount.getPercentDiscount() / 100));
        }
        if (discount.getAmountDiscount() != null) {
            originalPrice -= discount.getAmountDiscount();
        }
        return originalPrice;
    }

    public void cancelOrder(int id) {

        PreparedStatement pst = null;
        try {
            cancelOrderDetail(id);

            String query = "DELETE FROM ORDERS WHERE Orders_id = ?";
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

    public List<CampsiteOrder> getAllOrders(int page, int size) {
        List<CampsiteOrder> list = new ArrayList<>();
        try {
            PreparedStatement pst = this.con.prepareStatement("SELECT * FROM ORDERS ORDER BY Orders_id DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
            int offset = (page - 1) * size;
            pst.setInt(1, offset);
            pst.setInt(2, size);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CampsiteOrder order = new CampsiteOrder();
                int pId = rs.getInt("Campsite_id");
                CampsiteDAO campsiteDAO = new CampsiteDAO();
                Campsite camp = campsiteDAO.getSingleCampsite(pId);
                order.setOrderId(rs.getInt("Orders_id"));
                order.setCampsiteId(pId);
                order.setCampName(camp.getCampName());
                order.setCampDescription(camp.getCampDescription());

                order.setTimeStamp(rs.getTimestamp("TimeStamp"));
                order.setStartDate(rs.getTimestamp("StartDate"));
                order.setEndDate(rs.getTimestamp("EndDate"));
                order.setQuantity(rs.getInt("Quantity"));

                Timestamp startDate = rs.getTimestamp("StartDate");
                Timestamp endDate = rs.getTimestamp("EndDate");
                long milliseconds = endDate.getTime() - startDate.getTime();
                int bookingDays = (int) (milliseconds / (1000 * 60 * 60 * 24));

                order.setTotalAmountBooking(rs.getInt("BookingPrice"));
                order.setTotalAmount(rs.getInt("TotalAmount"));
                order.setBookingDays(bookingDays);
                order.setApproveStatus(rs.getBoolean("ApproveStatus"));
                order.setPaymentStatus(rs.getBoolean("PaymentStatus"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public int getTotalItem() {
        try {
            String sql = "SELECT COUNT(*) AS total_items \n"
                    + "FROM [ORDERS] \n";

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
}
