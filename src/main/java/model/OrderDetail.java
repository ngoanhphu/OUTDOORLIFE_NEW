/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class OrderDetail {

    private int id;
    private int orderId;
    private int quantity;
    private int gearId;
    private int subAmount;
    private Gear gear;

    public OrderDetail() {
    }

    public OrderDetail(int orderId, int quantity, int gearId, int subAmount) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.gearId = gearId;
        this.subAmount = subAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getGearId() {
        return gearId;
    }

    public void setGearId(int gearId) {
        this.gearId = gearId;
    }

    public int getSubAmount() {
        return subAmount;
    }

    public void setSubAmount(int subAmount) {
        this.subAmount = subAmount;
    }

    public Gear getGear() {
        return gear;
    }

    public void setGear(Gear gear) {
        this.gear = gear;
    }

}
