package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.ressources.*;

public class FabriqueSimpleRessource {

    public Ressource creerRessource(String type, Terrain terrain, Inventaire inventaire, Sid sid) {
        Ressource r = null;
        switch (type) {
            case "bois" :
                r = new Bois(terrain, inventaire, sid);
                break;
            case "glace" :
                r = new Glace(terrain, inventaire, sid);
                break;
            case "neige" :
                r = new Neige(terrain, inventaire, sid);
                break;
            case "eclatFeu" :
                r = new EclatFeu(terrain, inventaire, sid);
                break;
        }
        return r;
    }
}
