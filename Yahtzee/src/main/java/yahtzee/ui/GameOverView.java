package yahtzee.ui;

import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import yahtzee.domain.Game;

/**
 * Creates the game over view of the game and updates database.
 *
 * @author pertjenn
 */
public class GameOverView {

    private final Game game;

    public GameOverView(Game game) {
        this.game = game;
    }

    public Scene getScene() {

        // Update database
        
        game.updateUserStats(game.getGrandTotal());

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

        // Create layout
        
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setTop(topNode);
        layout.setCenter(centerNode);

        // Create scene
        
        Scene scene = new Scene(layout);

        return scene;
    }

    public void populateList(VBox centerNode) {
        
        List<String> list = game.getTopTen();

        for (int i = 0; i < list.size(); i++) {
            Label l = new Label(i + 1 + ". " + list.get(i));
            if (list.get(i).equals(game.getPlayer() + "\t" + game.getGrandTotal())) {
                l.setStyle("-fx-font-weight: bold");
            }
            centerNode.getChildren().add(l);
            if (i == 9) {
                break;
            }
        }
    }
}
