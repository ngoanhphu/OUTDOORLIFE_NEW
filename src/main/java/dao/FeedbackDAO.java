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
                    + "JOIN ACCOUNT a ON f.Account_id = a.Account_id";
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

    public boolean insertFeedback(Feedback feedback) {
        try {
            conn = getConnection();//mo ket noi

            String query = "insert FEEDBACK(Account_id,StarNumber,TimeStamp,Content)\n"
                    + "values (?,?,?,?)";

            ps = conn.prepareStatement(query);
            ps.setInt(1, feedback.getAccountId());
            ps.setString(2, feedback.getRating());
            ps.setString(3, feedback.getFeedbackDate());
            ps.setString(4, feedback.getContent());

            int rs = ps.executeUpdate();
            if (rs > 0) {
                return true;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Feedback> searchFeedbackByUser(String txtSearch) {

        List<Feedback> list = new ArrayList<>();
        try {
            conn = getConnection();//mo ket noi

            String query = "SELECT f.*, a.first_name + ' ' + a.last_name as FullName FROM FEEDBACK f JOIN ACCOUNT a ON f.Account_id = a.Account_id";
            if (!txtSearch.isEmpty()) {
                query += " where FullName like ?";
            }
            ps = conn.prepareStatement(query);
            if (!txtSearch.isEmpty()) {
                ps.setString(1, "%" + txtSearch + "%");
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                int feedbackId = rs.getInt("Feedback_id");
                int accountId = rs.getInt("Account_id");

                String feedbackDate = rs.getString("TimeStamp");
                String content = rs.getString("Content");
                String rating = rs.getString("StarNumber");
                String accountName = rs.getString("FullName");

                list.add(new Feedback(feedbackId, accountId, accountName, feedbackDate, content, rating));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Feedback> filterFeedbackByRating(String rating) {

        List<Feedback> list = new ArrayList<>();
        try {
            conn = getConnection();//mo ket noi

            String query = "select f.*, a.first_name + ' ' + a.last_name as FullName \n"
                    + "from Feedback  f JOIN ACCOUNT a ON f.Account_id = a.Account_id\n"
                    + "where StarNumber = ?";

            ps = conn.prepareStatement(query);
            ps.setString(1, rating);
            rs = ps.executeQuery();
            while (rs.next()) {
                int feedbackId = rs.getInt("Feedback_id");
                int accountId = rs.getInt("Account_id");

                String feedbackDate = rs.getString("TimeStamp");
                String content = rs.getString("Content");
                String ratingStr = rs.getString("StarNumber");
                String accountName = rs.getString("FullName");
                list.add(new Feedback(feedbackId, accountId, accountName, feedbackDate, content, ratingStr));
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        FeedbackDAO f = new FeedbackDAO();
        System.out.println(f.getAllFeedback());

    }
}
