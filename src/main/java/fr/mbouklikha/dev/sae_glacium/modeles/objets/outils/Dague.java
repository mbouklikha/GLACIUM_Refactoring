package fr.mbouklikha.dev.sae_glacium.modeles.objets.outils;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.AttaqueMelee;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Inventaire;

public class Dague extends Arme {
    public Dague(Terrain terrain, Inventaire inventaire, Sid sid) {
        super("dague", terrain, inventaire, sid,
                new AttaqueMelee(10, 128));
    }
}
