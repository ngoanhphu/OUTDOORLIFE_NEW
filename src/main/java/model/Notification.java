package model;

import java.sql.Timestamp;

public class Notification {
    private int id;
    private int receiverId;
    private String description;
    private String status;
    private java.util.Date notificationDate;

    public Notification() {}

    public Notification(int receiverId, String description, String status) {
        this.receiverId = receiverId;
        this.description = description;
        this.status = status;
        this.notificationDate = new java.util.Date();
    }

    public Notification(int notificationId, String description, Timestamp createdDate, boolean isRead) {
        this.receiverId = notificationId;
        this.description = description;
        this.notificationDate = new java.util.Date();
    }


    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.util.Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(java.util.Date notificationDate) {
        this.notificationDate = notificationDate;
    }
}
