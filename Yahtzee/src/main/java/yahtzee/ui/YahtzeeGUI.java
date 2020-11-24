package yahtzee.ui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import yahtzee.domain.Game;

/**
 *
 * @author pertjenn
 */
public class YahtzeeGUI extends Application {

    private Game game;
    private Stage stage;

    @Override
    public void init() throws Exception {
        this.game = new Game();
    }

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;
        stage.setTitle("Jatsi");
        stage.setWidth(400);

        // Create die row
        Button dieOne = new Button("1");
        dieOne.setPrefSize(50, 50);
        dieOne.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
        Button dieTwo = new Button("2");
        dieTwo.setPrefSize(50, 50);
        dieTwo.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
        Button dieThree = new Button("3");
        dieThree.setPrefSize(50, 50);
        dieThree.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
        Button dieFour = new Button("4");
        dieFour.setPrefSize(50, 50);
        dieFour.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
        Button dieFive = new Button("5");
        dieFive.setPrefSize(50, 50);
        dieFive.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");

        dieOne.setOnAction(value -> {
            if (game.checkHoldStatus(0)) {
                game.setHoldStatus(0, false);
                dieOne.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
            } else {
                game.setHoldStatus(0, true);
                dieOne.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em; -fx-border-color: #138d75; -fx-border-width: 5px;");
            }
        });

        dieTwo.setOnAction(value -> {
            if (game.checkHoldStatus(1)) {
                game.setHoldStatus(1, false);
                dieTwo.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
            } else {
                game.setHoldStatus(1, true);
                dieTwo.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em; -fx-border-color: #138d75; -fx-border-width: 5px;");
            }
        });

        dieThree.setOnAction(value -> {
            if (game.checkHoldStatus(2)) {
                game.setHoldStatus(2, false);
                dieThree.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
            } else {
                game.setHoldStatus(2, true);
                dieThree.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em; -fx-border-color: #138d75; -fx-border-width: 5px;");
            }
        });

        dieFour.setOnAction(value -> {
            if (game.checkHoldStatus(3)) {
                game.setHoldStatus(3, false);
                dieFour.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
            } else {
                game.setHoldStatus(3, true);
                dieFour.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em; -fx-border-color: #138d75; -fx-border-width: 5px;");
            }
        });

        dieFive.setOnAction(value -> {
            if (game.checkHoldStatus(4)) {
                game.setHoldStatus(4, false);
                dieFive.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
            } else {
                game.setHoldStatus(4, true);
                dieFive.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em; -fx-border-color: #138d75; -fx-border-width: 5px;");
            }
        });

        // Add dice to diePane
        HBox diePane = new HBox();
        diePane.setSpacing(30);
        diePane.setPadding(new Insets(0, 0, 30, 0));

        ObservableList dieRow = diePane.getChildren();
        dieRow.addAll(dieOne, dieTwo, dieThree, dieFour, dieFive);

        // Create roll button
        Button rollButton = new Button("Heitä!");
        rollButton.setStyle("-fx-background-color: #e9f7ef; -fx-font-size: 2em; -fx-border-color:  #48c9b0; -fx-border-width: 4px;");

        // Define roll action
        rollButton.setOnAction(value -> {
            int[] newValues = game.roll();
            dieOne.setText(Integer.toString(newValues[0]));
            dieTwo.setText(Integer.toString(newValues[1]));
            dieThree.setText(Integer.toString(newValues[2]));
            dieFour.setText(Integer.toString(newValues[3]));
            dieFive.setText(Integer.toString(newValues[4]));
        });
        
        Label instruction = new Label();
        instruction.setText("Lukitse tai vapauta noppa klikkaamalla.");
        instruction.setFont(new Font("Arial", 18));
        instruction.setPadding(new Insets(5, 5, 5, 5));
        instruction.setWrapText(true);
        instruction.setTextAlignment(TextAlignment.CENTER);

        // Add diePane and rollButton to playView
        Node top = diePane;
        Node center = rollButton;
        Node bottom = instruction;
        
        BorderPane playView = new BorderPane();
        Insets insets = new Insets(20, 20, 20, 20);
        playView.setPadding(insets);
        playView.setTop(top);
        playView.setCenter(center);
        playView.setBottom(bottom);
        playView.setMargin(bottom, insets);

        Scene scene = new Scene(playView);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
