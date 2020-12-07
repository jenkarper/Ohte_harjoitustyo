package yahtzee.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import yahtzee.domain.Game;

/**
 *
 * @author pertjenn
 */
public class YahtzeeGUI extends Application {

    private Game game;
    private PlayView playView;
    private StartView startView;

    @Override
    public void init() throws Exception {
        this.game = new Game();
    }

    @Override
    public void start(Stage stage) throws Exception {

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
