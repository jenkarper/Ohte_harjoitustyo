package yahtzee.domain;

/**
 *
 * @author pertjenn
 */
public class User {
    private final String username;
    private int highScore;
    private int lowScore;
    private int gamesPlayed;
    
    
    public User(String username) {
        this(username, 0, 0, 0);
    }
    
    public User(String username, int hs, int ls, int gp) {
        this.username = username;
        this.highScore = hs;
        this.lowScore = ls;
        this.gamesPlayed = gp;
    }
    
    public String getUsername() {
        return username;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int points) {
        this.highScore = points;
    }

    public int getLowScore() {
        return lowScore;
    }

    public void setLowScore(int lowScore) {
        this.lowScore = lowScore;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void play() {
        this.gamesPlayed++;
    }
    
    @Override
    public String toString() {
        StringBuilder u = new StringBuilder();
        u.append(this.username).append(", ").append(this.highScore).append(", ").append(this.lowScore).append(", ").append(this.gamesPlayed);
        return u.toString();
    }
}
