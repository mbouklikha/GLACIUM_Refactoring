package fr.mbouklikha.dev.sae_glacium.modeles.objets.ressources;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Inventaire;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.Item;
import fr.mbouklikha.dev.sae_glacium.modeles.objets.outils.Objets;

public abstract class Ressource extends Objets {

    public Ressource(String nom, Terrain terrain, Inventaire inventaire, Sid sid) {
        super(nom, terrain, inventaire, sid);
    }

    @Override
    public Item creerItem() {
        return new Item(this, 1);
    }

    public String getType() {
        return "Ressource";
    }

    public abstract void fonction(int x, int y);

    protected void poserBloc(int x, int y, int idBloc) {
        int[][] map = getTerrain().getMap();
        Objets objetEnMain = getSid().getObjetEnMain();

        // Recherche de l'objet dans l'inventaire
        Item itemTrouve = null;
        for (Item item : getInventaire().getItems()) {
            if (item.getObjet().equals(objetEnMain)) {
                itemTrouve = item;
            }
        }

        if (itemTrouve == null || itemTrouve.getQuantite().get() == 0) {
            System.out.println("Impossible de poser : objet non disponible ou quantité insuffisante.");
        } else {
            if (map[y][x] == -1) {
                map[y][x] = idBloc;
                getInventaire().retirerUnItem(objetEnMain);

                // Si l’objet a disparu de l’inventaire, on enlève aussi de la main
                if (!getInventaire().contient(objetEnMain)) {
                    getSid().setObjetEnMain(null);
                }

                getTerrain().mettreAJourHitboxBlocsSolides();
                System.out.println("Bloc de " + getNom() + " posé");
            } else {
                System.out.println("Impossible de poser : case déjà occupée");
            }
        }
    }


}
