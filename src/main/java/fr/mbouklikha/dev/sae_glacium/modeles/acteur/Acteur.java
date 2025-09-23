package fr.mbouklikha.dev.sae_glacium.modeles.acteur;

import fr.mbouklikha.dev.sae_glacium.modeles.Hitbox;
import fr.mbouklikha.dev.sae_glacium.modeles.monde.Environnement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Set;


public abstract class Acteur {

    private String nom;
    protected IntegerProperty pv;
    private Environnement environnement;
    private IntegerProperty x, y;
    protected boolean enSaut = false;

    public Acteur(String nom, int pv, int x, int y, Environnement environnement) {
        this.nom = nom;
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.pv= new SimpleIntegerProperty(pv);
        this.environnement = environnement;
    }

    /*public Acteur(String nom, int pv, Environnement environnement) {
        this.nom = nom;
        this.pv = pv;
        this.x= new SimpleIntegerProperty(0);
        this.y = new SimpleIntegerProperty(0);
    }*/


    public ArrayList<Acteur> getActeursAutour() {
        return environnement.getActeurs();
    }

    public String getNom(){
        return this.nom;
    }

    public int getPv() {
        return pv.get();
    }

    public int getX() {
        return x.getValue();
    }

    public IntegerProperty pvProperty() {
        return pv;
    }

    public IntegerProperty getXProperty(){
        return this.x;
    }

    public  void setX(double n){
        x.setValue(n);
    }

    public  int getY() {
        return y.getValue();
    }

    public IntegerProperty getYProperty(){
        return this.y;
    }

    public  void setY(double n){
        y.setValue(n);
    }

    public Environnement getEnvironnement(){
        return this.environnement;
    }



    public void decrementerPv(int n) {
        this.pv.set(getPv() - n);
    }

    public void incrementerPv(int n) {
        this.pv.set(getPv() + n);
    }

    public boolean estVivant() {
        return getPv() > 0;
    }


    public abstract void agir(Set<KeyCode> touches);
    public abstract void appliquerGravite(int[][] map, int tailleBloc);



    public abstract Hitbox getHitbox();

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }




}
