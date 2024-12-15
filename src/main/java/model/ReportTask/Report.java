package model.ReportTask;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public interface Report {
    int getId();
    String getDescription();
    Timestamp getReportDate();
    String getStatus();
    int getReporter();
    int getReportDetailId();
    void setDetailAttributes(List<String> detailAttributes);
    List<String> getDetailAttributes();

}
