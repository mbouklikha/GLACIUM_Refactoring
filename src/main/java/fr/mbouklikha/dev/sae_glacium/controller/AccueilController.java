package fr.mbouklikha.dev.sae_glacium.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class AccueilController {

    @FXML
    private Button btnJouer;

    @FXML
    private void initialize() {
        btnJouer.setOnAction(e -> lancerJeu());
    }

    private void lancerJeu() {
        try {
            // Charger le fichier FXML de ta scène de jeu (celui existant)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/mbouklikha/dev/sae_glacium/vue1.fxml"));
            Parent root = loader.load();

            Scene sceneJeu = new Scene(root);

            // Récupérer la fenêtre (stage) depuis le bouton
            Stage stage = (Stage) btnJouer.getScene().getWindow();
            stage.setScene(sceneJeu);
            stage.setTitle("Glacium - Jeu");
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
