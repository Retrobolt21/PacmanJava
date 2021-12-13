/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanproject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Corentin
 */
public class boules {
    int posX;
    int posY;
    int tileSize = 32;
    int Score = 0;
    private Image imgBoule;
    private Image imgBoule_2;
    private TiledMap map;
    int indexCalqueMurs;    
    int[][] array = new int[21][24];
    
    public boules() {
        
    }   
    
    public boules(Image imgBoule, TiledMap map) throws SlickException {
        this.imgBoule_2 = new Image("./sprites/boule_2.png");
        this.imgBoule = imgBoule;
        this.map = map;
        indexCalqueMurs = map.getLayerIndex("murs");
    }
    
    private int[][] getAllTiles() {
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 24; j++) {
               array[i][j] = map.getTileId(i, j, indexCalqueMurs);
            }
        }
        
        return array;
    }
    
    public void drawAllBoule() {
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 24; j++) {
                if (map.getTileId(i, j, indexCalqueMurs) == 0) {
                    if ((i != 0 && j != 0) && (i != 20 && j != 23)) {
                        if(array[i][j] == 0) {
                            drawBoule(i * 32, j * 32); 
                        }                                              
                    }                   
                } else if (array[i][j] == -1) {
                    drawBoule_2(i * 32, j * 32);
                }               
            }
        }
    }
    
    public void drawBoule(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        imgBoule.draw(posX, posY, 32, 32);        
    }
    
    public void drawBoule_2(int posX, int posY) {
        imgBoule_2.draw(posX, posY, 32, 32);
    }

    public void arrayPacman(int posX, int posY) {
        int posXTiled = posX / tileSize;
        int posYTiled = posY / tileSize;
        
        array[posXTiled][posYTiled] = -1;
    }
    
    public int countScore() {
        Score = 0;
        for (int i = 0; i < 21; i++) {
            for (int j = 0; j < 24; j++) {
                if(array[i][j] == -1) {
                    Score++;
                }
            }
        }
        
        return Score;
    }
}
