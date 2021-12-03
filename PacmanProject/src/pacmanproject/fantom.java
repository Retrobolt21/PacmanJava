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

/**
 *
 * @author ETI
 */
public class fantom {
    private Image imgFantom;
    int posX;
    int posY;
    int typeFantom;
    float movingStep = 32f;
    char directionFantom = aleaDirection();
    
    public fantom(int posX, int posY, Image imgFantom, int typeFantom) {
        this.posX = posX;
        this.posY = posY;
        this.imgFantom = imgFantom;
    }
    
    public void drawFantom() {
        
    }
    
    public void mooveFantom() {
        
    }
    
    private char aleaDirection() {
        int min = 1;
        int max = 5;
        int random = (int) (min + (Math.random() * (max - min)));
        
        switch(random) {
            case 1 :
                directionFantom = 'U';
            case 2 :
                directionFantom = 'D';
            case 3 :
                directionFantom = 'L';
            case 4 :
                directionFantom = 'R';
        }
        
        return directionFantom;
    }
    
}
