package dao;

import java.util.List;
import model.User;

public interface UserDAO {

    boolean insertGuest(String firstName, String lastName, String email, String phoneNumber, String password, boolean isOwner);

    boolean sendEmail(User user);

    boolean emailExists(String email) throws Exception;

    User findByEmail(String email);

    boolean updateInfo(User user) throws Exception;

    boolean updatePassword(String email, String newPassword) throws Exception;

    boolean changePassword(String email, String currentPassword, String newPassword) throws Exception;

    int getTotalItem();

    List<User> getAllAccount();

    void insertStaff(User u);

    void deleteStaff(String id);

    boolean updateAccount(User user);

    List<User> searchAccounts(String query);
}
