package app.src.scenes;

import java.awt.Point;

import app.src.resources.Bow;
import app.src.resources.Monster;

/**
 * Creates the level1 screen.
 * Extends the Scene class.
 * 
 * @param _bow      Bow object to track the Playerinput
 * @see             Bow
 * @see             Scene
 */
public class Level1 extends Scene {
    private Bow _bow;
    
    /**
     * Constructor.
     * Sets up the Level1 scene.
     * 
     * @see Monster
     * @see Bow
     */
    public Level1() {
        setTAG("level1");
        Monster gobclops = new Monster("gobclops.png", 100, 1);
        registerEntity(gobclops);
        gobclops.setMainHitbox(20, 20, 0, 0);

        _bow = new Bow();
        registerEntity(_bow);
    }

    /**
     * Includes mousePosition update for the Bow object.
     * 
     * @see Scene
     */
    @Override
    public void updateMousePosition(Point mousePoint) {
        super.updateMousePosition(mousePoint);
        _bow.updateMousePosition(mousePoint);
    }
}
