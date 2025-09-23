package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

public abstract class Ressource extends Objets {

    public Ressource(String nom, Terrain terrain, Inventaire inventaire, Sid sid) {
        super(nom, terrain, inventaire, sid);
    }

    @Override
    public Item creerItem() {
        return new Item(this, 1);
    }

    public String getType() {
        return "Ressource";
    }

    public abstract void fonction(int x, int y);

}
