package app.src.resources;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

import app.src.resources.StaticValues.Corners;
import app.src.resources.assets.Loader;

/**
 * Creates a Entity that serves as a basis for Monsters and other actors.
 * @see BufferedImage
 * @see Rectangle
 */
public class Entity {
    private BufferedImage image;
    private Point location;
    private int health, distance, speed;
    private Hitbox mainHitbox;
    private List<Hitbox> critBoxes;
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
        image = Loader.loadImage(imageName);
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        rect = new Rectangle(imageWidth, imageHeight, x, y);
        location = new Point(rect.getCorner(Corners.TOP_LEFT));
        distance = 0;
        speed = 0;
        setHealth(health);
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
     * @param width
     * @param height
     * @param offsetX
     * @param offsetY
     * @return
     */
    public Hitbox createHitbox(int width, int height, int offsetX, int offsetY, int multiplier) {
        Hitbox hitBox = new Hitbox(width, height, offsetX, offsetY, multiplier);
        return hitBox;
    }

    /**
     * Updates the location of all registered hitboxes.
     * @param newX
     * @param newY
     */
    public void updateHitBoxes(int newX, int newY) {
        List<Hitbox> boxes = critBoxes;
        boxes.add(getMainHitbox());
        for (Hitbox box: boxes) {
            box.setLocation(newX, newY);
        }
    }

    /**
     * Returns the standard hitbox
     * @return standard hitbox
     */
    public Hitbox getMainHitbox() {
        return mainHitbox;
    }

    /**
     * Returns a list of the critical hitboxes.
     * @return critical hitboxes
     */
    public List<Hitbox> getCritBoxes() {
        return critBoxes;
    }

    /**
     * Takes x and y coordinates to set a new location for the Entity.
     * @param newX  new x coordinate for the Entity
     * @param newY  new y coordinate for the Entity
     */
    public void setLocation(int newX, int newY) {
        newX -= rect.getWidth()/2;
        newY -= rect.getHeight()/2;
        rect.setLocation(newX, newY);
        location = new Point(rect.getCorner(Corners.TOP_LEFT));
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
        return location;
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
     * @param newImage new value to be stored
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
     * @param newImage new value to be stored
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
     * @param newImage new image to be stored
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