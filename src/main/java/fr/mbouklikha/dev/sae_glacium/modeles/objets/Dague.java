package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Acteur;
import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Yeti;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;


public class Dague extends Outil {

    private final int DISTANCE_MAX = 128;

    public Dague(Terrain terrain, Inventaire inventaire, Sid sid){
        super("dague", terrain, inventaire, sid);

    }

    public void fonction(int x, int y){
        int sourisX = x * 32;
        int sourisY = y * 32;

        int persoX = getSid().getX();
        int persoY = getSid().getY() + 28;

        int dx = persoX - sourisX;
        int dy = persoY - sourisY;
        int distanceCarree = dx * dx + dy * dy;

        if (distanceCarree <= DISTANCE_MAX * DISTANCE_MAX) {
            for (Acteur a : getSid().getActeursAutour()) {
                if (a instanceof Yeti yeti) {
                    if (yeti.getHitbox().contientPoint(sourisX, sourisY)) {
                        if (yeti.getHitbox().collisionAvec(getSid().getHitbox())) {
                            yeti.decrementerPv(10);
                            System.out.println("Yeti frappé ! PV restants : " + yeti.getPv());
                        }
                    }
                }
            }
        }
    }
}
