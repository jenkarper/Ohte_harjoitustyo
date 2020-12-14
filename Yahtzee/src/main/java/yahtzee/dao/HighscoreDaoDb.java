package yahtzee.dao;

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
 *
 * @author pertjenn
 */
public class HighscoreDaoDb implements HighscoreDao {

    private final String databaseName;
    private Connection connection;
    private final UserDao userDb;

    public HighscoreDaoDb(String databaseName, UserDao userDb) {
        this.databaseName = databaseName;
        initialise();
        this.userDb = userDb;
    }

    /**
     * Intitialises the database.
     *
     * @throws Exception
     */
    private void initialise() {

        this.connection = connect();
        createTable();
    }

    /**
     * Connects to existing database via JDBC driver (or creates a new
     * database).
     *
     * @return The connection.
     * @throws Exception
     */
    private Connection connect() {
        Connection c = null;

        try {
            c = DriverManager.getConnection(databaseName);
            DatabaseMetaData meta = c.getMetaData();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return c;
    }

    /**
     * Creates table Highscore (if it does not yet exist).
     *
     * @throws Exception
     */
    private void createTable() {

        String createHighscore = "CREATE TABLE IF NOT EXISTS Highscore (id INTEGER PRIMARY KEY, score INTEGER, player INTEGER, FOREIGN KEY(player)"
                + "REFERENCES User(id));";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createHighscore);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a new row to Highscore table.
     *
     * @param user Instance of User class.
     */
    @Override
    public void addHighscore(User user) {

        String addHighscore = "INSERT INTO Highscore (score, player) VALUES (?, ?);";
        User u = userDb.findUser(user.getUsername());
        int key = userDb.getUserPK(u);

        try (PreparedStatement pstmt = connection.prepareStatement(addHighscore)) {
            pstmt.setInt(1, user.getHighScore());
            pstmt.setInt(2, key);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Deletes the row corresponding to the user's primary key in User table.
     * 
     * @param user 
     */
    @Override
    public void deleteHighscore(User user) {
        
        String deleteHighscore = "DELETE FROM Highscore WHERE (player==? AND score==?);";
        int key = userDb.getUserPK(user);
        
        try (PreparedStatement pstmt = connection.prepareStatement(deleteHighscore)) {
            pstmt.setInt(1, key);
            pstmt.setInt(2, user.getHighScore());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the Highscore table and orders the result set by the points.
     *
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
}
