package app.src.resources;

import java.awt.Point;
import java.awt.image.BufferedImage;

import app.src.Utilities;

/**
 * Extends the Entity class to create a Bow
 * @see Entity
 */
public class Bow extends Entity {
    private Point mousePoint = new Point(0, 0);
    private BufferedImage originalImage;

    /**
     * Constructor. Creates a Entity object, specified as Bow
     */
    public Bow() {
        super("Bow1.png", 0, 0, 100);
        int x = StaticValues.CANVAS_WIDTH/2;
        int y = StaticValues.CANVAS_HEIGHT-200;
        rect.setLocation(x, y);
        originalImage = getImage();
    }

    /**
     * Rotates the image based on the mouseposition.
     */
    @Override
    public void update() {
        double mouseAngle = Utilities.calcAngle(rect.getLocation(),  mousePoint);
        BufferedImage rotatedImage = Utilities.rotate(originalImage, mouseAngle);
        setImage(rotatedImage);
        rect.setSize(rotatedImage.getWidth(), rotatedImage.getHeight());
        setLocation(rect.getX(), rect.getY());
    }

    /**
     * Takes a point to store as mousePosition
     * @param newMousePoint point to be stored
     */
    public void updateMousePosition(Point newMousePoint) {
         mousePoint = newMousePoint;
    }
}