package dao;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import yahtzee.dao.HighscoreDao;
import yahtzee.dao.UserDao;
import yahtzee.domain.Game;
import yahtzee.domain.User;

/**
 *
 * @author pertjenn
 */
public class HighscoreDaoDbTest {
    
    private final Game game;
    private final HighscoreDao db;
    private final UserDao userDb;
    private final User fixedUser;
    
    public HighscoreDaoDbTest() {
        this.game = new Game("jdbc:sqlite:yahtzeeTest.db");
        this.db = game.getHighscoreDb();
        this.userDb = game.getUserDb();
        this.fixedUser = new User("fixedUser", 200, 100, 2);
        
        userDb.addUser(fixedUser);
        db.addHighscore(fixedUser);
    }
    
    @Test
    public void highscoreAddedCorrectly() {
        List<String> list = db.getTopTen();
        String expected = "fixedUser\t200";
        assertEquals(expected, list.get(0));
    }
    
    @Test
    public void listOrderedCorrectly() {
        User u = new User("newTopScore", 400, 300, 4);
        userDb.addUser(u);
        db.addHighscore(u);
        List<String> list = db.getTopTen();
        
        String expected = "newTopScore\t400";
        assertEquals(expected, list.get(0));
        db.deleteHighscore(u);
    }
    
    @Test
    public void highscoreDeletedCorrectly() {
        User u = new User("toBeDeleted", 500, 300, 4);
        userDb.addUser(u);
        db.addHighscore(u);
        System.out.println(userDb.getUserPK(u));
        db.deleteHighscore(u);
        List<String> list = db.getTopTen();
        
        String expected = "fixedUser\t200";
        assertEquals(expected, list.get(0));
    }
}
