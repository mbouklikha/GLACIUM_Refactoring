package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;


public abstract class Objets {
    private String nom;
    private Terrain terrain;
    private Inventaire inventaire;
    private Sid sid;


    public Objets(String nom, Terrain terrain, Inventaire inventaire, Sid sid) {
        this.nom = nom;
        this.terrain = terrain;
        this.inventaire = inventaire;
        this.sid = sid;
    }

    public String getNom() {
        return nom;
    }

    public abstract Item creerItem();

    public Terrain getTerrain(){
        return this.terrain;
    }

    public Inventaire getInventaire(){
        return this.inventaire;
    }

    public Sid getSid(){
        return sid;
    }

    /*
     * Méthode abstraite représentant l'effet de l'objet lorsqu'il est utilisé à une position (x, y) donnée.
     * Cette méthode est appelée lorsqu'on utilise l'objet en main (ex. outil, arme, etc.).
     *
     * Pour certaines ressources comme le bois, cette méthode peut ne rien faire (pas de fonctionnalité directe),
     * mais elle doit être implémentée dans chaque sous-classe, notamment pour les objets de craft.
     */
    public abstract void fonction(int x, int y);
}
