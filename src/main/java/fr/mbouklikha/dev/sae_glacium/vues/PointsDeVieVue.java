package fr.mbouklikha.dev.sae_glacium.vues;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PointsDeVieVue {
    private HBox conteneurFlammes;
    private Image flammePleine;
    private Image flammeDemi;
    private Image flammeVide;
    private Sid sid;
    private ImageView[] flammes = new ImageView[5];

    public PointsDeVieVue(Pane zoneJeu, Sid sid) {
        this.sid = sid;

        this.flammePleine = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/vie/flamme.png"));
        this.flammeDemi = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/vie/demi_flamme.png"));
        this.flammeVide = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/vie/flamme_gris.png"));

        conteneurFlammes = new HBox(1); // espace entre flammes
        conteneurFlammes.setLayoutX(zoneJeu.getPrefWidth() - 320); // position droite
        conteneurFlammes.setLayoutY(30); // position haut

        // Initialisation des 5 flammes
        for (int i = 0; i < 5; i++) {
            flammes[i] = new ImageView(flammePleine); // par défaut pleine
            flammes[i].setFitWidth(40);
            flammes[i].setFitHeight(40);
            conteneurFlammes.getChildren().add(flammes[i]);

        }

        zoneJeu.getChildren().add(conteneurFlammes);

        sid.pvProperty().addListener((obs, oldVal, newVal) -> mettreAJourFlammes());

    }

    // Met à  jour les flamme selon les dégats reçu par yeti
    public void mettreAJourFlammes() {
        int vie = sid.getPv(); // 0 à 50
        int reste = vie;

        for (int i = 0; i < 5; i++) {
            if (reste >= 10) {
                flammes[i].setImage(flammePleine);
                reste -= 10;
            } else if (reste >= 5) {
                flammes[i].setImage(flammeDemi);
                reste -= 5;
            } else {
                flammes[i].setImage(flammeVide);
            }
        }
    }
}


