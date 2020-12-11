package dao;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
    
    private Game game;
    private User user;
    private HighscoreDao db;
    private UserDao userDb;
    
    public HighscoreDaoDbTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        
        this.game = new Game("jdbc:sqlite:yahtzeeTest.db");
        this.user = new User("testUser", 200, 100, 2);
        game.setUser(user);
        game.insertUser(user);
        
        this.db = game.getHighscoreDatabase();
        this.userDb = game.getUserDatabase();
    }
    
    @After
    public void tearDown() throws Exception {
        userDb.deleteUser(user);
    }

    @Test
    public void highscoreAddedCorrectly() throws Exception {
        db.addHighscore(user);
        List<String> list = db.getTopTen();
        String expected = "testUser\t200";
        
        assertEquals(expected, list.get(0));
    }
}
