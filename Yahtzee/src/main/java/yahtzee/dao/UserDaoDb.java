package yahtzee.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

import yahtzee.domain.User;

/**
 * Implements the methods defined in UserDao interface.
 * 
 * @author pertjenn
 */
public class UserDaoDb implements UserDao {

    private String databaseName;
    private Connection connection;

    /**
     * Creates new instance with given database location, creates User table in database.
     * 
     * @param databaseName The location of the database.
     */
    public UserDaoDb(String databaseName) {
        this.databaseName = databaseName;
        initialise(databaseName);
    }

    private void initialise(String databaseName) {

        this.databaseName = databaseName;
        this.connection = connect(databaseName);
        createTable();
    }

    private Connection connect(String databaseName) {
        Connection c = null;

        try {
            c = DriverManager.getConnection(databaseName);
            DatabaseMetaData meta = c.getMetaData();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return c;
    }

    private void createTable() {
        String createUser = "CREATE TABLE IF NOT EXISTS User (id INTEGER PRIMARY KEY, username TEXT, highscore INTEGER, lowscore INTEGER, gamesPlayed INTEGER);";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createUser);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a new row to User table.
     *
     * @param user Instance of class User.
     */
    @Override
    public void addUser(User user) {
        String addUser = "INSERT INTO User (username, highscore, lowscore, gamesPlayed) VALUES (?, ?, ?, ?);";

        try (PreparedStatement pstmt = connection.prepareStatement(addUser)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setInt(2, user.getHighScore());
            pstmt.setInt(3, user.getLowScore());
            pstmt.setInt(4, user.getGamesPlayed());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Matches given username to database rows and reads the associated values.
     *
     * @param username Input by user.
     * @return Instance of User (values from database or default).
     */
    @Override
    public User findUser(String username) {
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
     * Uodates the User table with current user's stats.
     *
     * @param user
     */
    @Override
    public void updateUser(User user) {
        String updateUser = "UPDATE User SET highscore=?, lowscore=?, gamesPlayed=? WHERE username=?;";
        User current = findUser(user.getUsername());

        try (PreparedStatement pstmt = connection.prepareStatement(updateUser)) {

            int highPoints = max(current.getHighScore(), user.getHighScore());
            int lowPoints = min(current.getLowScore(), user.getLowScore());

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
     * Deletes the row corresponding to current user's userame.
     *
     * @param user
     */
    @Override
    public void deleteUser(User user) {
        String deleteUser = "DELETE FROM User WHERE username==?;";

        try (PreparedStatement pstmt = connection.prepareStatement(deleteUser)) {
            pstmt.setString(1, user.getUsername());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reads the database row with specified username, returns corresponding primary key.
     * 
     * @param user Instance of User class.
     * @return The primary key associated with the username.
     */
    @Override
    public int getUserPK(User user) {
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
}
