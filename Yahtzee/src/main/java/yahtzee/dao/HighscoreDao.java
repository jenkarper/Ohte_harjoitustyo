package yahtzee.dao;

import java.util.List;
import yahtzee.domain.User;

/**
 * Defines database methods for Highscore table.
 * @author pertjenn
 */
public interface HighscoreDao {
    
    void addHighscore(User user);
    
    List<String> getTopTen();
    
}
