package domain;

import org.junit.Test;
import static org.junit.Assert.*;
import yahtzee.domain.Die;
import yahtzee.domain.Roll;

/**
 *
 * @author pertjenn
 */
public class RollTest {
    private Roll roll;
    
    public RollTest() {
        this.roll = new Roll();
    }
    
    @Test
    public void newRollExists() {
        assertTrue(roll!=null);
    }
    
    @Test
    public void newRollHasFiveDice() {
        assertTrue(roll.getDice().length == 5);
    }
    
    @Test
    public void newRollHasFiveValues() {
        assertTrue(roll.getValues().length == 5);
    }
    
    @Test
    public void newRollDiceHaveValueZero() {
        boolean allZeroes = true;
        for (Die d : roll.getDice()) {
            if (d.getValue()!=0) {
                allZeroes = false;
            }
        }
        assertTrue(allZeroes);
    }
    
    @Test
    public void rollChangesAllDieValues() {
        roll.roll();
        boolean allZeroes = true;
        for (Die d : roll.getDice()) {
            if (d.getValue()!=0) {
                allZeroes = false;
            }
        }
        assertTrue(!allZeroes);
    }
    
    @Test
    public void rollDoesNotChangeHeldValues() {
        roll.getDice()[0].setHold(true);
        roll.getDice()[4].setHold(true);
        boolean changed = false;
        for (int i = 0; i < 1000; i++) {
            roll.roll();
            if ((roll.getDice()[0].getValue()!=0) || (roll.getDice()[4].getValue()!=0)) {
                changed = true;
            }
        }
        assertTrue(!changed);
    }
    
    @Test
    public void releaseAllReleasesEveryDice() {
        for (Die d : roll.getDice()) {
            d.setHold(true);
        }
        roll.releaseAll();
        boolean held = false;
        for (Die d : roll.getDice()) {
            if (d.isHold()) {
                held = true;
            }
        }
        assertTrue(!held);
    }
    
    @Test
    public void stringRepresentationIsCorrectWhenDiceAreHeld() {
        roll.getDice()[0].setTestValue(1);
        roll.getDice()[0].setHold(true);
        roll.getDice()[1].setTestValue(2);
        roll.getDice()[2].setTestValue(3);
        roll.getDice()[3].setTestValue(4);
        roll.getDice()[4].setTestValue(5);
        roll.getDice()[4].setHold(true);
        
        assertEquals("1 5 | 2 3 4 ", roll.toString());
    }
    
    @Test
    public void stringRepresentationIsCorrectWhenDiceAreNotHeld() {
        roll.getDice()[0].setTestValue(1);
        roll.getDice()[1].setTestValue(2);
        roll.getDice()[2].setTestValue(3);
        roll.getDice()[3].setTestValue(4);
        roll.getDice()[4].setTestValue(5);
        
        assertEquals("1 2 3 4 5 ", roll.toString());
    }
}
