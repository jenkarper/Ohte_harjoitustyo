package yahtzee.domain;

import java.util.ArrayList;

/**
 *
 * @author pertjenn
 */
public class Game {
    private final Roll roll;
    private final Scorecard scorecard;
    private final Checker checker;
    
    public Game() {
        this.roll = new Roll();
        this.scorecard = new Scorecard();
        this.checker = new Checker();
    }
    
    public int[] roll() {
        roll.roll();
        return roll.getValues();
    }
    
    public boolean scoreRoll(int category, int[] dice) {
        int points = this.checker.check(category, dice);
        return this.scorecard.markScore(category, points);
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
    
    public boolean checkHoldStatus(int die) {
        return this.roll.getDice()[die].isHold();
    }
    
    public void holdDie(int die) {
        this.roll.getDice()[die].setHold(true);
    }
    
    public void setHoldStatus(int die, boolean status) {
        this.roll.getDice()[die].setHold(status);
    }
    
    public int checkScore(int type, int[] dice) {
        return this.checker.check(type, dice);
    }
}
