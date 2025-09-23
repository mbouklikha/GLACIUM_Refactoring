package fr.mbouklikha.dev.sae_glacium.vues.monde;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import fr.mbouklikha.dev.sae_glacium.modeles.monde.Terrain;
import javafx.scene.layout.TilePane;

public class TerrainVue {
    private Terrain terrain;

    private Image glaceImage;
    private Image neigeImage;

    public TerrainVue(Terrain terrain, TilePane tilePane) {
        this.terrain = terrain;
        this.neigeImage = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/map/neige.png"));
        this.glaceImage = new Image(getClass().getResourceAsStream("/fr/mbouklikha/dev/sae_glacium/images/map/glace.png"));
        afficherMap(tilePane);
    }

    // Permet d'afficher la map
    public void afficherMap(TilePane tilePane) {
        int[][] map = terrain.getMap();
        tilePane.getChildren().clear();

        for (int[] ligne : map) {
            for (int val : ligne) {

                ImageView bloc = new ImageView();
                bloc.setFitWidth(32);
                bloc.setFitHeight(32);

                switch (val) {
                    case 1:
                        bloc.setImage(neigeImage);
                        break;
                    case 2:
                        bloc.setImage(glaceImage);
                        break;
                }
                tilePane.getChildren().add(bloc);
            }
        }
    }
}

