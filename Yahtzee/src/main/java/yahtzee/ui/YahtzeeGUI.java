package yahtzee.ui;

import java.util.Arrays;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import yahtzee.domain.Game;

/**
 *
 * @author pertjenn
 */
public class YahtzeeGUI extends Application {

    private Game game;
    private Stage stage;
    private Button[] dieRow;
    private Label[] scorecard;
    private Insets insets;

    @Override
    public void init() throws Exception {
        this.game = new Game();
        this.dieRow = createDice();
        this.scorecard = createScorecard();
        this.insets = new Insets(20, 20, 20, 20);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        // 1 CREATE PLAYVIEW COMPONENTS
        
        // 1.1 Top: the dice
        
        HBox topNode = new HBox();
        topNode.setSpacing(30);
        topNode.setPadding(new Insets(0, 0, 30, 0));
        
        topNode.getChildren().addAll(Arrays.asList(dieRow));
        
        // 1.2 Left: the scorecard
        
        VBox leftNode = new VBox();
        leftNode.setSpacing(10);
        leftNode.setPadding(insets);
        
        leftNode.getChildren().addAll(Arrays.asList(scorecard));
        
        // 1.3 Center: the action buttons
        
        Button rollButton = new Button("Heitä");
        styleButton(rollButton);
        Button scoreButton = new Button("Pisteytä");
        styleButton(scoreButton);
        
        VBox centerNode = new VBox();
        centerNode.setPadding(insets);
        centerNode.setSpacing(20);
        centerNode.getChildren().addAll(rollButton, scoreButton);
        
        // 1.4 Bottom: the instruction button
        
        Button instructionButton = new Button("Ohje");
        styleButton(instructionButton);
        HBox bottomNode = new HBox(instructionButton);
        
        // 1.5 Right: player info (this node is still under construction!)
        
        VBox rightNode = new VBox();
        rightNode.setPadding(insets);
        rightNode.setSpacing(20);
        
        Label player = new Label("Pelaaja: <pelaajanimi>");
        Label upperTotal = new Label("Välisumma: ");
        Label bonus = new Label("BONUS: ");
        Label grandTotal = new Label("Yhteensä: ");
        
        rightNode.getChildren().addAll(player, upperTotal, bonus, grandTotal);
        
        // 2 CREATE PLAYVIEW LAYOUT
        
        BorderPane playView = new BorderPane();
        playView.setTop(topNode);
        playView.setCenter(centerNode);
        playView.setLeft(leftNode);
        playView.setRight(rightNode);
        playView.setBottom(bottomNode);
        
        playView.setPadding(insets);
        topNode.setAlignment(Pos.CENTER);
        bottomNode.setAlignment(Pos.BOTTOM_RIGHT);
        
        // 3 SET THE STAGE
        
        this.stage = stage;
        stage.setTitle("Jatsi");
        Scene playScene = new Scene(playView);
        stage.setScene(playScene);
        stage.show();
        
        // 4 DEFINE ACTION FOR PLAYVIEW BUTTONS

        // 4.1 Die buttons

        dieRow[0].setOnAction(value -> {
            if (game.checkHoldStatus(0)) {
                game.setHoldStatus(0, false);
                dieRow[0].setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
            } else {
                game.setHoldStatus(0, true);
                dieRow[0].setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em; -fx-border-color: #138d75; -fx-border-width: 5px;");
            }
        });
        
        dieRow[1].setOnAction(value -> {
            if (game.checkHoldStatus(1)) {
                game.setHoldStatus(1, false);
                dieRow[1].setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
            } else {
                game.setHoldStatus(1, true);
                dieRow[1].setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em; -fx-border-color: #138d75; -fx-border-width: 5px;");
            }
        });

        dieRow[2].setOnAction(value -> {
            if (game.checkHoldStatus(2)) {
                game.setHoldStatus(2, false);
                dieRow[2].setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
            } else {
                game.setHoldStatus(2, true);
                dieRow[2].setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em; -fx-border-color: #138d75; -fx-border-width: 5px;");
            }
        });

        dieRow[3].setOnAction(value -> {
            if (game.checkHoldStatus(3)) {
                game.setHoldStatus(3, false);
                dieRow[3].setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
            } else {
                game.setHoldStatus(3, true);
                dieRow[3].setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em; -fx-border-color: #138d75; -fx-border-width: 5px;");
            }
        });

        dieRow[4].setOnAction(value -> {
            if (game.checkHoldStatus(4)) {
                game.setHoldStatus(4, false);
                dieRow[4].setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
            } else {
                game.setHoldStatus(4, true);
                dieRow[4].setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em; -fx-border-color: #138d75; -fx-border-width: 5px;");
            }
        });

        // 4.2 Roll button
        
        rollButton.setOnAction((ActionEvent event) -> {
            int[] newValues = game.roll();
            for (int i = 0; i < dieRow.length; i++) {
                dieRow[i].setText(Integer.toString(newValues[i]));
            }
        });
        
        // 4.3 Instruction button

        instructionButton.setOnAction((ActionEvent event) -> {
            InstructionView instructionView = new InstructionView();
            Stage instructionWindow = instructionView.createInstructionView();
            instructionWindow.show();
        });
        
        // 4.4 Score button
        
        scoreButton.setOnAction((ActionEvent event) -> {
            ScoreView scoreView = new ScoreView(game, scorecard);
            Stage scoreWindow = scoreView.getScoreView();
            scoreWindow.show();
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

    private Button[] createDice() {
        Button[] dicePane = new Button[5];
        dicePane[0] = new Button("J");
        dicePane[1] = new Button("A");
        dicePane[2] = new Button("T");
        dicePane[3] = new Button("S");
        dicePane[4] = new Button("I");

        for (Button b : dicePane) {
            b.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
        }

        return dicePane;
    }

    private Label[] createScorecard() {
        Label[] sc = new Label[16];
        sc[0] = new Label("PÖYTÄKIRJA");
        sc[0].setFont(new Font("Arial", 18));
        for (int i = 1; i < sc.length; i++) {
            sc[i] = new Label(game.getScorecard().getCategories()[i]);
            sc[i].setFont(new Font("Arial", 15));
        }
        return sc;
    }
    
    private void styleButton(Button b) {
        b.setStyle("-fx-background-color: #e9f7ef; -fx-font-size: 1em; -fx-border-color:  #48c9b0; -fx-border-width: 1px;");
    }
}
