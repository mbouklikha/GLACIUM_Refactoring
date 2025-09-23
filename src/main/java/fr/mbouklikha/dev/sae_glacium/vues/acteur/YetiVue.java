package fr.mbouklikha.dev.sae_glacium.vues.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Yeti;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Objects;

public class YetiVue {

    private final Yeti yeti;
    private final ImageView imageView;

    private final Image imageDroite;
    private final Image imageGauche;
    private final Image imageBase;
    private final Image imageFrappeDroite;
    private final Image imageFrappeGauche;

    public YetiVue(Yeti yeti, Pane zoneJeu) {
        this.yeti = yeti;

        Text finJeuTexte = new Text("Bravo ! Fin du jeu !");
        finJeuTexte.setFill(Color.RED);
        finJeuTexte.setFont(Font.font("Arial", 40));
        finJeuTexte.setVisible(false);
        finJeuTexte.setX(390);  // Ajuste la position selon ta fenêtre
        finJeuTexte.setY(160);

        zoneJeu.getChildren().add(finJeuTexte);


        imageDroite = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/fr/mbouklikha/dev/sae_glacium/images/YETI/D/H2.png")));
        imageGauche = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/fr/mbouklikha/dev/sae_glacium/images/YETI/G/H2.png")));
        imageBase = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/fr/mbouklikha/dev/sae_glacium/images/YETI/YETI_IMMOBILE.png")));

        imageFrappeDroite = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/fr/mbouklikha/dev/sae_glacium/images/YETI/D/H3.png")));
        imageFrappeGauche = new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/fr/mbouklikha/dev/sae_glacium/images/YETI/G/H3.png")));

        imageView = new ImageView(imageBase);
        imageView.setFitWidth(64);
        imageView.setFitHeight(64);

        imageView.xProperty().bind(yeti.getXProperty().asObject());
        imageView.yProperty().bind(yeti.getYProperty().asObject());

        // Met à jour l’image à chaque changement de direction ou frappe
        yeti.getDirection().addListener((obs, oldDir, newDir) -> updateImage());

        yeti.getXProperty().addListener((obs, oldVal, newVal) -> {
            if (!yeti.estVivant()) {
                imageView.setVisible(false);
            }
        });

        // AnimationTimer pour mise à jour continue (ex: frappe)
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!yeti.estVivant()) {
                    imageView.setVisible(false);
                    finJeuTexte.setVisible(true);
                    this.stop();
                } else {
                    updateImage();
                }
            }
        }.start();

        zoneJeu.getChildren().add(imageView);
    }

    private void updateImage() {
        if (yeti.isFrappeEnCours()) {
            switch (yeti.getDirection().get()) {
                case "droite":
                    imageView.setImage(imageFrappeDroite);
                    break;
                case "gauche":
                    imageView.setImage(imageFrappeGauche);
                    break;
                default:
                    imageView.setImage(imageBase);
            }
        } else {
            switch (yeti.getDirection().get()) {
                case "droite":
                    imageView.setImage(imageDroite);
                    break;
                case "gauche":
                    imageView.setImage(imageGauche);
                    break;
                default:
                    imageView.setImage(imageBase);
            }
        }
    }

    public ImageView getImageView() {
        return imageView;
    }
}
