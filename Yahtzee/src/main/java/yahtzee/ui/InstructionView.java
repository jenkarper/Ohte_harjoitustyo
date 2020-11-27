package yahtzee.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author pertjenn
 */
public class InstructionView {

    public InstructionView() {

    }

    public Stage createInstructionView() {

        Label content = createContent();
        Button close = createCloseButton();
        VBox layout = createLayout(content, close);
        layout.setAlignment(Pos.CENTER_RIGHT);

        Scene scene = new Scene(layout);
        Stage stage = new Stage();
        stage.setTitle("Ohjeet");
        stage.setScene(scene);

        close.setOnAction(value -> {
            stage.close();
        });

        return stage;
    }

    private Label createContent() {
        Label content = new Label("Heitä noppia klikkaamalla 'Heitä'. Voit lukita tai vapauttaa yhden tai useamman nopan klikkaamalla itse noppaa. "
                + "Kun haluat pisteyttää heiton, klikkaa 'Pisteytä'. "
                + "Jos heitto ei tuo pisteitä missään kategoriassa, sinun täytyy valita jokin kategorioista ylivedettäväksi. "
                + "Tällöin kyseisen kategorian pistemääräksi merkitään 0. ");
        content.setPadding(new Insets(5, 5, 5, 5));
        content.setWrapText(true);
        content.setMaxWidth(280);
        content.setTextAlignment(TextAlignment.JUSTIFY);

        return content;
    }

    private Button createCloseButton() {
        Button close = new Button("Sulje");
        close.setStyle("-fx-background-color: #e9f7ef; -fx-font-size: 1em; -fx-border-color:  #48c9b0; -fx-border-width: 1px;");

        return close;
    }

    private VBox createLayout(Label content, Button close) {
        VBox layout = new VBox(content, close);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setSpacing(10);

        return layout;
    }

}
