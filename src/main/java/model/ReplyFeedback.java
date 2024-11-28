/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author ptd
 */
public class ReplyFeedback implements Serializable{
    private int replyId;
    private int feedbackId;
    private int accountId;
    private String content;

    public ReplyFeedback() {
    }

    public ReplyFeedback(int replyId, int feedbackId, int accountId, String content) {
        this.replyId = replyId;
        this.feedbackId = feedbackId;
        this.accountId = accountId;
        this.content = content;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
    
}
