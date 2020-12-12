package yahtzee.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import yahtzee.domain.Game;

/**
 * Builds the graphic user interface.
 * 
 * @author pertjenn
 */
public class YahtzeeGUI extends Application {

    private Game game;
    private PlayView playView;
    private StartView startView;

    @Override
    public void init() {
        this.game = new Game();
    }

    @Override
    public void start(Stage stage)  {

        stage.setTitle("Jatsi");

        // CREATE SCENES
        this.playView = new PlayView(game);
        Scene playScene = playView.getScene();
        this.startView = new StartView(game, playScene, playView, stage);
        Scene startScene = startView.getScene();

        // SET THE STAGE
        stage.setScene(startScene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
