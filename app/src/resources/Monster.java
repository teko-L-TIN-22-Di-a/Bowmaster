package app.src.resources;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import app.src.StaticValues;

/**
 * Creates and handles Monster objects.
 * Extends the Entity class.
 * 
 * @param   _originalSize   stores the original imagesize as reference for scaling
 * @param   _originalImage  stores the original image as reference for scaling
 * @see     Entity
 */
public class Monster extends Entity {

    private Point _originalSize;
    private BufferedImage _originalImage;

    /**
     * Constructor. Creates a Monster object.
     * 
     * @param imageName image for the Monster in app/src/resources/assets
     * @param health    hitpoints the Monster can take before dying
     * @param speed     speed of the Monster
     */
    public Monster(String imageName, int health, int speed) {
        super(imageName, StaticValues.CANVAS_WIDTH/2, StaticValues.SpawnY, health);
        setSpeed(speed);
        _originalImage = getImage();
        _originalSize = new Point(_originalImage.getWidth(), _originalImage.getHeight());
    }

    /**
     * Updates and scales the position of the Monster object based on the Distance value.
     */
    @Override
    public void update() {
        int dist = getDistance();
        if (dist + getSpeed() >= StaticValues.MAX_DISTANCE) {
            dist = StaticValues.MAX_DISTANCE;
        }
        else {
            updateDistance();
        }
        double factor = scale();

        int newY = StaticValues.SpawnY + (int) (StaticValues.TRAVEL_DISTANCE_Y*factor);
        Point pos = rect.getLocation();
        pos.y = newY;
        pos.x += rect.getWidth()/2; // horizontal correction based on scaling
        setLocation(pos.x, pos.y);
        updateHitBoxes(pos.x, pos.y);
    }

    /**
     * Scales the monster and its component based on the Distance value and the original values.
     * Returns the scaling factor.
     * 
     * @return scaling factor 
     */
    public double scale() {

        double factor = (double) getDistance()  / (double) StaticValues.MAX_DISTANCE;
        int newWidth = (int) (_originalSize.x * factor);
        int newHeight = (int) (_originalSize.y * factor);
        if (newWidth < 1) {newWidth = 1;}
        if (newHeight < 1) {newHeight = 1;}

        BufferedImage newImage = new BufferedImage(newWidth, newHeight, _originalImage.getType());
        
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(_originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();
        
        setImage(newImage);

        rect.setSize(newWidth, newHeight);

        return factor;
    }
}