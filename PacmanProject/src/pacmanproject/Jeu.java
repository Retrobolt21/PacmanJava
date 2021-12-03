/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacmanproject;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;
/**
 *
 * @author ETI
 */
public class Jeu extends BasicGame {
    private TiledMap map;
    
    public Jeu(String titre) {
        super(titre);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        map = new TiledMap("./maps/map_pacman_final.tmx");
        //map = new TiledMap("./maps/maPremiereCarte.tmx");
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        map.render(0,0);
    }
    
}
