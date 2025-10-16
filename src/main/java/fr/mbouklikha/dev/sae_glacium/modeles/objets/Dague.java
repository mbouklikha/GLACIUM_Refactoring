package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

public class Dague extends Arme {
    public Dague(Terrain terrain, Inventaire inventaire, Sid sid) {
        super("dague", terrain, inventaire, sid,
                new AttaqueMelee(10, 128));
    }
}
