package yahtzee.domain;

/**
 *
 * @author pertjenn
 */
public class Highscore {
    
    private final User player;
    private int score;
    
    public Highscore(User user, int points) {
        this.player = user;
        this.score = points;
    }

    public void updateScore(int points) {
        this.score = points;
    }

    public User getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }
    
}
