package fr.mbouklikha.dev.sae_glacium;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fr/mbouklikha/dev/sae_glacium/Accueil.fxml"));
        Scene scene = new Scene(fxmlLoader.  load(), 1120, 640);
        stage.setTitle("Glacium");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
