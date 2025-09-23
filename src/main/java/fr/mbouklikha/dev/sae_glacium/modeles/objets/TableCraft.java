package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;

public class TableCraft {

    private Inventaire inventaire;
    private Sid sid;

    public TableCraft(Inventaire inventaire, Sid sid) {
        this.inventaire = inventaire;
        this.sid = sid;
    }


    /*
     * Crée une pioche si l'inventaire contient au moins 3 glaces et 2 bois.
     * Si les conditions sont remplies, la pioche est ajoutée à l'inventaire
     * et les ressources sont retirées.
    */
    public void crafterPioche() {
        Glace glace = new Glace(sid.getEnvironnement().getTerrain(),sid.getInventaire(),sid);
        Bois bois = new Bois(sid.getEnvironnement().getTerrain(),sid.getInventaire(),sid);

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
        Glace glace = new Glace(sid.getEnvironnement().getTerrain(),sid.getInventaire(),sid);
        Neige neige = new Neige(sid.getEnvironnement().getTerrain(),sid.getInventaire(),sid);

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
        EclatFeu feu = new EclatFeu(sid.getEnvironnement().getTerrain(),sid.getInventaire(),sid);
        Bois bois = new Bois(sid.getEnvironnement().getTerrain(),sid.getInventaire(),sid);
        Glace glace = new Glace(sid.getEnvironnement().getTerrain(),sid.getInventaire(),sid);

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