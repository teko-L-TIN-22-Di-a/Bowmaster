package app.src.scenes;

import java.awt.Point;

import app.src.StaticValues;
import app.src.resources.Bow;
import app.src.resources.Monster;

/**
 * Creates the level1 screen.
 * Extends the Scene class.
 * @see Bow
 * @see Scene
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
        gobclops.setDistance(StaticValues.MAX_DISTANCE);
        gobclops.setMainHitbox(130, 170, 0, 0);
        gobclops.registerCritBox(45, 42, 0, -37, 2);
        gobclops.registerCritBox(80, 60, 0, 40, 2);

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
