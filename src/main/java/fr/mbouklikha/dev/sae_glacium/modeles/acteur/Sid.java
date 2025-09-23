package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.Hitbox;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Objets;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.*;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Set;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Inventaire;

public class Sid extends Acteur {

    private final StringProperty directionProperty = new SimpleStringProperty("base");
    private final BooleanProperty estRalenti = new SimpleBooleanProperty(false);

    private Objets objetEnMain = null;

    private final double GRAVITE = 0.4;
    private final double SAUT_FORCE = -8;
    private double vitesseY = 0;
    private boolean aDejaSaute = false;
    private Inventaire inventaire;
    private int finRalenti;
    private Hitbox hitbox;
    private Environnement environnement;


    public Sid(Environnement env) {
        super("Sid", 50, 100, 100, env); // position initiale (100,100)
        this.environnement = env;
        this.hitbox = new Hitbox(getX(), getY(), 25, 55); // taille du perso
        this.inventaire = new Inventaire();

    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public StringProperty getDirection() {
        return directionProperty;
    }

    public void setDirection(String direction) {
        directionProperty.set(direction);
    }

    public Inventaire getInventaire() {
        return inventaire;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }


    // Méthodes deplacer abstract de Acteur
    public BooleanProperty estRalentiProperty() {
        return estRalenti;
    }

    public boolean isEstRalenti() {
        return estRalenti.get();
    }

    public void setEstRalenti(boolean ralenti) {
        estRalenti.set(ralenti);
    }


    /*
     * Fait agir Sid en fonction des touches pressées.
     * Gère déplacement horizontal, saut, et collisions.
    */
    @Override
    public void agir(Set<KeyCode> touches) {
        int nouvelleX = getX();

        if (finRalenti == 240) {
            finRalenti = 0;
            setEstRalenti(false);
        }

        if (touches.contains(KeyCode.D)) {
            nouvelleX = getX() + (isEstRalenti() ? 2 : 4);
            setDirection("droite");
            if (isEstRalenti()) finRalenti++;
        }
        if (touches.contains(KeyCode.Q)) {
            nouvelleX = getX() - (isEstRalenti() ? 2 : 4);
            setDirection("gauche");
            if (isEstRalenti()) finRalenti++;
        }

        // Tester collision avant de bouger
        hitbox.setPosition(nouvelleX, getY());
        if (!collisionAvecBlocs(environnement.getTerrain().getHitboxBlocsSolides())) {
            setX(nouvelleX);
        }
        hitbox.setPosition(getX(), getY());

        if (touches.contains(KeyCode.SPACE)) {
            if (!enSaut && !aDejaSaute) {
                vitesseY = SAUT_FORCE;
                enSaut = true;
                aDejaSaute = true; // on empêche le saut tant que la touche est enfoncée
            }
        } else {
            aDejaSaute = false; // la touche a été relâchée
        }


        // Met à jour la position de la hitbox
        hitbox.setPosition(getX(), getY());

    }


    /*
     * Applique la gravité à Sid en mettant à jour sa position verticale,
     * et gère les collisions verticales avec les blocs solides.
    */
    @Override
    public void appliquerGravite(int[][] map, int tailleBloc) {
        vitesseY += GRAVITE;
        int newY = (int) (getY() + vitesseY);

        int caseX = getX() / tailleBloc;
        int caseY = (newY + 56) / tailleBloc;

        if (caseY < map.length && caseX < map[0].length && map[caseY][caseX] == 1 || map[caseY][caseX] == 2) {
            vitesseY = 0;
            enSaut = false;
            setY(caseY * tailleBloc - 56);
        } else {
            setY(newY);
        }

        hitbox.setPosition(getX(), newY);
        if (!collisionAvecBlocs(environnement.getTerrain().getHitboxBlocsSolides())) {
            setY(newY);
            hitbox.setPosition(getX(), newY);
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
        hitbox.setPosition(getX(), getY());

    }


    /*
     * Vérifie si la hitbox de Sid entre en collision avec un des blocs solides.
    */
    public boolean collisionAvecBlocs(ArrayList<Hitbox> blocsSolides) {
        for (Hitbox bloc : blocsSolides) {
            if (hitbox.collisionAvec(bloc)) {
                return true; // collision détectée avec un bloc solide
            }
        }
        return false;
    }


    public Objets getObjetEnMain() {
        return objetEnMain;
    }


    public void setObjetEnMain(Objets objet) {
        this.objetEnMain = objet;
    }

}