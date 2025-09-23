package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

public class EclatFeu extends Ressource {

    public EclatFeu(Terrain terrain, Inventaire inventaire, Sid sid){
        super("eclat_feu", terrain, inventaire, sid);
    }

    public void fonction(int x, int y){
        // Pas de fonctionnalité
    }
}
