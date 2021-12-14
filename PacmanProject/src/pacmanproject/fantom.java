/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanproject;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author ETI
 */

public class fantom {
    private Image imgFantom;
    int posX;
    int posY;
    int min;
    int max;
    int typeFantom;
    int tileSize = 32;
    float movingStep = 32f;
    char directionFantom = 'R';
    int timeAnimationFantome = 0;
    int vitesseAnimFantom = 200; //Vitesse en Ms
    int compteurVitesseAnimFantom = 0;
    int vitesseFantome;
    int animationFantom = 0;
    boolean fantomInit = false;
    
    Font font = new Font("Verdana", Font.BOLD, 40);
    TrueTypeFont trueTypeFont = new TrueTypeFont(font, true);
    
    public fantom(int posX, int posY, Image imgFantom, int typeFantom, int vitesseFantome) {
        this.posX = posX;
        this.posY = posY;
        this.imgFantom = imgFantom;
        this.vitesseFantome = vitesseFantome;
        this.typeFantom = typeFantom + 3;
    }
    
    public void drawFantom(int i) {
        //variables animation
        int tailleSubImage = 16;
        int decouper_X1 = tailleSubImage * 1; 
        int decouper_Y1 = tailleSubImage * 1;        
        int decouper_X2 = tailleSubImage * 2;
        int decouper_Y2 = tailleSubImage * 2;
        
        //Time Animation Pacman
        if (compteurVitesseAnimFantom > vitesseAnimFantom) {
            animationFantom++;
            compteurVitesseAnimFantom = 0;
        }
        //System.out.println(animationFantom);
        compteurVitesseAnimFantom+=i;
              
        //Switch Case Animation pacman
        switch(animationFantom % 2) {
            case 0 :
                switch(directionFantom) {
                    case 'R' :                       
                        decouper_X1 = tailleSubImage * 0;
                        decouper_Y1 = tailleSubImage * typeFantom;
                        decouper_X2 = tailleSubImage * 1;
                        decouper_Y2 = tailleSubImage * (typeFantom + 1);
                        break;
                    case 'L' :
                        decouper_X1 = tailleSubImage * 2;
                        decouper_Y1 = tailleSubImage * typeFantom;
                        decouper_X2 = tailleSubImage * 3;
                        decouper_Y2 = tailleSubImage * (typeFantom + 1);
                        break;
                    case 'U' :
                        decouper_X1 = tailleSubImage * 4;
                        decouper_Y1 = tailleSubImage * typeFantom;
                        decouper_X2 = tailleSubImage * 5;
                        decouper_Y2 = tailleSubImage * (typeFantom + 1);
                        break;
                    case 'D' :
                        decouper_X1 = tailleSubImage * 6;
                        decouper_Y1 = tailleSubImage * typeFantom;
                        decouper_X2 = tailleSubImage * 7;
                        decouper_Y2 = tailleSubImage * (typeFantom + 1);
                        break;
                }
                break;
            case 1 :
                switch(directionFantom) {
                    case 'R' :                       
                        decouper_X1 = tailleSubImage * 1;
                        decouper_Y1 = tailleSubImage * typeFantom;
                        decouper_X2 = tailleSubImage * 2;
                        decouper_Y2 = tailleSubImage * (typeFantom + 1);
                        break;
                    case 'L' :
                        decouper_X1 = tailleSubImage * 3;
                        decouper_Y1 = tailleSubImage * typeFantom;
                        decouper_X2 = tailleSubImage * 4;
                        decouper_Y2 = tailleSubImage * (typeFantom + 1);
                        break;
                    case 'U' :
                        decouper_X1 = tailleSubImage * 5;
                        decouper_Y1 = tailleSubImage * typeFantom;
                        decouper_X2 = tailleSubImage * 6;
                        decouper_Y2 = tailleSubImage * (typeFantom + 1);
                        break;
                    case 'D' :
                        decouper_X1 = tailleSubImage * 7;
                        decouper_Y1 = tailleSubImage * typeFantom;
                        decouper_X2 = tailleSubImage * 8;
                        decouper_Y2 = tailleSubImage * (typeFantom + 1);
                        break;
                }
                break;
        }
        
        //Ancien Draw
        //imgFantom.draw(posX, posY, 32, 32);
        //Nouveau Draw
        imgFantom.draw(posX, posY, posX + 32, posY + 32, decouper_X1, decouper_Y1, decouper_X2, decouper_Y2);
        
    }
    
    public int getPosX() {
        return posX / tileSize;
    }
    
    public int getPosY() {
        return posY / tileSize;
    }
    
