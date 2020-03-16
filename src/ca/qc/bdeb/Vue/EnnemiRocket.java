/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.Vue;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1749637
 */
public class EnnemiRocket extends Ennemi {

    private ArrayList<Image> listeAnimation = new ArrayList<>();
    private float deltaX = 20;
    private Random r = new Random();

    /**
     * @param x position en x
     * @param y position en y
     * @param spriteSheet spriteSheet ou se trouve son image
     */
    public EnnemiRocket(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 0, 0);
        listeAnimation.add(spriteSheet.getSubImage(5, 3));
        listeAnimation.add(spriteSheet.getSubImage(6, 3));
//        listeAnimation.add(spriteSheet.getSubImage(5, 1));
//        listeAnimation.add(spriteSheet.getSubImage(6, 1));
//        deltaY = (float) r.nextDouble();
    }

    @Override
    public void bouger() {
        super.bouger();
        x -= deltaX;
//        if (y + width < 0) {
//            setDetruire(true);
//        }
        changerAnimation();

    }

    private void changerAnimation() {
        if (tempsAnimation == 0) {
            image = listeAnimation.get(0);
        } else if (tempsAnimation == 10) {
            image = listeAnimation.get(1);
        } else {
            tempsAnimation = -1;
        }

        tempsAnimation++;

    }

}
