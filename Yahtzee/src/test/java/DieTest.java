import org.junit.Test;
import static org.junit.Assert.*;
import yahtzee.domain.Die;

/**
 *
 * @author pertjenn
 */
public class DieTest {
    
    private Die die;
    
    public DieTest() {
        this.die = new Die();
    }

    @Test
    public void newDieExists() {
        assertTrue(die!=null);
    }
    
    @Test
    public void newDieIsNotHeld() {
        assertTrue(!die.isHold());
    }
    
    @Test
    public void newDieValueIsZero() {
        assertTrue(die.getValue()==0);
    }
    
    @Test
    public void rollChangesValueInValidRange() {
        die.roll();
        assertTrue((die.getValue()>=1)&&(die.getValue()<=6));
    }
    
    @Test
    public void setHoldChangesHoldStatus() {
        die.setHold(true);
        assertTrue(die.isHold());
    }
    
    @Test
    public void heldDieIsNotRolled() {
        int value = die.roll();
        die.setHold(true);
        boolean changed = false;
        for (int i = 0; i < 1000; i++) {
            die.roll();
            if (die.getValue()!=value) {
                changed = true;
            }
        }
        assertTrue(!changed);
    }
    
    
}
