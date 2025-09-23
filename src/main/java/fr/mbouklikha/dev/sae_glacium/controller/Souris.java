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
    private int TAILLE_BLOC = 32;
    private TerrainVue terrainVue;
    private TilePane tilePane;
    private Inventaire inventaire;
    private final int DISTANCE_MAX = 128; // distance maximale autorisée (4 bloc)
    public boolean peutCasser = false;

    public Souris(Sid sid, Terrain terrain, TerrainVue terrainVue, TilePane tilePane) {
        this.sid = sid;
        this.terrain = terrain;
        this.terrainVue = terrainVue;
        this.tilePane = tilePane;
    }

    // Utiliser par les outil ou arme afin de casser ou attaquer
    private void clic_gauche(int x, int y) {
        Objets objets = sid.getObjetEnMain();
        if (objets != null && objets instanceof Outil) {
            objets.fonction(x, y);
        }
        terrainVue.afficherMap(tilePane);
    }

    // Utiliser par les ressources posable afin d'être poser
    private void clic_droit(int x, int y) {
        Objets objets = sid.getObjetEnMain();
        if (objets != null && objets instanceof Ressource) {
            objets.fonction(x, y);
        }
        terrainVue.afficherMap(tilePane);
    }


    // Gère les clic
    public void gererClic(MouseEvent event) {
        int sourisX = (int) event.getX();
        int sourisY = (int) event.getY();
        int blocX = sourisX / TAILLE_BLOC;
        int blocY = sourisY / TAILLE_BLOC;

        int persoX = sid.getX();
        int persoY = sid.getY() + 28;

        int dx = persoX - sourisX;
        int dy = persoY - sourisY;
        int distanceCarree = dx * dx + dy * dy;

        if (distanceCarree <= DISTANCE_MAX * DISTANCE_MAX) {
            if (event.getButton() == MouseButton.PRIMARY) {
                clic_gauche(blocX, blocY);
            } else if (event.getButton() == MouseButton.SECONDARY) {
                clic_droit(blocX, blocY);
            }
        }
    }

}
