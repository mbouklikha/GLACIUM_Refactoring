package fr.mbouklikha.dev.sae_glacium.vues.objet;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Objets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ObjetEnMainVue {

    private VBox conteneur;
    private ImageView imageView;
    private Label labelNom;

    private Sid sid;

    public ObjetEnMainVue(Sid sid) {
        this.sid = sid;

        conteneur = new VBox();
        conteneur.setSpacing(5);
        conteneur.setAlignment(Pos.CENTER);
        conteneur.setStyle("-fx-border-color: grey; -fx-border-width: 2; -fx-padding: 5; -fx-background-color: rgba(255,255,255,0.7);");
        conteneur.setPrefWidth(65);
        conteneur.setPrefHeight(85);

        imageView = new ImageView();
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);

        labelNom = new Label("Rien");
        labelNom.setStyle("-fx-font-weight: bold;");

        conteneur.getChildren().addAll(imageView, labelNom);

        // Initialisation de l'affichage
        mettreAJour();
    }

    public VBox getConteneur() {
        return conteneur;
    }


    /*
     * Met à jour l'affichage de l'objet tenu en main.
     */
    public void mettreAJour() {
        Objets objetEnMain = sid.getObjetEnMain();
        if (objetEnMain == null || objetEnMain.getNom().isEmpty()){
            imageView.setImage(null);
            labelNom.setText("Vide");
        } else {
            Image image = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/item/" + objetEnMain.getNom() + ".png"));
            imageView.setImage(image);
            labelNom.setText(objetEnMain.getNom());
        }
    }


}
