package yahtzee.domain;

/**
 *
 * @author pertjenn
 */
public class Game {

    private final Roll roll;
    private final Scorecard scorecard;
    private final Checker checker;
    private int rollCounter;
    private int roundCounter;
    private String player;

    public Game(String player) {
        this.roll = new Roll();
        this.scorecard = new Scorecard();
        this.checker = new Checker();
        this.rollCounter = 3;
        this.roundCounter = 15;
        this.player = player;
    }

    public int[] roll() {
        roll.roll();
        rollCounter--;
        return roll.getValues();
    }

    public boolean scoreRoll(int category, int[] dice) {
        int points = this.checker.check(category, dice);
        roundCounter--;
        return this.scorecard.markScore(category, points);
    }

    public void resetRollCounter() {
        rollCounter = 3;
    }

    public void resetRoundCounter() {
        roundCounter = 15;
    }

    public boolean checkHoldStatus(int die) {
        return this.roll.getDice()[die].isHold();
    }

    public void setHoldStatus(int die, boolean status) {
        this.roll.getDice()[die].setHold(status);
    }

    public void releaseAll() {
        this.roll.releaseAll();
    }

    public int checkScore(int type, int[] dice) {
        return this.checker.check(type, dice);
    }

    public void reset() {
        this.scorecard.reset();
        resetRollCounter();
        resetRoundCounter();
        releaseAll();
    }

    public int getUpperTotal() {
        return this.scorecard.getUpperTotal();
    }

    public int getBonus() {
        return this.scorecard.getBonus();
    }

    public int getGrandTotal() {
        return this.scorecard.getGrandTotal();
    }

    public Roll getRoll() {
        return roll;
    }

    public Scorecard getScorecard() {
        return scorecard;
    }

    public Checker getChecker() {
        return checker;
    }

    public int getRollCounter() {
        return rollCounter;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public String getPlayer() {
        return player;
    }
}
