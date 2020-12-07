package yahtzee.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import yahtzee.domain.Game;
import yahtzee.domain.User;

/**
 * Creates the start view of the game.
 * @author pertjenn
 */
public class StartView {

    private final Game game;
    private final Scene playScene;
    private final PlayView playView;
    private final Stage stage;

    public StartView(Game game, Scene playScene, PlayView playView, Stage stage) {
        this.game = game;
        this.playScene = playScene;
        this.playView = playView;
        this.stage = stage;
    }

    public Scene getScene() {
        
        // Create layout components
        Label label = new Label("Syötä nimi:");
        TextField field = new TextField();
        field.setMaxWidth(300);
        Button ok = new Button("Aloita uusi peli!");
        ok.setStyle("-fx-background-color: #e9f7ef; -fx-font-size: 1em; -fx-border-color:  #48c9b0; -fx-border-width: 1px;");

        // Create layout
        VBox layout = new VBox(label, field, ok);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);

        // Set scene
        Scene scene = new Scene(layout, 500, 300);
        
        // Define button action
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
            
            stage.setScene(this.playScene);
        });
        
        return scene;
    }
}
