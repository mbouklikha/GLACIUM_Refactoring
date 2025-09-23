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




    /*
     * Ajoute un objet à l'inventaire avec une quantité de 1.
     * Si l'objet est déjà présent, augmente sa quantité de 1.
    */
    public void ajouterItem(Objets objetAAjouter) {
        boolean trouve = false;
        for (Item item : items) {
            if (item.getNom().equals(objetAAjouter.getNom())) {
                item.ajouter(1);
                trouve = true;
                break;
            }
        }

        if (!trouve) {
            items.add(objetAAjouter.creerItem());
        }
    }



    /*
     * Retire un exemplaire d'un objet dans l'inventaire.
     * Si la quantité atteint 0, supprime l'objet de l'inventaire.
    */
    public void retirerUnItem(Objets objetARetirer) {
        for (Item item : items) {
            if (item.getNom().equals(objetARetirer.getNom())) {
                item.getQuantite().set(item.getQuantite().get() - 1);
                if (item.getQuantite().get() <= 0) {
                    item.supprimer(items);
                }
                break;
            }
        }
    }



    /*
     * Vérifie si l'inventaire contient un objet donné.
    */
    public boolean contient(Objets objet) {
        for (Item item : items) {
            if (item.getObjet().equals(objet)) {
                return true;
            }
        }
        return false;
    }



    /*
     * Vérifie si l'inventaire possède au moins une certaine quantité d'un objet.
    */
    public boolean aAssez(Objets objet, int quantiteRequise) {
        for (Item item : items) {
            if (item.getNom().equals(objet.getNom())) {
                return item.getQuantite().get() >= quantiteRequise;
            }
        }
        return false;
    }



    /*
     * Ajoute une certaine quantité d'un objet dans l'inventaire.
     * Si l'objet est déjà présent, augmente la quantité.
     * Sinon, crée un nouvel item avec cette quantité.
    */
    public void ajouter(Objets objetAAjouter, int quantite) {
        boolean trouve = false;
        for (Item item : items) {
            if (item.getNom().equals(objetAAjouter.getNom())) {
                item.ajouter(quantite);
                trouve = true;
                break;
            }
        }
        if (!trouve) {
            items.add(new Item(objetAAjouter, quantite));
        }
    }




    /*
     * Retire une certaine quantité d'un objet dans l'inventaire.
     * Si la quantité devient 0 ou moins, supprime l'objet de l'inventaire.
     * Sinon, met à jour la quantité.
    */
    public void retirer(Objets objetARetirer, int quantite) {
        for (Item item : items) {
            if (item.getNom().equals(objetARetirer.getNom())) {
                int actuelle = item.getQuantite().get();
                int nouvelleQuantite = actuelle - quantite;
                if (nouvelleQuantite <= 0) {
                    item.supprimer(items);
                } else {
                    item.getQuantite().set(nouvelleQuantite);
                }
                break;
            }
        }
    }

}

