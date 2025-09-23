package fr.mbouklikha.dev.sae_glacium.modeles.objets;

import fr.mbouklikha.dev.sae_glacium.modeles.acteur.Sid;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventaireTest {

    private Inventaire inventaire;
    private Sid sid;
    private Environnement env;
    private Objets glace;
    private Objets bois;

    @BeforeEach
    public void setUp() throws Exception  {
        // Initialisation avant chaque test : inventaire vide, environnement et joueur Sid
        inventaire = new Inventaire();
        env = new Environnement(992, 576);
        sid = new Sid(env);

        // Création de deux objets test : glace et bois
        glace = new Glace(sid.getEnvironnement().getTerrain(), sid.getInventaire(), sid);
        bois = new Bois(sid.getEnvironnement().getTerrain(), sid.getInventaire(), sid);
    }

    @Test
    public void testAjouterItem() {
        // Vérifie que l'inventaire est initialement vide
        assertTrue(inventaire.getItems().isEmpty());

        // Ajoute un objet glace et vérifie qu'il est bien dans l'inventaire
        inventaire.ajouterItem(glace);
        assertEquals(1, inventaire.getItems().size());
        assertEquals("glace", inventaire.getItems().get(0).getNom());

        // Vérifie que la quantité est à 1 après ajout
        assertEquals(1, inventaire.getItems().get(0).getQuantite().get());

        // Ajoute encore une glace, doit incrémenter la quantité (pas ajouter un nouvel item)
        inventaire.ajouterItem(glace);
        assertEquals(1, inventaire.getItems().size()); // Pas d'item supplémentaire
        assertEquals(2, inventaire.getItems().get(0).getQuantite().get()); // Quantité augmentée
    }

    @Test
    public void testRetirerUnItem() {
        // Ajoute 2 glaces dans l'inventaire
        inventaire.ajouter(glace, 2);

        // Retire une glace, la quantité doit passer à 1
        inventaire.retirerUnItem(glace);
        assertEquals(1, inventaire.getItems().get(0).getQuantite().get());

        // Retire une glace supplémentaire, l'inventaire doit être vide
        inventaire.retirerUnItem(glace);
        assertTrue(inventaire.getItems().isEmpty());
    }

    @Test
    public void testContient() {
        // Vérifie que l'inventaire ne contient pas la glace au départ
        assertFalse(inventaire.contient(glace));

        // Après ajout, l'inventaire doit contenir la glace
        inventaire.ajouterItem(glace);
        assertTrue(inventaire.contient(glace));
    }

    @Test
    public void testAAssez() {
        // Ajoute 3 glaces dans l'inventaire
        inventaire.ajouter(glace, 3);

        // Vérifie que l'on a assez de glace pour 2 unités
        assertTrue(inventaire.aAssez(glace, 2));

        // Vérifie que l'on n'a pas assez pour 5 unités
        assertFalse(inventaire.aAssez(glace, 5));
    }

    @Test
    public void testAjouterAvecQuantite() {
        // Ajoute 5 glaces d'un coup
        inventaire.ajouter(glace, 5);
        assertEquals(1, inventaire.getItems().size());
        assertEquals(5, inventaire.getItems().get(0).getQuantite().get());

        // Ajoute 3 glaces supplémentaires, quantité doit être mise à jour à 8
        inventaire.ajouter(glace, 3);
        assertEquals(8, inventaire.getItems().get(0).getQuantite().get());
    }

    @Test
    public void testRetirerAvecQuantite() {
        // Ajoute 5 glaces
        inventaire.ajouter(glace, 5);

        // Retire 2 glaces, quantité doit être 3
        inventaire.retirer(glace, 2);
        assertEquals(3, inventaire.getItems().get(0).getQuantite().get());

        // Retire les 3 restantes, inventaire doit être vide
        inventaire.retirer(glace, 3);
        assertTrue(inventaire.getItems().isEmpty());
    }

}
