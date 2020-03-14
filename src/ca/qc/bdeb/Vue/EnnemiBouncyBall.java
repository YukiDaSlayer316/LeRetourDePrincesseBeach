/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.Vue;

import ca.qc.bdeb.Controleur.Controleur;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1749637
 */
public class EnnemiBouncyBall extends Ennemi {

    private int tempsAnimation = 0;
    private int posImage = 0;
    private ArrayList<Image> listeAnimation = new ArrayList<>();
    private Random random = new Random();
    private static float gravite = 0.5f;
    private float speedY = 0;

    /**
     * @param x position en x
     * @param y position en y
     * @param spriteSheet spriteSheet ou se trouve son image
     */
    public EnnemiBouncyBall(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 2, 2);
        listeAnimation.add(spriteSheet.getSubImage(4,3));
        listeAnimation.add(spriteSheet.getSubImage(3,3));
        deltaX = 4f;
        deltaY = 15f;
    }

    @Override
    /**
     * anime et fait bouger l'objet
     */
    public void bouger() {
        super.bouger(); //To change body of generated methods, choose Tools | Templates.
        x -= deltaX;
        speedY+=gravite;
        y+= speedY;
        if(y+height>Controleur.HAUTEUR-Controleur.HAUTEUR_SOL){
            y=Controleur.HAUTEUR-Controleur.HAUTEUR_SOL-height;
            speedY*=-1;
        }

        changerAnimation();

    }

    private void changerAnimation() {
        if (tempsAnimation == 0) {
            image = listeAnimation.get(0);
        } else if (tempsAnimation == 5) {
            image = listeAnimation.get(1);
        } else if (tempsAnimation == 10) {
            tempsAnimation = -1;
        }
        tempsAnimation++;

    }

    

}
