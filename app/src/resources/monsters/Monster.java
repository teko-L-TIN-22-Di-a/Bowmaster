package app.src.resources.monsters;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.sound.sampled.Clip;

import app.src.StaticValues;
import app.src.Utilities;
import app.src.resources.Entity;

/**
 * Creates and handles Monster objects.
 * Extends the Entity class.
 * @see Entity
 */
public class Monster extends Entity {

    private String TYPE;

    /**
     * Creates a Monster object.
     * @param loadedImage previously loaded Image for the Monster
     * @param health hitpoints the Monster can take before dying
     * @param speed speed of the Monster
     * @param type TYPE of the Monster
     * @param noise previously loaded audio Clip for the Monster
     */
    public Monster(BufferedImage loadedImage, int health, int speed, String type, Clip noise) {
        super(loadedImage, 0, 0, health);
        setTAG("monster");
        setHealthbar();
        setSpeed(speed);
        setNoise(noise);
        TYPE = type;
    }

    /**
     * Returns the TYPE of the Monster.
     * @return TYPE
     */
    public String getTYPE() {
        return TYPE;
    }

    /**
     * Sets the x coordinate to a specified value.
     * @param newX new value for location.x
     */
    public void setX(int newX) {
        Point location = getLocation();
        setLocation(newX, location.y);
    }

    /**
     * Updates the cooldown for the noise.
     * If the Cooldown is 0, the noise is played
     * and a new, random cooldown is set.
     */
    public void updateNoise() {
        if (getCooldown() == 0) {
            makeNoise();
            int newCooldown = new Random().nextInt(120, 240);
            setCooldown(newCooldown);
        }
        else {
            decreaseCooldown();
        }
    }

    /**
     * Updates and scales the position and Hitboxes of the Monster object based on the Distance value.
     */
    @Override
    public void update() {
        super.update();
        if (getNoise() != null) {
            updateNoise();
        }
        if (getState()) {
            int spawnDist = StaticValues.MONSTER_SPAWN_DISTANCE;
            int dist = getDistance();
            if (dist + getSpeed() >= spawnDist) {
                dist = spawnDist;
            }
            else {
                updateDistance();
            }

            dist = getDistance();
            double factor = (double) dist / (double) spawnDist;

            scaleImage(factor);
            scaleHitBoxes(factor);

            Point location = getLocation();
            Point playerLocation = getPlayerLocation();

            double angle = Utilities.calcAngle(playerLocation, location);
            int distRemaining = spawnDist - dist;

            int newX = playerLocation.x + (int) (distRemaining * Math.sin(angle));
            int newY = playerLocation.y - (int) (distRemaining * Math.cos(angle));
            setLocation(newX, newY);
            updateHitBoxes(newX, newY);
        }
    }
}