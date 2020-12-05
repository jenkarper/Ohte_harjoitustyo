package yahtzee.ui;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import yahtzee.domain.Game;
import yahtzee.domain.User;

/**
 *
 * @author pertjenn
 */
public class GameOverView {
    private Game game;
    
    public GameOverView(Game game) {
        this.game = game;
    }
    
    public Scene getScene() {
        
        try {
            game.updateUser();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        // Create layout components
        
        Label topNode = new Label("Peli ohi! Keräsit " + game.getGrandTotal() + " pistettä.");
        
        VBox centerNode = new VBox(new Label("TOP TEN"));
        centerNode.setPadding(new Insets(20, 20, 20, 20));
        centerNode.setSpacing(20);
        
        populateList(centerNode);
        
//        Label instruction = new Label("Syötä nimesi highscore-listausta varten:");
//        
//        HBox input = new HBox();
//        input.setPadding(new Insets(20, 20, 20, 20));
//        input.setSpacing(20);
//        
//        TextField nameField = new TextField();
//        Button save = new Button("Tallenna");
//        input.getChildren().addAll(nameField, save);
//        
//        centerNode.getChildren().addAll(instruction, input);
//        
//        VBox highscoreList = new VBox(new Label("TOP TEN"));
//        highscoreList.setPadding(new Insets(20, 20, 20, 20));
//        highscoreList.setSpacing(10);
        
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setTop(topNode);
        layout.setCenter(centerNode);
        
//        save.setOnAction(value -> {
//            highscoreList.getChildren().add(new Label(nameField.getText() + ": " + game.getGrandTotal() + " pistettä"));
//            layout.setCenter(highscoreList);
//        });
        
        Scene scene = new Scene(layout);
        return scene;
    }
    
    public void populateList(VBox centerNode) {
        List<String> list = game.getTopTen();
        
        for (int i = 0; i < list.size(); i++) {
            centerNode.getChildren().add(new Label(i+1 + ". " + list.get(i)));
        }
    }
}
