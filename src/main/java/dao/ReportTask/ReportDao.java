package dao.ReportTask;

import dao.DBContext;
import model.ReportTask.Report;
import model.ReportTask.ReportLocation;
import model.ReportTask.ReportOwner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDao extends DBContext {

    public int insertReportDetail(String reportType, boolean fraudulent, boolean malicious,
                                  boolean scam, boolean racist, boolean environment,
                                  boolean security, boolean quality, String another1, String another2, String another3) {
        int generatedId = -1;
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO REPORT_Detail (report_type, fraudulent, malicious, scam, racist, environment, security, quality, another1, another2, another3) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, reportType);
                stmt.setBoolean(2, fraudulent);
                stmt.setBoolean(3, malicious);
                stmt.setBoolean(4, scam);
                stmt.setBoolean(5, racist);
                stmt.setBoolean(6, environment);
                stmt.setBoolean(7, security);
                stmt.setBoolean(8, quality);
                stmt.setString (9, another1);
                stmt.setString (10, another2);
                stmt.setString (11, another3);

                stmt.executeUpdate();

                // Lấy report_detail_ID mới tạo
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    } else {
                        System.err.println("No generated key returned");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    public void insertReportLocation(ReportLocation r, int reporterId, int campsiteId, int reportDetailId) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO REPORT_LOCATION (reporter, Campsite_id, report_detail_id, report_date, description) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, reporterId);
                stmt.setInt(2, campsiteId);
                stmt.setInt(3, reportDetailId);
                stmt.setTimestamp(4, r.getReportDate());
                stmt.setString(5, r.getDescription());


                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void insertReportOwner(ReportOwner r, int reporterId, int ownerId, int reportDetailId) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO REPORT_Owner (reporter, owner_id, report_detail_id, report_date, description) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, reporterId);
                stmt.setInt(2, ownerId);
                stmt.setInt(3, reportDetailId);
                stmt.setTimestamp(4, r.getReportDate());
                stmt.setString(5, r.getDescription());


                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ReportLocation> getAllReportsLocation() throws SQLException {
        List<ReportLocation> reports = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM REPORT_LOCATION";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                ReportLocation reportLocation = new ReportLocation();
                        reportLocation.setId(rs.getInt("report_id"));
                        reportLocation.setCampsiteId(rs.getInt("Campsite_id"));
                        reportLocation.setDescription(rs.getString("description"));
                        reportLocation.setReportDate(rs.getTimestamp("report_date"));
                        reportLocation.setReportDetailId(rs.getInt("report_detail_id"));
                        reportLocation.setReporter(rs.getInt("reporter"));
                        reportLocation.setStatus(rs.getString("status"));
                reports.add(reportLocation);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return reports;
    }
    public List<ReportOwner> getAllOwnerReports() throws SQLException {
        List<ReportOwner> reports = new ArrayList<>();
        try (Connection conn = getConnection()) {
            String query = "SELECT * FROM REPORT_OWNER";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                ReportOwner reportOwner = new ReportOwner();
                reportOwner.setId(rs.getInt("report_id"));
                reportOwner.setOwnerId(rs.getInt("owner_id"));
                reportOwner.setDescription(rs.getString("description"));
                reportOwner.setReportDate(rs.getTimestamp("report_date"));
                reportOwner.setReportDetailId(rs.getInt("report_detail_id"));
                reportOwner.setReporter(rs.getInt("reporter"));
                reportOwner.setStatus(rs.getString("status"));
                reports.add(reportOwner);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return reports;
    }
    public List<Report> getAllPendingReports() throws SQLException {
        List<Report> reportListPending = new ArrayList<>();

        // Lấy báo cáo từ ReportLocation
        List<ReportLocation> locationReports = this.getAllReportsLocation();
        for (ReportLocation location : locationReports) {
            if ("Pending".equals(location.getStatus())) {
                reportListPending.add(location);
            }
        }

        // Lấy báo cáo từ ReportOwner
        List<ReportOwner> ownerReports = this.getAllOwnerReports();
        for (ReportOwner owner : ownerReports) {
            if ("Pending".equals(owner.getStatus())) {
                reportListPending.add(owner);
            }
        }

        return reportListPending;
    }
    public List<Report> getAllCompletedReports() throws SQLException {
        List<Report> reportListCompleted = new ArrayList<>();

        // Lấy báo cáo từ ReportLocation
        List<ReportLocation> locationReports = this.getAllReportsLocation();
        for (ReportLocation location : locationReports) {
            if ("Completed".equals(location.getStatus())) {
                reportListCompleted.add(location);
            }
        }

        // Lấy báo cáo từ ReportOwner
        List<ReportOwner> ownerReports = this.getAllOwnerReports();
        for (ReportOwner owner : ownerReports) {
            if ("Completed".equals(owner.getStatus())) {
                reportListCompleted.add(owner);
            }
        }

        return reportListCompleted;
    }

    public List<Report> getAllReports() throws SQLException {
        List<Report> reportListAll = new ArrayList<>();

        // Lấy báo cáo từ ReportLocation
        List<ReportLocation> locationReports = this.getAllReportsLocation();
        for (ReportLocation location : locationReports) {
                reportListAll.add(location);
        }

        // Lấy báo cáo từ ReportOwner
        List<ReportOwner> ownerReports = this.getAllOwnerReports();
        for (ReportOwner owner : ownerReports) {
                reportListAll.add(owner);

        }

        return reportListAll;
    }

    public List<String> getReportDetailColumns(int reportDetailId) throws SQLException {
        List<String> columns = new ArrayList<>();
        String sql = "SELECT " +
                "CASE WHEN fraudulent = 1 THEN 'Fraudulent' ELSE NULL END AS Fraudulent, " +
                "CASE WHEN malicious = 1 THEN 'Malicious' ELSE NULL END AS Malicious, " +
                "CASE WHEN scam = 1 THEN 'Scam' ELSE NULL END AS Scam, " +
                "CASE WHEN racist = 1 THEN 'Racist' ELSE NULL END AS Racist, " +
                "CASE WHEN environment = 1 THEN 'Environment' ELSE NULL END AS Environment, " +
                "CASE WHEN security = 1 THEN 'Security' ELSE NULL END AS Security, " +
                "CASE WHEN quality = 1 THEN 'Quality' ELSE NULL END AS Quality, " +
                "another1, another2, another3 " +
                "FROM REPORT_Detail WHERE report_detail_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reportDetailId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Thêm tên cột có giá trị không null vào danh sách
                    if (rs.getString("Fraudulent") != null) columns.add("Fraudulent");
                    if (rs.getString("Malicious") != null) columns.add("Malicious");
                    if (rs.getString("Scam") != null) columns.add("Scam");
                    if (rs.getString("Racist") != null) columns.add("Racist");
                    if (rs.getString("Environment") != null) columns.add("Environment");
                    if (rs.getString("Security") != null) columns.add("Security");
                    if (rs.getString("Quality") != null) columns.add("Quality");

                    // Kiểm tra các cột another
                    if (rs.getString("another1") != null && !rs.getString("another1").isEmpty())
                        columns.add(rs.getString("another1"));
                    if (rs.getString("another2") != null && !rs.getString("another2").isEmpty())
                        columns.add(rs.getString("another2"));
                    if (rs.getString("another3") != null && !rs.getString("another3").isEmpty())
                        columns.add(rs.getString("another3"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return columns;
    }




}
