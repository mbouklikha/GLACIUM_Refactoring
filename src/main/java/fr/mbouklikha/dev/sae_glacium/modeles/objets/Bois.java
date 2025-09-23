package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

public class Bois extends Ressource {

    public Bois(Terrain terrain, Inventaire inventaire, Sid sid){
        super("bois", terrain, inventaire, sid);
    }

    public void fonction(int x, int y){
        // Pas de fonctionnalité
    }
}
