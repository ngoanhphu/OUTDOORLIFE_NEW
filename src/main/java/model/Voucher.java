/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Voucher {
    private int id;
    private String code;
    private int percent;
    private Date startDate;
    private Date endDate;
    private boolean isUsed;

    public Voucher() {
    }

    public Voucher(int id, String code, int percent, Date startDate, Date endDate, boolean isUsed) {
        this.id = id;
        this.code = code;
        this.percent = percent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isUsed = isUsed;
    }

    public Voucher(String code, int percent, Date startDate, Date endDate, boolean isUsed) {
        this.code = code;
        this.percent = percent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isUsed = isUsed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
    
}
