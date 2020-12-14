package dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import yahtzee.dao.UserDao;
import yahtzee.domain.Game;
import yahtzee.domain.User;

/**
 *
 * @author pertjenn
 */
public class UserDaoDbTest {
    private final Game game;
    private final UserDao db;
    private User user;
    
    public UserDaoDbTest() throws Exception {
        this.game = new Game("jdbc:sqlite:yahtzeeTest.db");
        this.db = game.getUserDb();
    }
    
    @Before
    public void setUp() throws Exception {
        this.user = new User("testUser", 200, 100, 2);
        game.setUser(user);
        game.insertUser(user);
    }
    
    @After
    public void tearDown() throws Exception {
        db.deleteUser(user);
    }

    @Test
    public void duplicateUsernameRecognised() throws Exception {
        assertFalse(game.validateUsername("testUser"));
    }
    
    @Test
    public void userFoundByUsername() throws Exception {
        User u = db.findUser(user.getUsername());
        String expected = "testUser, 200, 100, 2";
        assertEquals(expected, u.toString());
    }
    
    @Test
    public void userUpdatedCorrectly() throws Exception {
        game.updateUserStats(250);
        db.updateUser(user);
        
        String expected = "testUser, 250, 100, 3";
        User u = db.findUser("testUser");
        assertEquals(expected, u.toString());
    }
    
    @Test
    public void userDeletedCorrecty() throws Exception {
        User toBeDeleted = new User("testUser2");
        db.addUser(toBeDeleted);
        db.deleteUser(toBeDeleted);
        assertEquals("", db.findUser("testUser2").getUsername());
    }
}
