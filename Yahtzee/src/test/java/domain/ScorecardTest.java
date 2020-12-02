package domain;

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
        assertTrue(sc != null);
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
        assertTrue(sc.getPoints()[11] == 15);
    }

    @Test
    public void upperScoreCountedCorrectly() {
        sc.setTestValues();
        assertTrue(sc.getUpperTotal() == 21);
    }

    @Test
    public void bonusNotAwardedForLowPoints() {
        sc.markScore(16, 62);
        assertTrue(sc.getBonus() == 0);
    }

    @Test
    public void bonusAwardedForHighPoints() {
        sc.markScore(16, 63);
        assertTrue(sc.getBonus() == 50);
    }

    @Test
    public void lowerScoreCountedCorrectly() {
        sc.setTestValues();
        assertTrue(sc.getGrandTotal() == 120);
    }

    @Test
    public void scorecardResetsCorreclty() {
        sc.setTestValues();
        sc.reset();
        boolean reset = true;

        for (int i = 1; i < sc.getPoints().length; i++) {
            if (sc.getPoints()[i] != -1) {
                reset = false;
            }
        }
        assertTrue(reset);
    }
}
