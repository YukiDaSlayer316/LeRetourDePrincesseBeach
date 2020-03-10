/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.Vue;

import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1749637
 */
public class BonusVie extends Bonus{
    
     /**
     * @param x position en x
     * @param y position en y
     * @param spriteSheet spriteSheet ou se trouve son image
     */
    
    public BonusVie(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 1, 0);
    }
 
    
}
