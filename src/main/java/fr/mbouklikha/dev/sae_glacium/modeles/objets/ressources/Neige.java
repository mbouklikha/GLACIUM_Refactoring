package fr.mbouklikha.dev.sae_glacium.modeles.objets.ressources;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Inventaire;

public class Neige extends Ressource {

    public Neige(Terrain terrain, Inventaire inventaire, Sid sid){
        super("neige", terrain, inventaire, sid);
    }

    public void fonction(int x, int y){
        poserBloc(x, y, 1); // id du bloc de neige
    }
}
