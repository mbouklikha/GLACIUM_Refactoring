package fr.mbouklikha.dev.sae_glacium.vues.objet;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Inventaire;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Item;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Objets;
import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InventaireVue {

    private HBox conteneur;
    private VBox[] cases = new VBox[8];
    private ImageView[] images = new ImageView[8];
    private Label[] quantites = new Label[8];

    private Sid sid;
    private ObjetEnMainVue objetEnMainVue;

    public InventaireVue(HBox conteneur, Sid sid) {
        this.conteneur = conteneur;
        this.sid = sid;
    }


    /*
     * Initialise les cases de l'inventaire graphique.
     * Crée 8 cases avec image et quantité, configure les styles et événements souris.
     * Ajoute un listener sur la liste observable des items pour mettre à jour l'affichage.
    */
    public void initialiserCases(Inventaire inventaire) {
        conteneur.getChildren().clear();

        // Ajout listener pour l'observableList
        inventaire.getItems().addListener((ListChangeListener<Item>) change -> {
            mettreAJourInventaire(inventaire);
        });

        for (int i = 0; i < 8; i++) {
            VBox caseObjet = new VBox();
            caseObjet.setSpacing(5);
            caseObjet.setAlignment(Pos.CENTER);
            caseObjet.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-padding: 5;");

            ImageView imageView = new ImageView();
            imageView.setFitWidth(30);
            imageView.setFitHeight(30);

            Label quantiteLabel = new Label();
            final int index = i;

            caseObjet.setOnMouseClicked(event -> {
                if (index < inventaire.getItems().size()) {
                    Item nouvelItem = inventaire.getItems().get(index);
                    Objets nouvelObjet = nouvelItem.getObjet();

                    Objets ancienObjet = sid.getObjetEnMain();
                    if (ancienObjet == null || !ancienObjet.equals(nouvelObjet)) {
                        if (ancienObjet != null) {
                            inventaire.ajouterItem(ancienObjet);
                        }
                        sid.setObjetEnMain(nouvelObjet);
                        inventaire.retirerUnItem(nouvelObjet);
                        objetEnMainVue.mettreAJour();  // MAJ de l'objet en main
                    }
                }
            });

            caseObjet.setOnMouseEntered(e -> caseObjet.setStyle(
                    "-fx-border-color: yellow; -fx-border-width: 2; -fx-padding: 5;"
            ));
            caseObjet.setOnMouseExited(e -> caseObjet.setStyle(
                    "-fx-border-color: black; -fx-border-width: 2; -fx-padding: 5;"
            ));

            caseObjet.getChildren().addAll(imageView, quantiteLabel);
            conteneur.getChildren().add(caseObjet);

            cases[i] = caseObjet;
            images[i] = imageView;
            quantites[i] = quantiteLabel;
        }

    }


    /*
     * Met à jour l'affichage des cases de l'inventaire en fonction des items présents.
     * Affiche l'image et la quantité des objets ou vide la case si aucun item.
    */
    public void mettreAJourInventaire(Inventaire inventaire) {
        for (int i = 0; i < 8; i++) {
            if (i < inventaire.getItems().size()) {
                Item item = inventaire.getItems().get(i);

                String nomImage = item.getObjet().getNom().toLowerCase();
                Image image = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/item/" + nomImage + ".png"));
                images[i].setImage(image);
                quantites[i].textProperty().bind(Bindings.convert(item.getQuantite()));
            } else {
                images[i].setImage(null);  // Enlève l’image
                quantites[i].textProperty().unbind();  // Déconnecte la quantité
                quantites[i].setText("");  // Vide le texte
            }
        }
    }

    public void setObjetEnMainVue(ObjetEnMainVue objetEnMainVue) {
        this.objetEnMainVue = objetEnMainVue;
    }

    public boolean isVisible() {
        return conteneur.isVisible();
    }

}
