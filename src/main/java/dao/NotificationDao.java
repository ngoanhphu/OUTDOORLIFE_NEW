package dao;

import model.Notification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotificationDao extends DBContext{

    public boolean insertNotification(Notification notification) {
        String sql = "INSERT INTO Notification (receiver_id, description, status) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, notification.getReceiverId());
            stmt.setString(2, notification.getDescription());
            stmt.setBoolean(3, notification.getStatus());

            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean sendNotification(int reportId, String description) {
        try (Connection connection = getConnection()) {
            // Lấy owner_id thông qua campsite_id
            String queryOwnerId = "SELECT CAMPSITE.Campsite_owner " +
                    "FROM REPORT_LOCATION " +
                    "JOIN CAMPSITE ON REPORT_LOCATION.Campsite_id = CAMPSITE.Campsite_id " +
                    "WHERE REPORT_LOCATION.report_id = ?";

            PreparedStatement stmtOwner = connection.prepareStatement(queryOwnerId);
            stmtOwner.setInt(1, reportId);
            ResultSet rs = stmtOwner.executeQuery();

            if (rs.next()) {
                int ownerId = rs.getInt("Campsite_owner");

                // Insert vào Notification
                String insertNotification = "INSERT INTO NOTIFICATION (owner_id, report_id, description) VALUES (?, ?, ?)";
                PreparedStatement stmtInsert = connection.prepareStatement(insertNotification);
                stmtInsert.setInt(1, ownerId);
                stmtInsert.setInt(2, reportId);
                stmtInsert.setString(3, description);

                stmtInsert.executeUpdate();

                // Update status
                String updateStatus = "UPDATE REPORT_LOCATION SET status = 'Completed' WHERE report_id = ?";
                PreparedStatement stmtUpdate = connection.prepareStatement(updateStatus);
                stmtUpdate.setInt(1, reportId);
                stmtUpdate.executeUpdate();

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public boolean removeReport(int reportId) {
        try (Connection connection = getConnection()) {
            String deleteQuery = "DELETE FROM REPORT_LOCATION WHERE report_id = ?";
            PreparedStatement stmtDelete = connection.prepareStatement(deleteQuery);
            stmtDelete.setInt(1, reportId);

            stmtDelete.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public int getUnreadCount(int ownerId) {
        try (Connection connection = getConnection()) {
            String query = "SELECT COUNT(*) AS unread_count FROM NOTIFICATION WHERE owner_id = ? ";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, ownerId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("unread_count");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Notification> getNotifications(int ownerId) {
        List<Notification> notifications = new ArrayList<>();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM NOTIFICATION WHERE owner_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, ownerId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Notification notification = new Notification(
                        rs.getInt("notification_id"),
                        rs.getString("description"),
                        rs.getBoolean("is_read")
                );
                notifications.add(notification);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notifications;
    }


}