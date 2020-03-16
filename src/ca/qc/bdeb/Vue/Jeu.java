/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.Vue;

import ca.qc.bdeb.Controleur.Controleur;
import static ca.qc.bdeb.Controleur.Controleur.HAUTEUR;
import static ca.qc.bdeb.Controleur.Controleur.HAUTEUR_SOL;
import static ca.qc.bdeb.Controleur.Controleur.LARGEUR;
import ca.qc.bdeb.Modele.Modele;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Jeu extends BasicGame implements Observer {

    private Controleur controleur;
    private Modele modele;
    private CopyOnWriteArrayList<Bougeable> listeBougeable = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Entite> listeEntite = new CopyOnWriteArrayList<>();
    private ArrayList<KeyCode> listeKeys = new ArrayList<>(); // Les touches enfoncées
    private Input input; // L’entrée (souris/touches de clavier, etc.)
    private SpriteSheet spriteSheetTiles, spriteSheetDivers,
            spriteSheetCoeur, spriteSheetMonde, spriteSheetPrincesse,
            spriteSheetBonus, spriteSheetMoreTiles,spriteSheetMoreItems;

    public static final float buffer = 50;
    private Princess princesse;
    private boolean isFinDePartie = false;
    private int vies, points, decallage, intervaleHauteur, indexBackGround;
    private final Random random = new Random();

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
        spriteSheetCoeur = new SpriteSheet("/images/coeur.png", 32, 32);
        spriteSheetBonus = new SpriteSheet("images/sprites_divers.png", 32, 32);
        spriteSheetMoreItems = new SpriteSheet("/images/sprites_more_items.jpg", 58, 58);
        spriteSheetMoreTiles = new SpriteSheet("images/spritesheet_more_tiles.png", 16, 16);

        vies = modele.getHealthPoints();
        waveTimer.start();
        decallage = HAUTEUR - HAUTEUR_SOL;

        initCiel();
        initPlancher();
        initPrincesse();

        indexBackGround = listeEntite.size();

        intervaleHauteur = (int) (HAUTEUR - HAUTEUR_SOL - princesse.getHeight());

    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        for (Bougeable bougeable : listeBougeable) {
            bougeable.bouger();
        }
        deplacerBackground();
        enleverEntitesExterieurEcran();

        if (!isFinDePartie) {

            getKeys();
            traiterKeys();

            gererCollisionJoueurEnnemi();
            gererCollisionBulletEnnemi();
            gererCollisionJoueurBonus();
        }

        if (isFinDePartie) {
            if (input.isKeyDown(Input.KEY_R)) {
                restartGame();
            }
        }

    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        for (Entite entite : listeEntite) {
            g.drawImage(entite.getImage(), entite.getX(), entite.getY());
        }

        g.drawString("Points: " + points, LARGEUR - buffer * 5, 10);

        int posXCoeur = 16, posYCoeur = 32;
        for (int i = 0; i < vies; i++) {
            g.drawImage(spriteSheetCoeur, posXCoeur, posYCoeur);
            posXCoeur += 32;
        }

        if (isFinDePartie) {
            princesse.falling();
            //stopBackgroundFromMoving();
            g.drawString("GAME OVER. PRESS R TO RESTART", (LARGEUR / 2) - buffer, HAUTEUR / 2);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        isFinDePartie = modele.isFinDePartie();
        if (isFinDePartie) {
            // tempsCompteARebours = System.currentTimeMillis() + 2500;
            //clearCollisionableList();
            stopBackgroundFromMoving();
            //beach doit crash
            //game over pour 2 sec+ freeze app
            // restartGame();

        }
        vies = modele.getHealthPoints();
        points = modele.getPoints();
    }

    private void clearCollisionableList() {
        ArrayList<Entite> listeTemp = new ArrayList();
        for (Entite entite : listeEntite) {
            if (entite instanceof Collisionable) {
                listeTemp.add(entite);
            }
        }
        listeEntite.removeAll(listeTemp);
        listeBougeable.removeAll(listeTemp);
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
        if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
            if (!listeKeys.contains(KeyCode.RIGHT)) {
                listeKeys.add(KeyCode.RIGHT);
            }
        } else {
            listeKeys.remove(KeyCode.RIGHT);

        }
        if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
            if (!listeKeys.contains(KeyCode.LEFT)) {
                listeKeys.add(KeyCode.LEFT);
            }
        } else {
            listeKeys.remove(KeyCode.LEFT);

        }
        if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
            if (!listeKeys.contains(KeyCode.UP)) {
                listeKeys.add(KeyCode.UP);
            }
        } else {
            listeKeys.remove(KeyCode.UP);

        }
        if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
            if (!listeKeys.contains(KeyCode.DOWN)) {
                listeKeys.add(KeyCode.DOWN);
            }
        } else {
            listeKeys.remove(KeyCode.DOWN);

        }
    }

    private void traiterKeys() throws SlickException {
        princesse.bouger(listeKeys);
        if (listeKeys.contains(KeyCode.SPACE) && princesse.peutAttaquer()) {
            tirerBalle();
        }

    }
    private javax.swing.Timer waveTimer = new javax.swing.Timer(5000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                //  if (!isFinDePartie) {
                declencherWaveEnnemis();
                // }
            } catch (Exception ex) {
                Logger.getLogger(Jeu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    });

    public boolean isBonusTriple() {
//        if (tempsBonusTriple + 10000 < System.currentTimeMillis()) {
//            return false;
//        }
        return false;
    }

    private void tirerBalle() throws SlickException {
        if (!isBonusTriple()) {
            Bullet bullet = new Bullet(princesse.getX() + princesse.getWidth(), princesse.getY() + (princesse.getHeight() / 4));
            listeBougeable.add(bullet);
            listeEntite.add(bullet);
        } else {
            Bullet bullet = new Bullet(princesse.getX() + princesse.getWidth(), princesse.getY() + (princesse.getHeight() / 4));
            listeBougeable.add(bullet);
            listeEntite.add(bullet);
            Bullet bullet1 = new Bullet(princesse.getX() + princesse.getWidth(), princesse.getY() + (princesse.getHeight() / 4));
            bullet.setDeltaY(12);
            listeBougeable.add(bullet1);
            listeEntite.add(bullet1);
            Bullet bullet2 = new Bullet(princesse.getX() + princesse.getWidth(), princesse.getY() + (princesse.getHeight() / 4));
            bullet2.setDeltaY(-12);
            listeBougeable.add(bullet2);
            listeEntite.add(bullet2);
        }
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

    private void initPrincesse() {
        princesse = new Princess(64, 64, spriteSheetPrincesse);
        listeEntite.add(princesse);

    }

    private void initPlancher() {
        int hauteurPlancher = 2;
        for (int i = 0; i <= LARGEUR + 32; i += 32) {
            for (int j = 0; j <= hauteurPlancher; j++) {
                Plancher plancher = new Plancher(i, HAUTEUR - j * 32, spriteSheetTiles);
                listeBougeable.add(plancher);
                listeEntite.add(plancher);
            }
        }
    }

    private void initCiel() {
        for (int i = 0; i < LARGEUR; i += 32) {
            for (int j = 0; j < HAUTEUR; j += 32) {
                Ciel ciel = new Ciel(i, j, spriteSheetTiles);
                listeBougeable.add(ciel);
                listeEntite.add(ciel);

            }
        }
    }

    private void gererCollisionBulletEnnemi() throws SlickException {
        ArrayList<Entite> listeTemp = new ArrayList();
        for (Bougeable b1 : listeBougeable) {

            for (Bougeable b2 : listeBougeable) {
                if ((b1 instanceof Ennemi && b2 instanceof Bullet)) {

                    if (b1.getRectangle().intersects(b2.getRectangle())) {

                        listeTemp.add((Entite) b1);
                        listeTemp.add((Entite) b2);

                        controleur.elimineEnnemis();
                    }
                }
            }
        }

        if (!listeTemp.isEmpty()) {
            //int r = random.nextInt(10);
            int r=1;
            if (r == 1) {
                spondBonus(listeTemp.get(0).getRectangle().getX(), listeTemp.get(0).getRectangle().getY());
            }
        }
        listeEntite.removeAll(listeTemp);
        listeBougeable.removeAll(listeTemp);
    }

    private void spondBonus(float x, float y) throws SlickException {
        // int choixBonus = random.nextInt(3);
        int choixBonus = 0;

        Bonus bonus = null;
        switch (choixBonus) {
            case 0:
                bonus = new BoostEnergie(x, y, spriteSheetBonus);
                break;
            case 1:
//                bonus = new BonusFleur(x, y, spriteSheetMoreItems);
//                bonus = new BonusFleur(x, y, spriteSheetMoreTiles);
                
                
                
                //  bonus = new BombeMega(x, y, spriteSheetBonus);

                break;
            case 2:
                //  bonus = new ArmeABalles(x, y, spriteSheetBonus);
                break;
              default:
        }
        listeEntite.add(indexBackGround, bonus);
        listeBougeable.add((Bougeable) bonus);
    }

    private void gererCollisionJoueurEnnemi() {
        ArrayList<Entite> listeTemp = new ArrayList();

        for (Bougeable bougeable : listeBougeable) {
            if (bougeable instanceof Ennemi || bougeable instanceof BulletEnnemi) {
                if (princesse.getRectangle().intersects(bougeable.getRectangle())) {
                    listeTemp.add((Entite) bougeable);
                    controleur.perdVie();
                }
            }
        }

        listeEntite.removeAll(listeTemp);
        listeBougeable.removeAll(listeTemp);

    }

    private void gererCollisionJoueurBonus() {
        ArrayList<Entite> listeTemp = new ArrayList();
        Bonus bonus = null;

        for (Bougeable bougeable : listeBougeable) {
            if (bougeable instanceof Bonus) {
                if (princesse.getRectangle().intersects(bougeable.getRectangle())) {
                    listeTemp.add((Entite) bougeable);

                    controleur.ramasseBonus();
                    bonus = (Bonus) bougeable;

                }
            }
        }

        if (!listeTemp.isEmpty()) {
            activerPouvoirBonus(bonus);
        }

        listeBougeable.removeAll(listeTemp);
        listeEntite.removeAll(listeTemp);

    }

    private void enleverEntitesExterieurEcran() {
        ArrayList<Entite> listeTemp = new ArrayList();

        for (Entite entite : listeEntite) {
            if (entite.getDetruire()) {
                listeTemp.add(entite);
            }
        }
        listeEntite.removeAll(listeTemp);
        listeBougeable.remove(listeTemp);
    }

    private void activerPouvoirBonus(Bonus bonus) {
        ArrayList<Entite> listeTemp = new ArrayList();
//
//        if (bonus instanceof BombeMega) {
//            for (Bougeable bougeable : listeBougeable) {
//                if (bougeable instanceof Ennemi && ((Entite) bougeable).getX() < LONGUEUR) {
//                    listeTemp.add((Entite) bougeable);
//                }
//            }

//            int nbEnnemisTues = listeTemp.size();
//            controleur.ramasseBombeMega(nbEnnemisTues);
//
//            listeEntite.removeAll(listeTemp);
//            listeBougeable.removeAll(listeTemp);
//        }
        if (bonus instanceof BoostEnergie) {
            controleur.ramasseBoostEnergie();
        }
//        if (bonus instanceof ArmeABalles) {
//            tempsBonusTriple = System.currentTimeMillis();
//
//        }

    }

    private void declencherWaveEnnemis() throws SlickException {
//         int choixTypeEnnemi = random.nextInt(3);
        int choixTypeEnnemi = 1;

        switch (choixTypeEnnemi) {
            case 0:
                creerEnnemisBulles();
                break;
            case 1:
                creerEnnemiRocket();
                break;
            case 2:
                creerEnnemiBouncyBall();
                break;
                
            case 3:
                creerEnnemiFlappyBird();
                break;
            case 4:
                creerEnnemiSShape();
                break;
            case 5:
                creerEnnemiWheel();
            default:

        }
    }

    private int getNumberEnnemisSpawned() {
        int nbEnnemis;
        do {
            nbEnnemis = random.nextInt(6) * 2;//pour que ca soit pair
        } while (nbEnnemis == 0);

        return nbEnnemis;
    }

    private void creerEnnemisBulles() {
       int sep = random.nextInt(20);
        
        for (int i = 0; i < getNumberEnnemisSpawned(); i++) {
            EnnemiBulle ennemiBulle = new EnnemiBulle(LARGEUR + i * 25,
                    HAUTEUR-HAUTEUR_SOL-32-sep, spriteSheetDivers);
            listeEntite.add(ennemiBulle);
            listeBougeable.add(ennemiBulle);
        }
        
    }

    private void creerEnnemiRocket() {
        int hauteur = random.nextInt(intervaleHauteur);
        EnnemiRocket ennemiRocket = new EnnemiRocket(LARGEUR, hauteur, spriteSheetDivers);
        listeEntite.add(ennemiRocket);
        listeBougeable.add(ennemiRocket);
    }

    private void restartGame() {
        modele.restartPartie();
//        makeBackgroundMoveAgain();
        clearCollisionableList();
        princesse.setLocation(64, HAUTEUR - HAUTEUR_SOL - princesse.getHeight());

    }

    private void makeBackgroundMoveAgain() {

    }

    private void stopBackgroundFromMoving() {

        //todo
        ArrayList<Entite> listeTemp = new ArrayList();

        for (Bougeable bougeable : listeBougeable) {
            if (bougeable instanceof Background) {
                listeTemp.add((Entite) bougeable);
            }
        }

    }

    private void creerEnnemiBouncyBall() {
         int posY = random.nextInt(HAUTEUR / 16) + 20;
        for (int i = 0; i < getNumberEnnemisSpawned(); i++) {
            EnnemiBouncyBall ennemi = new EnnemiBouncyBall(LARGEUR + buffer,
                    posY + i * 32, spriteSheetDivers);
            listeEntite.add(ennemi);
            listeBougeable.add(ennemi);
            posY += 30;
        }



    }

    private void creerEnnemiFlappyBird() {
     int posY = random.nextInt(HAUTEUR / 16) + 20;
        for (int i = 0; i < getNumberEnnemisSpawned(); i++) {
            EnnemiFlappyBird ennemi = new EnnemiFlappyBird(LARGEUR + buffer,
                    posY + i * 32, spriteSheetDivers);
            listeEntite.add(ennemi);
            listeBougeable.add(ennemi);
            posY += 30;
        }
        
        
    }

    private void creerEnnemiSShape() {
 int posY = random.nextInt(200) + HAUTEUR/4;
        for (int i = 0; i < getNumberEnnemisSpawned(); i++) {
            EnnemiSShape ennemiAerienZigZag = new EnnemiSShape(LARGEUR + buffer + i * 42, posY, spriteSheetDivers);
            listeEntite.add(ennemiAerienZigZag);
            listeBougeable.add(ennemiAerienZigZag);
        }

    }

    private void creerEnnemiWheel() {
       int posY = random.nextInt(250) + 150;

        float[][] positionRoue = modele.getPositionRoue();
        for (int i = 0; i < positionRoue[0].length; i++) {

            Ennemi ennemiAerienRoue = new EnnemiWheel(LARGEUR + buffer + positionRoue[0][i],
                    posY - positionRoue[1][i],
                    spriteSheetDivers, positionRoue[2][i], posY);

            listeEntite.add(ennemiAerienRoue);
            listeBougeable.add(ennemiAerienRoue);
        }
    
    
    }

}
