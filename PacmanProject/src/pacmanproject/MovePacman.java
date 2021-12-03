/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanproject;

import java.util.TimerTask;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author ETI
 */
public class MovePacman extends TimerTask {

    public float imgPacmanPosX;
    public float imgPacmanPosY;
    public int indexCalqueMurs;
    public float imgPacmanMovingStep;
    public TiledMap map;
    public char directionPacman;
    public int tileSize;
    public int posX;
    public int posY;

    /**
     *
     * @param imgPacmanPosX
     * @param imgPacmanPosY
     * @param indexCalqueMurs
     * @param imgPacmanMovingStep
     * @param map
     * @param directionPacman
     * @param tileSize
     */
    public MovePacman(float imgPacmanPosX, float imgPacmanPosY, int indexCalqueMurs, float imgPacmanMovingStep, TiledMap map, char directionPacman, int tileSize) {
        this.imgPacmanPosX = imgPacmanPosX;
        this.imgPacmanPosY = imgPacmanPosY;
        this.indexCalqueMurs = indexCalqueMurs;
        this.imgPacmanMovingStep = imgPacmanMovingStep;
        this.map = map;
        this.directionPacman = directionPacman;
        this.tileSize = tileSize;
    }
    
    @Override
    public void run() {
        System.out.println("Move Pacman");
        posX = Math.round(imgPacmanPosX) / tileSize;
        posY = Math.round(imgPacmanPosY) / tileSize;
        if (directionPacman == 'R') {
            if (map.getTileId(posX + 1, posY, indexCalqueMurs) == 0) {
                imgPacmanPosX += imgPacmanMovingStep;
            }
        } else if (directionPacman == 'L') {
            if (map.getTileId(posX - 1, posY, indexCalqueMurs) == 0) {
                imgPacmanPosX -= imgPacmanMovingStep;
            }             
        } else if (directionPacman == 'U') {
            if (map.getTileId(posX, posY - 1, indexCalqueMurs) == 0) {
                imgPacmanPosY -= imgPacmanMovingStep; 
            } 
        } else if (directionPacman == 'D') {
            if (map.getTileId(posX, posY + 1, indexCalqueMurs) == 0) {
                imgPacmanPosY += imgPacmanMovingStep;
            }             
        }
    }
}
