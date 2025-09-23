package fr.mbouklikha.dev.sae_glacium.modeles;

import javafx.scene.shape.Rectangle;

public class Hitbox {
    private Rectangle rectangle;

    public Hitbox(int x, int y, int largeur, int hauteur) {
        this.rectangle = new Rectangle(x, y, largeur, hauteur);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setPosition(int x, int y) {
        rectangle.setX(x);
        rectangle.setY(y);
    }

    public boolean collisionAvec(Hitbox autre) {
        Rectangle r1 = this.rectangle;
        Rectangle r2 = autre.getRectangle();
        return r1.intersects(r2.getX(), r2.getY(), r2.getWidth(), r2.getHeight());
    }

    public boolean contientPoint(int x, int y) {
        return rectangle.contains(x, y);
    }


}
