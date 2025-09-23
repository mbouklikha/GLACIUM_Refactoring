package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.Hitbox;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;


import static org.junit.jupiter.api.Assertions.*;

class YetiTest {

    private Environnement environnement;
    private Sid sid;
    private Yeti yeti;

    @BeforeEach
    public void setup() throws Exception {
        // Création d'un environnement minimal avec terrain vide (pas de blocs solides)
        environnement = new Environnement(992, 576);

        // Création d'un Sid positionné
        sid = new Sid(environnement);

        // Création du Yeti avec référence à Sid
        yeti = new Yeti(environnement, sid);

        // Position initiale Yeti proche de Sid (x=90, y=350)
        yeti.setX(90);
        yeti.setY(350);
    }

    @Test
    public void testDirectionInitiale() {
        assertEquals("immobile", yeti.getDirection().get());
    }

    @Test
    public void testAgir_immobileQuandSidMort() {
        sid.decrementerPv(10); // Sid mort
        yeti.agir(Collections.emptySet());
        assertEquals("immobile", yeti.getDirection().get());
        assertFalse(yeti.isFrappeEnCours());
    }

    @Test
    public void testAgir_frappesEtDegats() {
        sid.setX(105); // à moins de 20 px de distance
        sid.setY(350); // même hauteur
        yeti.setX(100);
        yeti.setY(350);

        yeti.agir(Collections.emptySet());

        assertTrue(yeti.isFrappeEnCours());
        assertEquals("droite", yeti.getDirection().get());
        assertTrue(sid.getPv() <= 95); // peut être <95 si compteurDegats >= 30
    }

    @Test
    public void testAgir_deplacementSansCollision() {
        // Positionner Sid et Yeti à une distance correcte
        sid.setX(150); // dx = 50 : entre 20 et 180 : le Yeti doit bouger
        sid.setY(350);
        yeti.setX(100);
        yeti.setY(350);

        // Vider les blocs solides pour qu’il n’y ait pas de collision
        environnement.getTerrain().getHitboxBlocsSolides().clear();

        // Mise à jour de la hitbox du Yeti à sa position
        yeti.getHitbox().setPosition(yeti.getX(), yeti.getY());

        int positionAvant = yeti.getX();

        yeti.agir(Collections.emptySet());

        assertTrue(yeti.getX() > positionAvant, "Le Yeti doit s’être déplacé vers la droite");
        assertEquals("droite", yeti.getDirection().get());
    }


    @Test
    public void testGraviteFaitTomberYeti() {
        int[][] map = new int[20][20]; // inutilisé mais nécessaire

        yeti.setY(100); // assez haut pour tomber

        int initialY = yeti.getY();

        for (int i = 0; i < 20; i++) {
            yeti.appliquerGravite(map, 32);
            System.out.println("Step " + i + " Yeti Y = " + yeti.getY());
        }

        int finalY = yeti.getY();

        assertTrue(finalY > initialY, "Le Yeti doit tomber sous gravité");
        assertTrue(finalY <= 400, "Le Yeti ne doit pas passer sous le sol");
    }


    @Test
    public void testCollisionAvecBlocs() {
        ArrayList<Hitbox> blocs = new ArrayList<>();
        blocs.add(new Hitbox(100, 350, 60, 60)); // même position que le Yeti

        yeti.setX(100);
        yeti.setY(350);
        yeti.getHitbox().setPosition(100, 350); // met à jour la hitbox

        assertTrue(yeti.collisionAvecBlocs(blocs));
    }
}