    public void mooveFantom(int tileID_LEFT, int tileID_RIGHT, int tileID_DOWN, int tileID_UP, int  i) throws SlickException {
        timeAnimationFantome++;            
        
        //Direction Fantom
        //System.out.println(tileID_RIGHT);
        if (timeAnimationFantome > vitesseFantome) {
            switch (directionFantom) {
            case 'R':
                if (tileID_RIGHT == 0) {
                    if (tileID_UP == 0 || tileID_DOWN == 0) {
                        directionFantom = aleaDirection();
                        while (directionFantom == 'L') {
                            directionFantom = aleaDirection();
                        }                        
                        switch(directionFantom) {
                            case 'R' :
                                if (tileID_RIGHT == 0) {                                    
                                    posX += movingStep;
                                }
                                break;
                            case 'L' :                                
                                if (tileID_LEFT == 0) {                                    
                                    posX -= movingStep;
                                }
                                break;
                            case 'U' :
                                if (tileID_UP == 0) {                                    
                                    posY -= movingStep;
                                }
                                break;
                            case 'D' :
                                if (tileID_DOWN == 0) {                                    
                                    posY += movingStep;
                                }
                                break;
                        }
                        timeAnimationFantome = vitesseFantome + 1;
                        break;
                    } else {
                        posX += movingStep;
                        break;
                    }
                } else {
                    directionFantom = aleaDirection();
                    while (directionFantom == 'L') {
                        directionFantom = aleaDirection();
                    }
                    break;
                }
            case 'L':
                if (tileID_LEFT == 0) {
                    if (tileID_UP == 0 || tileID_DOWN == 0) {
                        directionFantom = aleaDirection();
                        while (directionFantom == 'R') {
                            directionFantom = aleaDirection();
                        }
                        switch(directionFantom) {
                            case 'R' :
                                if (tileID_RIGHT == 0) {                                    
                                    posX += movingStep;
                                }
                                break;
                            case 'L' :                                
                                if (tileID_LEFT == 0) {                                    
                                    posX -= movingStep;
                                }
                                break;
                            case 'U' :
                                if (tileID_UP == 0) {                                    
                                    posY -= movingStep;
                                }
                                break;
                            case 'D' :
                                if (tileID_DOWN == 0) {                                    
                                    posY += movingStep;
                                }
                                break;
                        }
                        timeAnimationFantome = vitesseFantome + 1;
                        break;
                    } else {
                        posX -= movingStep;
                        break;
                    }
                } else {
                    directionFantom = aleaDirection();
                    while (directionFantom == 'R') {
                        directionFantom = aleaDirection();
                    }
                    break;
                }
            case 'U':
                if (tileID_UP == 0) {
                    if (tileID_RIGHT == 0 || tileID_LEFT == 0) {
                        directionFantom = aleaDirection();
                        while (directionFantom == 'D') {
                            directionFantom = aleaDirection();
                        }
                        switch(directionFantom) {
                            case 'R' :
                                if (tileID_RIGHT == 0) {                                    
                                    posX += movingStep;
                                }
                                break;
                            case 'L' :                                
                                if (tileID_LEFT == 0) {                                    
                                    posX -= movingStep;
                                }
                                break;
                            case 'U' :
                                if (tileID_UP == 0) {                                    
                                    posY -= movingStep;
                                }
                                break;
                            case 'D' :
                                if (tileID_DOWN == 0) {                                    
                                    posY += movingStep;
                                }
                                break;
                        }
                        timeAnimationFantome = vitesseFantome + 1;
                        break;
                    } else {
                        posY -= movingStep;
                        break;
                    }
                } else {
                    directionFantom = aleaDirection();
                    while (directionFantom == 'D') {
                        directionFantom = aleaDirection();
                    }
                    break;
                }
            case 'D':
                if (tileID_DOWN == 0) {
                    if (tileID_RIGHT == 0 || tileID_LEFT == 0) {
                        directionFantom = aleaDirection();
                        while (directionFantom == 'U') {
                            directionFantom = aleaDirection();
                        }
                        switch(directionFantom) {
                            case 'R' :
                                if (tileID_RIGHT == 0) {                                    
                                    posX += movingStep;
                                }
                                break;
                            case 'L' :                                
                                if (tileID_LEFT == 0) {                                    
                                    posX -= movingStep;
                                }
                                break;
                            case 'U' :
                                if (tileID_UP == 0) {                                    
                                    posY -= movingStep;
                                }
                                break;
                            case 'D' :
                                if (tileID_DOWN == 0) {                                    
                                    posY += movingStep;
                                }
                                break;
                        }
                        timeAnimationFantome = vitesseFantome + 1;
                        break;
                    } else {
                        posY += movingStep;
                        break;
                    }
                } else {
                    directionFantom = aleaDirection();
                    while (directionFantom == 'U') {
                        directionFantom = aleaDirection();
                    }
                    break;
                }         
            default:
                break;
            }
            timeAnimationFantome = 0;
        }
        //System.out.println(directionFantom);
        drawFantom(i);
        }
    
    private char aleaDirection() {
        min = 1;
        max = 5;
        int random = (int) (min + (Math.random() * (max - min)));
        
        if (random == 1) {
            directionFantom = 'U';
        } else if (random == 2) {
            directionFantom = 'D';
        } else if (random == 3) {
            directionFantom = 'L';
        } else if (random == 4) {
            directionFantom = 'R';
        }
        
        return directionFantom;
    }
    
    private int aleaNb(int min, int max) {
        this.min = min;
        this.max = max;
        int random = (int) (min + (Math.random() * (max - min)));
        
        return random;
    }
    
    public void gameOver() {
        trueTypeFont.drawString(210, 300, "Game Over", Color.red);
    }
    
}
