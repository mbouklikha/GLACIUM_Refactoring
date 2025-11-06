package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.Hitbox;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.AttaqueComposite;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.AttaqueDistance;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.AttaqueMelee;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.StrategieAttaque;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Set;

public class Glacior extends Acteur {

    private final StringProperty direction = new SimpleStringProperty("immobile");
    private static final double GRAVITE = 0.4;
    private static final int VITESSE_X = 2;
    private double vitesseY = 0;
    private boolean frappeEnCours = false;
    private int compteurDegats = 0;
    private StrategieAttaque strategieAttaque;

    private Sid sid;
    private final Hitbox hitboxGlacior;

    public Glacior (Sid sid) {
        super("Yeti", 120, 500, 350);
        this.sid = sid;
        this.hitboxGlacior = new Hitbox(getX(), getY(), 66, 64);
        this.strategieDeplacement = new DeplacementIA();
    }

    @Override
    public Hitbox getHitbox() {
        return hitboxGlacior;
    }

    public StringProperty getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction.set(direction);
    }

    public void setStrategieAttaque(StrategieAttaque strategieAttaque) {
        this.strategieAttaque = strategieAttaque;
    }


    public boolean isFrappeEnCours() {
        return frappeEnCours;
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /*
     * Gère le comportement du Yeti selon la position de Sid et son état.
     * Si Sid est hors de portée verticale, le Yeti reste immobile.
     * Si Sid est proche horizontalement, le Yeti attaque et inflige des dégâts périodiques,
     * en ralentissant Sid.
     * Sinon, le Yeti se déplace horizontalement vers Sid en évitant les collisions.
     */

    @Override
    public void gererDeplacement(Set<KeyCode> touches) {
        if (!estEnCombatValide()) {
            setDirection("immobile");
            frappeEnCours = false;
            return;
        }

        int dx = sid.getX() - getX();
        int dy = sid.getY() - getY();

        if (Math.abs(dy) > 50) {
            resterImmobile();
        } else if (Math.abs(dx) <= 180) {
            seDeplacerVersSid(dx);
            attaquer(dx);
        } else {
            resterImmobile();
        }
    }



    private void attaquer(int dx) {
        frappeEnCours = true;
        setDirection(dx > 0 ? "droite" : "gauche");
        sid.setEstRalenti(true);

        if (strategieAttaque != null) {
            // On attaque Sid avec la stratégie composite
            strategieAttaque.attaquer(sid, sid.getX() / 32, sid.getY() / 32);
        }


        if (compteurDegats == 0) {
            sid.decrementerPv(5);
        }

        compteurDegats++;
        if (compteurDegats >= 30) {
            sid.decrementerPv(5);
            compteurDegats = 0;
        }
    }


    private void seDeplacerVersSid(int dx) {
        frappeEnCours = false;
        int deplacementX = (dx > 0 ? VITESSE_X : -VITESSE_X);

        hitboxGlacior.setPosition(getX() + deplacementX, getY());
        boolean collision = collisionAvecBlocs(hitboxGlacior, Environnement.getInstance().getTerrain().getHitboxBlocsSolides());

        if (!collision) {
            setX(getX() + deplacementX);
        }

        setDirection(dx > 0 ? "droite" : "gauche");
    }

    private boolean estEnCombatValide() {
        return getPv() > 0 && sid != null && sid.estVivant();
    }


    private void resterImmobile() {
        frappeEnCours = false;
        setDirection("immobile");
    }

    public void initialiserStrategieComposite() {
        AttaqueMelee attaqueMelee = new AttaqueMelee(10, 128);
        AttaqueDistance attaqueDistance = new AttaqueDistance(5, 256);
        ArrayList<StrategieAttaque> strategies = new ArrayList<>();
        strategies.add(attaqueMelee);
        strategies.add(attaqueDistance);
        AttaqueComposite attaqueComposite = new AttaqueComposite(strategies);
        this.setStrategieAttaque(attaqueComposite);
    }






////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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
            hitboxGlacior.setPosition(getX(), newY);
        } else {
            hitboxGlacior.setPosition(getX(), newY);
            if (!collisionAvecBlocs(hitboxGlacior, Environnement.getInstance().getTerrain().getHitboxBlocsSolides())) {
                setY(newY);
            } else {
                vitesseY = 0;
            }
        }
        hitboxGlacior.setPosition(getX(), getY());
    }



}
