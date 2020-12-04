package yahtzee.domain;

import yahtzee.dao.UserDao;
import yahtzee.dao.UserDaoDb;

/**
 * Represents the Yahtzee game as a whole, connecting the other domain classes
 * (the GUI accesses game logic through this class).
 *
 * @author pertjenn
 */
public class Game {

    private final Roll roll;
    private final Scorecard scorecard;
    private final Checker checker;
    private int rollCounter;
    private int roundCounter;
    private User user;
    private UserDao db;

    public Game() throws Exception {
        this.roll = new Roll();
        this.scorecard = new Scorecard();
        this.checker = new Checker();
        this.rollCounter = 3;
        this.roundCounter = 15;

        this.db = new UserDaoDb();
        createTables();
    }

    /**
     * Rolls all five dice and updates the rollCounter value.
     *
     * @return The values of each of the five dice (held and not held alike) as
     * an int array.
     */
    public int[] roll() {
        roll.roll();
        rollCounter--;
        return roll.getValues();
    }

    /**
     * Scores the roll in the scorecard in the designated category.
     *
     * @param category One of the 15 categories in a Yahtzee scorecard.
     * @param dice The dice values as an array.
     * @return Returns true if the desired slot was available, false otherwise.
     */
    public boolean scoreRoll(int category, int[] dice) {
        int points = this.checker.check(category, dice);
        roundCounter--;
        return this.scorecard.markScore(category, points);
    }

    /**
     * Resets the rollCounter to 3 (called after the previous roll has been
     * scored).
     */
    public void resetRollCounter() {
        rollCounter = 3;
    }

    /**
     * Resets the roundCounter to 15 (called after all 15 categories in the
     * scorecard have been scored and the game is over).
     */
    public void resetRoundCounter() {
        roundCounter = 15;
    }

    /**
     * Checks whether a particular die is held or not (called when user clicks
     * on a die button).
     *
     * @param die Index number of the die whose hold status is checked.
     * @return The hold status of the die corresponding to the given index.
     */
    public boolean checkHoldStatus(int die) {
        return this.roll.getDice()[die].isHold();
    }

    /**
     * Sets the hold status of a given die (called when user clicks on a die
     * button).
     *
     * @param die Index number of the die whose hold status is changed.
     * @param status Status to be set.
     */
    public void setHoldStatus(int die, boolean status) {
        this.roll.getDice()[die].setHold(status);
    }

    /**
     * Releases all five dice (called after the previous roll has been scored).
     */
    public void releaseAll() {
        this.roll.releaseAll();
    }

    /**
     * Calls the Checker to calculate how many points a roll would score in a
     * particular category.
     *
     * @param type The type of the check required (corresponds to the category
     * index).
     * @param dice The dice values as an array.
     * @return The actual points.
     */
    public int checkScore(int type, int[] dice) {
        return this.checker.check(type, dice);
    }

    /**
     * Resets the counters and the scorecard points, releases the dice (called
     * at the end of a game).
     */
    public void reset() {
        this.scorecard.reset();
        resetRollCounter();
        resetRoundCounter();
        releaseAll();
    }

    /**
     * Calls getUpperTotal() in Scorecard.
     *
     * @return The total points of the upper section.
     */
    public int getUpperTotal() {
        return this.scorecard.getUpperTotal();
    }

    /**
     * Calls getBonus() in Scorecard.
     *
     * @return The awarded bonus points (50 or 0).
     */
    public int getBonus() {
        return this.scorecard.getBonus();
    }

    /**
     * Calls the getGrandTotal() in Scorecard.
     *
     * @return The grand total.
     */
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

    // USER AND DATABASE METHODS
    public void createTables() throws Exception {
        db.createTable();
    }
}
