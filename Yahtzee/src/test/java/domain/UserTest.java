package domain;

import org.junit.Test;
import static org.junit.Assert.*;
import yahtzee.domain.User;

/**
 *
 * @author pertjenn
 */
public class UserTest {
    private final User userOneParam;
    private final User userManyParams;
    
    public UserTest() {
        this.userOneParam = new User("username");
        this.userManyParams = new User("username", 1, 1, 1);
        
    }

    @Test
    public void newUserWithOneParameterInitialisesCorrectly() {
        String result = userOneParam.getUsername() + userOneParam.getHighScore() + userOneParam.getLowScore() + userOneParam.getGamesPlayed();
        
        assertEquals("username000", result);
    }
    
    @Test
    public void newUserWithManyParametersInitialisesCorrectly() {
        String result = userManyParams.getUsername() + userManyParams.getHighScore() + userManyParams.getLowScore() + userManyParams.getGamesPlayed();
        
        assertEquals("username111", result);
    }
    @Test
    public void playIncreasesGamesPlayed() {
        userOneParam.play();
        
        assertTrue(userOneParam.getGamesPlayed()==1);
    }
    
    @Test
    public void stringRepresentationIsCorrect() {
        String expected = "username, 0, 0, 0";
        
        assertEquals(expected, userOneParam.toString());
    }
}
