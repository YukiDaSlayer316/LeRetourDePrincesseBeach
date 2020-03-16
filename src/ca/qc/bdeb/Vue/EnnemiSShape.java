/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.Vue;

import static java.lang.Math.sin;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1749637
 */
public class EnnemiSShape extends Ennemi {

    private int tempsAnimation = 0;
    private int posImage = 0;
    private ArrayList<Image> listeAnimation = new ArrayList<>();
    Random random=new Random();
    private float posBase, amplitude;
    
    
    /**
     * @param x position en x
     * @param y position en y
     * @param spriteSheet spriteSheet ou se trouve son image
     */
    public EnnemiSShape(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 2, 2);
        listeAnimation.add(spriteSheet.getSubImage(4, 2));
        listeAnimation.add(spriteSheet.getSubImage(5, 2));
        listeAnimation.add(spriteSheet.getSubImage(6, 2));
        deltaX = 2.5f;
        deltaY = 0.4f;
        posBase=y;
        amplitude= (float) (random.nextFloat()/100);
    }

    @Override
    /**
     * anime et fait bouger l'objet
     */
    public void bouger() {
        super.bouger(); //To change body of generated methods, choose Tools | Templates.
        x -= deltaX;
        y = (float) (110 * sin((amplitude) * x) +posBase);
        // y = (float) (90 * sin((0.009) * x) + posBase);

        
        
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
