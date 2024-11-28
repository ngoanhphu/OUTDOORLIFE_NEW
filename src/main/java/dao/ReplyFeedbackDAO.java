/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Feedback;
import model.ReplyFeedback;

/**
 *
 * @author ptd
 */
public class ReplyFeedbackDAO extends DBContext {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public boolean insertReplyFeedback(ReplyFeedback replyFeedback) {
        try {
            conn = getConnection();//mo ket noi

            String query = "insert ReplyFeedback(IDAccount,IDFeedback,ReplyContent)\n"
                    + "values (?,?,?)";

            ps = conn.prepareStatement(query);
            ps.setInt(1, replyFeedback.getAccountId());
            ps.setInt(2, replyFeedback.getFeedbackId());
            ps.setString(3, replyFeedback.getContent());
           

            int rs = ps.executeUpdate();
            if (rs > 0) {
                return true;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public void addReplyFeedback(String IDAccount, String IDFeedback, String ReplyContent) {
        String query = "insert ReplyFeedback(IDAccount,IDFeedback,ReplyContent)\n"
                    + "values (?,?,?)";
        try {
            conn = getConnection();//mo ket noi
            ps = conn.prepareStatement(query);
            ps.setString(1, IDAccount);
            ps.setString(2, IDFeedback);
            ps.setString(3, ReplyContent);

            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
