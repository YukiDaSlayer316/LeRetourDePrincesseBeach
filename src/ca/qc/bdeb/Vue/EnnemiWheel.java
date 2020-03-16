/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.Vue;

import ca.qc.bdeb.Controleur.Controleur;
import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author Pasteur
 */
public class EnnemiWheel extends Ennemi {

    private float angle, rayon = 100, yBase, xBase= Controleur.LARGEUR + 100 ;
    private ArrayList<Image> listeAnimation = new ArrayList<>();

    /**
     * @param x position en x
     * @param y position en y
     * @param spriteSheet spriteSheet ou se trouve son image   
     */
    public EnnemiWheel(float x, float y, SpriteSheet spriteSheet, float angle, float yBase) {
        super(x, y, spriteSheet, 1, 3);
        this.angle = angle;
        this.yBase = yBase;
        listeAnimation.add(spriteSheet.getSubImage(3, 1));
        listeAnimation.add(spriteSheet.getSubImage(4, 1));
        listeAnimation.add(spriteSheet.getSubImage(5, 1));
        listeAnimation.add(spriteSheet.getSubImage(6, 1));

    }

    /**
     * anime et fait bouger l'objet
     * 
     */
    @Override
    public void bouger() {
        super.bouger();
        deltaX = (float) cos(angle) * rayon  ;
        deltaY = (float) sin(angle) * rayon  ;
      //  deltaX = (float) cos(angle) * rayon * vitesse;
       // deltaY = (float) sin(angle) * rayon * vitesse;
        x = deltaX + xBase;
        y = deltaY + yBase;

        angle -= PI / 200;
        xBase -= 2.8f;

        changerAnimation();
    }

    private void changerAnimation() {
        if (tempsAnimation == 0) {
            image = listeAnimation.get(0);
        } else if (tempsAnimation == 15) {
            image = listeAnimation.get(1);
        } else if (tempsAnimation == 30) {
            image = listeAnimation.get(2);
        } else if (tempsAnimation == 45) {
            image = listeAnimation.get(3);
        } else if (tempsAnimation == 60) {
            tempsAnimation = -1;
        }
        tempsAnimation++;

    }

}
