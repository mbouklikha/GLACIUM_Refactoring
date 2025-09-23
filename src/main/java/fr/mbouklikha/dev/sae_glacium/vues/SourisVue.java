package fr.mbouklikha.dev.sae_glacium.vues;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SourisVue {

    private final Rectangle curseurBloc;
    private final Pane zoneJeu;

    private final int TAILLE_BLOC = 32;

    public SourisVue(Pane zoneJeu) {
        this.zoneJeu = zoneJeu;
        this.curseurBloc = new Rectangle(TAILLE_BLOC, TAILLE_BLOC);
        curseurBloc.setStroke(Color.YELLOW);
        curseurBloc.setFill(Color.TRANSPARENT);
        curseurBloc.setVisible(false); // pas visible au début
        zoneJeu.getChildren().add(curseurBloc);
    }


    /*
     * Met à jour la position du curseur de sélection de bloc en fonction de la position de la souris.
     * Création d'une zone morte pour pouvoir cliquer sur l'inventaire, craft et ne pas prioriser la pose, casse de bloc ou attaque.
    */
    public void majPositionCurseur(double sourisX, double sourisY) {

        int HAUTEUR_ZONE_MORTE = 132; // crée la zone morte en haut pour l'inventaire

        if (sourisY >= HAUTEUR_ZONE_MORTE) {
            int blocX = (int) sourisX / TAILLE_BLOC;
            int blocY = (int) sourisY / TAILLE_BLOC;

            curseurBloc.setLayoutX(blocX * TAILLE_BLOC);
            curseurBloc.setLayoutY(blocY * TAILLE_BLOC);
            curseurBloc.setVisible(true);
        } else {
            curseurBloc.setVisible(false); // cache le curseur si on est dans la zone morte
        }
    }

    public void cacherCurseur() {
        curseurBloc.setVisible(false);
    }
}
