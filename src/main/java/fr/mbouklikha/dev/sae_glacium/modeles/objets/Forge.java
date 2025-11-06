package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.ressources.Ressource;

public class Forge {

    private Environnement env;
    private FabriqueSimpleRessource fabrique;
    private Inventaire inv;
    private Sid sid;

    public Forge(FabriqueSimpleRessource fabrique, Inventaire inv, Sid sid) {
        this.env = Environnement.getInstance();
        this.fabrique = fabrique;
        this.inv = inv;
        this.sid = sid;
    }

    public Ressource fabriquerRessource (String type) {
        Ressource r = this.fabrique.creerRessource(type, env.getTerrain(), inv, sid);
        return r;
    }
}
