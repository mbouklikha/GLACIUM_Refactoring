package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Acteur;
import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Glacior;
import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Yeti;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

public class AttaqueMelee implements StrategieAttaque {

    private final int degats;
    private final int distanceMax;

    public AttaqueMelee(int degats, int distanceMax) {
        this.degats = degats;
        this.distanceMax = distanceMax;
    }

    @Override
    public void attaquer(Sid sid, int x, int y) {
        int sourisX = x * Terrain.TAILLE_BLOC;
        int sourisY = y * Terrain.TAILLE_BLOC;

        int persoX = sid.getX();
        int persoY = sid.getY() + 28;

        int dx = persoX - sourisX;
        int dy = persoY - sourisY;
        int distanceCarree = dx * dx + dy * dy;

        if (distanceCarree <= distanceMax * distanceMax) {
            for (Acteur a : sid.getActeursAutour()) {
                if (a instanceof Yeti yeti && yeti.getHitbox().collisionAvec(sid.getHitbox())) {
                    yeti.decrementerPv(degats);
                    System.out.println("Attaque de mélée yeti : -" + degats + " PV !");
                }
                else if (a instanceof Glacior glacior && glacior.getHitbox().collisionAvec(sid.getHitbox())) {
                    glacior.decrementerPv(degats);
                    System.out.println("Attaque de mélée glacior : -" + degats + " PV !");
                }
            }
        }
    }
}
