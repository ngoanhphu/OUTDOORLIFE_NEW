package model;

import java.sql.Timestamp;

public class Order extends Gear {
    private int ordersId;
    private Timestamp timeStamp;
    private int booker;
    private int campsiteId;
    private Timestamp startDate;
    private Timestamp endDate;
    private boolean approveStatus;
    private boolean paymentStatus;
    private int quantity;
    private int totalAmount;
    private int bookingPrice;
    private String bookerName;
    private int ownerIsRequired;

    public Order(int booker, int campsiteId, Timestamp startDate, Timestamp endDate, boolean approveStatus, boolean paymentStatus, int quantity, int totalAmount, int bookingPrice, int ownerIsRequired) {
        this.booker = booker;
        this.campsiteId = campsiteId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approveStatus = approveStatus;
        this.paymentStatus = paymentStatus;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.bookingPrice = bookingPrice;
        this.ownerIsRequired = ownerIsRequired;
    }

    public Order(int booker, int campsiteId, Timestamp startDate, Timestamp endDate, boolean approveStatus, boolean paymentStatus, int quantity, int totalAmount, int bookingPrice) {
        this.booker = booker;
        this.campsiteId = campsiteId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approveStatus = approveStatus;
        this.paymentStatus = paymentStatus;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.bookingPrice = bookingPrice;
    }
    
    
    
    public Order() {
    }

    public Order(int ordersId, Timestamp timeStamp, int booker, Timestamp startDate, Timestamp endDate, boolean approveStatus, boolean paymentStatus, int quantity) {
        this.ordersId = ordersId;
        this.timeStamp = timeStamp;
        this.booker = booker;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approveStatus = approveStatus;
        this.paymentStatus = paymentStatus;
        this.quantity = quantity;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getBooker() {
        return booker;
    }

    public void setBooker(int booker) {
        this.booker = booker;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public boolean isApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(boolean approveStatus) {
        this.approveStatus = approveStatus;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCampsiteId() {
        return campsiteId;
    }

    public void setCampsiteId(int campsiteId) {
        this.campsiteId = campsiteId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getBookingPrice() {
        return bookingPrice;
    }

    public void setBookingPrice(int bookingPrice) {
        this.bookingPrice = bookingPrice;
    }

    public String getBookerName() {
        return bookerName;
    }

    public void setBookerName(String bookerName) {
        this.bookerName = bookerName;
    }

    public int getOwnerIsRequired() {
        return ownerIsRequired;
    }

    public void setOwnerIsRequired(int ownerIsRequired) {
        this.ownerIsRequired = ownerIsRequired;
    }
}
