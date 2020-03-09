/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.Controleur;

import ca.qc.bdeb.Modele.Modele;
import ca.qc.bdeb.Vue.Jeu;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author emuli
 */
public class Controleur {
     private Modele modele = new Modele();
    public static int LARGEUR=1200,HAUTEUR=900,HAUTEUR_SOL=64;

    public Controleur(){

        try {
            AppGameContainer app;
            app = new AppGameContainer(new Jeu("Jeu", this, modele));
            app.setDisplayMode(LARGEUR, HAUTEUR, false);
            app.setShowFPS(false);
            app.setVSync(false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
