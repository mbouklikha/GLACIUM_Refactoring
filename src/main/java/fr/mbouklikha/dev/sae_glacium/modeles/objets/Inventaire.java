package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventaire {

    private ObservableList<Item> items;
    private StringProperty objetEnMain = new SimpleStringProperty("pioche");


    public Inventaire() {
        this.items = FXCollections.observableArrayList();
    }

    public ObservableList<Item> getItems() {
        return items;
    }

    // Méthode privée pour retrouver un item existant
    private Item trouverItem(Objets objet) {
        for (Item item : items) {
            if (item.getNom().equals(objet.getNom())) {
                return item;
            }
        }
        return null;
    }


    /*
     * Ajoute un objet à l'inventaire avec une quantité de 1.
     * Si l'objet est déjà présent, augmente sa quantité de 1.
    */
    public void ajouterItem(Objets objetAAjouter) {
        Item item = trouverItem(objetAAjouter);
        if (item != null) {
            item.ajouter(1);
        } else {
            items.add(objetAAjouter.creerItem());
        }
    }


    /*
     * Retire un exemplaire d'un objet dans l'inventaire.
     * Si la quantité atteint 0, supprime l'objet de l'inventaire.
    */
    public void retirerUnItem(Objets objetARetirer) {
        Item item = trouverItem(objetARetirer);
        if(item != null){
            item.getQuantite().set(item.getQuantite().get() - 1);
        }   if (item.getQuantite().get() <= 0) {
            item.supprimer(items);
        }
    }



    /*
     * Vérifie si l'inventaire contient un objet donné.
    */
    public boolean contient(Objets objet) {
        return trouverItem(objet) != null;
    }



    /*
     * Vérifie si l'inventaire possède au moins une certaine quantité d'un objet.
    */
    public boolean aAssez(Objets objet, int quantiteRequise) {
        Item item = trouverItem(objet);
        return item != null && item.getQuantite().get() >= quantiteRequise;
    }



    /*
     * Ajoute une certaine quantité d'un objet dans l'inventaire.
     * Si l'objet est déjà présent, augmente la quantité.
     * Sinon, crée un nouvel item avec cette quantité.
    */
    public void ajouter(Objets objetAAjouter, int quantite) {
        Item item = trouverItem(objetAAjouter);
        if (item != null) {
            item.ajouter(quantite);
        } else {
            items.add(new Item(objetAAjouter, quantite));
        }
    }




    /*
     * Retire une certaine quantité d'un objet dans l'inventaire.
     * Si la quantité devient 0 ou moins, supprime l'objet de l'inventaire.
     * Sinon, met à jour la quantité.
    */
    public void retirer(Objets objetARetirer, int quantite) {
        Item item = trouverItem(objetARetirer);
        if (item != null) {
            int nouvelleQuantite = item.getQuantite().get() - quantite;
            if (nouvelleQuantite <= 0) {
                item.supprimer(items);
            } else {
                item.getQuantite().set(nouvelleQuantite);
            }
        }
    }

}

