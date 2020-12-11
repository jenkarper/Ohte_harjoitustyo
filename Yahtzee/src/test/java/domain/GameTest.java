package domain;

import org.junit.Test;
import static org.junit.Assert.*;

import yahtzee.domain.Game;
import yahtzee.domain.User;

/**
 *
 * @author pertjenn
 */
public class GameTest {
    private final Game game;
    
    public GameTest() throws Exception {
        this.game = new Game();
    }
    
    @Test
    public void newGameExists() {
        assertTrue(game!=null);
    }
    
    @Test
    public void rollReturnsDieArray() {
        assertTrue(game.roll() instanceof int[]);
    }
    
    @Test
    public void rollDecreasesRollCounter() {
        game.roll();
        assertTrue(game.getRollCounter()==2);
    }
    
    @Test
    public void scoreDecreasesRoundCounter() {
        int[] dice = new int[]{4, 4, 4, 5, 5};
        game.scoreRoll(4, dice);
        game.scoreRoll(5, dice);
        game.scoreRoll(13, dice);
        assertTrue(game.getRoundCounter()==12);
    }
    
    @Test
    public void availableSlotScoredCorrectly() {
        int[] dice = new int[]{4, 4, 4, 5, 5};
        assertTrue(game.scoreRoll(4, dice));
    }
    
    @Test
    public void usedSlotNotScoredAgain() {
        int[] dice = new int[]{4, 4, 4, 5, 5};
        game.scoreRoll(4, dice);
        int[] newDice = new int[]{4, 4, 4, 4, 4};
        assertTrue(!game.scoreRoll(4, dice));
    }
    
    @Test
    public void failedScoringDoesNotChangePoints() {
        int[] dice = new int[]{5, 3, 6, 5, 1};
        game.scoreRoll(5, dice);
        int[] newDice = new int[]{5, 5, 5, 5, 2};
        game.scoreRoll(5, newDice);
        assertTrue(game.getScorecard().getPoints()[5] == 10);
    }
    
    @Test
    public void dieIsHeldCorrectly() {
        game.setHoldStatus(0, true);
        assertTrue(game.checkHoldStatus(0));
    }
    
    @Test
    public void dieIsReleasedCorrectly() {
        game.setHoldStatus(0, true);
        game.roll();
        game.setHoldStatus(0, false);
        assertTrue(!game.checkHoldStatus(0));
    }
    
    @Test
    public void heldDieIsNotRolled() {
        game.setHoldStatus(0, true);
        boolean held = true;
        for (int i = 0; i < 1000; i++) {
            game.roll();
            if (game.getRoll().getDice()[0].getValue()!=0) {
                held = false;
            }
        }
        assertTrue(held);
    }
    
    @Test
    public void checkFindsHeldDice() {
        game.setHoldStatus(0, true);
        assertTrue(game.checkHoldStatus(0));
    }
    
    @Test
    public void checkReturnsCorrectScore() {
        int[] dice = new int[]{4, 4, 4, 5, 5};
        game.scoreRoll(4, dice);
        assertTrue(game.checkScore(4, dice)==12);
    }
    
    @Test
    public void releaseAllWorksCorrectly() {
        game.setHoldStatus(0, true);
        game.setHoldStatus(3, true);
        game.releaseAll();
        boolean released = true;
        
        for (int i = 0; i < 5; i++) {
            if (game.checkHoldStatus(i)) {
                released = false;
            }
        }
        assertTrue(released);
    }
    
    @Test
    public void rollCounterResetsCorrectly() {
        game.roll();
        game.roll();
        game.resetRollCounter();
        assertTrue(game.getRollCounter()==3);
    }
    
    @Test
    public void roundCounterResetsCorrectly() {
        int[] dice = new int[]{4, 4, 4, 5, 5};
        game.scoreRoll(4, dice);
        game.scoreRoll(5, dice);
        game.scoreRoll(13, dice);
        game.resetRoundCounter();
        assertTrue(game.getRoundCounter()==15);
    }
    
    @Test
    public void gameResetsCorreclty() {
        game.setHoldStatus(0, true);
        int[] dice = game.roll();
        game.scoreRoll(1, dice);
        game.reset();
        assertTrue((game.getRollCounter()==3) && (game.getRoundCounter()==15) && !game.checkHoldStatus(0) && (game.getScorecard().getPoints()[1]==-1));
    }
    
    @Test
    public void userStatsUpdateCorrectlyAfterOneGame() {
        User u = new User("testUser");
        game.setUser(u);
        game.updateUserStats(50);
        
        String expected = "testUser, 50, 50, 1";
        assertEquals(expected, u.toString());
    }
    
    @Test
    public void newHighScoreDoesNotUpdateLowScore() {
        User u = new User("testUser");
        game.setUser(u);
        game.updateUserStats(50);
        game.updateUserStats(100);
        
        String expected = "testUser, 100, 50, 2";
        assertEquals(expected, u.toString());
    }
    
    @Test
    public void newLowScoreDoesNotUpdateHighScore() {
        User u = new User("testUser");
        game.setUser(u);
        game.updateUserStats(50);
        game.updateUserStats(25);
        
        String expected = "testUser, 50, 25, 2";
        assertEquals(expected, u.toString());
    }
}
