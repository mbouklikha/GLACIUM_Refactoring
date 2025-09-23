package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.Hitbox;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Set;

public class Yeti extends Acteur {

    private final StringProperty direction = new SimpleStringProperty("immobile");
    private static final double GRAVITE = 0.4;
    private static final int VITESSE_X = 2;
    private double vitesseY = 0;
    private boolean frappeEnCours = false;
    private int compteurDegats = 0;

    private Sid sid;
    private final Environnement environnement;
    private final Hitbox hitboxYeti;

    public Yeti(Environnement env, Sid sid) {
        super("Yeti", 100, 800, 350, env);
        this.sid = sid;
        this.environnement = env;
        this.hitboxYeti = new Hitbox(getX(), getY(), 60, 60);
    }

    @Override
    public Hitbox getHitbox() {
        return hitboxYeti;
    }

    public StringProperty getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction.set(direction);
    }


    public boolean isFrappeEnCours() {
        return frappeEnCours;
    }


    /*
     * Gère le comportement du Yeti selon la position de Sid et son état.
     * Si Sid est hors de portée verticale, le Yeti reste immobile.
     * Si Sid est proche horizontalement, le Yeti attaque et inflige des dégâts périodiques,
     * en ralentissant Sid.
     * Sinon, le Yeti se déplace horizontalement vers Sid en évitant les collisions.
    */
    public void agir(Set<KeyCode> touches) {
        if (getPv() < 0 || sid == null || !sid.estVivant()) {
            setDirection("immobile");
            frappeEnCours = false;
        } else {

        int dx = sid.getX() - getX();
        int dy = sid.getY() - getY();

        if (Math.abs(dy) > 50) {
            frappeEnCours = false;
            setDirection("immobile");
        } else if (Math.abs(dx) <= 20) {
            frappeEnCours = true;
            setDirection(dx > 0 ? "droite" : "gauche");
            sid.setEstRalenti(true);

            if(compteurDegats == 0){
                sid.decrementerPv(5);
            }

            compteurDegats ++;

            if(compteurDegats >= 30) {
                sid.decrementerPv(5);
                compteurDegats = 0;
            }

        } else if (Math.abs(dx) <= 180) {
            frappeEnCours = false;
            int deplacementX = (dx > 0 ? VITESSE_X : -VITESSE_X);

            // Collision latérale comme pour Sid
            hitboxYeti.setPosition(getX() + deplacementX, getY());
            boolean collision = false;
            for (Hitbox bloc : environnement.getTerrain().getHitboxBlocsSolides()) {
                if (hitboxYeti.collisionAvec(bloc)) {
                    collision = true;
                    break;
                }
            }

            if (!collision) {
                setX(getX() + deplacementX);
            }

            setDirection(dx > 0 ? "droite" : "gauche");
            hitboxYeti.setPosition(getX(), getY());
        } else {
            frappeEnCours = false;
            setDirection("immobile");
            }
        }

        // Mise à jour finale de la hitbox
        hitboxYeti.setPosition(getX(), getY());
    }



    /*
     * Applique la gravité au Yeti en mettant à jour sa position verticale,
     * tout en gérant les collisions avec le terrain.
    */
    @Override
    public void appliquerGravite(int[][] map, int tailleBloc) {
        vitesseY += GRAVITE;
        int newY = (int) (getY() + vitesseY);

        int caseX = getX() / tailleBloc;
        int caseY = (newY + 60) / tailleBloc;

        if (caseY >= map.length || caseX >= map[0].length || caseX < 0) {
            setY(newY);
            hitboxYeti.setPosition(getX(), newY);
        } else {
            hitboxYeti.setPosition(getX(), newY);
            if (!collisionAvecBlocs(environnement.getTerrain().getHitboxBlocsSolides())) {
                setY(newY);
            } else {
                vitesseY = 0;
            }
        }
        hitboxYeti.setPosition(getX(), getY());
    }



    /*
     * Vérifie si la hitbox du Yeti entre en collision avec un des blocs solides.
    */
    public boolean collisionAvecBlocs(ArrayList<Hitbox> blocsSolides) {
        for (Hitbox bloc : blocsSolides) {
            if (hitboxYeti.collisionAvec(bloc)) {
                return true;
            }
        }
        return false;
    }
}
