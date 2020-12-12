package yahtzee.ui;

import java.util.Arrays;
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
 * Creates the main view of the game.
 *
 * @author pertjenn
 */
public class PlayView {

    private final Game game;
    private final Button[] dieRow;
    private final Label[] scorecard;
    private final Label[] playerInfo;
    private final Insets insets;
    private final GameAlert alert;

    public PlayView(Game game) {
        this.game = game;
        this.dieRow = createDice();
        this.scorecard = createScorecard();
        this.playerInfo = createPlayerInfo();
        this.insets = new Insets(20, 20, 20, 20);
        this.alert = new GameAlert();
    }

    public Scene getScene() {

        // 1 CREATE LAYOUT COMPONENTS
        // 1.1 Top: the dice
        HBox topNode = new HBox();
        topNode.setSpacing(30);
        topNode.setPadding(insets);

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
        centerNode.setMinWidth(50);
        centerNode.getChildren().addAll(rollButton, scoreButton);

        // 1.4 Bottom: the instruction and new game buttons
        Button instructionButton = new Button("Ohje");
        styleButton(instructionButton);
        Button newGameButton = new Button("Aloita uusi peli");
        styleButton(newGameButton);
        Button playerStatsButton = new Button("Omat tiedot");
        styleButton(playerStatsButton);
        HBox bottomNode = new HBox(instructionButton, newGameButton, playerStatsButton);
        bottomNode.setSpacing(20);

        // 1.5 Right: player info
        VBox rightNode = new VBox();
        rightNode.setPadding(insets);
        rightNode.setSpacing(10);

        rightNode.getChildren().addAll(Arrays.asList(playerInfo));

        // 2 CREATE LAYOUT
        BorderPane layout = new BorderPane();
        layout.setTop(topNode);
        layout.setCenter(centerNode);
        layout.setLeft(leftNode);
        layout.setRight(rightNode);
        layout.setBottom(bottomNode);

        layout.setPadding(insets);
        topNode.setAlignment(Pos.CENTER);
        centerNode.setAlignment(Pos.TOP_CENTER);
        bottomNode.setAlignment(Pos.BOTTOM_RIGHT);

        // 3 DEFINE BUTTON ACTION
        defineButtonEvents(rollButton, scoreButton, instructionButton, newGameButton, playerStatsButton, rightNode);

        return new Scene(layout);
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
            b.setPrefSize(50, 50);
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

    private Label[] createPlayerInfo() {
        Label[] info = new Label[5];

        info[0] = new Label("PELITILANNE");
        info[0].setFont(new Font("Arial", 18));

        info[1] = new Label();
        info[1].setFont(new Font("Arial", 15));

        info[2] = new Label("Välisumma");
        info[2].setFont(new Font("Arial", 15));

        info[3] = new Label("Bonus");
        info[3].setFont(new Font("Arial", 15));

        info[4] = new Label("Yhteensä");
        info[4].setFont(new Font("Arial", 15));
        info[4].setStyle("-fx-font-weight: bold");

        return info;
    }

    public void setPlayer() {
        this.playerInfo[1].setText("Pelaaja: " + game.getPlayer());
    }

    private void resetPlayerInfo() {
        playerInfo[2].setText("Välisumma");
        playerInfo[3].setText("Bonus");
        playerInfo[4].setText("Yhteensä");
    }

    private void styleButton(Button b) {
        b.setStyle("-fx-background-color: #e9f7ef; -fx-font-size: 1em; -fx-border-color:  #48c9b0; -fx-border-width: 1px;");
    }

    private void reset() {
        dieRow[0].setText("J");
        dieRow[1].setText("A");
        dieRow[2].setText("T");
        dieRow[3].setText("S");
        dieRow[4].setText("I");

        markDiceReleased();
        resetPlayerInfo();

        for (int i = 1; i < scorecard.length; i++) {
            scorecard[i].setText(game.getScorecard().getCategories()[i]);
        }
    }

    private void markDiceReleased() {
        for (Button b : dieRow) {
            b.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
        }
    }

    private void defineButtonEvents(Button rollButton, Button scoreButton, Button instructionButton, Button newGameButton, Button playerStatsButton, VBox rightNode) {
        for (int i = 0; i < dieRow.length; i++) {
            int die = i;
            dieRow[die].setOnAction(value -> {
                if (game.checkHoldStatus(die)) {
                    game.setHoldStatus(die, false);
                    dieRow[die].setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
                } else {
                    game.setHoldStatus(die, true);
                    dieRow[die].setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em; -fx-border-color: #138d75; -fx-border-width: 3px;");
                }
            });
        }

        // 4.2 Roll button
        rollButton.setOnAction((ActionEvent event) -> {

            if (game.getRollCounter() > 0) {
                int[] newValues = game.roll();
                for (int i = 0; i < dieRow.length; i++) {
                    dieRow[i].setText(Integer.toString(newValues[i]));
                }
            } else {
                markDiceReleased();
                alert.getAlert(2, 0);
            }

        });

        // 4.3 Instruction button
        instructionButton.setOnAction((ActionEvent event) -> {
            alert.getAlert(1, 0);
        });

        // 4.4 New game button
        newGameButton.setOnAction((ActionEvent event) -> {
            game.reset();
            reset();
            markDiceReleased();
        });
        
        playerStatsButton.setOnAction((ActionEvent event) -> {
            PlayerStatsView playerStatsView = new PlayerStatsView(game.getUser());
            Stage playerStatsWindow = playerStatsView.getPlayerStatsView();
            playerStatsWindow.show();
        });

        // 4.5 Score button
        scoreButton.setOnAction((ActionEvent event) -> {
            if (game.getRollCounter() == 3) {
                alert.getAlert(5, 0);
            } else {
                markDiceReleased();
                game.releaseAll();
                ScoreView scoreView = new ScoreView(game, scorecard, alert, rightNode);
                Stage scoreWindow = scoreView.getScoreView();
                scoreWindow.show();
            }
        });
    }
}
