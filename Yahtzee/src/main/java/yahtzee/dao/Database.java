package yahtzee.dao;

import static java.lang.Integer.max;
import static java.lang.Integer.min;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import yahtzee.domain.User;

/**
 * Offers methods for setting up, reading from, and writing to a database.
 * @author pertjenn
 */
public class Database implements UserDao, HighscoreDao {

    private Connection connection;

    public Database() throws Exception {
        initialise();
    }

    // SETTING UP DATABASE
    
    /**
     * Intitialises the database.
     * @throws Exception 
     */
    private void initialise() throws Exception {

        this.connection = connect();
        createTables();
    }

    /**
     * Connects to existing database via JDBC driver (or creates a new database).
     * @return The connection.
     * @throws Exception 
     */
    private Connection connect() throws Exception {
        String url = "jdbc:sqlite:yahtzee.db";
        Connection c = null;

        try {
            c = DriverManager.getConnection(url);
            DatabaseMetaData meta = c.getMetaData();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return c;
    }

    /**
     * Creates tables User and Highscore (if they do not yet exist).
     * @throws Exception 
     */
    private void createTables() throws Exception {
        String url = "jdbc:sqlite:yahtzee.db";
        String createUser = "CREATE TABLE IF NOT EXISTS User (id INTEGER PRIMARY KEY, username TEXT, highscore INTEGER, lowscore INTEGER, gamesPlayed INTEGER);";
        String createHighscore = "CREATE TABLE IF NOT EXISTS Highscore (id INTEGER PRIMARY KEY, score INTEGER, player INTEGER, FOREIGN KEY(player) REFERENCES User(id));";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createUser);
            stmt.execute(createHighscore);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // USER METHODS
    
    /**
     * Adds a new row to User table.
     * @param user Instance of class User.
     * @throws Exception 
     */
    @Override
    public void addUser(User user) throws Exception {
        String addUser = "INSERT INTO User (username, highscore) VALUES (?, ?);";

        try (PreparedStatement pstmt = connection.prepareStatement(addUser)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setInt(2, user.getHighScore());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Matches given username to database rows and reads the associated values.
     * @param username Input by user.
     * @return Instance of User (values from database or default).
     * @throws Exception 
     */
    @Override
    public User findUser(String username) throws Exception {
        User u = new User("");

        String findUser = "SELECT * FROM User WHERE (username==?);";

        try (PreparedStatement pstmt = connection.prepareStatement(findUser)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("username");
                int hs = rs.getInt("highscore");
                int ls = rs.getInt("lowscore");
                int gp = rs.getInt("gamesPlayed");
                u = new User(name, hs, ls, gp);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return u;
    }

    /**
     * Updates User table.
     * @param user Instance of User class with values to be updated.
     * @throws Exception 
     */
    @Override
    public void updateUser(User user) throws Exception {
        String updateUser = "UPDATE User SET highscore=?, lowscore=?, gamesPlayed=? WHERE username=?;";
        User current = findUser(user.getUsername());

        try (PreparedStatement pstmt = connection.prepareStatement(updateUser)) {
            
            int highPoints = max(current.getHighScore(), user.getHighScore());
            int lowPoints = min(current.getLowScore(), user.getLowScore());
//            if (current.getHighScore() < user.getHighScore()) {
//                pstmt.setInt(1, user.getHighScore());
//            } else {
//                pstmt.setInt(1, current.getHighScore());
//            }
//
//            if (current.getLowScore() > user.getLowScore()) {
//                pstmt.setInt(2, user.getLowScore());
//            } else {
//                pstmt.setInt(2, current.getLowScore());
//            }

            pstmt.setInt(1, highPoints);
            pstmt.setInt(2, lowPoints);
            pstmt.setInt(3, current.getGamesPlayed() + 1);
            pstmt.setString(4, user.getUsername());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 
     * @param user Instance of User class.
     * @return The primary key associated with the username.
     * @throws Exception 
     */
    public int getUserPK(User user) throws Exception {
        String getPK = "SELECT id FROM User WHERE (username==?);";
        int pk = -1;

        try (PreparedStatement pstmt = connection.prepareStatement(getPK)) {
            pstmt.setString(1, user.getUsername());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                pk = rs.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return pk;
    }

    // HIGHSCORE METHODS
    
    /**
     * Adds a new row to Highscore table.
     * @param user Instance of User class.
     * @throws Exception 
     */
    @Override
    public void addHighscore(User user) throws Exception {
        String addHighscore = "INSERT INTO Highscore (score, player) VALUES (?, ?);";
        User u = findUser(user.getUsername());
        int key = getUserPK(u);

        try (PreparedStatement pstmt = connection.prepareStatement(addHighscore)) {
            pstmt.setInt(1, user.getHighScore());
            pstmt.setInt(2, key);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the highscore table and orders the result set by the points.
     * @return A list of the highscores in descending order.
     */
    @Override
    public List<String> getTopTen() {
        List<String> list = new ArrayList<>();
        String getTopTen = "SELECT username, score FROM Highscore JOIN User ON User.id = Highscore.player ORDER BY score DESC;";
        
        try (PreparedStatement pstmt = connection.prepareStatement(getTopTen)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int score = rs.getInt("score");
                String player = rs.getString("username");
                list.add(player + "\t" + score);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return list;
    }
    
    // HELPER METHODS
    
    private int updateHighscore(User oldUser, User newUser) {
        return max(oldUser.getHighScore(), newUser.getHighScore());
    }
}
