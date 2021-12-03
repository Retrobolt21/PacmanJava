package pacmanproject;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;
/**
 *
 * @author Benjamin et Corentin
 */
public class Jeu extends BasicGame {
    private TiledMap map;
    private Image imgPacman;
    int indexCalqueMurs;
    float imgPacmanPosX;
    float imgPacmanPosY;
    int imgPacmanDiametre;
    float imgPacmanMovingStep;
    float imgPacmanMovingStepBase;
    char directionPacman;
    
    int tileSize;

    int timeI;
    
    Timer timer;
    
    public Jeu(String titre) {
        super(titre);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        map = new TiledMap("./maps/map_pacman_final.tmx");
        imgPacman = new Image("./images/pacman.png");
        indexCalqueMurs = map.getLayerIndex("murs");
        imgPacmanPosX = 320;
        imgPacmanPosY = 320;
        imgPacmanDiametre = 32;
        imgPacmanMovingStepBase = 32f;
        imgPacmanMovingStep = 32f;
        directionPacman = 'D';
        timer = new Timer();
        
        tileSize = 32;
        
        timeI = 0;
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
        
        int vitessePacman = 500; //En ms
        
        timeI += i;
        //System.out.println("temps : " + timeI);
        
        //Récupération input
        Input input = gc.getInput();

        //Direction Pacman continu dans la direction Pressed
        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            directionPacman = 'R';
        }
        if (input.isKeyPressed(Input.KEY_LEFT)) {
            directionPacman = 'L';
        }
        if (input.isKeyPressed(Input.KEY_UP)) {
            directionPacman = 'U';
        }
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            directionPacman = 'D';
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
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {        
        int largeur = 32;
        int hauteur = 32;
        
        map.render(0,0);
        imgPacman.draw(imgPacmanPosX, imgPacmanPosY, largeur, hauteur);
    }
    
}
