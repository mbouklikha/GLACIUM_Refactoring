package fr.mbouklikha.dev.sae_glacium.vues.objet;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TableCraftVue {

    private VBox conteneur;
    private HBox ligneCraft;

    private Button boutonPioche;
    private Button boutonDague;
    private Button boutonArc;

    public TableCraftVue() {
        conteneur = new VBox(10);
        conteneur.setAlignment(Pos.CENTER);
        conteneur.setStyle("-fx-background-color: rgba(0,0,0,0.7); -fx-padding: 10; -fx-border-color: white; -fx-border-width: 2;");
        conteneur.setVisible(false);

        ligneCraft = new HBox(10);
        ligneCraft.setAlignment(Pos.CENTER);

        // ✅ Chargement des images
        ImageView imagePioche = new ImageView(new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/item/pioche.png")));
        imagePioche.setFitWidth(32);
        imagePioche.setFitHeight(32);

        ImageView imageDague = new ImageView(new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/item/dague.png")));
        imageDague.setFitWidth(32);
        imageDague.setFitHeight(32);

        ImageView imageArc = new ImageView(new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/item/arc.png")));
        imageArc.setFitWidth(32);
        imageArc.setFitHeight(32);

        // ✅ Boutons avec image (et texte si tu veux)
        boutonPioche = new Button("", imagePioche); // texte vide + image
        boutonDague = new Button("", imageDague);
        boutonArc = new Button("", imageArc);

        ligneCraft.getChildren().addAll(boutonPioche, boutonDague, boutonArc);
        conteneur.getChildren().addAll(ligneCraft);
    }

    public VBox getConteneur() {
        return conteneur;
    }

    public void setVisible(boolean visible) {
        conteneur.setVisible(visible);
    }

    public boolean isVisible() {
        return conteneur.isVisible();
    }

    public Button getBoutonPioche() {
        return boutonPioche;
    }

    public Button getBoutonDague() {
        return boutonDague;
    }

    public Button getBoutonArc() {
        return boutonArc;
    }
}
