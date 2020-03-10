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
 * @author 1749637
 */
public class BombeMega extends Bonus {

    private ArrayList<Image> listeAnimation = new ArrayList<>();
    private int tempsAnimation = 0, posImage = 0;

     /**
     *
     * @param x position en x
     * @param y position en y
     * @param spriteSheet spriteSheet ou se trouve son image
     */
    public BombeMega(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 4, 6);
        listeAnimation.add(spriteSheet.getSubImage(3, 4));
        listeAnimation.add(spriteSheet.getSubImage(4, 4));
        listeAnimation.add(spriteSheet.getSubImage(5, 4));
        listeAnimation.add(spriteSheet.getSubImage(6, 4));

    }

    /**
     *fait bouger l'objet telle que le parent, puis fait l'animation de l'objet
     */
    @Override
    public void bouger() {
        super.bouger(); //To change body of generated methods, choose Tools | Templates.
        changerImage();

    }

    private void changerImage() {

        if (tempsAnimation == 0) {
            posImage = 0;
        } else if (tempsAnimation == 8) {
            posImage = 1;
        } else if (tempsAnimation == 16) {
            posImage = 2;
        } else if (tempsAnimation == 24) {
            tempsAnimation = -1;
        }

        this.image = listeAnimation.get(posImage);

        tempsAnimation++;

    }
}
