package yahtzee.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import yahtzee.dao.HighscoreDao;
import yahtzee.dao.HighscoreDaoDb;
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
    private final UserDao userDb;
    private final HighscoreDao highscoreDb;
    private final String databaseName;

    /**
     * Constructor without parameter creates the database in the default location.
     * 
     * @throws Exception 
     */
    public Game() throws Exception {
        this("jdbc:sqlite:yahtzee.db");
    }

    /**
     * Creates database in the specified location.
     * 
     * @param databaseName
     * @throws Exception 
     */
    public Game(String databaseName) throws Exception {
        this.roll = new Roll();
        this.scorecard = new Scorecard();
        this.checker = new Checker();
        this.rollCounter = 3;
        this.roundCounter = 15;

        this.databaseName = databaseName;
        this.userDb = new UserDaoDb(databaseName);
        this.highscoreDb = new HighscoreDaoDb(databaseName, userDb);
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
    
    
    public String getPlayer() {
        return this.user.getUsername();
    }

    public User getUser() {
        return this.user;
    }

    public HighscoreDao getHighscoreDatabase() {
        return this.highscoreDb;
    }
    
    public UserDao getUserDatabase() {
    return this.userDb;
}

    /**
     * Calls database method for checking whether a user already exists.
     *
     * @param username Input by user.
     * @return False if username already exists in database, true otherwise.
     * @throws Exception
     */
    public boolean validateUsername(String username) throws Exception {
        return userDb.findUser(username).getUsername().equals("");
    }

    /**
     * Calls database method for adding a new user.
     *
     * @param user
     * @throws Exception
     */
    public void insertUser(User user) throws Exception {
        userDb.addUser(user);
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Calls database method for finding user by username
     *
     * @param username
     * @return
     * @throws Exception
     */
    public User findUser(String username) throws Exception {
        return userDb.findUser(username);
    }

    /**
     * Calls database method to update User table
     *
     * @throws Exception
     */
    public void updateUser() throws Exception {
        userDb.updateUser(user);
        highscoreDb.addHighscore(user);
    }

    /**
     * Updates current user stats
     * 
     * @param points The grand total of the last game.
     */
    public void updateUserStats(int points) {

        user.play();

        if (user.getGamesPlayed() == 1) {
            user.setHighScore(points);
            user.setLowScore(points);
        } else {
            if (user.getHighScore() < points) {
                user.setHighScore(points);
            } else if (user.getLowScore() > points) {
                user.setLowScore(points);
            }
        }
    }

    public void deleteUser(User user) throws Exception {
        userDb.deleteUser(user);
    }

    /**
     * Calls database method to get the top ten scores
     *
     * @return
     */
    public List<String> getTopTen() {
        try {
            List<String> list = new ArrayList<>();
            
            list = highscoreDb.getTopTen();
            
            return list;
        } catch (Exception ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
