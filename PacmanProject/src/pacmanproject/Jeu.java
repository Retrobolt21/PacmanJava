package pacmanproject;
import pacmanproject.fantom;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;
/**
 *
 * @author Benjamin et Corentin
 */

enum Direction {
    UP, DOWN, RIGHT, LEFT
}

public class Jeu extends BasicGame {
    private TiledMap map;
    private Image imgPacman;
    private Image imgPacmanAnim;
    private Image imgBoule;
    int indexCalqueMurs;
    float imgPacmanPosX;
    float imgPacmanPosY;
    int imgPacmanDiametre;
    float imgPacmanMovingStep;
    float imgPacmanMovingStepBase;
    char directionPacman;
    
    boolean gameOver = false;
    
    public int vitesseI;
    
    //Variable FantomTileID
    int tileID_LEFT;
    int tileID_RIGHT;
    int tileID_DOWN;
    int tileID_UP;
    
    fantom fantom1;
    fantom fantom2;
    fantom fantom3;
    fantom fantom4;
    fantom fantom5;
    fantom fantom6;
    fantom fantom7;
    fantom fantom8;
    
    boules boule1;
    
    ScoreBoard ScoreBoard1;    
    
    private Direction direction;
    
    int tileSize;

    int timeI;
    int timeAnimationPacman;
    int animationPacman;
    
    Timer timer;
    
    public Jeu(String titre) {
        super(titre);
    }
    
