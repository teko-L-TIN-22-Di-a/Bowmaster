package app.src.resources;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Clip;

import app.src.Utilities;
import app.src.StaticValues.Corners;
import app.src.resources.components.Hitbox;
import app.src.resources.components.Rectangle;

/**
 * Creates an Entity that serves as a base for Monsters and other actors.
 * @see BufferedImage
 * @see Rectangle
 */
public class Entity {
    private BufferedImage image, originalImage;
    private int maxHealth, health, distance, speed, cooldown;
    private List<Hitbox> hitBoxes;
    private Boolean state, healthbar, charging;
    private String TAG;
    private Clip noise;
    private Point playerLocation;
    private Rectangle rect;
    

    /**
     * Takes an image name, coordinates and health value to create an Entity.
     * Loads the image from app/src/resources/assets.
     * Takes width and Height from the image to create a Rectangle
     * @param loadedImage previously loaded Image
     * @param x x coodinate for the location
     * @param y y coodinate for the location
     * @param health determines how much damage an entity can take before death
     */
    public Entity(BufferedImage loadedImage, int x, int y, int health) {
        hitBoxes = new ArrayList<Hitbox>();
        originalImage = loadedImage;
        image = loadedImage;
        int imageWidth = 1;
        int imageHeight = 1;
        if (image != null) {
            imageWidth = image.getWidth();
            imageHeight = image.getHeight();
        }
        rect = new Rectangle(imageWidth, imageHeight, x, y);
        distance = 0;
        speed = 0;
        cooldown = 0;
        state = true;
        healthbar = false;
        maxHealth = health;
        playerLocation = new Point(0,0);
        charging = false;
        setHealth(health);
    }

    /**
     * Takes a Clip object to set as the noise of the Monster.
     * @param noise noise that the Monster will make
     */
    public void setNoise(Clip noise) {
        this.noise = noise;
    }

    /**
     * Retunrs the Clip object stored in the noise value or null.
     * @return Clip object or null
     */
    public Clip getNoise() {
        return noise;
    }

    /**
     * Plays the audio Clip stored in the noise variable.
     */
    public void makeNoise() {
        noise.stop();
        noise.setFramePosition(0);
        noise.start();
    }

    /**
     * Sets the state of the charging variable to either true of false.
     * @param state indicates, if the charge variable should be increased or not
     */
    public void setCharging(boolean state) {
        charging = state;
    }

    /**
     * Indicates, if the charge variable should be increased or not.
     * @return true, if the charge variable is currently beeing increased
     */
    public boolean getCharging() {
        return charging;
    }

    /**
     * Sets the Healthbar variable to true and inidcates
     * that a healthbar should be drawn for the Entity.
     */
    public void setHealthbar() {
        healthbar = true;
    }

    /**
     * Takes x and y coordinates to store in the playerLocation Point.
     * @param playerX new x coordinate
     * @param playerY new y coordinate
     */
    public void setPlayerLocation(int playerX, int playerY) {
        playerLocation = new Point(playerX, playerY);
    }

    /**
     * Returns the playerLocation variable.
     * @return playerLocation
     */
    public Point getPlayerLocation() {
        return playerLocation;
    }

    /**
     * Sets the state of the Entity to false.
     * Entities with state false will be removed.
     */
    public void setState() {
        state = false;
    }

    /**
     * Returns the state of an Entity.
     * @return state of Entity
     */
    public Boolean getState() {
        return state;
    }

    /**
     * Takes a String to set as a TAG.
     * TAGs are used as a identifier.
     * @param newTAG TAG to be set
     */
    public void setTAG(String newTAG) {
        TAG = newTAG;
    }

    /**
     * Returns the TAG of an Entity.
     * @return TAG of Entity
     */
    public String getTAG() {
        return TAG;
    }

    /**
     * Takes an int Value and stores it in the cooldwon variable.
     * @param newCooldown Value for the new cooldown
     */
    public void setCooldown(int newCooldown) {
        cooldown = newCooldown;
    }

    /**
     * Returns the cooldown of an Entity.
     * @return cooldown of Entity
     */
    public int getCooldown() {
        return cooldown;
    }

    /**
     * Decreases the Entity cooldown by 1.
     */
    public void decreaseCooldown() {
        --cooldown;
    }

    /**
     * Base Method for extended classes.
     * Reduces the cooldown, if it is set.
     * Initiates death when Entity health is below zero.
     */
    public void update() {
        if (cooldown > 0) {cooldown -= 1;}
        if (health < 0) {death();}
    }

