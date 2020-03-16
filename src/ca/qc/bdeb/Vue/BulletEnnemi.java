/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.Vue;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1767250
 */
public class BulletEnnemi extends Entite implements Collisionable, Bougeable {

    private float deltaY = -6.0f;
    private int tempsAnimation = 0;
    private boolean finAnimation = false;
    private ArrayList<Image> listeImage = new ArrayList();

      /**
     * @param x position en x
     * @param y position en y
     * @param spriteSheet spriteSheet ou se trouve son image
     */
    
    public BulletEnnemi(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 5, 5);
        listeImage.add(spriteSheet.getSubImage(5, 5));
        listeImage.add(spriteSheet.getSubImage(4, 5));
        listeImage.add(spriteSheet.getSubImage(3, 5));

        

    }

    /**
     *fait bouger l'objet en y, vérifie qu'il est dans les bornes sinon le prépare
     * à être détruit et appelle la méthode qui anime l'objet
     */
    @Override
    public void bouger() {
        y = y + deltaY;
        x -= 1.6f;
        if (y < 0 || x + width < 0) {
            this.setDetruire(true);
        }
        animation();

    }

    private void animation() {
        if (tempsAnimation == 0) {
            image = listeImage.get(0);
        } else if (tempsAnimation == 4) {
            image = listeImage.get(1);
        } else if (tempsAnimation == 8) {
            image = listeImage.get(2);
        } else if (tempsAnimation == 12) {
      
            tempsAnimation = -1;
        }

        tempsAnimation++;

    }

}
