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

    public void updateHitBoxes(int newX, int newY) {
        List<Hitbox> boxes = critBoxes;
        boxes.add(getMainHitbox());
        for (Hitbox box: boxes) {
            box.setLocation(newX, newY);
        }
    }

    public Hitbox getMainHitbox() {
        return mainHitbox;
    }

    public List<Hitbox> getCritBoxes() {
        return critBoxes;
    }

    public void setLocation(int newX, int newY) {
        newX -= rect.getWidth()/2;
        newY -= rect.getHeight()/2;
        rect.setLocation(newX, newY);
        location = new Point(rect.getCorner(Corners.TOP_LEFT));
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage newImage) {
        image = newImage;
    }

    public Point getLocation() {
        return location;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int value) {
        distance = value;
    }

    public void updateDistance() {
        int newDistance = getDistance() + getSpeed();
        setDistance(newDistance);
    }

    public void setHealth(int value) {
        health = value;
    }

    public void death() {
        // determine what happens for entity death
    }

    public void updateHealth(int value) {
        health += value;
        if (health <= 0) {
            death();
        }
    }
}