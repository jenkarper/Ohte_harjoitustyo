package domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import yahtzee.domain.Scorecard;

/**
 *
 * @author pertjenn
 */
public class ScorecardTest {
    private Scorecard sc;
    
    public ScorecardTest() {
        this.sc = new Scorecard();
    }
    
    @Test
    public void newScorecardExists() {
        assertTrue(sc!=null);
    }
    
    @Test
    public void pointsInitialisedToNeg() {
        boolean correct = true;
        for (int i = 1; i < sc.getPoints().length; i++) {
            if (sc.getPoints()[i] >= 0) {
                correct = false;
            }
        }
        
        assertTrue(correct);
    }
    
    @Test
    public void categoriesInitialisedToSlotNames() {
        assertEquals("Kolmiluku", sc.getCategories()[9]);
    }
    
    @Test
    public void pointsAreScoredCorrectly() {
        sc.markScore(11, 15);
        assertTrue(sc.getPoints()[11]==15);
    }
}
