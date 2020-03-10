/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.Vue;

import static ca.qc.bdeb.Vue.Jeu.buffer;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author emuli
 */
public abstract class Ennemi extends Entite implements Bougeable, Collisionable{

    protected float deltaX = 1.4f,deltaY = 0.3f;
    protected int tempsAnimation=0;
    
       /**
     * @param x position en x
     * @param y position en y
     * @param spriteSheet spriteSheet ou se trouve son image 
     * @param ligne ligne dans le spriteSheet où se trouve l'image
     * @param colonne colonne dans le spriteSheet où se trouve l'image 
     */
    public Ennemi(float x, float y, SpriteSheet spriteSheet, int ligne, int colonne) {
        super(x, y, spriteSheet, ligne, colonne);
    }
    
    @Override
    public void bouger() {
       if (x < -buffer) {
            this.setDetruire(true);
        }
    }

   
    
}
