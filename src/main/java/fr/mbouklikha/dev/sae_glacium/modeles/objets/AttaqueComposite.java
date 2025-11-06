package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;

import java.util.ArrayList;

public class AttaqueComposite implements StrategieAttaque {

    private ArrayList<StrategieAttaque> attaques;

    public AttaqueComposite(ArrayList<StrategieAttaque> strategieAttaques) {
        this.attaques = new ArrayList<>(strategieAttaques);

    }

    // Ajouter une attaque à la composition
    public void ajouter(StrategieAttaque strategie) {
        this.attaques.add(strategie);
    }

    // Supprimer une attaque si besoin
    public void retirer(StrategieAttaque strategie) {
        this.attaques.remove(strategie);
    }

    public void attaquer(Sid sid, int x, int y) {
        for (StrategieAttaque attaque : attaques) {
            attaque.attaquer(sid, x, y);
        }
    }
}
