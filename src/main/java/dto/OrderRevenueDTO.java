package dto;

public class OrderRevenueDTO {

    private int year;
    private int month;
    private int numberOfOrders;
    private int totalAmount;

    public OrderRevenueDTO() {
    }

    public OrderRevenueDTO(int year, int month, int numberOfOrders, int totalAmount) {
        this.year = year;
        this.month = month;
        this.numberOfOrders = numberOfOrders;
        this.totalAmount = totalAmount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
