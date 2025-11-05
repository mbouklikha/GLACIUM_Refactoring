package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

public class MarteauDeFeu extends Arme {

    public MarteauDeFeu(Terrain terrain, Inventaire inventaire, Sid sid) {
        super("marteau", terrain, inventaire, sid,
                new AttaqueMelee(200, 128));
    }
}
