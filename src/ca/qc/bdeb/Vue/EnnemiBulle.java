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
public class EnnemiBulle extends Ennemi {

    private ArrayList<Image> listeAnimation = new ArrayList<>();
    private float deltaY=(float) 0.003;
    private Random r = new Random();

    /**
     * @param x position en x
     * @param y position en y
     * @param spriteSheet spriteSheet ou se trouve son image   
     */
    public EnnemiBulle(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 0, 0);
        listeAnimation.add(spriteSheet.getSubImage(0, 0));
        listeAnimation.add(spriteSheet.getSubImage(1, 0));
        listeAnimation.add(spriteSheet.getSubImage(2, 0));
        listeAnimation.add(spriteSheet.getSubImage(3, 0));
        listeAnimation.add(spriteSheet.getSubImage(4, 0));
        listeAnimation.add(spriteSheet.getSubImage(5, 0));
        listeAnimation.add(spriteSheet.getSubImage(6, 0));
        listeAnimation.add(spriteSheet.getSubImage(7, 0));
        deltaY = (float) r.nextDouble();
    }

    @Override
    public void bouger() {
        super.bouger();
        y -= deltaY;
        x -= deltaX;
        if (y + width < 0) {
            setDetruire(true);
        }
        changerAnimation();

    }

private void changerAnimation() {
        if (tempsAnimation == 0) {
            image = listeAnimation.get(0);
        } else if (tempsAnimation == 10) {
            image = listeAnimation.get(1);
        } else if (tempsAnimation == 20) {
            image = listeAnimation.get(2);
        } else if (tempsAnimation == 30) {
            image = listeAnimation.get(3);
        } else if (tempsAnimation == 40) {
            image = listeAnimation.get(4);
        } else if (tempsAnimation == 50) {
            image = listeAnimation.get(5);
        } else if (tempsAnimation == 60) {
            image = listeAnimation.get(6);
        } else if (tempsAnimation == 70) {
            image = listeAnimation.get(7);
        } else if (tempsAnimation == 80) {
            tempsAnimation = -1;

        }
        tempsAnimation++;

    }

}
