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
 * @author 1749637
 */
public abstract class Bonus extends Entite implements Bougeable,Collisionable{
    
    protected float deltaX=1.6f,  deltaY;
    
    /**
     * @param x position en x
     * @param y position en y
     * @param spriteSheet spriteSheet ou se trouve son image
     * @param ligne
     * @param colonne
     */
    public Bonus(float x, float y, SpriteSheet spriteSheet, int ligne, int colonne) {
        super(x, y, spriteSheet, ligne, colonne);
    }

    /**
     *bouge vers la gauche et le prépare a la destruction s'il dépasse les bornes
     * horizontales du jeu
     */
    @Override
    public void bouger() {
        x=x-deltaX;        
//     if( x<-buffer){
//         this.setDetruire(true);
//     }
    }


    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }
    
    
    
}
