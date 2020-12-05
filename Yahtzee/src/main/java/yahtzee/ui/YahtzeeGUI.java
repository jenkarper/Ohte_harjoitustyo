package yahtzee.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import yahtzee.dao.UserDao;
import yahtzee.dao.Database;
import yahtzee.domain.Game;
import yahtzee.domain.User;

/**
 *
 * @author pertjenn
 */
public class YahtzeeGUI extends Application {

    private PlayView playView;
    private Game game;
    private UserDao db;

    @Override
    public void init() throws Exception {
        this.game = new Game();
        this.db = new Database();
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Jatsi");

        // CREATE STARTVIEW
        Label label = new Label("Syötä nimi:");
        TextField field = new TextField();
        field.setMaxWidth(300);
        Button ok = new Button("Aloita uusi peli!");
        ok.setStyle("-fx-background-color: #e9f7ef; -fx-font-size: 1em; -fx-border-color:  #48c9b0; -fx-border-width: 1px;");

        VBox layout = new VBox(label, field, ok);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);

        Scene startScene = new Scene(layout, 500, 300);

        // CREATE PLAYVIEW
        this.playView = new PlayView(game);
        Scene playScene = playView.getScene();

        ok.setOnAction((event) -> {
            
            User loggingIn = new User("");
            
            try {
                String input = field.getText();
                if (game.validateUsername(input)) {
                    loggingIn = new User(input);
                } else {
                    loggingIn = game.findUser(input);
                }
                game.insertUser(loggingIn);
                game.setUser(loggingIn);
                playView.setPlayer();
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
            stage.setScene(playScene);
        });

        stage.setScene(startScene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