    private int[][] drawAllBoules() {
        int tileSizeArray = 16;
        int tilePosX = 0;
        int tilePosY = 0;
        int[][] array = new int[21][24];
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 24; j++) {
               array[i][j] = map.getTileId(i, j, indexCalqueMurs);
            }
        }       
        
        /*System.out.println("DEBUT TABLEAU -----------------------------------------");
        for (int k = 0; k < 21; k++) {
            System.out.println("X : " + k);
            for (int l = 0; l < 24; l++) {
                System.out.println(array[k][l]);
            }
        }
        System.out.println("FIN TABLEAU -----------------------------------------");*/
        return array;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        //map = new TiledMap("./maps/map_pacman_final.tmx");
        map = new TiledMap("./maps/mapv2.tmx");
        imgPacman = new Image("./images/pacman.png");
        imgPacmanAnim = new Image("./sprites/pacmanv2.png");
        imgBoule = new Image("./sprites/boule.png");
        indexCalqueMurs = map.getLayerIndex("murs");
        imgPacmanPosX = 320;
        imgPacmanPosY = 320;
        imgPacmanDiametre = 32;
        imgPacmanMovingStepBase = 32f;
        imgPacmanMovingStep = 32f;
        directionPacman = 'D';  
        
        vitesseI = 0;
        
        direction = Direction.RIGHT;
        
        timer = new Timer();
        
        tileSize = 32;
        
        timeI = 0;
        timeAnimationPacman = 0;
        animationPacman = 0;
        
        //int[][] arrayRetour = getAllTilesMap();
        
        /*for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 24; j++) {
                System.out.println(arrayRetour[i][j]);
            }
        }*/
        
        fantom1 = new fantom(64, 64, imgPacmanAnim, 1, 20);
        fantom2 = new fantom(320, 64, imgPacmanAnim, 2, 20);
        fantom3 = new fantom(576, 64, imgPacmanAnim, 3, 20);
        fantom4 = new fantom(64, 320, imgPacmanAnim, 4, 20);
        fantom5 = new fantom(576, 320, imgPacmanAnim, 5, 20);
        fantom6 = new fantom(64, 672, imgPacmanAnim, 6, 20);
        fantom7 = new fantom(576, 672, imgPacmanAnim, 7, 20);        
        
        boule1 = new boules(imgBoule, map);
        
        ScoreBoard1 = new ScoreBoard();
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        //--------DECLARATION VARIABLES--------
        //variables pour ne pas sortir de la map
        int largeurFenetre = gc.getWidth();
        int hauteurFenetre = gc.getHeight();
        int fenetreLimiteDroite = largeurFenetre - imgPacmanDiametre;
        int fenetreLimitebas = hauteurFenetre - imgPacmanDiametre;
        int posX = Math.round(imgPacmanPosX) / tileSize;
        int posY = Math.round(imgPacmanPosY) / tileSize;  
        
        vitesseI = i;
        
        int vitessePacman = 300; //En ms
        int vitesseAnimationPacman = 150; //En ms
        
        timeI += i;
        timeAnimationPacman += i;
        //System.out.println("temps : " + timeI);
        
        //Récupération input
        Input input = gc.getInput();

        //Direction Pacman continu dans la direction Pressed
        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            directionPacman = 'R';
            direction = Direction.RIGHT;
        }
        if (input.isKeyPressed(Input.KEY_LEFT)) {
            directionPacman = 'L';
            direction = Direction.LEFT;
        }
        if (input.isKeyPressed(Input.KEY_UP)) {
            directionPacman = 'U';
            direction = Direction.UP;
        }
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            directionPacman = 'D';
            direction = Direction.DOWN;
        }
        
        //Test Timer Moove pacman
        //timer.schedule(new MovePacman(imgPacmanPosX, imgPacmanPosY, indexCalqueMurs, imgPacmanMovingStep, map, directionPacman, tileSize), 0, 5000);
        
        //Direction Pacman
        if (timeI > vitessePacman) {
            switch (directionPacman) {
            case 'R':
                if (map.getTileId(posX + 1, posY, indexCalqueMurs) == 0) {
                    imgPacmanPosX += imgPacmanMovingStep;
                }   break;
            case 'L':
                if (map.getTileId(posX - 1, posY, indexCalqueMurs) == 0) {
                    imgPacmanPosX -= imgPacmanMovingStep;
                }   break;
            case 'U':
                if (map.getTileId(posX, posY - 1, indexCalqueMurs) == 0) {
                    imgPacmanPosY -= imgPacmanMovingStep;
                }   break;
            case 'D':
                if (map.getTileId(posX, posY + 1, indexCalqueMurs) == 0) {
                    imgPacmanPosY += imgPacmanMovingStep;
                }   break;             
            default:
                break;
            }
            timeI = 0;
        }        
        
        //Empêcher de sortir de la map
        if (imgPacmanPosX > fenetreLimiteDroite) {
            imgPacmanPosX = fenetreLimiteDroite;
        } else if (imgPacmanPosX < 0.0f) {
            imgPacmanPosX = 0.0f;
        } else if (imgPacmanPosY < 0.0f) {
            imgPacmanPosY = 0.0f;
        } else if (imgPacmanPosY > fenetreLimitebas) {
            imgPacmanPosY = fenetreLimitebas;
        }
        
        //Time Animation Pacman
        if (timeAnimationPacman > vitesseAnimationPacman) {
            animationPacman++;
            timeAnimationPacman = 0;
        }        
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {        
        //variables animation
        int tailleSubImage = 16;
        int decouper_X1 = tailleSubImage * 1; 
        int decouper_Y1 = tailleSubImage * 1;        
        int decouper_X2 = tailleSubImage * 2;
        int decouper_Y2 = tailleSubImage * 2;
        
        map.render(0,0);
        
        if (gameOver == false) {
            //Switch Case Animation pacman
            switch(animationPacman % 2) {
                case 0 :
                    switch(direction) {
                        case RIGHT :                       
                            decouper_X1 = tailleSubImage * 0;
                            decouper_Y1 = tailleSubImage * 0;
                            decouper_X2 = tailleSubImage * 1;
                            decouper_Y2 = tailleSubImage * 1;
                            break;
                        case LEFT :
                            decouper_X1 = tailleSubImage * 0;
                            decouper_Y1 = tailleSubImage * 1;
                            decouper_X2 = tailleSubImage * 1;
                            decouper_Y2 = tailleSubImage * 2;
                            break;
                        case UP :
                            decouper_X1 = tailleSubImage * 0;
                            decouper_Y1 = tailleSubImage * 2;
                            decouper_X2 = tailleSubImage * 1;
                            decouper_Y2 = tailleSubImage * 3;
                            break;
                        case DOWN :
                            decouper_X1 = tailleSubImage * 0;
                            decouper_Y1 = tailleSubImage * 3;
                            decouper_X2 = tailleSubImage * 1;
                            decouper_Y2 = tailleSubImage * 4;
                            break;
                    }
                    break;
                case 1 :
                    switch(direction) {
                        case RIGHT :                       
                            decouper_X1 = tailleSubImage * 1;
                            decouper_Y1 = tailleSubImage * 0;
                            decouper_X2 = tailleSubImage * 2;
                            decouper_Y2 = tailleSubImage * 1;
                            break;
                        case LEFT :
                            decouper_X1 = tailleSubImage * 1;
                            decouper_Y1 = tailleSubImage * 1;
                            decouper_X2 = tailleSubImage * 2;
                            decouper_Y2 = tailleSubImage * 2;
                            break;
                        case UP :
                            decouper_X1 = tailleSubImage * 1;
                            decouper_Y1 = tailleSubImage * 2;
                            decouper_X2 = tailleSubImage * 2;
                            decouper_Y2 = tailleSubImage * 3;
                            break;
                        case DOWN :
                            decouper_X1 = tailleSubImage * 1;
                            decouper_Y1 = tailleSubImage * 3;
                            decouper_X2 = tailleSubImage * 2;
                            decouper_Y2 = tailleSubImage * 4;
                            break;
                    }
                    break;
            }

            //int[][] testArray = Jeu.getAllTiles();
            //System.out.println(testArray[0][0]);
            
            boule1.drawAllBoule();

            imgPacmanAnim.draw(imgPacmanPosX, imgPacmanPosY, imgPacmanPosX + 32, imgPacmanPosY + 32, decouper_X1, decouper_Y1, decouper_X2, decouper_Y2);

            //System.out.println("PositionX fantom : " + fantom1.getPosX() + " / PositionY fantom : " + fantom1.getPosY());
            //System.out.println("PositionX pacman : " + ((int)imgPacmanPosX / 32) + " / PositionY pacman : " + ((int)imgPacmanPosY / 32));
            
            //fantom1
            if((fantom1.getPosX() == ((int)imgPacmanPosX / 32)) && (fantom1.getPosY() == ((int)imgPacmanPosY / 32))) {
            //System.out.println("GameOver");
            fantom1.gameOver();
            imgPacmanMovingStep = 0;
            gameOver = true;
            } else {
                tileID_RIGHT = map.getTileId(fantom1.getPosX() + 1, fantom1.getPosY(), indexCalqueMurs);
                tileID_LEFT = map.getTileId(fantom1.getPosX() - 1, fantom1.getPosY(), indexCalqueMurs);
                tileID_UP = map.getTileId(fantom1.getPosX(), fantom1.getPosY() - 1, indexCalqueMurs);
                tileID_DOWN = map.getTileId(fantom1.getPosX(), fantom1.getPosY() + 1, indexCalqueMurs);        
                fantom1.mooveFantom(tileID_LEFT, tileID_RIGHT, tileID_DOWN, tileID_UP, vitesseI); 
            }               

            //Fantom2
            if((fantom2.getPosX() == ((int)imgPacmanPosX / 32)) && (fantom2.getPosY() == ((int)imgPacmanPosY / 32))) {
                //System.out.println("GameOver");
                fantom2.gameOver();
                imgPacmanMovingStep = 0;
                gameOver = true;
            } else {            
                tileID_RIGHT = map.getTileId(fantom2.getPosX() + 1, fantom2.getPosY(), indexCalqueMurs);
                tileID_LEFT = map.getTileId(fantom2.getPosX() - 1, fantom2.getPosY(), indexCalqueMurs);
                tileID_UP = map.getTileId(fantom2.getPosX(), fantom2.getPosY() - 1, indexCalqueMurs);
                tileID_DOWN = map.getTileId(fantom2.getPosX(), fantom2.getPosY() + 1, indexCalqueMurs);        
                fantom2.mooveFantom(tileID_LEFT, tileID_RIGHT, tileID_DOWN, tileID_UP, vitesseI);
            }

            //Fantom3
            if((fantom3.getPosX() == ((int)imgPacmanPosX / 32)) && (fantom3.getPosY() == ((int)imgPacmanPosY / 32))) {
                //System.out.println("GameOver");
                fantom3.gameOver();
                imgPacmanMovingStep = 0;
                gameOver = true;
            } else {
                tileID_RIGHT = map.getTileId(fantom3.getPosX() + 1, fantom3.getPosY(), indexCalqueMurs);
                tileID_LEFT = map.getTileId(fantom3.getPosX() - 1, fantom3.getPosY(), indexCalqueMurs);
                tileID_UP = map.getTileId(fantom3.getPosX(), fantom3.getPosY() - 1, indexCalqueMurs);
                tileID_DOWN = map.getTileId(fantom3.getPosX(), fantom3.getPosY() + 1, indexCalqueMurs);        
                fantom3.mooveFantom(tileID_LEFT, tileID_RIGHT, tileID_DOWN, tileID_UP, vitesseI);
            }        

            //Fantom4
            if((fantom4.getPosX() == ((int)imgPacmanPosX / 32)) && (fantom4.getPosY() == ((int)imgPacmanPosY / 32))) {
                //System.out.println("GameOver");
                fantom4.gameOver();
                imgPacmanMovingStep = 0;
                gameOver = true;
            } else {
                tileID_RIGHT = map.getTileId(fantom4.getPosX() + 1, fantom4.getPosY(), indexCalqueMurs);
                tileID_LEFT = map.getTileId(fantom4.getPosX() - 1, fantom4.getPosY(), indexCalqueMurs);
                tileID_UP = map.getTileId(fantom4.getPosX(), fantom4.getPosY() - 1, indexCalqueMurs);
                tileID_DOWN = map.getTileId(fantom4.getPosX(), fantom4.getPosY() + 1, indexCalqueMurs);        
                fantom4.mooveFantom(tileID_LEFT, tileID_RIGHT, tileID_DOWN, tileID_UP, vitesseI);
            }        

            //Fantom5
            if((fantom5.getPosX() == ((int)imgPacmanPosX / 32)) && (fantom5.getPosY() == ((int)imgPacmanPosY / 32))) {
                //System.out.println("GameOver");
                fantom5.gameOver();
                imgPacmanMovingStep = 0;
                gameOver = true;
            } else {
                tileID_RIGHT = map.getTileId(fantom5.getPosX() + 1, fantom5.getPosY(), indexCalqueMurs);
                tileID_LEFT = map.getTileId(fantom5.getPosX() - 1, fantom5.getPosY(), indexCalqueMurs);
                tileID_UP = map.getTileId(fantom5.getPosX(), fantom5.getPosY() - 1, indexCalqueMurs);
                tileID_DOWN = map.getTileId(fantom5.getPosX(), fantom5.getPosY() + 1, indexCalqueMurs);        
                fantom5.mooveFantom(tileID_LEFT, tileID_RIGHT, tileID_DOWN, tileID_UP, vitesseI);
            }        

            //Fantom6
            if((fantom6.getPosX() == ((int)imgPacmanPosX / 32)) && (fantom6.getPosY() == ((int)imgPacmanPosY / 32))) {
                //System.out.println("GameOver");
                fantom6.gameOver();
                imgPacmanMovingStep = 0;
                gameOver = true;
            } else {
                tileID_RIGHT = map.getTileId(fantom6.getPosX() + 1, fantom6.getPosY(), indexCalqueMurs);
                tileID_LEFT = map.getTileId(fantom6.getPosX() - 1, fantom6.getPosY(), indexCalqueMurs);
                tileID_UP = map.getTileId(fantom6.getPosX(), fantom6.getPosY() - 1, indexCalqueMurs);
                tileID_DOWN = map.getTileId(fantom6.getPosX(), fantom6.getPosY() + 1, indexCalqueMurs);        
                fantom6.mooveFantom(tileID_LEFT, tileID_RIGHT, tileID_DOWN, tileID_UP, vitesseI);
            }        

            //Fantom7
            if((fantom7.getPosX() == ((int)imgPacmanPosX / 32)) && (fantom7.getPosY() == ((int)imgPacmanPosY / 32))) {
                //System.out.println("GameOver");
                fantom7.gameOver();
                imgPacmanMovingStep = 0;
                gameOver = true;
            } else {
                tileID_RIGHT = map.getTileId(fantom7.getPosX() + 1, fantom7.getPosY(), indexCalqueMurs);
                tileID_LEFT = map.getTileId(fantom7.getPosX() - 1, fantom7.getPosY(), indexCalqueMurs);
                tileID_UP = map.getTileId(fantom7.getPosX(), fantom7.getPosY() - 1, indexCalqueMurs);
                tileID_DOWN = map.getTileId(fantom7.getPosX(), fantom7.getPosY() + 1, indexCalqueMurs);        
                fantom7.mooveFantom(tileID_LEFT, tileID_RIGHT, tileID_DOWN, tileID_UP, vitesseI);
            }                

            //fantom1.drawFantom();

            boule1.arrayPacman((int)imgPacmanPosX, (int)imgPacmanPosY);

            ScoreBoard1.drawScoreBoard(boule1.countScore());
        } else {
            boule1.drawAllBoule();
            fantom1.drawFantom(vitesseI);
            fantom2.drawFantom(vitesseI);
            fantom3.drawFantom(vitesseI);
            fantom4.drawFantom(vitesseI);
            fantom5.drawFantom(vitesseI);
            fantom6.drawFantom(vitesseI);
            fantom7.drawFantom(vitesseI);
            
            fantom1.gameOver();
            ScoreBoard1.drawScoreBoard(boule1.countScore());
        }
        
    }
    
}
