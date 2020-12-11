package yahtzee.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import yahtzee.domain.Game;

/**
 * Creates the score view of the game.
 * @author pertjenn
 */
public class ScoreView {

    private final Game game;
    private final Label[] sc;
    private final GameAlert alert;
    private final VBox playerInfo;
    private final GameOverView gov;

    public ScoreView(Game game, Label[] scorecard, GameAlert alert, VBox playerInfo) {
        this.game = game;
        this.sc = scorecard;
        this.alert = alert;
        this.playerInfo = playerInfo;
        this.gov = new GameOverView(game);
    }

    public Stage getScoreView() {

        // Create layout components
        Label instructions = new Label("Valitse kategoria, johon haluat pisteyttää heiton");
        Button ok = new Button("OK");
        Button cancel = new Button("Peruuta");
        styleButton(ok);
        styleButton(cancel);

        HBox buttons = new HBox(ok, cancel);
        buttons.setSpacing(20);

        RadioButton[] options = createOptions();
        ToggleGroup tg = createToggleGroup(options);
        VBox optionsList = createOptionsList(options);

        // Create layout
        BorderPane scoreLayout = new BorderPane();
        Insets insets = new Insets(30, 30, 30, 30);

        scoreLayout.setTop(instructions);
        scoreLayout.setCenter(optionsList);
        scoreLayout.setBottom(buttons);

        scoreLayout.setPadding(insets);
        scoreLayout.setMargin(instructions, insets);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);

        // Create stage and set scene
        Scene scene = new Scene(scoreLayout);
        Stage stage = new Stage();
        stage.setTitle("Pisteytys");
        stage.setScene(scene);

        // Define button actions
        ok.setOnAction(value -> {
            if (tg.getSelectedToggle() == null) {
                alert.getAlert(3, 0);
            } else {
                RadioButton selected = (RadioButton) tg.getSelectedToggle();
                int[] dice = game.getRoll().getValues();
                String[] categories = game.getScorecard().getCategories();
                int points = 0;

                for (int i = 1; i < sc.length; i++) {
                    if (selected.equals(options[i])) {
                        game.scoreRoll(i, dice);
                        points = game.checkScore(i, dice);
                        sc[i].setText(categories[i] + ": " + points);
                    }
                }
                game.resetRollCounter();

                if (game.getRoundCounter() == 0) {
                    markFinalScore();
                    Scene govScene = gov.getScene();
                    Stage govStage = new Stage();
                    govStage.setScene(govScene);
                    govStage.show();
                }
                stage.close();
            }
        });

        cancel.setOnAction(value -> {
            stage.close();
        });

        return stage;
    }

    private RadioButton[] createOptions() {
        RadioButton[] options = new RadioButton[16];
        String[] categories = game.getScorecard().getCategories();
        int[] dice = game.getRoll().getValues();

        for (int i = 1; i < options.length; i++) {
            options[i] = new RadioButton(categories[i] + ": " + game.checkScore(i, dice));
        }

        return options;
    }

    private ToggleGroup createToggleGroup(RadioButton[] options) {
        ToggleGroup tg = new ToggleGroup();

        for (int i = 1; i < options.length; i++) {
            options[i].setToggleGroup(tg);
        }

        return tg;
    }

    private VBox createOptionsList(RadioButton[] options) {
        VBox list = new VBox();
        list.setSpacing(10);
        int[] currentPoints = game.getScorecard().getPoints();

        for (int i = 1; i < options.length; i++) {
            if (currentPoints[i] == -1) {
                list.getChildren().add(options[i]);
            }
        }

        return list;
    }

    private Button createOKButton() {
        Button ok = new Button("OK");
        ok.setStyle("-fx-background-color: #e9f7ef; -fx-font-size: 1em; -fx-border-color:  #48c9b0; -fx-border-width: 1px;");

        return ok;
    }

    private void styleButton(Button b) {
        b.setStyle("-fx-background-color: #e9f7ef; -fx-font-size: 1em; -fx-border-color:  #48c9b0; -fx-border-width: 1px;");
    }
    
    private void markFinalScore() {
        Label upper = (Label) playerInfo.getChildren().get(2);
        upper.setText("Välisumma: " + game.getUpperTotal());
        
        Label bonus = (Label) playerInfo.getChildren().get(3);
        bonus.setText("Bonus: " + game.getBonus());
        
        Label total = (Label) playerInfo.getChildren().get(4);
        total.setText("Yhteensä: " + game.getGrandTotal());
    }
}
