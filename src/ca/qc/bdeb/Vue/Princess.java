/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.Vue;

import static ca.qc.bdeb.Controleur.Controleur.HAUTEUR;
import static ca.qc.bdeb.Controleur.Controleur.HAUTEUR_SOL;
import static ca.qc.bdeb.Controleur.Controleur.LARGEUR;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author emuli
 */
public class Princess extends Entite{
    
     private float deltaX = 1.6f, deltaY = 1.6f;
    private ArrayList<Image> listeAnimationVol = new ArrayList<>();
        private ArrayList<Image> listeAnimationSol = new ArrayList<>();

    private int animation = 0, posImage=0;
    private boolean enVol=false;
    public Princess(float x, float y, SpriteSheet spriteSheet) {
        super(x, y, spriteSheet, 0, 2);
        listeAnimationVol.add(spriteSheet.getSubImage(0, 0));

        listeAnimationVol.add(spriteSheet.getSubImage(1, 0));
// Image #2 de l'animation - position 0,1
        listeAnimationVol.add(spriteSheet.getSubImage(2, 0));
// Image #3 de l'animation - position 0,2
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
        
      //  enVol=estEnVol();
        
     

        changerImage();

    }

    private void changerImage() {
        
        if (animation == 0) {
            
            posImage=0;
        } else if (animation == 50) {
           // this.image = listeAnimation.get(1);
                       posImage=1;

        } else if (animation == 100) {
           // this.image = listeAnimation.get(2);
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

   /* private boolean estEnVol() {
          if(y+height==HAUTEUR-HAUTEUR_SOL){
            return false;
        }
            return true;
        
    }*/

}
