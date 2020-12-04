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
import yahtzee.domain.Game;

/**
 *
 * @author pertjenn
 */
public class YahtzeeGUI extends Application {
    private PlayView playView;
    private Game game;

    @Override
    public void init() throws Exception {
        this.game = new Game();
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Jatsi");
        stage.setWidth(550);
        stage.setHeight(670);
        
        // CREATE STARTVIEW
        
        Label label = new Label("Syötä nimi:");
        TextField field = new TextField();
        field.setMaxWidth(300);
        Button ok = new Button("Aloita peli!");
        ok.setStyle("-fx-background-color: #e9f7ef; -fx-font-size: 1em; -fx-border-color:  #48c9b0; -fx-border-width: 1px;");
        
        VBox layout = new VBox(label, field, ok);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);
        
        Scene startScene = new Scene(layout);

        // CREATE PLAYVIEW
        
        this.playView = new PlayView(game);
        Scene playScene = playView.getScene();
        
        ok.setOnAction((event) -> {
           String player = field.getText();
           stage.setScene(playScene);
        });
        
        stage.setScene(startScene);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
