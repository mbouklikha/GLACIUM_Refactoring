package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.Hitbox;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.EclatFeu;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Set;

public class Sorcier extends Acteur {

    private Sid sid;
    private static final double GRAVITE = 0.4;
    private double vitesseY = 0;

    private final StringProperty direction = new SimpleStringProperty("occupe");     // "discute" ou "occupe"
    private final StringProperty orientation = new SimpleStringProperty("droite");    // "gauche" ou "droite"
    private boolean occupe = true;
    private Hitbox hitboxSorcier;
    private Environnement environnement;

    public Sorcier(Environnement env, Sid sid) {
        super("Sorcier", 10, 995, 250, env);
        this.environnement = env;
        this.sid = sid;
        this.hitboxSorcier = new Hitbox(getX(), getY(), 45, 62);
    }


    /*
     * Définit le comportement du sorcier à chaque frame en fonction des touches pressées.
     * Le sorcier s'oriente vers Sid, détermine s'il est "occupé" ou "discute" selon la distance.
     * Si la hitbox du sorcier touche Sid, ajoute un éclat de feu à l'inventaire de Sid s'il n'en a pas déjà.
    */
    @Override
    public void agir(Set<KeyCode> touches) {
        int dx = sid.getX() - getX();
        EclatFeu feu = new EclatFeu(sid.getEnvironnement().getTerrain(), sid.getInventaire(), sid);

        // Orientation graphique
        if (dx > 0) {
            orientation.set("droite");
        } else {
            orientation.set("gauche");
        }

        // Logique "discute" vs "occupe"
        if (Math.abs(dx) > 50) {
            occupe = false;
            direction.set("discute");
        } else {
            occupe = true;
            direction.set("occupe");
        }

        if(sid.getHitbox().collisionAvec(this.hitboxSorcier)){
            if(!sid.getInventaire().aAssez(feu,1)){
                sid.getInventaire().ajouter(feu,1);
            }else{
                System.out.println("déjà récolté");
            }

        }
    }


    /*
     * Applique la gravité sur le sorcier, met à jour sa position verticale et gère les collisions avec les blocs solides.
     * Si une collision est détectée en descendant, arrête la chute et ajuste la position.
     * Si une collision est détectée en montant, stoppe la montée et repositionne juste en dessous du bloc.
    */
    @Override
    public void appliquerGravite(int[][] map, int tailleBloc) {
        vitesseY += GRAVITE;
        int newY = (int) (getY() + vitesseY);

        int caseX = getX() / tailleBloc;
        int caseY = (newY + 62) / tailleBloc;

        if (caseY < map.length && caseX < map[0].length && map[caseY][caseX] == 1 || map[caseY][caseX] == 2) {
            vitesseY = 0;
            enSaut = false;
            setY(caseY * tailleBloc - 62);
        } else {
            setY(newY);
        }

        hitboxSorcier.setPosition(getX(), newY);
        if (!collisionAvecBlocs(environnement.getTerrain().getHitboxBlocsSolides())) {
            setY(newY);
            hitboxSorcier.setPosition(getX(), newY);
        } else {
            if (vitesseY > 0) {
                // Collision avec le sol, on bloque la chute
                enSaut = false;
                setY((int) (getY() / tailleBloc) * tailleBloc);
            }
            else if (vitesseY < 0) {
                // En montée : on se cogne la tête
                // On aligne Sid juste en dessous du bloc touché
                setY((getY() / tailleBloc + 1) * tailleBloc);
                vitesseY = 0.1;
            }

        }
        hitboxSorcier.setPosition(getX(), getY());

    }


    /*
     * Vérifie si la hitbox du sorcier entre en collision avec un bloc solide.
    */
    public boolean collisionAvecBlocs(ArrayList<Hitbox> blocsSolides) {
        for (Hitbox bloc : blocsSolides) {
            if (hitboxSorcier.collisionAvec(bloc)) {
                return true; // collision détectée avec un bloc solide
            }
        }
        return false;
    }

    public StringProperty getDirection() {
        return direction;
    }

    public StringProperty getOrientation() {
        return orientation;
    }

    public Hitbox getHitbox() {
        return hitboxSorcier;
    }

    public boolean isOccupe() {
        return occupe;
    }
}