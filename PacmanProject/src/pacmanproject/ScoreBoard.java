/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanproject;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Corentin
 */
public class ScoreBoard {
    // initialise the font
    Font font = new Font("Verdana", Font.BOLD, 20);
    TrueTypeFont trueTypeFont = new TrueTypeFont(font, true);
    
    public void drawScoreBoard(int Score) {
        // render some text to the screen
        trueTypeFont.drawString(500, 5, "Score : " + Score, Color.white);
    }    
}
