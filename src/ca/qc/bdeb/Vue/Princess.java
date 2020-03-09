/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.Vue;

import static ca.qc.bdeb.Controleur.Controleur.HAUTEUR;
import static ca.qc.bdeb.Controleur.Controleur.HAUTEUR_SOL;
import static ca.qc.bdeb.Controleur.Controleur.LARGEUR;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author emuli
 */
public class Princess extends Entite{
    
     private float deltaX = 1.6f, deltaY = 1.6f, hover = 0.0f;
    private ArrayList<Image> listeAnimationVol = new ArrayList<>();
        private ArrayList<Image> listeAnimationSol = new ArrayList<>();
private double gravity=5.34;
        
    private int animation = 0, posImage=0;
    private boolean enVol=false;
    
    private long tempsAttaque = 0;
    
    public Princess(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 0, 2);
        listeAnimationVol.add(spriteSheet.getSubImage(0, 0));
        listeAnimationVol.add(spriteSheet.getSubImage(1, 0));
        listeAnimationVol.add(spriteSheet.getSubImage(2, 0));
        listeAnimationSol.add(spriteSheet.getSubImage(3, 0));
        listeAnimationSol.add(spriteSheet.getSubImage(4, 0));
        listeAnimationSol.add(spriteSheet.getSubImage(5, 0));

    }
    
     void bouger(ArrayList<KeyCode> listeKeys) {
        if (listeKeys.contains(KeyCode.RIGHT)) {
            if (x + deltaX + width < LARGEUR) {
                x = x + deltaX;
            }
        }
        if (listeKeys.contains(KeyCode.LEFT)) {
            if (x - deltaX > 0) {
                x = x - deltaX;
            }

        }
        if (listeKeys.contains(KeyCode.UP)) {
            if (y - deltaY > height) {
                y = y - deltaY;
            }

        }
        if (listeKeys.contains(KeyCode.DOWN)) {
            if (y + deltaY  < HAUTEUR - HAUTEUR_SOL-height) {
                y = y + deltaY;  //x = x - deltaX;
            }

        }
        
        enVol=estEnVol();
        
         if (enVol && listeKeys.isEmpty()) {
            if ((y - cos(hover) > 0) || y - cos(hover) < HAUTEUR_SOL) {
                y = (float) (y + cos(hover)*0.15+ gravity*0.08);
                //+ cos(hover)*0.15
               // hover += 0.05;
            }

        }
     

        changerImage();

    }

    private void changerImage() {
        
        if (animation == 0) {
            posImage=0;
        } else if (animation == 50) {
                       posImage=1;
        } else if (animation == 100) {
                       posImage=2;
        } 
        else if (animation == 150) {
            animation = -1; 
        }
        
        if(enVol){
          this.image = listeAnimationVol.get(posImage);
        }else{
           this.image = listeAnimationSol.get(posImage);
        }
        animation++;

    }

    private boolean estEnVol() {
          if(y+height==HAUTEUR-HAUTEUR_SOL){
            return false;
        }
            return true;
        
    }

}
