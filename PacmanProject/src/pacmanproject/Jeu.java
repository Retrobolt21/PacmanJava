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
    int indexCalqueMurs;
    float imgPacmanPosX;
    float imgPacmanPosY;
    int imgPacmanDiametre;
    float imgPacmanMovingStep;
    float imgPacmanMovingStepBase;
    char directionPacman;
    
    //Variable FantomTileID
    int tileID_LEFT;
    int tileID_RIGHT;
    int tileID_DOWN;
    int tileID_UP;
    
    fantom fantom1;
    
    private Direction direction;
    
    int tileSize;

    int timeI;
    int timeAnimationPacman;
    int animationPacman;
    
    Timer timer;
    
    public Jeu(String titre) {
        super(titre);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        map = new TiledMap("./maps/map_pacman_final.tmx");
        imgPacman = new Image("./images/pacman.png");
        imgPacmanAnim = new Image("./sprites/pacman.png");
        indexCalqueMurs = map.getLayerIndex("murs");
        imgPacmanPosX = 320;
        imgPacmanPosY = 320;
        imgPacmanDiametre = 32;
        imgPacmanMovingStepBase = 32f;
        imgPacmanMovingStep = 32f;
        directionPacman = 'D';  
        
        direction = Direction.RIGHT;
        
        timer = new Timer();
        
        tileSize = 32;
        
        timeI = 0;
        timeAnimationPacman = 0;
        animationPacman = 0;
        
        fantom1 = new fantom(64, 64, imgPacman, 1, 20);
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
        
        map.render(0,0);
        imgPacmanAnim.draw(imgPacmanPosX, imgPacmanPosY, imgPacmanPosX + 32, imgPacmanPosY + 32, decouper_X1, decouper_Y1, decouper_X2, decouper_Y2);
    
        tileID_RIGHT = map.getTileId(fantom1.getPosX() + 1, fantom1.getPosY(), indexCalqueMurs);
        tileID_LEFT = map.getTileId(fantom1.getPosX() - 1, fantom1.getPosY(), indexCalqueMurs);
        tileID_UP = map.getTileId(fantom1.getPosX(), fantom1.getPosY() - 1, indexCalqueMurs);
        tileID_DOWN = map.getTileId(fantom1.getPosX(), fantom1.getPosY() + 1, indexCalqueMurs);
        
        fantom1.mooveFantom(tileID_LEFT, tileID_RIGHT, tileID_DOWN, tileID_UP);
        //fantom1.drawFantom();
    }
    
}
