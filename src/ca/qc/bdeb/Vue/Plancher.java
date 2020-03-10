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
public class Plancher extends Background {

    public static float VITESSE_PLANCHER = 0.6f;
    private float deltaX = VITESSE_PLANCHER;

    public Plancher(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 0, 0);
    }

    @Override
    public void bouger() {
        x -= deltaX;
    }

    @Override
    public void isMoving() {
        deltaX = VITESSE_PLANCHER;
    }

}
