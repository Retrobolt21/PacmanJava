package pacmanproject;
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
    
    int tileSize;
    
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
        imgPacmanMovingStep = 32f;
        
        tileSize = 32;
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
        
        //Récupération input
        Input input = gc.getInput();        
        
        //Direction Pacman
        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            if (map.getTileId(posX + 1, posY, indexCalqueMurs) == 0) {
                imgPacmanPosX += imgPacmanMovingStep;
            }
        } else if (input.isKeyPressed(Input.KEY_LEFT)) {
            if (map.getTileId(posX - 1, posY, indexCalqueMurs) == 0) {
                imgPacmanPosX -= imgPacmanMovingStep;
            }             
        } else if (input.isKeyPressed(Input.KEY_UP)) {
            if (map.getTileId(posX, posY - 1, indexCalqueMurs) == 0) {
                imgPacmanPosY -= imgPacmanMovingStep; 
            } 
        } else if (input.isKeyPressed(Input.KEY_DOWN)) {
            if (map.getTileId(posX, posY + 1, indexCalqueMurs) == 0) {
                imgPacmanPosY += imgPacmanMovingStep;
            }             
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
