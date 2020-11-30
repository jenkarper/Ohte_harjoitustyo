package domain;

import org.junit.Test;
import static org.junit.Assert.*;

import yahtzee.domain.Game;

/**
 *
 * @author pertjenn
 */
public class GameTest {
    private final Game game;
    
    public GameTest() {
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
    public void heldDieIsNotRolled() {
        game.holdDie(0);
        boolean held = true;
        for (int i = 0; i < 1000; i++) {
            game.roll();
            if (game.getRoll().getDice()[0].getValue()!=0) {
                held = false;
            }
        }
        assertTrue(held);
    }
}