    /**
     * Takes an angle and rotates the Image of the Entity.
     * The original Image is used as base for the rotation.
     * @param angle rotation angle
     */
    public void rotateImage(double angle) {
        int oldHeight = image.getHeight();
        BufferedImage rotatedImage = Utilities.rotateImage(originalImage, angle);
        setImage(rotatedImage);
        int newHeight = rotatedImage.getHeight();
        rect.setSize(rotatedImage.getWidth(), newHeight);
        int newY = getLocation().y + (newHeight-oldHeight)/2;
        setLocation(rect.getX(), newY);
    }

    /**
     * Takes a factor and scales the Image of the Entity.
     * The original Image is used as base for the scaling.
     * If the healthbar option is set, a healthbar will be drawn.
     * @param factor scaling factor
     */
    public void scaleImage(double factor) {
        BufferedImage newImage = new BufferedImage(
                originalImage.getWidth(),
                originalImage.getHeight(),
                originalImage.getType()
            );
        if (healthbar) {
            Graphics2D g = newImage.createGraphics();
            g.drawImage(originalImage, null, 0, 0);
            int imgWidth = originalImage.getWidth();
            int hbWidth = (int) (imgWidth * health / maxHealth);
            g.setColor(Color.RED);
            g.fillRect((imgWidth - hbWidth) /2, 0, hbWidth, 16);
            newImage = Utilities.scaleImage(newImage, factor);
        }
        else {
            newImage = Utilities.scaleImage(originalImage, factor);
        }
        setImage(newImage);

        int newWidth = newImage.getWidth();
        int newHeight = newImage.getHeight();
        rect.setSize(newWidth, newHeight);
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
        rect.setSize(image.getWidth(), image.getHeight());
    }

    /**
     * Takes a BufferedImage and stores it as originalImage.
     * @param newImage Image to be stored as originalImage
     */
    public void setOriginalImage(BufferedImage newImage) {
        originalImage = newImage;
    }

    /**
     * Takes a point and checks, if it collides with any of the Entities Hitbox.
     * Returns the highest multiplier value of collided Hitboxes.
     * @param point Point for collision detection
     * @return multiplier value
     */
    public int getMultiplier(Point point) {
        int multiplier = 0;
        for (Hitbox hitBox: hitBoxes) {
            Boolean boxHit = hitBox.collidePoint(point);
            if (boxHit) {
                int boxMultiplier = hitBox.getDamageMutiplier();
                if (boxMultiplier > multiplier) {
                    multiplier = boxMultiplier;
                }
            }
        }
        return multiplier;
    }

    /**
     * Creates a Hitbox with size, offsets and damage multiplier.
     * @param width         width of the Hitbox
     * @param height        height of the Hitbox
     * @param offsetX       x offset to Entity location
     * @param offsetY       y offset to Entity location
     * @param multiplier    multiplier for received damage of this Hitbox
     */
    public void addHitBox(int width, int height, int offsetX, int offsetY, int multiplier) {
        Hitbox hitBox = new Hitbox(width, height, offsetX, offsetY, multiplier);
        hitBoxes.add(hitBox);
    }

    /**
     * Takes new coordinates and updates the location of all registered hitboxes.
     * @param newX  new x corrdinate
     * @param newY  new y corrdinate
     */
    public void updateHitBoxes(int newX, int newY) {
        List<Hitbox> boxes = getHitBoxes();
        for (Hitbox box: boxes) {
            box.setLocation(newX, newY);
        }
    }

    /**
     * Takes a factor value to scale all Hitboxes.
     * @param factor scaling factor
     */
    public void scaleHitBoxes(double factor) {
        List<Hitbox> boxes = getHitBoxes();
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
     * Returns a list of all Hitboxes.
     * @return all Hitboxes
     */
    public List<Hitbox> getHitBoxes() {
        return hitBoxes;
    }

    public Point getSize() {
        return rect.getSize();
    }

    public Point getCorner(Corners corner) {
        return rect.getCorner(corner);
    }

    public Rectangle getRect() {
        return rect;
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
     * Returns the health value of the Entity.
     * @return health of Entity
     */
    public int getHealth() {
        return health;
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

    /**
     * Determines what happens when the Entity dies.
     */
    public void death() {
        setState();
        noise.stop();
        noise.setFramePosition(cooldown);
    }
}