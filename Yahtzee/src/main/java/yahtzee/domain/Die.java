package yahtzee.domain;

/**
 * Represents a 6-sided die.
 * @author pertjenn
 */
public class Die {

    private int value;
    private boolean hold;

    public Die() {
        this.value = 0;
        this.hold = false;
    }

    /**
     * Rolls the die.
     *
     * @return A value between 1 and 6.
     */
    public int roll() {
        if (!this.hold) {
            this.value = (int) (Math.random() * 6) + 1;
        }
        return this.value;
    }

    public boolean isHold() {
        return this.hold;
    }

    /**
     * @param hold New hold status to set.
     */
    public void setHold(boolean hold) {
        this.hold = hold;
    }
    
    /**
     * @return The current value of the die.
     */
    public int getValue() {
        return this.value;
    }

    // TEST METHODS
    /**
     * @param value Specific (as opposed to random) value to set for testing purposes.
     */
    public void setTestValue(int value) {
        this.value = value;
    }
}
