package fr.mbouklikha.dev.sae_glacium.modeles.objets.outils;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Inventaire;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.StrategieAttaque;

public class Arme extends Outil {

    private final StrategieAttaque strategieAttaque;

    public Arme(String nom, Terrain terrain, Inventaire inventaire, Sid sid, StrategieAttaque strategie) {
        super(nom, terrain, inventaire, sid);
        this.strategieAttaque = strategie;
    }

    @Override
    public void fonction(int x, int y) {
        strategieAttaque.attaquer(getSid(), x, y);
    }
}