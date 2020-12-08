package yahtzee.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;

/**
 * Generates information alerts for the graphic interface.
 * @author pertjenn
 */
public class GameAlert {
    private Alert alert;
    
    public GameAlert() {
        alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
    }
    
    public Alert getAlert(int type, int points) {
        if (type == 1) {
            Label instructions = new Label("Heitä noppia klikkaamalla 'Heitä'. Voit lukita ja vapauttaa noppia klikkaamalla itse noppia. "
                    + "Sinulla on kolme heittoa vuorossa. Kun haluat pisteyttää heiton, klikkaa 'Pisteytä'. "
                    + "Jos heitto ei tuo pisteitä missään kategoriassa, sinun on valittava jokin kategoria ylivedettäväksi. "
                    + "Kyseisen kategorian pistemääräksi merkitään tällöin 0.");
            instructions.setWrapText(true);
            alert.getDialogPane().setContent(instructions);
            alert.getDialogPane().setPrefSize(400, 200);
            alert.setTitle("Ohje");
        } else if (type == 2) {
            alert.setContentText("Olet jo käyttänyt kolme heittoa tällä kierroksella!");
        } else if (type == 3) {
            alert.setContentText("Yksi kategoria on valittava!");
        } else if (type == 4) {
            alert.setContentText("Peli ohi! Keräsit " + points + " pistettä.");
        } else if (type == 5) {
            alert.setContentText("Heitä vähintään kerran!");
        }
        alert.show();
        
        return alert;
    }
}
