package yahtzee.ui;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author pertjenn
 */
public class YahtzeeGUI extends Application {
    private PlayView playView;
    private Stage stage;

    @Override
    public void init() throws Exception {
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Jatsi");
        stage.setWidth(550);
        stage.setHeight(670);
        
        // Create startview
        
        
        // Create playview
        this.playView = new PlayView("Pelaaja");
        stage.setScene(playView.getScene());
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
