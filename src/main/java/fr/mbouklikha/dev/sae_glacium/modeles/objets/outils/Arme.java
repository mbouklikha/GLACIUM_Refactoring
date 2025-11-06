package fr.mbouklikha.dev.sae_glacium.modeles.objets.outils;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Acteur;
import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Yeti;
import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Inventaire;

public abstract class Arme extends Outil {

    public Arme(String nom, Terrain terrain, Inventaire inventaire, Sid sid) {
        super(nom, terrain, inventaire, sid);
    }


    protected void attaquer(int x, int y, int distanceMax, int degats, boolean necessiteCollisionProche) {
        int sourisX = x * Terrain.TAILLE_BLOC;
        int sourisY = y * Terrain.TAILLE_BLOC;

        int persoX = getSid().getX();
        int persoY = getSid().getY() + 28;

        int dx = persoX - sourisX;
        int dy = persoY - sourisY;
        int distanceCarree = dx * dx + dy * dy;

        if (distanceCarree <= distanceMax * distanceMax) {
            for (Acteur a : getSid().getActeursAutour()) {
                if (a instanceof Yeti yeti) {
                    if (yeti.getHitbox().contientPoint(sourisX, sourisY)) {
                        if (!necessiteCollisionProche || yeti.getHitbox().collisionAvec(getSid().getHitbox())) {
                            yeti.decrementerPv(degats);
                            if (yeti.getPv() > 0) {
                                System.out.println(getNom() + " utilisé ! PV restants du Yeti : " + yeti.getPv());
                            } else {
                                System.out.println("Yeti mort !");
                            }
                        }
                    }
                }
            }
        }
    }
}
