package fr.mbouklikha.dev.sae_glacium.controller;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sorcier;
import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Yeti;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;

import fr.mbouklikha.dev.sae_glacium.modeles.objets.*;
import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Acteur;
import fr.mbouklikha.dev.sae_glacium.vues.PointsDeVieVue;
import fr.mbouklikha.dev.sae_glacium.vues.SourisVue;
import fr.mbouklikha.dev.sae_glacium.vues.acteur.SidVue;
import fr.mbouklikha.dev.sae_glacium.vues.acteur.SorcierVue;
import fr.mbouklikha.dev.sae_glacium.vues.acteur.YetiVue;
import fr.mbouklikha.dev.sae_glacium.vues.monde.TerrainVue;

import fr.mbouklikha.dev.sae_glacium.vues.objet.InventaireVue;
import fr.mbouklikha.dev.sae_glacium.vues.objet.ObjetEnMainVue;
import fr.mbouklikha.dev.sae_glacium.vues.objet.TableCraftVue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.Set;

// Sprint 3
public class Controller {

    @FXML
    private TilePane tilePane;

    @FXML
    private Pane zoneJeu;

    @FXML
    private HBox conteneurInventaire;

    private InventaireVue inventaireVue;
    private ObjetEnMainVue objetEnMainVue;


    private Timeline gameLoop;
    private Set<KeyCode> touchesActives = new HashSet<>();
    private int temps = 0 ;

    private Environnement env;
    private Sid sid;
    private SidVue sidVue;

    private Yeti yeti;
    private YetiVue yetiVue;

    private Sorcier sorcier;
    private SorcierVue sorcierVue;

    private Souris souris;
    private final int TAILLE_BLOC = 32;
    private SourisVue sourisVue;
    PointsDeVieVue pdvVue;



    private TableCraft tableCraft;
    private TableCraftVue tableCraftVue;




    @FXML
    public void initialize() {
        this.env = new Environnement(992, 576);
        TerrainVue terrainVue = new TerrainVue(env.getTerrain(), tilePane);

        sid = new Sid(env);
        sidVue = new SidVue(sid, zoneJeu);
        souris = new Souris(sid, env.getTerrain(), terrainVue, tilePane);
        sourisVue = new SourisVue(zoneJeu);
        pdvVue = new PointsDeVieVue(zoneJeu, sid);




        yeti = new Yeti(env, sid);
        yetiVue = new YetiVue(yeti, zoneJeu);

        // Création du Sorcier et sa vue
        sorcier = new Sorcier(env, sid);
        sorcierVue = new SorcierVue(sorcier, zoneJeu);


        // Ajoute les acteurs dans l'environnement
        env.ajouterActeur(sid);
        env.ajouterActeur(yeti);
        env.ajouterActeur(sorcier);



        // -------------------------------------------------------------------------------------------

        // Inventaire
        Inventaire inv = sid.getInventaire();
        sid.getInventaire().ajouter(new Glace(env.getTerrain(), inv, sid),3);
        sid.getInventaire().ajouter(new Bois(env.getTerrain(), inv, sid),8);




        inventaireVue = new InventaireVue(conteneurInventaire, sid);
        inventaireVue.initialiserCases(inv);
        inventaireVue.mettreAJourInventaire(inv);
        conteneurInventaire.setVisible(true);


        objetEnMainVue = new ObjetEnMainVue(sid);

        // Positionner en haut à droite
        objetEnMainVue.getConteneur().setLayoutX(zoneJeu.getPrefWidth() - 70);
        objetEnMainVue.getConteneur().setLayoutY(10);

        zoneJeu.getChildren().add(objetEnMainVue.getConteneur());

        inventaireVue.setObjetEnMainVue(objetEnMainVue);



        // Table de Craft
        tableCraftVue = new TableCraftVue();
        zoneJeu.getChildren().add(tableCraftVue.getConteneur());
        tableCraftVue.getConteneur().setLayoutX(490);
        tableCraftVue.getConteneur().setLayoutY(10);

        tableCraft = new TableCraft(sid.getInventaire(), sid);

        tableCraftVue.getBoutonPioche().setOnAction(e -> tableCraft.crafterPioche());
        tableCraftVue.getBoutonDague().setOnAction(e -> tableCraft.crafterDague());
        tableCraftVue.getBoutonArc().setOnAction(e -> tableCraft.crafterArc());

        // ---------------------------------------------------------------------------------

        // Focus sur les élements du fxml
        tilePane.setFocusTraversable(false);
        zoneJeu.setFocusTraversable(true);


        // Utilisation de cela car ne fonctionne pas sans comme nous l'avons vue au cours Sprint1
        Platform.runLater(() -> {
            zoneJeu.setOnKeyPressed(event -> touchesActives.add(event.getCode()));
            zoneJeu.setOnKeyReleased(event -> touchesActives.remove(event.getCode()));
            zoneJeu.setOnMouseClicked(event -> souris.gererClic(event));
            zoneJeu.setOnMouseMoved(event -> {
                sourisVue.majPositionCurseur(event.getX(), event.getY());
            });

            zoneJeu.setOnKeyPressed(event -> {
                touchesActives.add(event.getCode());

                if (event.getCode() == KeyCode.G) {
                    boolean isVisible = tableCraftVue.isVisible();
                    tableCraftVue.setVisible(!isVisible);
                }
            });


            zoneJeu.requestFocus();

            initAnimation();
            gameLoop.play();
        });
    }


    private void initAnimation() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(0.017), // ≈ 60 FPS
                ev -> {
                    for (Acteur a : env.getActeurs()) {
                        a.appliquerGravite(env.getTerrain().getMap(), TAILLE_BLOC);
                        a.agir(touchesActives);
                    }
                    temps++;
                }
        );

        gameLoop.getKeyFrames().add(keyFrame);
    }
}