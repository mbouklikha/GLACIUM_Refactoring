package fr.mbouklikha.dev.sae_glacium.modeles.monde;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Acteur;

import java.util.ArrayList;

public class Environnement {

    private static Environnement uniqueInstance = null;

    private int width, height;
    private ArrayList<Acteur> acteurs;
    private Terrain terrain;

    public Environnement(int width, int height) {
        this.width = width;
        this.height = height;
        this.acteurs = new ArrayList<>();
        this.terrain = new Terrain(); // création par défaut
    }

    public static Environnement getInstance() {
        if(uniqueInstance == null) {
            uniqueInstance = new Environnement(992, 576);
        }
        return uniqueInstance;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Acteur> getActeurs() {
        return acteurs;
    }

    public void ajouterActeur(Acteur a) {
        acteurs.add(a);
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    public boolean estDansLimite(int x, int y) {
        return (0 <= x && x < this.width && 0 <= y && y < this.height);
    }
}
