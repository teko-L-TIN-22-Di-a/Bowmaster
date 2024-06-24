package app.src.resources;

import java.awt.Point;

import app.src.StaticValues;
import app.src.Utilities;

/**
 * Extends the Entity class to create a Bow
 * @see Entity
 */
public class Bow extends Entity {
    private Point mousePoint = new Point(0, 0);

    /**
     * Constructor. Creates a Entity object, specified as Bow
     */
    public Bow() {
        super("Bow1.png", 0, 0, 100);
        int x = StaticValues.CANVAS_WIDTH/2;
        int y = StaticValues.CANVAS_HEIGHT-200;
        rect.setLocation(x, y);
    }

    /**
     * Rotates the image based on the mouseposition.
     */
    @Override
    public void update() {
        double mouseAngle = Utilities.calcAngle(rect.getLocation(),  mousePoint);
        rotateImage(mouseAngle);
    }

    /**
     * Takes a point to store as mousePosition
     * @param newMousePoint point to be stored
     */
    public void updateMouseLocation(int x, int y) {
        mousePoint = new Point(x, y);
    }
}