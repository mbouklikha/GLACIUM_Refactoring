package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.outils.Arc;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.outils.Dague;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.outils.Pioche;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.ressources.*;

public class TableCraft {

    private Inventaire inventaire;
    private Sid sid;


    public TableCraft(Inventaire inventaire, Sid sid) {
        this.inventaire = inventaire;
        this.sid = sid;
    }

    FabriqueSimpleRessource fabrique = new FabriqueSimpleRessource();
    Forge forge = new Forge(fabrique, inventaire, sid);


    /*
     * Crée une pioche si l'inventaire contient au moins 3 glaces et 2 bois.
     * Si les conditions sont remplies, la pioche est ajoutée à l'inventaire
     * et les ressources sont retirées.
    */
    public void crafterPioche() {
        Ressource glace = forge.fabriquerRessource("glace");
        Ressource bois = forge.fabriquerRessource("bois");

        if (sid.getInventaire().aAssez(glace,3) && sid.getInventaire().aAssez(bois,2)) {
            System.out.println("On peut crafter !");
            sid.getInventaire().ajouterItem(new Pioche(sid.getEnvironnement().getTerrain(), sid.getInventaire(), sid));
            sid.getInventaire().retirer(glace,3);
            sid.getInventaire().retirer(bois,2);
        } else if (!sid.getInventaire().aAssez(glace,3)){
            System.out.println("Pas assez de glace");
        } else{
            System.out.println("Pas assez de bois");
        }
    }


    /*
     * Crée une dague si l'inventaire contient au moins 3 glaces et 2 neiges.
     * Si les conditions sont remplies, la dague est ajoutée à l'inventaire
     * et les ressources sont retirées.
    */
    public void crafterDague() {
        Ressource glace = forge.fabriquerRessource("glace");
        Ressource neige = forge.fabriquerRessource("glace");

        if (sid.getInventaire().aAssez(glace,3) && sid.getInventaire().aAssez(neige,2)) {
            System.out.println("On peut crafter !");
            sid.getInventaire().ajouterItem(new Dague(sid.getEnvironnement().getTerrain(), sid.getInventaire(), sid));
            sid.getInventaire().retirer(glace,3);
            sid.getInventaire().retirer(neige,2);
        } else if (!sid.getInventaire().aAssez(glace,3)){
            System.out.println("Pas assez de glace");
        } else{
            System.out.println("Pas assez de neige");
        }
    }


    /*
     * Crée un arc de feu si l'inventaire contient au moins :
     * 1 éclat de feu, 3 bois et 5 glaces.
     * Si les conditions sont remplies, l’arc est ajouté à l’inventaire
     * et les ressources sont retirées.
    */
    public void crafterArc() {
        Ressource feu = forge.fabriquerRessource("eclatFeu");
        Ressource bois = forge.fabriquerRessource("bois");
        Ressource glace = forge.fabriquerRessource("glace");

        if (sid.getInventaire().aAssez(feu,1) && sid.getInventaire().aAssez(bois,3) && sid.getInventaire().aAssez(glace,5)) {
            System.out.println("On peut crafter !");
            sid.getInventaire().ajouterItem(new Arc(sid.getEnvironnement().getTerrain(), sid.getInventaire(), sid));
            sid.getInventaire().retirer(feu,1);
            sid.getInventaire().retirer(bois,2);
            sid.getInventaire().retirer(glace,5);
        } else if (!sid.getInventaire().aAssez(feu,1)) {
            System.out.println("Pas assez d'éclat de feu");
        }else if(!sid.getInventaire().aAssez(bois,2)){
            System.out.println("Pas assez de bois");
        } else{
            System.out.println("Pas assez de glace");
        }
    }


}