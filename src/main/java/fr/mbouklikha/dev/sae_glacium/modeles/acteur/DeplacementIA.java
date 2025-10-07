package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import javafx.scene.input.KeyCode;

import java.util.Set;

public class DeplacementIA implements StrategieDeplacement{

    public void deplacer(Acteur acteur, Set<KeyCode> touches) {
        if (acteur instanceof Yeti yeti) {
            yeti.gererDeplacement(touches);
        }
        else if (acteur instanceof Sorcier sorcier) {
            sorcier.gererDeplacement(touches);
        }
    }
}
