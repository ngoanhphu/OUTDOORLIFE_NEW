/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Feedback;
import model.User;

/**
 *
 * @author ptd
 */
public class FeedbackDAO extends DBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Feedback> getLastFiveFeedback() {

        List<Feedback> list = new ArrayList<>();
        try {
            conn = getConnection();//mo ket noi

            String query = "SELECT * FROM FEEDBACK";

            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int feedbackId = rs.getInt("Feedback_id");
                int accountId = rs.getInt("Account_id");
                //  String customerName=rs.getString("CustomerName");
                String feedbackDate = rs.getString("TimeStamp");
                String content = rs.getString("Content");
                String rating = rs.getString("StarNumber");
                // String accountName = rs.getString("FullName");
                // String replyContent = rs.getString("ReplyContent");
                // String authorReply = rs.getString("UserName");

                list.add(new Feedback(feedbackId, accountId, feedbackDate, content, rating));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Feedback> getAllFeedback() {

        List<Feedback> list = new ArrayList<>();
        try {
            conn = getConnection();//mo ket noi

            // Sử dụng JOIN để kết hợp dữ liệu từ hai bảng
            String query = "SELECT top 5 f.Feedback_id, a.Account_id, a.first_name, a.last_name, f.TimeStamp, f.Content, f.StarNumber "
                    + "FROM FEEDBACK f "
                    + "JOIN ACCOUNT a ON f.commenter = a.Account_id";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int feedbackId = rs.getInt("Feedback_id");
                int accountId = rs.getInt("Account_id");
                String customerName = rs.getString("first_name") + " " + rs.getString("last_name");
                String feedbackDate = rs.getString("TimeStamp");
                String content = rs.getString("Content");
                String rating = rs.getString("StarNumber");

                list.add(new Feedback(feedbackId, accountId, customerName, feedbackDate, content, rating));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insertFeedback(int rating, String feedbackDate, int accountId, int campsiteId, String content) {
        try {
            conn = getConnection(); // Mở kết nối

            // Sửa lại câu lệnh SQL để đúng với bảng và các cột
            String query = "INSERT INTO FEEDBACK (StarNumber, TimeStamp, commenter, Campsite_id, Content) VALUES (?, ?, ?, ?, ?)";

            ps = conn.prepareStatement(query);

            // Gán giá trị vào các tham số trong câu lệnh SQL
            ps.setInt(1 , rating); // StarNumber
            ps.setString(2, feedbackDate); // TimeStamp
            ps.setInt(3, accountId); // commenter
            ps.setInt(4, campsiteId); // Campsite_id
            ps.setString(5, content); // Content

            // Thực hiện cập nhật vào cơ sở dữ liệu
            int rs = ps.executeUpdate();
            if (rs > 0) {
                return true; // Nếu thành công
            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý ngoại lệ
        }
        return false; // Nếu có lỗi hoặc không thành công
    }

    public List<Feedback> searchFeedbackByUser(String txtSearch) {
        List<Feedback> list = new ArrayList<>();
        try {
            conn = getConnection(); // Mở kết nối

            // Lệnh SQL
            String query = "SELECT f.Feedback_id, f.commenter, f.TimeStamp, f.Content, f.StarNumber, f.Campsite_id," +
                    "CONCAT(a.first_name, ' ', a.last_name) AS FullName, " +
                    "c.Name AS CampsiteName " +
                    "FROM FEEDBACK f " +
                    "JOIN ACCOUNT a ON f.commenter = a.Account_id " +
                    "LEFT JOIN CAMPSITE c ON f.Campsite_id = c.Campsite_id";

            if (!txtSearch.isEmpty()) {
                query += " WHERE CONCAT(a.first_name, ' ', a.last_name) LIKE ?"; // Điều kiện tìm kiếm
            }

            ps = conn.prepareStatement(query);

            // Gán tham số nếu có
            if (!txtSearch.isEmpty()) {
                ps.setString(1, "%" + txtSearch + "%");
            }

            rs = ps.executeQuery();

            // Xử lý dữ liệu trả về
            while (rs.next()) {
                int feedbackId = rs.getInt("Feedback_id");
                int accountId = rs.getInt("commenter");
                String feedbackDate = rs.getString("TimeStamp");
                String content = rs.getString("Content");
                String rating = rs.getString("StarNumber");
                String accountName = rs.getString("FullName");
                int campsiteId=rs.getInt("Campsite_id");
                String campsiteName = rs.getString("CampsiteName") ;

                list.add(new Feedback(feedbackId, accountId, accountName, feedbackDate, content, rating, campsiteId, campsiteName));
            }
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi
        }
        return list;
    }




    public List<Feedback> filterFeedbackByRating(String rating) {
        List<Feedback> list = new ArrayList<>();
        String query = "SELECT f.Feedback_id, f.commenter, f.TimeStamp, f.Content, f.StarNumber, f.Campsite_id, " +
                "CONCAT(a.first_name, ' ', a.last_name) AS FullName, c.Name AS CampsiteName " +
                "FROM FEEDBACK f " +
                "JOIN ACCOUNT a ON f.commenter = a.Account_id " +
                "LEFT JOIN CAMPSITE c ON f.Campsite_id = c.Campsite_id " +
                "WHERE f.StarNumber = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, rating);  // Gán giá trị cho tham số rating

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int feedbackId = rs.getInt("Feedback_id");
                    int accountId = rs.getInt("commenter");
                    String feedbackDate = rs.getString("TimeStamp");
                    String content = rs.getString("Content");
                    String starRating = rs.getString("StarNumber");
                    String accountName = rs.getString("FullName");
                    Integer campsiteId = rs.getObject("Campsite_id") != null ? rs.getInt("Campsite_id") : null;
                    String campsiteName = rs.getString("CampsiteName");

                    list.add(new Feedback(feedbackId, accountId, accountName, feedbackDate, content, starRating, campsiteId, campsiteName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // In lỗi nếu có
        }
        return list;
    }



    public boolean deleteFeedback(int feedbackId) {
        try {
            conn = getConnection();//mo ket noi

            String query = "delete Feedback\n"
                    + "where Feedback_id = ?";

            ps = conn.prepareStatement(query);
            ps.setInt(1, feedbackId);

            int rs = ps.executeUpdate();
            if (rs > 0) {
                return true;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        FeedbackDAO f=new FeedbackDAO();
        System.out.println(f.getLastFiveFeedback());
    }


}
