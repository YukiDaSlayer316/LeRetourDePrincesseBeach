package ca.qc.bdeb.Vue;

import static ca.qc.bdeb.Controleur.Controleur.HAUTEUR;
import static ca.qc.bdeb.Controleur.Controleur.HAUTEUR_SOL;
import static ca.qc.bdeb.Controleur.Controleur.LARGEUR;
import static ca.qc.bdeb.Vue.Jeu.buffer;
import org.newdawn.slick.SpriteSheet;


public class Bullet extends Entite implements Bougeable, Collisionable {

    private int deltaX = 12, deltaY = 0;
  /**
     * @param x position en x
     * @param y position en y
     */
    
    public Bullet(float x, float y) {
        super(x, y, 16,16,"images/boulet.png");
    }

    /**
     *fait bouger vers la droite ou vers le haut si besoin et prépare
     * à la destruction si l'objet dépasse les bornes
     */
    @Override
    public void bouger() {
        x = x + deltaX;
        y = y + deltaY;

        if (x > LARGEUR + buffer||y>HAUTEUR-HAUTEUR_SOL-height||y<0) {
            this.setDetruire(true);
        }
    }

  
    protected void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

}
