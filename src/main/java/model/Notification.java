package model;

import java.sql.Timestamp;

public class Notification {
    private int id;
    private int receiverId;
    private String description;
    private boolean status;
    private String notificationDate;

    public Notification() {}

    public Notification(int receiverId, String description, boolean status) {
        this.receiverId = receiverId;
        this.description = description;
        this.status = status;

    }


    public Notification(int notificationId, String description, Timestamp createdDate, boolean isRead, String notificationDate) {
        this.receiverId = notificationId;
        this.description = description;
        this.notificationDate = notificationDate;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        this.notificationDate = notificationDate;
    }
}