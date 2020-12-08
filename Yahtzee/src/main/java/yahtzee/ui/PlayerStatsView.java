package yahtzee.ui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import yahtzee.domain.User;

/**
 * Creates the view for current player's statistics
 * 
 * @author pertjenn
 */
public class PlayerStatsView {
    private final User user;
    
    public PlayerStatsView(User user) {
        this.user = user;
    }
    
    public Stage getPlayerStatsView() {
        
        // Create layout components
        
        Label header = new Label("PELAAJATILASTO");
        Label name = new Label("Pelaaja: " + user.getUsername());
        Label games = new Label("Pelejä pelattu: " + user.getGamesPlayed());
        
        Label high = new Label();
        Label low = new Label();
        
        if (user.getGamesPlayed() == 0) {
            high.setText("Korkein tulos: -");
            low.setText("Matalin tulos: -");
        } else {
            high.setText("Korkein tulos: " + user.getHighScore());
            low.setText("Matalin tulos: " + user.getLowScore());
        }
        
        Button close = new Button("Sulje");
        close.setStyle("-fx-background-color: #e9f7ef; -fx-font-size: 1em; -fx-border-color:  #48c9b0; -fx-border-width: 1px;");
        
        // Create layout
        
        VBox layout = new VBox(header, name, high, low, games, close);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setSpacing(10);
        
        // Create stage and set scene
        
        Scene scene = new Scene(layout);
        Stage stage = new Stage();
        stage.setScene(scene);
        
        // Define button action
        
        close.setOnAction((ActionEvent event) -> {
            stage.close();
        });
        
        return stage;
    }
}
