package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import javafx.scene.input.KeyCode;

import java.util.Set;

public class DeplacementJoueur implements StrategieDeplacement {

    public void deplacer(Acteur acteur, Set<KeyCode> touches) {
        acteur.gererDeplacement(touches);
    }
}
