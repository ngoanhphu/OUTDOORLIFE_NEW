package dao;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

public class UserDaoImpl extends DBContext implements UserDAO {

    // Method to hash password using SHA-256
    private String hashPassword(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(password.getBytes());

        // Convert byte array to hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    @Override
    public User findByEmail(String email) {
        String query = "SELECT * FROM ACCOUNT WHERE Gmail = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("Account_id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setEmail(email);
                    user.setPhoneNumber(rs.getString("phone_number"));
                    user.setPasswordHash(rs.getString("passwordHash"));
                    user.setAdmin(rs.getBoolean("isAdmin"));
                    user.setOwner(rs.getBoolean("isOwner"));
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertGuest(String firstName, String lastName, String email, String phoneNumber, String password,boolean isOwner) {
        try {
            // Get connection
            Connection conn = getConnection();
            System.out.println(conn);
            // Hash the password using SHA-256
            String hashedPassword = hashPassword(password);

            // Prepare SQL statement
            String sql = "INSERT INTO ACCOUNT (first_name, last_name,  Gmail, phone_number, passwordHash,isOwner) VALUES (?, ?, ?, ?, ?,?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, email);
                pstmt.setString(4, phoneNumber);
                pstmt.setString(5, hashedPassword);
                pstmt.setBoolean(6, isOwner);

                System.out.println(firstName + " " + lastName + " " + email + " " + phoneNumber + " " + hashedPassword);

                // Execute the insert statement
                int rowsAffected = pstmt.executeUpdate();

                // Check if the insertion was successful
                return rowsAffected > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getRandom() {
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }

    public boolean sendEmail(User user) {
        boolean test = false;
        String toEmail = user.getEmail();
        String fromEmail = "vietnqde170722@fpt.edu.vn";
        String passwordEmail = "enyujdkngfzylzkj";
        try {
            Properties pr = new Properties();
            pr.put("mail.smtp.host", "smtp.gmail.com");
            pr.put("mail.smtp.port", "587");
            pr.put("mail.smtp.auth", "true");
            pr.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, passwordEmail);
                }
            });

            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mess.setSubject("Verify Email Register Account");
            mess.setText("Register Successfully!! Please verify your account using this code: " + user.getCode());

            Transport.send(mess);
            test = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }

    public boolean emailExists(String email) throws Exception {
        boolean exists = false;
        String query = "SELECT 1 FROM ACCOUNT WHERE Gmail = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                exists = rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public boolean updateInfo(User user) throws Exception {
        String sql = "UPDATE ACCOUNT SET first_name = ?, last_name = ?, phone_number = ?, Gmail = ? WHERE Account_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPasswordHash());
            ps.setInt(5, user.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updatePassword(String email, String newPassword) throws Exception {
        String hashedNewPassword = hashPassword(newPassword);
        String sql = "UPDATE ACCOUNT SET passwordHash = ? WHERE Gmail = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, hashedNewPassword);
            stmt.setString(2, email);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean changePassword(String email, String currentPassword, String newPassword) {
        User user = findByEmail(email);
        if (user != null) {
            try {
                String hashedCurrentPassword = hashPassword(currentPassword);
                if (user.getPasswordHash().equals(hashedCurrentPassword)) {
                    return updatePassword(email, newPassword);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public int getTotalItem() {
        try {
            String sql = "SELECT COUNT(*) AS total_items \n"
                    + "FROM [ACCOUNT] \n";
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("total_items");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<User> getAllAccount() {
        List<User> accounts = new ArrayList<>();
        String query = "SELECT * FROM ACCOUNT WHERE deactivated IS NULL OR deactivated = 0";

        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("Account_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("Gmail"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setPasswordHash(rs.getString("passwordHash"));
                user.setAdmin(rs.getBoolean("isAdmin"));
                user.setOwner(rs.getBoolean("isOwner"));
                accounts.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    @Override
    public void insertStaff(User u) {
        String query = "INSERT INTO [ACCOUNT] \n"
                + "              VALUES (?,?,?,?,0,0,?,1)";
        try {
            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement(query);
            //default
            String passwordHashed = hashPassword("123");

            ps.setString(1, u.getFirstName());
            ps.setString(2, u.getLastName());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getPhoneNumber());
            ps.setString(5, passwordHashed);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteStaff(String id) {
        String query = "DELETE FROM ACCOUNT WHERE Account_id = ?";

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean updateAccount(User user) {
        String sql = "UPDATE ACCOUNT SET first_name = ?, last_name = ?, Gmail = ?, phone_number = ?, passwordHash = ?, isAdmin = ?, isOwner = ? WHERE Account_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhoneNumber());
            ps.setString(5, user.getPasswordHash());
            ps.setBoolean(6, user.isAdmin());
            ps.setBoolean(7, user.isOwner());
            ps.setInt(8, user.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> searchAccounts(String query) {
        List<User> accounts = new ArrayList<>();
        String sql = "SELECT * FROM ACCOUNT WHERE (Account_id LIKE ? OR Gmail LIKE ? OR phone_number LIKE ?) AND (deactivated IS NULL OR deactivated = 0)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            String searchPattern = "%" + query + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("Account_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("Gmail"),
                        rs.getString("phone_number"),
                        rs.getString("passwordHash"),
                        rs.getBoolean("isAdmin"),
                        rs.getBoolean("isOwner")
                );
                accounts.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    public List<User> searchDeactivatedAccounts(String query) {
        List<User> accounts = new ArrayList<>();
        String sql = "SELECT * FROM ACCOUNT WHERE (Account_id LIKE ? OR Gmail LIKE ? OR phone_number LIKE ?) AND deactivated = 1";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            String searchPattern = "%" + query + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("Account_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("Gmail"),
                        rs.getString("phone_number"),
                        rs.getString("passwordHash"),
                        rs.getBoolean("isAdmin"),
                        rs.getBoolean("isOwner")
                );
                accounts.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    public boolean deactivateAccount(int accountId) {
        String sql = "UPDATE ACCOUNT SET deactivated = 1 WHERE Account_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean reactivateAccount(int accountId) {
        String sql = "UPDATE ACCOUNT SET deactivated = NULL WHERE Account_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getDeactivatedAccounts() {
        List<User> accounts = new ArrayList<>();
        String query = "SELECT * FROM ACCOUNT WHERE deactivated = 1";

        try (Connection con = getConnection(); PreparedStatement pst = con.prepareStatement(query); ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("Account_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("Gmail"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setPasswordHash(rs.getString("passwordHash"));
                user.setAdmin(rs.getBoolean("isAdmin"));
                user.setOwner(rs.getBoolean("isOwner"));
                accounts.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    public boolean isAccountDeactivated(int accountId) {
        String sql = "SELECT deactivated FROM [ACCOUNT] WHERE Account_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("deactivated") == 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public User findById(int id) {
        String query = "SELECT * FROM ACCOUNT WHERE Account_id = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("Account_id"));
                    user.setFirstName(rs.getString("first_name"));
                    user.setLastName(rs.getString("last_name"));
                    user.setEmail(rs.getString("Gmail"));
                    user.setPhoneNumber(rs.getString("phone_number"));
                    user.setPasswordHash(rs.getString("passwordHash"));
                    user.setAdmin(rs.getBoolean("isAdmin"));
                    user.setOwner(rs.getBoolean("isOwner"));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}