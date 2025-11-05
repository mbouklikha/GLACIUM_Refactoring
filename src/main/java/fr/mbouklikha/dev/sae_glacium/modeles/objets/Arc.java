package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

public class Arc extends Arme {
    public Arc(Terrain terrain, Inventaire inventaire, Sid sid) {
        super("arc", terrain, inventaire, sid,
                new AttaqueDistance(30, 600));
    }
}
