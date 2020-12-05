package yahtzee.dao;

import java.sql.Connection;
import java.util.List;
import yahtzee.domain.User;
import yahtzee.domain.Highscore;

/**
 *
 * @author pertjenn
 */
public interface HighscoreDao {
    
    //Connection connect() throws Exception;
    
    void addHighscore(User user) throws Exception;
    
    List<String> getTopTen() throws Exception;
    
}
