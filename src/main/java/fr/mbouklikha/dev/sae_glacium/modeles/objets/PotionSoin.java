package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

public class PotionSoin extends Objets {

    private final int soin;

    public PotionSoin(Terrain terrain, Inventaire inventaire, Sid sid) {
        super("potionsoin", terrain, inventaire, sid);
        this.soin = 50;
    }

    @Override
    public void fonction(int x, int y) {
        Sid sid = getSid();

        if (sid.getPv() < 50) {
            sid.incrementerPv(soin);
            System.out.println("Sid boit une potion et récupère " + soin + " PV");
            getInventaire().retirerUnItem(this);
        } else {
            System.out.println("Sid a déjà tous ses PV");
        }
    }

    public Item creerItem() {
        return new Item(this, 1);
    }
}
