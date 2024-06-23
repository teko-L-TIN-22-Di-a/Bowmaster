package app.src.resources;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import app.src.StaticValues;
import app.src.Utilities;
import app.src.StaticValues.Corners;
import app.src.resources.assets.Loader;
import app.src.resources.components.Hitbox;
import app.src.resources.components.Rectangle;

/**
 * Creates a Entity that serves as a basis for Monsters and other actors.
 * @see BufferedImage
 * @see Rectangle
 */
public class Entity {
    private BufferedImage image;
    private int health, distance, speed;
    private Hitbox mainHitbox;
    private List<Hitbox> critBoxes;
    private Boolean state;
    /** Rectangle to track size and location of the Entity */
    public Rectangle rect;

    /**
     * Constructor. Takes an iamge name, coordinates and health value to create an Entity.
     * Loads the image from app/src/resources/assets.
     * Takes width and Height from the image to create a Rectangle
     * @param imageName name of the image in app/src/resources/assets
     * @param x         x coodinate for the location
     * @param y         y coodinate for the location
     * @param health    determines how much damage an entity can take before death
     */
    public Entity(String imageName, int x, int y, int health) {
        critBoxes = new ArrayList<Hitbox>();
        image = Loader.loadImage(imageName);
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        rect = new Rectangle(imageWidth, imageHeight, x, y);
        distance = 0;
        speed = 0;
        setHealth(health);
    }

    public void setState() {
        state = false;
    }

    public Boolean getState() {
        return state;
    }

    /**
     * Base Method for extended classes.
     */
    public void update() {
        // to overide per entity
    }

    /**
     * Creates a Hitbox for basic damage.
     * @param width     width of the Hitbox
     * @param height    height of the Hitbox
     * @param offsetX   x offset to Entity location
     * @param offsetY   y offset to Entity location
     */
    public void setMainHitbox(int width, int height, int offsetX, int offsetY) {
        mainHitbox = createHitbox(width, height, offsetX, offsetY, StaticValues.BASEDAMAGE);
    }

    /**
     * Creates a Hitbox for adjusted damage and adds it to the critBoxes list.
     * @param width         width of the Hitbox
     * @param height        height of the Hitbox
     * @param offsetX       x offset to Entity location
     * @param offsetY       y offset to Entity location
     * @param multiplier    damage multiplier
     */
    public void registerCritBox(int width, int height, int offsetX, int offsetY, int multiplier) {
        critBoxes.add(createHitbox(width, height, offsetX, offsetY, multiplier));
    }

    /**
     * Creates a Hitbox with size, offsets and damage multiplier.
     * @param width         width of the Hitbox
     * @param height        height of the Hitbox
     * @param offsetX       x offset to Entity location
     * @param offsetY       y offset to Entity location
     * @param multiplier    multiplier for received damage of this Hitbox
     * @return              the created Hitbox object
     */
    public Hitbox createHitbox(int width, int height, int offsetX, int offsetY, int multiplier) {
        Hitbox hitBox = new Hitbox(width, height, offsetX, offsetY, multiplier);
        return hitBox;
    }

    /**
     * Takes new coordinates and updates the location of all registered hitboxes.
     * @param newX  new x corrdinate
     * @param newY  new y corrdinate
     */
    public void updateHitBoxes(int newX, int newY) {
        List<Hitbox> boxes = getAllHitboxs();
        for (Hitbox box: boxes) {
            box.setLocation(newX, newY);
        }
    }

    /**
     * Takes a factor value to scale all Hitboxes.
     * @param factor scaling factor
     */
    public void scaleHitBoxes(double factor) {
        List<Hitbox> boxes = getAllHitboxs();
        for (Hitbox box: boxes) {
            Point originalSize = box.getOriginalSize();
            Point newSize = Utilities.scaleSize(originalSize.x, originalSize.y, factor);
            box.setSize(newSize.x, newSize.y);
            
            Point originalOffsets = box.getOriginalOffsets();
            Point newOffsets = Utilities.scaleSize(originalOffsets.x, originalOffsets.y, factor);
            box.setOffsets(newOffsets.x, newOffsets.y);
        }
    }

    /**
     * Returns the standard Hitbox.
     * @return standard Hitbox
     */
    public Hitbox getMainHitbox() {
        return mainHitbox;
    }

    /**
     * Returns a list of the critical Hitboxes.
     * @return critical Hitboxes
     */
    public List<Hitbox> getCritBoxes() {
        return critBoxes;
    }

    /**
     * Returns a list of all Hitboxes.
     * @return all Hitboxes
     */
    public List<Hitbox> getAllHitboxs() {
        List<Hitbox> boxes = new ArrayList<Hitbox>();
        for (Hitbox hb: critBoxes) {
            boxes.add(hb);
        }
        boxes.add(getMainHitbox());
        return boxes;
    }

    /**
     * Takes x and y coordinates to set a new location for the Entity.
     * @param newX  new x coordinate for the Entity
     * @param newY  new y coordinate for the Entity
     */
    public void setLocation(int newX, int newY) {
        rect.setLocation(newX, newY);
    }

    /**
     * Returns the Entity's image.
     * @return Entity's image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Takes an image and stores it.
     * @param newImage new image to be stored
     */
    public void setImage(BufferedImage newImage) {
        image = newImage;
    }

    /**
     * Returns the Entity's location.
     * @return Entity's location
     */
    public Point getLocation() {
        return rect.getLocation();
    }

    /**
     * Returns the Entity's drawing location (top left corner).
     * @return Entity's drawing location
     */
    public Point getDrawPosition() {
        return rect.getCorner(Corners.TOP_LEFT);
    }

    /**
     * Returns the Entity's speed.
     * @return Entity's speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Takes a int value and stores it in speed.
     * @param newSpeed new value to be stored
     */
    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

    /**
     * Returns the Entity's distance.
     * @return Entity's distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Takes a int value and stores it in distance.
     * @param value new value to be stored
     */
    public void setDistance(int value) {
        distance = value;
    }

    /**
     * Gets distance and speed of the Entity to calculate the new distance.
     */
    public void updateDistance() {
        int newDistance = getDistance() + getSpeed();
        setDistance(newDistance);
    }

    /**
     * Takes an image and stores it.
     * @param value new image to be stored
     */
    public void setHealth(int value) {
        health = value;
    }

    /**
     * Determines what happens when the Entity dies.
     */
    public void death() {
        // determine what happens for Entity death
    }

    /**
     * Takes a int value and adds it to the Entity's health.
     * @param value will be added to Entity health
     */
    public void updateHealth(int value) {
        health += value;
        if (health <= 0) {
            death();
        }
    }
}