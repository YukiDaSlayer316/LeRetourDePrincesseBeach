/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.Vue;

import ca.qc.bdeb.Controleur.Controleur;
import static ca.qc.bdeb.Controleur.Controleur.HAUTEUR;
import static ca.qc.bdeb.Controleur.Controleur.LARGEUR;
import ca.qc.bdeb.Modele.Modele;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.input.KeyCode;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author emuli
 */
public class Jeu extends BasicGame implements Observer{

     private Controleur controleur;
    private Modele modele;
    private ArrayList<Bougeable> listeBougeable = new ArrayList<>(); // Tous ce qui bouge
    private ArrayList<Entite> listeEntite = new ArrayList<>(); // Toutes les entités
    private ArrayList<KeyCode> listeKeys = new ArrayList<>(); // Les touches enfoncées
    private Input input; // L’entrée (souris/touches de clavier, etc.)
    private SpriteSheet spriteSheetTiles, spriteSheetDivers, spriteSheetMonde, spriteSheetPrincesse;

    private Princess princesse;
    
    /**
     * Contructeur de Jeu
     *
     * @param gamename Le nom du jeu
     * @param controleur Le controleur du jeu
     * @param modele Le modèle du jeu
     */
    public Jeu(String gamename, Controleur controleur, Modele modele) {
        super(gamename);
        this.modele = modele;
        this.controleur = controleur;
        modele.addObserver(this);
        // …
    }
    
    @Override
    public void init(GameContainer container) throws SlickException {
   input = container.getInput();

        spriteSheetDivers = new SpriteSheet("images/sprites_divers.png", 32, 32);
        spriteSheetMonde = new SpriteSheet("images/sprites_monde.png", 32, 32);
        spriteSheetTiles = new SpriteSheet("images/tiles.png", 32, 32);
        spriteSheetPrincesse = new SpriteSheet("images/sprites_princess.png", 32, 64);

        for (int i = 0; i < LARGEUR; i += 32) {
            for (int j = 0; j < HAUTEUR; j += 32) {
                Ciel ciel = new Ciel(i, j, spriteSheetTiles);
                listeBougeable.add(ciel);
                listeEntite.add(ciel);

            }
        }

        int hauteurPlancher = 2;
        for (int i = 0; i <= LARGEUR+32; i += 32) {
            for (int j = 0; j<=hauteurPlancher; j ++) {
                Plancher plancher = new Plancher(i, HAUTEUR-j*32, spriteSheetTiles);
                listeBougeable.add(plancher);
                listeEntite.add(plancher);
            }
        }
        
        

        princesse = new Princess(64, 64, spriteSheetPrincesse);
        listeEntite.add(princesse);
    
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
    for (Bougeable bougeable : listeBougeable) {
            bougeable.bouger();
        }
        getKeys();
        traiterKeys();
        gererCollision();
        deplacerBackground();
    
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
    for (Entite entite : listeEntite) {
            g.drawImage(entite.getImage(), entite.getX(), entite.getY());
        }
    }

    @Override
    public void update(Observable o, Object o1) {
    }
     private void getKeys() {
        if (input.isKeyDown(Input.KEY_SPACE)) {
            if (!listeKeys.contains(KeyCode.SPACE)) {
                listeKeys.add(KeyCode.SPACE);
            }
        } else {
            listeKeys.remove(KeyCode.SPACE);
        }

        //FLECHE DROITE
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            if (!listeKeys.contains(KeyCode.RIGHT)) {
                listeKeys.add(KeyCode.RIGHT);
            }
        } else {
            listeKeys.remove(KeyCode.RIGHT);

        }
        if (input.isKeyDown(Input.KEY_LEFT)) {
            if (!listeKeys.contains(KeyCode.LEFT)) {
                listeKeys.add(KeyCode.LEFT);
            }
        } else {
            listeKeys.remove(KeyCode.LEFT);

        }
        if (input.isKeyDown(Input.KEY_UP)) {
            if (!listeKeys.contains(KeyCode.UP)) {
                listeKeys.add(KeyCode.UP);
            }
        } else {
            listeKeys.remove(KeyCode.UP);

        }
        if (input.isKeyDown(Input.KEY_DOWN)) {
            if (!listeKeys.contains(KeyCode.DOWN)) {
                listeKeys.add(KeyCode.DOWN);
            }
        } else {
            listeKeys.remove(KeyCode.DOWN);

        }
    }

    private void traiterKeys() {
        princesse.bouger(listeKeys); // Bouger le joueur tiendra compte de la liste

        if (listeKeys.contains(KeyCode.SPACE)) {
            tirerBalle(); // Méthode qui va ajouter un projectile tiré à l’écran
        }

    }

    private void gererCollision() {
    }

    private void tirerBalle() {
    }

    private void deplacerBackground() {
        for (Entite entite : listeEntite) {
            if (entite instanceof Plancher) {
                if (entite.getX() + entite.getWidth() <= 0) {
                    entite.setX(LARGEUR);

                }
            }
        }

    }
}
