import org.junit.Test;
import static org.junit.Assert.*;
import yahtzee.domain.Checker;

/**
 *
 * @author pertjenn
 */
public class CheckerTest {
    private Checker c;
    
    public CheckerTest() {
        this.c = new Checker();
    }
    
    @Test
    public void newCheckerExists() {
        assertTrue(c!=null);
    }
    
    @Test
    public void checkerFindsDuplicates() {
        int[] dice = new int[]{1, 2, 4, 2, 2};
        assertTrue(c.duplicates(dice, 2)==6);
    }
    
    @Test
    public void checkerFindsOnePair() {
        int[] dice = new int[]{4, 1, 4, 3, 2};
        assertTrue(c.pair(dice)==8);
    }
    
    @Test
    public void checkerFindsOnePairInManyKind() {
        int[] dice = new int[]{4, 4, 4, 2, 4};
        assertTrue(c.pair(dice)==8);
    }
    
    @Test
    public void checkerFindsLargestPair() {
        int[] dice = new int[]{4, 1, 4, 5, 5};
        assertTrue(c.pair(dice)==10);
    }
    
    @Test
    public void checkerIdentifiesNoPair() {
        int[] dice = new int[]{1, 4, 2, 3, 5};
        assertTrue(c.pair(dice)==0);
    }
    
    @Test
    public void checkerFindsTwoPairs() {
        int[] dice = new int[]{4, 1, 4, 3, 1};
        assertTrue(c.twoPairs(dice)==10);
    }
    
    @Test
    public void checkerFindsTwoPairsInManyKind() {
        int[] dice = new int[]{5, 5, 2, 5, 5};
        assertTrue(c.twoPairs(dice)==20);
    }
    
    @Test
    public void checkerFindsNoTwoPairs() {
        int[] dice = new int[]{4, 1, 4, 3, 2};
        assertTrue(c.twoPairs(dice)==0);
    }
    
    @Test
    public void checkerFindsThreeKindS() {
        int[] dice = new int[]{1, 1, 4, 1, 2};
        assertTrue(c.threeKind(dice)==3);
    }
    
    @Test
    public void checkerFindsThreeKindM() {
        int[] dice = new int[]{3, 1, 3, 3, 6};
        assertTrue(c.threeKind(dice)==9);
    }
    
    @Test
    public void checkerFindsThreeKindL() {
        int[] dice = new int[]{5, 5, 4, 1, 5};
        assertTrue(c.threeKind(dice)==15);
    }
    
    @Test
    public void checkerFindsThreeKindInYahtzee() {
        int[] dice = new int[]{6, 6, 6, 6, 6};
        assertTrue(c.threeKind(dice)==18);
    }
    
    @Test
    public void checkerFindsNoThreeKind() {
        int[] dice = new int[]{2, 5, 4, 1, 5};
        assertTrue(c.threeKind(dice)==0);
    }
    
    @Test
    public void checkerFindsFourKindS() {
        int[] dice = new int[]{4, 6, 4, 4, 4};
        assertTrue(c.fourKind(dice)==16);
    }
    
    @Test
    public void checkerFindsFourKindL() {
        int[] dice = new int[]{6, 5, 6, 6, 6};
        assertTrue(c.fourKind(dice)==24);
    }
    
    @Test
    public void checkerFindsFourKindInYahtzee() {
        int[] dice = new int[]{6, 6, 6, 6, 6};
        assertTrue(c.fourKind(dice)==24);
    }
    
    @Test
    public void checkerFindsNoFourKind() {
        int[] dice = new int[]{3, 3, 4, 3, 5};
        assertTrue(c.fourKind(dice)==0);
    }
    
    @Test
    public void checkerFindsSmallStraight() {
        int[] dice = new int[]{2, 1, 4, 3, 5};
        assertTrue(c.smallStraight(dice)==15);
    }
    
    @Test
    public void checkerFindsNoSmallStraight() {
        int[] dice = new int[]{2, 1, 4, 3, 6};
        assertTrue(c.smallStraight(dice)==0);
    }
    
    @Test
    public void checkerFindsLargeStraight() {
        int[] dice = new int[]{2, 5, 4, 3, 6};
        assertTrue(c.largeStraight(dice)==20);
    }
    
    @Test
    public void checkerFindsNoLargeStraight() {
        int[] dice = new int[]{4, 5, 1, 3, 6};
        assertTrue(c.largeStraight(dice)==0);
    }
    
    @Test
    public void checkerFindsFullHouse() {
        int[] dice = new int[]{4, 5, 4, 4, 5};
        assertTrue(c.fullHouse(dice)==22);
    }
    
    @Test
    public void checkerFindsNoFullHouse() {
        int[] dice = new int[]{4, 4, 4, 4, 5};
        assertTrue(c.fullHouse(dice)==0);
    }
    
    @Test
    public void checkerFindsYahtzee() {
        int[] dice = new int[]{6, 6, 6, 6, 6};
        assertTrue(c.yahtzee(dice)==80);
    }
    
    @Test
    public void checkerFindsNoYahtzee() {
        int[] dice = new int[]{4, 4, 4, 4, 5};
        assertTrue(c.yahtzee(dice)==0);
    }
}
