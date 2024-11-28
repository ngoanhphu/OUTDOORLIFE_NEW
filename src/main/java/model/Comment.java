/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author vietn
 */
public class Comment extends Campsite {
    private int commentId;
    private Timestamp timeStamp;
    private String content;
    private int accountId;
    private String campsiteAddress;
    private String name;
    
    public Comment(){
        
    }
    public Comment(int commentId, Timestamp timeStamp, String content, int accountId, String campsiteAddress, String name) {
        this.commentId = commentId;
        this.timeStamp = timeStamp;
        this.content = content;
        this.accountId = accountId;
        this.campsiteAddress = campsiteAddress;
        this.name = name;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getCampsiteAddress() {
        return campsiteAddress;
    }

    public void setCampsiteAddress(String campsiteAddress) {
        this.campsiteAddress = campsiteAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
