import org.junit.Test;
import static org.junit.Assert.*;
import yahtzee.domain.Die;
import yahtzee.domain.Roll;

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
    public void testValuesAreSetCorrectly() {
        int[] testValues = new int[]{1,1,1,1,1};
        roll.setTestValues(testValues);
        boolean correct = true;
        for (int i = 0; i < 5; i++) {
            if (roll.getValues()[i] != 1) {
                correct = false;
            }
        }
        assertTrue(correct);
    }
    
    @Test
    public void checkFindsDuplicates() {
        int[] testValues = new int[]{1,1,2,3,1};
        roll.setTestValues(testValues);
        assertTrue(roll.checkDuplicates(1)==3);
    }
    
    @Test
    public void checkFindsPair() {
        int[] testValues = new int[]{1,3,5,6,3};
        roll.setTestValues(testValues);
        assertTrue(roll.checkOnePair()==6);
    }
    
    @Test
    public void checkFindsLargestPair() {
        int[] testValues = new int[]{1,6,2,1,6};
        roll.setTestValues(testValues);
        assertTrue(roll.checkOnePair()==12);
    }
    
    @Test
    public void checkRecognisesFalsePair() {
        int[] testValues = new int[]{1,2,3,4,5};
        roll.setTestValues(testValues);
        assertTrue(roll.checkOnePair()==0);
    }
    
    @Test
    public void checkFindsSmallAndMiddlePair() {
        int[] testValues = new int[]{1,3,2,2,1};
        roll.setTestValues(testValues);
        assertTrue(roll.checkTwoPairs()==6);
    }
    
    @Test
    public void checkFindsSmallAndLargePair() {
        int[] testValues = new int[]{1,5,3,5,1};
        roll.setTestValues(testValues);
        assertTrue(roll.checkTwoPairs()==12);
    }
    
    @Test
    public void checkFindsMiddleAndLargePair() {
        int[] testValues = new int[]{4,3,3,2,4};
        roll.setTestValues(testValues);
        assertTrue(roll.checkTwoPairs()==14);
    }
    
    @Test
    public void checkRecognisesFalseTwoPairs() {
        int[] testValues = new int[]{1,1,2,3,1};
        roll.setTestValues(testValues);
        assertTrue(roll.checkTwoPairs()==0);
    }
    
    @Test
    public void checkFindsSmallValueThreeKind() {
        int[] testValues = new int[]{1,1,2,3,1};
        roll.setTestValues(testValues);
        assertTrue(roll.checkThreeKind()==3);
    }
    
    @Test
    public void checkFindsTMiddleValuehreeKind() {
        int[] testValues = new int[]{1,2,2,3,2};
        roll.setTestValues(testValues);
        assertTrue(roll.checkThreeKind()==6);
    }
    
    @Test
    public void checkFindsLargeValueThreeKind() {
        int[] testValues = new int[]{1,3,2,3,3};
        roll.setTestValues(testValues);
        assertTrue(roll.checkThreeKind()==9);
    }
    
    @Test
    public void checkRecognisesFalseThreeKind() {
        int[] testValues = new int[]{1,3,2,2,1};
        roll.setTestValues(testValues);
        assertTrue(roll.checkThreeKind()==0);
    }
    
    @Test
    public void checkFindsFourKind() {
        int[] testValues = new int[]{1,1,1,3,1};
        roll.setTestValues(testValues);
        assertTrue(roll.checkFourKind()==4);
    }
    
    public void checkRecognisesFalseFourKind() {
        int[] testValues = new int[]{1,3,2,2,1};
        roll.setTestValues(testValues);
        assertTrue(roll.checkFourKind()==0);
    }
    
    @Test
    public void checkFindsSmallStraight() {
        int[] testValues = new int[]{1,2,3,4,5};
        roll.setTestValues(testValues);
        assertTrue(roll.checkSmallStraight()==15);
    }
    
    @Test
    public void checkFindsLargeStraight() {
        int[] testValues = new int[]{2,3,4,5,6};
        roll.setTestValues(testValues);
        assertTrue(roll.checkLargeStraight()==20);
    }
    
    @Test
    public void checkRecognisesFalseStraight() {
        int[] testValues = new int[]{2,3,4,5,5};
        roll.setTestValues(testValues);
        assertTrue(roll.checkLargeStraight()==0);
    }
    
    @Test
    public void checkFindsFullHouse() {
        int[] testValues = new int[]{2,4,2,4,4};
        roll.setTestValues(testValues);
        assertTrue(roll.checkFullHouse()==16);
    }
    
    @Test
    public void checkRecognisesFalseFullHouse() {
        int[] testValues = new int[]{2,4,2,6,4};
        roll.setTestValues(testValues);
        assertTrue(roll.checkFullHouse()==0);
    }
    
    @Test
    public void checkFindsYahtzee() {
        int[] testValues = new int[]{2,2,2,2,2};
        roll.setTestValues(testValues);
        assertTrue(roll.checkYahtzee()==60);
    }
    
    @Test
    public void checkRecognisesFalseYahtzee() {
        int[] testValues = new int[]{2,2,2,3,2};
        roll.setTestValues(testValues);
        assertTrue(roll.checkYahtzee()==0);
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
