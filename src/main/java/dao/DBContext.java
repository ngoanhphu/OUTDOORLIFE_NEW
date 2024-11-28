package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//123


public class DBContext {
     private final String serverName = "localhost";
    private final String dbName = "OJT";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "123";

    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }
   

    public void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
    public static void main(String[] args) throws Exception {
       try {
            DBContext dbContext = new DBContext();
            Connection conn = dbContext.getConnection();
            if (conn != null) {
                System.out.println("Connection successful");
            } else {
                System.out.println("Failed to connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}


