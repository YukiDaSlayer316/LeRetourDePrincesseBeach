/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.Modele;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.Observable;
import java.util.Random;

/**
 *
 * @author emuli
 */
public class Modele extends Observable {

    private Random rand = new Random();
    private boolean finDePartie = false;

    public static int PTS_ENNEMIS = 5, PTS_BONUS = 10,
            MAX_HEALTH = 3;

    private int healthPoints = MAX_HEALTH, points = 0;

    public int getHealthPoints() {
        return healthPoints;
    }

    public void enleverHealthPoints() {
        this.healthPoints -= 1;
        if (healthPoints < 1) {
            finDePartie = true;
            //restartPartie();
        }
        majObservers();
    }

    public void ajouterPoints(int points) {
        this.points += points;
        majObservers();
    }

    public int getPoints() {
        return points;
    }

    public boolean isFinDePartie() {
        return finDePartie;
    }

    public void resetPoints() {
        points = 0;
        majObservers();
    }

    public void ajouterHealthPoints() {
        if (healthPoints < MAX_HEALTH) {
            this.healthPoints++;
        }
        majObservers();
    }

    public void resetHealthPoints() {
        healthPoints = MAX_HEALTH;
        majObservers();
    }

    public void restartPartie() {
        finDePartie = false;
        healthPoints = MAX_HEALTH;
        points = 0;

        majObservers();
    }

    public void majObservers() {
        setChanged();
        notifyObservers();
    }
    
       public float[][] getPositionRoue() {
        int rayon = 100;
        float angle = 0;
        float[][] positionRoue = new float[3][8];

        for (int i = 0; i < 8; i++) {
            positionRoue[0][i] = (float) cos(angle) * rayon;
            positionRoue[1][i] = (float) sin(angle) * rayon;
            positionRoue[2][i] = angle;
            angle += PI / 4;
        }

        return positionRoue;

    }
}
