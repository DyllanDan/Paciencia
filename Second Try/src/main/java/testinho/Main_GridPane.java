package testinho;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Main_GridPane extends Application
{

    @Override
    public void start(Stage stage)
    {
        Deck fullDeck = new Deck();
        fullDeck.createFullDeck();
        fullDeck.shuffledDeck();

        SetDecks setDeck = new SetDecks();
        setDeck.SetupSetDeck(fullDeck);
        Pane root = new Pane();
        ImageManager manager = new ImageManager();
        root.setStyle("-fx-background-color: #2e7d32;");
        Render renderer = new Render(root, manager);
        renderer.renderGame(setDeck, fullDeck);
        Scene scene = new Scene(root, 700, 700);
        stage.setTitle("Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}