package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

public class Glace extends Ressource {

    public Glace(Terrain terrain, Inventaire inventaire, Sid sid){
        super("glace", terrain, inventaire, sid);
    }

    public void fonction(int x, int y){
        poserBloc(x, y,2); // 2 = id du bloc de glace
    }
}
