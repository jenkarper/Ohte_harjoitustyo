package yahtzee.ui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

        // 1 Create components
        // 1.1 DIE ROW
        Button dieOne = new Button("J");
        dieOne.setPrefSize(50, 50);
        dieOne.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
        Button dieTwo = new Button("A");
        dieTwo.setPrefSize(50, 50);
        dieTwo.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
        Button dieThree = new Button("T");
        dieThree.setPrefSize(50, 50);
        dieThree.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
        Button dieFour = new Button("S");
        dieFour.setPrefSize(50, 50);
        dieFour.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");
        Button dieFive = new Button("I");
        dieFive.setPrefSize(50, 50);
        dieFive.setStyle("-fx-background-color: #48c9b0; -fx-font-size: 2em;");

        // Define action when a die button is clicked
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

        // 1.2 CURRENT SITUATION INFO LABEL
        Label player = new Label("Pelaaja: <pelaajanimi>");
        Label upperTotal = new Label("Välisumma: ");
        Label bonus = new Label("BONUS: ");
        Label grandTotal = new Label("Yhteensä: ");

        VBox currentSituation = new VBox(player, upperTotal, bonus, grandTotal);

        // 1.3 ROLL BUTTON
        Button rollButton = new Button("Heitä");
        rollButton.setStyle("-fx-background-color: #e9f7ef; -fx-font-size: 1em; -fx-border-color:  #48c9b0; -fx-border-width: 1px;");

        // Define roll action
        rollButton.setOnAction(value -> {
            int[] newValues = game.roll();
            dieOne.setText(Integer.toString(newValues[0]));
            dieTwo.setText(Integer.toString(newValues[1]));
            dieThree.setText(Integer.toString(newValues[2]));
            dieFour.setText(Integer.toString(newValues[3]));
            dieFive.setText(Integer.toString(newValues[4]));
        });

        // 1.4 SCORE BUTTON
        Button scoreButton = new Button("Pisteytä");
        scoreButton.setStyle("-fx-background-color: #e9f7ef; -fx-font-size: 1em; -fx-border-color:  #48c9b0; -fx-border-width: 1px;");

        // Scoring a roll opens a new window
        // 1.5 INSTRUCTION BUTTON
        Button instructionButton = new Button("Kuinka peli toimii?");
        instructionButton.setStyle("-fx-background-color: #e9f7ef; -fx-font-size: 1em; -fx-border-color:  #48c9b0; -fx-border-width: 1px;");
        instructionButton.setOnAction((ActionEvent event) -> {
            Label instruction = new Label();
            instruction.setText("Heitä noppia klikkaamalla 'Heitä'. Voit lukita tai vapauttaa yhden tai useamman nopan klikkaamalla itse noppaa. "
                    + "Kun haluat pisteyttää heiton, klikkaa 'Pisteytä'. "
                    + "Jos heitto ei tuo pisteitä missään kategoriassa, sinun täytyy valita jokin kategorioista ylivedettäväksi. "
                    + "Tällöin kyseisen kategorian pistemääräksi merkitään 0. ");
            instruction.setPadding(new Insets(5, 5, 5, 5));
            instruction.setWrapText(true);
            instruction.setMaxWidth(280);
            instruction.setTextAlignment(TextAlignment.JUSTIFY);
            
            Button closeButton = new Button("Sulje");
            closeButton.setStyle("-fx-background-color: #e9f7ef; -fx-font-size: 1em; -fx-border-color:  #48c9b0; -fx-border-width: 1px;");
            
            VBox instructionView = new VBox(instruction, closeButton);
            instructionView.setPadding(new Insets(10, 10, 10, 10));
            instructionView.setSpacing(10);
            
            Scene instructionScene = new Scene(instructionView);
            Stage instructionWindow = new Stage();
            instructionWindow.setTitle("Ohjeet");
            instructionWindow.setScene(instructionScene);
            instructionWindow.show();
            
            closeButton.setOnAction(value -> {
                instructionWindow.close();
            });
        });

        // 2 Create layouts
        // 2.1 CENTER NODE FOR BUTTONS AND INSTRUCTIONS
        VBox centerNode = new VBox();
        centerNode.setPadding(new Insets(20, 20, 20, 20));
        centerNode.setSpacing(20);
        centerNode.getChildren().addAll(rollButton, scoreButton);

        // 2.2 LEFT NODE FOR SCORECARD
        VBox scorecard = new VBox();
        Label scorecardTitle = new Label("PÖYTÄKIRJA");
        scorecardTitle.setFont(new Font("Arial", 18));

        Label ones = new Label("Ykköset: ");
        Label twos = new Label("Kakkoset: ");
        Label threes = new Label("Kolmoset: ");
        Label fours = new Label("Neloset: ");
        Label fives = new Label("Viitoset: ");
        Label sixes = new Label("Kuutoset: ");
        Label pair = new Label("Pari: ");
        Label twoPairs = new Label("Kaksi paria: ");
        Label threeKind = new Label("Kolmiluku: ");
        Label fourKind = new Label("Neliluku: ");
        Label smallStraight = new Label("Pieni suora: ");
        Label largeStraight = new Label("Iso suora: ");
        Label fullHouse = new Label("Mökki: ");
        Label chance = new Label("Sattuma: ");
        Label yahtzee = new Label("Jatsi: ");

        scorecard.getChildren().addAll(scorecardTitle, ones, twos, threes, fours, fives, sixes,
                pair, twoPairs, threeKind, fourKind, smallStraight, largeStraight,
                fullHouse, chance, yahtzee);
        
        // 2.3 BOTTOM NODE FOR INSTRUCTIONS
        HBox bottomNode = new HBox(instructionButton);

        // 2.3 PRIMARY LAYOUT PLAYVIEW
        BorderPane playView = new BorderPane();
        playView.setPadding(new Insets(40, 40, 40, 40));
        playView.setTop(diePane);
        diePane.setAlignment(Pos.CENTER);
        playView.setCenter(centerNode);
        playView.setLeft(scorecard);
        playView.setRight(currentSituation);
        playView.setBottom(bottomNode);
        bottomNode.setAlignment(Pos.BOTTOM_RIGHT);

        // 2.4 SCORE SCENE
        Label scoreInstruction = new Label();
        scoreInstruction.setText("Valitse kategoria, johon haluat pisteyttää heiton!");

        // Create radio buttons for all categories
        scoreButton.setOnAction((ActionEvent event) -> {

            // Create elements in scoreview
            ToggleGroup catGroup = new ToggleGroup();
            VBox scoreOptions = new VBox();
            scoreOptions.setSpacing(10);
            Button scoreOK = new Button("OK");
            scoreOK.setStyle("-fx-background-color: #e9f7ef; -fx-font-size: 1em; -fx-border-color:  #48c9b0; -fx-border-width: 1px;");
            Button scoreCancel = new Button("Peruuta");
            scoreCancel.setStyle("-fx-background-color: #e9f7ef; -fx-font-size: 1em; -fx-border-color:  #48c9b0; -fx-border-width: 1px;");
            HBox scoreViewButtons = new HBox(scoreOK, scoreCancel);
            scoreViewButtons.setSpacing(20);

            // Add radiobuttons to catList
            int[] dice = game.getRoll().getValues();

            RadioButton catOnes = new RadioButton("Ykköset: " + game.checkScore(1, dice));
            catOnes.setToggleGroup(catGroup);
            RadioButton catTwos = new RadioButton("Kakkoset: " + game.checkScore(2, dice));
            catTwos.setToggleGroup(catGroup);
            RadioButton catThrees = new RadioButton("Kolmoset: " + game.checkScore(3, dice));
            catThrees.setToggleGroup(catGroup);
            RadioButton catFours = new RadioButton("Neloset: " + game.checkScore(4, dice));
            catFours.setToggleGroup(catGroup);
            RadioButton catFives = new RadioButton("Viitoset: " + game.checkScore(5, dice));
            catFives.setToggleGroup(catGroup);
            RadioButton catSixes = new RadioButton("Kuutoset: " + game.checkScore(6, dice));
            catSixes.setToggleGroup(catGroup);
            RadioButton catPair = new RadioButton("Pari: " + game.checkScore(7, dice));
            catPair.setToggleGroup(catGroup);
            RadioButton catTwoPairs = new RadioButton("Kaksi paria: " + game.checkScore(8, dice));
            catTwoPairs.setToggleGroup(catGroup);
            RadioButton catThreeKind = new RadioButton("Kolmiluku: " + game.checkScore(9, dice));
            catThreeKind.setToggleGroup(catGroup);
            RadioButton catFourKind = new RadioButton("Neliluku: " + game.checkScore(10, dice));
            catFourKind.setToggleGroup(catGroup);
            RadioButton catSmallStraight = new RadioButton("Pieni suora: " + game.checkScore(11, dice));
            catSmallStraight.setToggleGroup(catGroup);
            RadioButton catLargeStraight = new RadioButton("Iso suora: " + game.checkScore(12, dice));
            catLargeStraight.setToggleGroup(catGroup);
            RadioButton catFullHouse = new RadioButton("Mökki: " + game.checkScore(13, dice));
            catFullHouse.setToggleGroup(catGroup);
            RadioButton catChance = new RadioButton("Sattuma: " + game.checkScore(14, dice));
            catChance.setToggleGroup(catGroup);
            RadioButton catYahtzee = new RadioButton("Jatsi: " + game.checkScore(15, dice));
            catYahtzee.setToggleGroup(catGroup);

            scoreOptions.getChildren().addAll(catOnes, catTwos, catThrees, catFours, catFives, catSixes,
                    catPair, catTwoPairs, catThreeKind, catFourKind, catSmallStraight, catLargeStraight,
                    catFullHouse, catChance, catYahtzee);

            // Create scoreview layout
            BorderPane scoreView = new BorderPane();
            scoreView.setPadding(new Insets(40, 40, 40, 40));
            scoreView.setTop(scoreInstruction);
            scoreView.setBottom(scoreViewButtons);
            scoreViewButtons.setAlignment(Pos.BOTTOM_RIGHT);
            scoreView.setCenter(scoreOptions);

            // Set scorescene
            Scene scoreScene = new Scene(scoreView);
            Stage scoreWindow = new Stage();
            scoreWindow.setTitle("Pisteytys");
            scoreWindow.setScene(scoreScene);
            scoreWindow.show();

            // 
            scoreOK.setOnAction(value -> {
                RadioButton selected = (RadioButton) catGroup.getSelectedToggle();
                int points = 0;
                if (selected.equals(catOnes)) {
                    game.scoreRoll(1, dice);
                    points = game.checkScore(1, dice);
                    ones.setText("Ykköset: " + points);
                } else if (selected.equals(catTwos)) {
                    game.scoreRoll(2, dice);
                    points = game.checkScore(2, dice);
                    twos.setText("Kakkoset: " + points);
                } else if (selected.equals(catThrees)) {
                    game.scoreRoll(3, dice);
                    points = game.checkScore(3, dice);
                    threes.setText("Kolmoset: " + points);
                } else if (selected.equals(catFours)) {
                    game.scoreRoll(4, dice);
                    points = game.checkScore(4, dice);
                    threes.setText("Neloset: " + points);
                } else if (selected.equals(catFives)) {
                    game.scoreRoll(5, dice);
                    points = game.checkScore(5, dice);
                    threes.setText("Viitoset: " + points);
                } else if (selected.equals(catSixes)) {
                    game.scoreRoll(6, dice);
                    points = game.checkScore(6, dice);
                    threes.setText("Kuutoset: " + points);
                } else if (selected.equals(catPair)) {
                    game.scoreRoll(7, dice);
                    points = game.checkScore(7, dice);
                    threes.setText("Pari: " + points);
                } else if (selected.equals(catTwoPairs)) {
                    game.scoreRoll(8, dice);
                    points = game.checkScore(8, dice);
                    threes.setText("Kaksi paria: " + points);
                } else if (selected.equals(catThreeKind)) {
                    game.scoreRoll(9, dice);
                    points = game.checkScore(9, dice);
                    threes.setText("Kolmiluku: " + points);
                } else if (selected.equals(catFourKind)) {
                    game.scoreRoll(10, dice);
                    points = game.checkScore(10, dice);
                    threes.setText("Neliluku: " + points);
                } else if (selected.equals(catSmallStraight)) {
                    game.scoreRoll(11, dice);
                    points = game.checkScore(11, dice);
                    threes.setText("Pieni suora: " + points);
                } else if (selected.equals(catLargeStraight)) {
                    game.scoreRoll(12, dice);
                    points = game.checkScore(12, dice);
                    threes.setText("Iso suora: " + points);
                } else if (selected.equals(catFullHouse)) {
                    game.scoreRoll(13, dice);
                    points = game.checkScore(13, dice);
                    threes.setText("Mökki: " + points);
                } else if (selected.equals(catChance)) {
                    game.scoreRoll(14, dice);
                    points = game.checkScore(14, dice);
                    threes.setText("Sattuma: " + points);
                } else if (selected.equals(catYahtzee)) {
                    game.scoreRoll(15, dice);
                    points = game.checkScore(15, dice);
                    threes.setText("Jatsi: " + points);
                }
                scoreWindow.close();
            });

            scoreCancel.setOnAction(value -> {
                scoreWindow.close();
            });
        });

        // 3 Set the scene
        Scene playScene = new Scene(playView);
        stage.setScene(playScene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    private VBox createScorecard() {
        VBox sc = new VBox();

        Label scorecardTitle = new Label("PÖYTÄKIRJA");
        scorecardTitle.setFont(new Font("Arial", 18));

        Label ones = new Label("Ykköset: ");
        Label twos = new Label("Kakkoset: ");
        Label threes = new Label("Kolmoset: ");
        Label fours = new Label("Neloset: ");
        Label fives = new Label("Viitoset: ");
        Label sixes = new Label("Kuutoset: ");
        Label pair = new Label("Pari: ");
        Label twoPairs = new Label("Kaksi paria: ");
        Label threeKind = new Label("Kolmiluku: ");
        Label fourKind = new Label("Neliluku: ");
        Label smallStraight = new Label("Pieni suora: ");
        Label largeStraight = new Label("Iso suora: ");
        Label fullHouse = new Label("Mökki: ");
        Label chance = new Label("Sattuma: ");
        Label yahtzee = new Label("Jatsi: ");

        sc.getChildren().addAll(scorecardTitle, ones, twos, threes, fours, fives, sixes,
                pair, twoPairs, threeKind, fourKind, smallStraight, largeStraight,
                fullHouse, chance, yahtzee);

        return sc;
    }

    private VBox createCatRadioButtons(ToggleGroup catGroup) {
        VBox list = new VBox();
        list.setSpacing(10);
        int[] dice = game.getRoll().getValues();

        RadioButton catOnes = new RadioButton("Ykköset: " + game.checkScore(1, dice));
        catOnes.setToggleGroup(catGroup);
        RadioButton catTwos = new RadioButton("Kakkoset: " + game.checkScore(2, dice));
        catTwos.setToggleGroup(catGroup);
        RadioButton catThrees = new RadioButton("Kolmoset: " + game.checkScore(3, dice));
        catThrees.setToggleGroup(catGroup);
        RadioButton catFours = new RadioButton("Neloset: " + game.checkScore(4, dice));
        catFours.setToggleGroup(catGroup);
        RadioButton catFives = new RadioButton("Viitoset: " + game.checkScore(5, dice));
        catFives.setToggleGroup(catGroup);
        RadioButton catSixes = new RadioButton("Kuutoset: " + game.checkScore(6, dice));
        catSixes.setToggleGroup(catGroup);
        RadioButton catPair = new RadioButton("Pari: " + game.checkScore(7, dice));
        catPair.setToggleGroup(catGroup);
        RadioButton catTwoPairs = new RadioButton("Kaksi paria: " + game.checkScore(8, dice));
        catTwoPairs.setToggleGroup(catGroup);
        RadioButton catThreeKind = new RadioButton("Kolmiluku: " + game.checkScore(9, dice));
        catThreeKind.setToggleGroup(catGroup);
        RadioButton catFourKind = new RadioButton("Neliluku: " + game.checkScore(10, dice));
        catFourKind.setToggleGroup(catGroup);
        RadioButton catSmallStraight = new RadioButton("Pieni suora: " + game.checkScore(11, dice));
        catSmallStraight.setToggleGroup(catGroup);
        RadioButton catLargeStraight = new RadioButton("Iso suora: " + game.checkScore(12, dice));
        catLargeStraight.setToggleGroup(catGroup);
        RadioButton catFullHouse = new RadioButton("Mökki: " + game.checkScore(13, dice));
        catFullHouse.setToggleGroup(catGroup);
        RadioButton catChance = new RadioButton("Sattuma: " + game.checkScore(14, dice));
        catChance.setToggleGroup(catGroup);
        RadioButton catYahtzee = new RadioButton("Jatsi: " + game.checkScore(15, dice));
        catYahtzee.setToggleGroup(catGroup);

        list.getChildren().addAll(catOnes, catTwos, catThrees, catFours, catFives, catSixes,
                catPair, catTwoPairs, catThreeKind, catFourKind, catSmallStraight, catLargeStraight,
                catFullHouse, catChance, catYahtzee);

        return list;
    }

}
