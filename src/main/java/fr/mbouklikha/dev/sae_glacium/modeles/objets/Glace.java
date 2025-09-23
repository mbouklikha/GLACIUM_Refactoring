package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;

public class Glace extends Ressource {

    public Glace(Terrain terrain, Inventaire inventaire, Sid sid){
        super("glace", terrain, inventaire, sid);
    }

    public void fonction(int x, int y){
        int[][] map = getTerrain().getMap();
        Objets objetEnMain = getSid().getObjetEnMain();

        // Vérifie si l'objet en main est encore dans l'inventaire avec une quantité > 0
        Item itemTrouve = null;
        for (Item item : getInventaire().getItems()) {
            if (item.getObjet().equals(objetEnMain)) {
                itemTrouve = item;
                break;
            }
        }

        if (itemTrouve == null || itemTrouve.getQuantite().get() == 0) {
            System.out.println("Impossible de poser : objet non disponible ou quantité insuffisante.");

        } else{
            // Poser le bloc
            if (map[y][x] == -1) {
                map[y][x] = 2;
                getInventaire().retirerUnItem(objetEnMain);

                // Si l’objet a disparu de l’inventaire, on enlève aussi de la main
                if (!getInventaire().contient(objetEnMain)) {
                    getSid().setObjetEnMain(null);
                }

                getTerrain().mettreAJourHitboxBlocsSolides();
                System.out.println("Bloc posé");
            }
        }
    }
}
