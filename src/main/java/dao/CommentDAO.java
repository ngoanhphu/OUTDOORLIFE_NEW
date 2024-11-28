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
import model.Campsite;
import model.Comment;

/**
 *
 * @author vietn
 */
public class CommentDAO extends DBContext {
    
    public List<Comment> getAllRiverComment() throws Exception {
        List<Comment> cmt = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT C.*, A.last_name FROM COMMENT C INNER JOIN ACCOUNT A ON C.Account_id = A.Account_id WHERE C.CampsiteAddress LIKE N'%Sông%';"); 
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("Comment_id"));
                comment.setTimeStamp(rs.getTimestamp("TimeStamp"));
                comment.setContent(rs.getString("Content"));
                comment.setAccountId(rs.getInt("Account_id"));
                comment.setCampAddress(rs.getString("CampsiteAddress"));
                comment.setName(rs.getString("last_name"));
                cmt.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cmt;
    }
    public List<Comment> getAllMountainComment() throws Exception {
        List<Comment> cmt = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT C.*, A.last_name FROM COMMENT C INNER JOIN ACCOUNT A ON C.Account_id = A.Account_id WHERE C.CampsiteAddress LIKE N'%Núi%';"); 
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("Comment_id"));
                comment.setTimeStamp(rs.getTimestamp("TimeStamp"));
                comment.setContent(rs.getString("Content"));
                comment.setAccountId(rs.getInt("Account_id"));
                comment.setCampAddress(rs.getString("CampsiteAddress"));
                comment.setName(rs.getString("last_name"));
                cmt.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cmt;
    }
    public List<Comment> getAllBeachComment() throws Exception {
        List<Comment> cmt = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement("SELECT C.*, A.last_name FROM COMMENT C INNER JOIN ACCOUNT A ON C.Account_id = A.Account_id WHERE C.CampsiteAddress LIKE N'%Biển%';"); 
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("Comment_id"));
                comment.setTimeStamp(rs.getTimestamp("TimeStamp"));
                comment.setContent(rs.getString("Content"));
                comment.setAccountId(rs.getInt("Account_id"));
                comment.setCampAddress(rs.getString("CampsiteAddress"));
                comment.setName(rs.getString("last_name"));
                cmt.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cmt;
    }
    public void insertComment(Comment comment) throws Exception {
    String query = "INSERT INTO COMMENT (TimeStamp, Content, Account_id, CampsiteAddress) VALUES (?, ?, ?, ?)";
    try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(query)) {
        pst.setTimestamp(1, comment.getTimeStamp());
        pst.setString(2, comment.getContent());
        pst.setInt(3, comment.getAccountId());
        pst.setString(4, comment.getCampsiteAddress());
        pst.executeUpdate();
    }
}
    
}
