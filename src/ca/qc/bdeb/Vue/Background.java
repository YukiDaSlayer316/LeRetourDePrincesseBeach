/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.Vue;

import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author emuli
 */
public abstract class Background extends Entite implements Bougeable{

    public Background(float x, float y, SpriteSheet spriteSheet, int ligne, int colonne) {
        super(x, y, spriteSheet, ligne, colonne);
    }

}
