package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

public class Item {
    private Objets objet;
    private IntegerProperty quantite;

    public Item(Objets objet, int quantite) {
        this.objet = objet;
        this.quantite = new SimpleIntegerProperty(quantite);
    }

    public String getNom() {
        return objet.getNom();
    }

    public Objets getObjet(){
        return this.objet;
    }

    public IntegerProperty getQuantite() {
        return quantite;
    }


    public void ajouter(int nb) {
        this.quantite.set(quantite.get() + nb);
    }

    public void supprimer(ObservableList<Item> inventaire) {
        inventaire.remove(this);
    }




}


