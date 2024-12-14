package model.ReportTask;

import java.util.Date;

public class ReportLocation {
    private int reporter;
    private Integer campsiteId;
    private Date reportDate;
    private String description;
    private String status;
    private int reportDetailId;

    public ReportLocation() {
    }

    public ReportLocation(Integer campsiteId, String description, Date reportDate, int reportDetailId, int reporter, String status) {
        this.campsiteId = campsiteId;
        this.description = description;
        this.reportDate = reportDate;
        this.reportDetailId = reportDetailId;
        this.reporter = reporter;
        this.status = status;
    }

    public Integer getCampsiteId() {
        return campsiteId;
    }

    public void setCampsiteId(Integer campsiteId) {
        this.campsiteId = campsiteId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
