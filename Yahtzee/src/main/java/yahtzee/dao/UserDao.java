package yahtzee.dao;

import yahtzee.domain.User;

/**
 *
 * @author pertjenn
 */
public interface UserDao {
    
    //Connection connect() throws Exception;
    
    //void createTables() throws Exception;
    
    void addUser(User user) throws Exception;
    
    User findUser(String username) throws Exception;
    
    void updateUser(User user) throws Exception;
}
