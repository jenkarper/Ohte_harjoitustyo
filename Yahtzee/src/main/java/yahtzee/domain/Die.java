package yahtzee.domain;

/**
 *
 * @author pertjenn
 */
public class Die {

    private int value;
    private boolean hold;

    public Die() {
        this.value = 0;
        this.hold = false;
    }

    public int roll() {
        if (!this.hold) {
            this.value = (int) (Math.random() * 6) + 1;
        }
        return this.value;
    }

    public boolean isHold() {
        return this.hold;
    }

    public void setHold(boolean hold) {
        this.hold = hold;
    }

    public int getValue() {
        return this.value;
    }
    // TEST METHODS
    public void setTestValue(int value) {
        this.value = value;
    }
}
