package yahtzee.domain;

/**
 *
 * @author pertjenn
 */
public class Roll {

    private final Die[] dice;
    private final int[] values;

    public Roll() {
        this.dice = new Die[5];
        this.values = new int[5];
        for (int i = 0; i < 5; i++) {
            this.dice[i] = new Die();
        }
    }

    public void roll() {
        for (int i = 0; i < 5; i++) {
            Die d = this.dice[i];
            if (!d.isHold()) {
                d.roll();
                this.values[i] = d.getValue();
            }
        }
    }
    
    public Die[] getDice() {
        return this.dice;
    }
    
    public int[] getValues() {
        return this.values;
    }
    
    public void releaseAll() {
        for (Die d : this.dice) {
            d.setHold(false);
        }
    }
}
