package yahtzee.domain;

/**
 * Represents the group of five dice used in a Yahtzee game.
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

    /**
     * Rolls such of the five dice whose hold status is false.
     */
    public void roll() {
        for (int i = 0; i < 5; i++) {
            Die d = this.dice[i];
            if (!d.isHold()) {
                d.roll();
                this.values[i] = d.getValue();
            }
        }
    }
    
    /**
     * @return The array of dice.
     */
    public Die[] getDice() {
        return this.dice;
    }
    
    /**
     * @return The array of dice values.
     */
    public int[] getValues() {
        return this.values;
    }
    
    /**
     * Sets the hold status of all five dice to false.
     */
    public void releaseAll() {
        for (Die d : this.dice) {
            d.setHold(false);
        }
    }
}
