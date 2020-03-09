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
public class Ciel extends Entite implements Bougeable{

    @Override
    public void bouger() {
    }
     public Ciel(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet,6 , 12);
    }
}
