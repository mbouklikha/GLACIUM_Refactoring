package fr.mbouklikha.dev.sae_glacium.modeles.objets.outils;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Inventaire;

public class Dague extends Arme {

    private final int DISTANCE_MAX = 128;
    private final int DEGATS = 10;

    public Dague(Terrain terrain, Inventaire inventaire, Sid sid){
        super("dague", terrain, inventaire, sid);
    }

    @Override
    public void fonction(int x, int y) {
        attaquer(x, y, DISTANCE_MAX, DEGATS, true); // true = collision proche nécessaire
    }
}
