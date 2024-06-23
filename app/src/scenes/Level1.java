package app.src.scenes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import app.src.StaticValues;
import app.src.resources.Arrow;
import app.src.resources.Bow;
import app.src.resources.Monster;
import app.src.resources.components.Button;

/**
 * Creates the level1 screen.
 * Extends the Scene class.
 * @see Bow
 * @see Scene
 */
public class Level1 extends Scene {
    private Bow bow;
    private Arrow nextArrow;
    private List<Arrow> arrows;
    
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
        gobclops.setMainHitbox(130, 170, 0, 0);
        gobclops.registerCritBox(45, 42, 0, -37, 2);
        gobclops.registerCritBox(80, 60, 0, 40, 2);

        Button shooter = new Button(
            StaticValues.CANVAS_WIDTH,
            StaticValues.CANVAS_HEIGHT,
            StaticValues.CANVAS_WIDTH/2,
            StaticValues.CANVAS_HEIGHT/2
        );
        shooter.setAction(() -> {shoot();});
        registerButton(shooter);

        bow = new Bow();
        registerEntity(bow);

        arrows = new ArrayList<Arrow>();

        nextArrow = new Arrow(0);
        registerEntity(nextArrow);
    }

    /**
     * Includes mousePosition update for the Bow object.
     * 
     * @see Scene
     */
    @Override
    public void updateMousePosition(Point mousePoint) {
        super.updateMousePosition(mousePoint);
        bow.updateMousePosition(mousePoint);
        for (Arrow arrow: arrows) {
            arrow.updateMousePosition(mousePoint);
        }
    }

    public void shoot() {
        nextArrow.setShot();
        arrows.add(nextArrow);
        nextArrow = null;
    }
}
