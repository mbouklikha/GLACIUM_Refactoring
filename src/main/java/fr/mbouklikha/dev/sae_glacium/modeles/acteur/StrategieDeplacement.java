package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import javafx.scene.input.KeyCode;

import java.util.Set;

public interface StrategieDeplacement {
    void deplacer(Acteur acteur, Set<KeyCode> touches);
}
