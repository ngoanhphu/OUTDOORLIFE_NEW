package model.ReportTask;

import java.util.Date;

public class ReportOwner {
    private int reportId;
    private int reporter;
    private int ownerId;
    private Date reportDate;
    private String description;
    private int reportDetailId;
    private String status;

    public ReportOwner() {
    }

    public ReportOwner(String description, int ownerId, Date reportDate, int reportDetailId, int reporter, int reportId, String status) {
        this.description = description;
        this.ownerId = ownerId;
        this.reportDate = reportDate;
        this.reportDetailId = reportDetailId;
        this.reporter = reporter;
        this.reportId = reportId;
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public int getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(int reportDetailId) {
        this.reportDetailId = reportDetailId;
    }

    public int getReporter() {
        return reporter;
    }

    public void setReporter(int reporter) {
        this.reporter = reporter;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
