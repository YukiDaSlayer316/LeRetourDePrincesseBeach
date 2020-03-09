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
public class Plancher extends Entite implements Bougeable{
    private float deltaX=0.6f;
    public Plancher(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 0, 0);
    }

    @Override
    public void bouger() {
        x-=deltaX;
    }
}
