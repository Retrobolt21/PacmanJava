/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanproject;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
/**
 *
 * @author Corentin
 */
public class PacmanProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println("Test github !");
        //System.out.println("Test 2 github");
        //System.out.println("Test Moi github");
        
        int largeurAffichage = 672;
        int hauteurAffichage = 768;
        //int largeurAffichage = 1400;
        //int hauteurAffichage = 800;
        boolean siPleinEcran = false;
        
        try {
            AppGameContainer app = new AppGameContainer( new Jeu("Mon gentil premier jeu") );
            app.setDisplayMode(largeurAffichage, hauteurAffichage, siPleinEcran);
            app.start();
        } catch (SlickException ex) {
            //System.out.println(ex);
        }
    }
    
}
