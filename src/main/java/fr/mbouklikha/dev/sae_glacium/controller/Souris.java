package fr.mbouklikha.dev.sae_glacium.controller;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Inventaire;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Objets;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Outil;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Ressource;
import fr.mbouklikha.dev.sae_glacium.vues.monde.TerrainVue;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;



public class Souris {

    private Sid sid;
    private Terrain terrain;
    private TerrainVue terrainVue;
    private TilePane tilePane;
    private final int DISTANCE_MAX = 128;
    private int TAILLE_BLOC = 32;// distance maximale autorisée (4 bloc)

    public Souris(Sid sid, Terrain terrain, TerrainVue terrainVue, TilePane tilePane) {
        this.sid = sid;
        this.terrain = terrain;
        this.terrainVue = terrainVue;
        this.tilePane = tilePane;
    }

    // Méthode pour utiliser l'objet en main
    private void utiliserObjet(int x, int y, MouseButton bouton) {
        Objets objets = sid.getObjetEnMain();

        if (objets != null) {
            if (bouton == MouseButton.PRIMARY && objets instanceof Outil) {
                objets.fonction(x, y);
            } else if (bouton == MouseButton.SECONDARY && objets instanceof Ressource) {
                objets.fonction(x, y);
            }

            terrainVue.afficherMap(tilePane);
        }
    }



    // Vérifie si la souris est dans la portée d’action
    private boolean estDansPortee(int sourisX, int sourisY) {
        int persoX = sid.getX();
        int persoY = sid.getY() + 28;

        int dx = persoX - sourisX;
        int dy = persoY - sourisY;
        int distanceCarree = dx * dx + dy * dy;

        return distanceCarree <= DISTANCE_MAX * DISTANCE_MAX;
    }


    // Gère les clic
    public void gererClic(MouseEvent event) {
        int sourisX = (int) event.getX();
        int sourisY = (int) event.getY();

        int blocX = sourisX / TAILLE_BLOC;
        int blocY = sourisY / TAILLE_BLOC;

        if (estDansPortee(sourisX, sourisY)) {
            utiliserObjet(blocX, blocY, event.getButton());
        }
    }

}
