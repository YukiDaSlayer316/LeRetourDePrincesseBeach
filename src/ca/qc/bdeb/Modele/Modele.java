/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.Modele;

import java.util.Observable;
import java.util.Random;

/**
 *
 * @author emuli
 */
public class Modele extends Observable{
     private Random rand = new Random();
    private int  healthPoints, points = 0;
    private boolean finDePartie = false;
    
     public static int PTS_ENNEMIS = 5, PTS_BONUS = 25,
             MAX_HEALTH = 3;
   
     public int getHealthPoints() {
        return healthPoints;
    }

    public void enleverHealthPoints() {
        this.healthPoints -= 1;
        if (healthPoints < 1) {
            finDePartie = true;
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
   
    public void resetPartie() {
        this.finDePartie = false;
        majObservers();
    }
      public void majObservers() {
        setChanged();
        notifyObservers();
    }
}
