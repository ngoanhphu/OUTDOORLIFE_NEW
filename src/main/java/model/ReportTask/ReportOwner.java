package model.ReportTask;

import java.sql.Timestamp;
import java.util.List;


public class ReportOwner implements Report {
    private int id;
    private int reporter;
    private int ownerId;
    private Timestamp reportDate;
    private String description;
    private int reportDetailId;
    private String status;
    private List<String> detailAttributes;

    public ReportOwner() {
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
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
    @Override
    public Timestamp getReportDate() {
        return reportDate;
    }

    public void setReportDate(Timestamp reportDate) {
        this.reportDate = reportDate;
    }
    @Override
    public int getReportDetailId() {
        return reportDetailId;
    }

    public void setReportDetailId(int reportDetailId) {
        this.reportDetailId = reportDetailId;
    }
    @Override
    public int getReporter() {
        return reporter;
    }

    public void setReporter(int reporter) {
        this.reporter = reporter;
    }
    @Override
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public void setDetailAttributes(List<String> detailAttributes) {
        this.detailAttributes = detailAttributes;
    }

    @Override
    public List<String> getDetailAttributes() {
        return this.detailAttributes;
    }
}
