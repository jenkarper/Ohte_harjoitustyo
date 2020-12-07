package yahtzee.dao;

import java.util.List;
import yahtzee.domain.User;

/**
 * Defines database methods for Highscore table.
 * @author pertjenn
 */
public interface HighscoreDao {
    
    //Connection connect() throws Exception;
    
    void addHighscore(User user) throws Exception;
    
    List<String> getTopTen() throws Exception;
    
}
