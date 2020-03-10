/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.Controleur;

import ca.qc.bdeb.Modele.Modele;
import static ca.qc.bdeb.Modele.Modele.PTS_BONUS;
import static ca.qc.bdeb.Modele.Modele.PTS_ENNEMIS;
import ca.qc.bdeb.Vue.Jeu;
import java.io.File;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author emuli
 */
public class Controleur {
     private Modele modele = new Modele();
    public static int LARGEUR=1000,HAUTEUR=600,HAUTEUR_SOL=64;

    public Controleur(){
        System.setProperty("org.lwjgl.librarypath", new File("slick2dlib").getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath", new File("slick2dlib").getAbsolutePath());
        try {
            AppGameContainer app;
            app = new AppGameContainer(new Jeu("Jeu", this, modele));
            app.setDisplayMode(LARGEUR, HAUTEUR, false);
            app.setShowFPS(true);
            app.setVSync(true);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
    
    
    public void perdVie() {
        modele.enleverHealthPoints();
    }

    public void elimineEnnemis() {
        modele.ajouterPoints(PTS_ENNEMIS);
    }

    public void ramasseBonus() {
        modele.ajouterPoints(PTS_BONUS);
    }

    public void ramasseBoostEnergie() {
        modele.ajouterHealthPoints();
    }

    public int getPointsJoueur() {
        return modele.getPoints();
    }

    public void ramasseBombeMega(int nbEnnemisTues) {
        modele.ajouterPoints(nbEnnemisTues *PTS_ENNEMIS );
    }

    public void finDePartie() {
        modele.resetHealthPoints();
        modele.resetPoints();
        modele.restartPartie();
    }
}
