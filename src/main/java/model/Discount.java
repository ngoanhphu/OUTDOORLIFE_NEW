package model;

import java.util.Date;

public class Discount {
    private int code;
    private Double percentDiscount; // Sử dụng Double để cho phép null
    private Double amountDiscount; // Sử dụng Double để cho phép null
    private Date expiryDate;
    private int usageCount;

    public Discount() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Double getPercentDiscount() {
        return percentDiscount;
    }

    public void setPercentDiscount(Double percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    public Double getAmountDiscount() {
        return amountDiscount;
    }

    public void setAmountDiscount(Double amountDiscount) {
        this.amountDiscount = amountDiscount;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(int usageCount) {
        this.usageCount = usageCount;
    }
}
