package dao;

import java.util.List;
import model.User;

public interface UserDAO {

    /**
     * Insert a guest account into the database.
     * @param firstName User's first name
     * @param lastName User's last name
     * @param email User's email
     * @param phoneNumber User's phone number
     * @param password User's raw password
     * @param isOwner Whether the user is an owner or not
     * @return true if insertion is successful, otherwise false
     */
    boolean insertGuest(String firstName, String lastName, String email, String phoneNumber, String password, boolean isOwner);

    /**
     * Send a verification email to the user.
     * @param user User object containing email and other details
     * @return true if email is sent successfully, otherwise false
     */
    boolean sendEmail(User user);

    /**
     * Check if an email already exists in the database.
     * @param email User's email
     * @return true if email exists, otherwise false
     * @throws Exception in case of SQL or connection issues
     */
    boolean emailExists(String email) throws Exception;

    /**
     * Find a user by their email address.
     * @param email User's email
     * @return User object if found, otherwise null
     */
    User findByEmail(String email);

    /**
     * Update user information.
     * @param user User object containing updated information
     * @return true if update is successful, otherwise false
     * @throws Exception in case of SQL or connection issues
     */
    boolean updateInfo(User user) throws Exception;

    /**
     * Update a user's password.
     * @param email User's email
     * @param newPassword New raw password
     * @return true if update is successful, otherwise false
     * @throws Exception in case of SQL or connection issues
     */
    boolean updatePassword(String email, String newPassword) throws Exception;

    /**
     * Change the user's password after verifying the current password.
     * @param email User's email
     * @param currentPassword User's current password
     * @param newPassword New raw password
     * @return true if password is changed successfully, otherwise false
     * @throws Exception in case of SQL or connection issues
     */
    boolean changePassword(String email, String currentPassword, String newPassword) throws Exception;

    /**
     * Get the total number of user accounts.
     * @return Total number of accounts
     */
    int getTotalItem();

    /**
     * Get a paginated list of user accounts.
     * @param page Current page number
     * @param size Number of accounts per page
     * @return List of User objects
     */
    List<User> getAllAccount(int page, int size);

    /**
     * Insert a staff account with default admin role.
     * @param u User object containing staff details
     */
    void insertStaff(User u);

    /**
     * Delete a staff account by ID.
     * @param id ID of the staff account to delete
     */
    void deleteStaff(String id);
}
