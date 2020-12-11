package yahtzee.dao;

import yahtzee.domain.User;

/**
 * Defines database methods for User table
 * @author pertjenn
 */
public interface UserDao {
    
    void addUser(User user) throws Exception;
    
    User findUser(String username) throws Exception;
    
    void updateUser(User user) throws Exception;
    
    void deleteUser(User user) throws Exception;
    
    int getUserPK(User user) throws Exception;
}
