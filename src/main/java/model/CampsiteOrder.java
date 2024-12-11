package model;

import java.sql.Timestamp;

public class CampsiteOrder extends Campsite {
    private int orderId;
    private Timestamp timeStamp;
    private int booker;
    private int campsiteId; 
    private int quantity;
    private Timestamp startDate;
    private Timestamp endDate;
    private boolean approveStatus;
    private boolean paymentStatus;
    private String discount;
    private int bookingDays;
    private boolean discountApplied;
    private int totalAmountBooking;
    private int totalAmount;
    private Integer voucherId;
    private String campsiteName;
    private int onwer;
    private String image;
    
    public CampsiteOrder(){
        
    }
    
    public CampsiteOrder(int orderId, Timestamp timeStamp, int booker, int campsiteId, int quantity, Timestamp startDate, Timestamp endDate, boolean approveStatus, boolean paymentStatus, String discount, int bookingDays, boolean discountApplied, int owner, String image) {
        this.orderId = orderId;
        this.timeStamp = timeStamp;
        this.booker = booker;
        this.campsiteId = campsiteId;
        this.quantity = quantity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approveStatus = approveStatus;
        this.paymentStatus = paymentStatus;
        this.discount = discount;
        this.bookingDays = bookingDays;
        this.discountApplied = discountApplied;
        this.onwer = owner;
        this.image = image;
        
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public int getCampsiteId() {
        return campsiteId;
    }

    public void setCampsiteId(int campsiteId) {
        this.campsiteId = campsiteId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getBookingDays() {
        return bookingDays;
    }

    public void setBookingDays(int bookingDays) {
        this.bookingDays = bookingDays;
    }

    public boolean isDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(boolean discountApplied) {
        this.discountApplied = discountApplied;
    }

    public int getOnwer() {
        return onwer;
    }

    public void setOnwer(int onwer) {
        this.onwer = onwer;
    }

    public int getTotalAmountBooking() {
        return totalAmountBooking;
    }

    public void setTotalAmountBooking(int totalAmountBooking) {
        this.totalAmountBooking = totalAmountBooking;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public String getCampsiteName() {
        return campsiteName;
    }

    public void setCampsiteName(String campsiteName) {
        this.campsiteName = campsiteName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
