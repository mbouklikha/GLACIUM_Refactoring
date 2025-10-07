package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

public class Arc extends Arme {

    private final int DISTANCE_MAX = 320;
    private final int DEGATS = 30;

    public Arc(Terrain terrain, Inventaire inventaire, Sid sid){
        super("arc", terrain, inventaire, sid);
    }

    @Override
    public void fonction(int x, int y) {
        attaquer(x, y, DISTANCE_MAX, DEGATS, false);
    }
}
