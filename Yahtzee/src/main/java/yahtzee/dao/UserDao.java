package yahtzee.dao;

import yahtzee.domain.User;

/**
 * Defines database methods for User table
 * @author pertjenn
 */
public interface UserDao {
    
    void addUser(User user);
    
    User findUser(String username);
    
    void updateUser(User user);
    
    void deleteUser(User user);
    
    int getUserPK(User user);
}
