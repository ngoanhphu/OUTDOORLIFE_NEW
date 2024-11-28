package model;

import java.sql.Date;

public class OwnerContract {
    private int contractId;
    private int accountId;
    private Date registration;
    private String status;
    private Date startDate;
    private Date endDate;
    private String notes;
    private int ownerId;

    public OwnerContract(Date registration, String status, Date startDate, Date endDate, String notes) {
        this.registration = registration;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
    }

    public OwnerContract() {
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
