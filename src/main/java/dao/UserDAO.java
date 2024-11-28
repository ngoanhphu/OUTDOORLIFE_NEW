package dao;

import java.util.List;
import model.*;

public interface UserDAO {

    boolean insertGuest(String firstName, String lastName, String email, String phoneNumber, String password);

    boolean sendEmail(User user);

    boolean emailExists(String email) throws Exception;

    User findByEmail(String email);

    boolean updateInfo(User user) throws Exception;

    boolean updatePassword(String email, String newPassword) throws Exception;

    boolean changePassword(String email, String currentPassword, String newPassword) throws Exception;

    int getTotalItem();

    List<User> getAllAccount(int page, int size);

    void insertStaff(User u);
    
    void deleteStaff(String id);
}
