package app.src.resources;

import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

import app.src.StaticValues;
import app.src.Utilities;
import app.src.resources.assets.Loader;
import app.src.resources.assets.sounds.SoundMapping;

/**
 * Extends the Entity class to create a Bow.
 * @see Entity
 */
public class Bow extends Entity {
    private Point mousePoint = new Point(0, 0);
    private int charge, overcharge;
    private int chargeLimit = StaticValues.CHARGELIMIT;
    private int overchargeLimit = StaticValues.OVERCHARGELIMIT;
    private int chargeCooldownLimit = StaticValues.CHARGECOOLDOWN;
    private Clip drawNoise;
    private Arc arc;

    /**
     * Takes an Image and creates a Entity object, specified as Bow.
     * @param loadedImage previously loaded Image
     */
    public Bow(BufferedImage loadedImage) {
        super(loadedImage, 0, 0, 100);
        int x = StaticValues.PLAYERSPAWNX;
        int y = StaticValues.PLAYERSPAWNY;
        setLocation(x, y);
        drawNoise = Loader.loadSound(SoundMapping.DRAW);
        arc = new Arc(500, 500, StaticValues.RANGE, StaticValues.HEIGHT);
    }

    /**
     * Rotates the image based on the mouseposition.
     */
    @Override
    public void update() {
        super.update();
        updateCharge();
        updateArc();
        double mouseAngle = Utilities.calcAngle(getLocation(),  mousePoint);
        rotateImage(mouseAngle);
    }

    /**
     * Takes x and y cooridinates to store as mousePosition
     * @param x x coordnate
     * @param y y coordnate
     */
    public void updateMouseLocation(int x, int y) {
        mousePoint = new Point(x, y);
    }
    
    /**
     * Increased the charged variable while charging is true.
     */
    private void updateCharge() {
        int cooldown = getCooldown();
        if (cooldown == 0) {
            if (getCharging()) {
                if (overcharge >= overchargeLimit) {
                    resetCharge();
                    setCooldown(chargeCooldownLimit);
                }
                ++charge;
                if (charge == 1) {
                    drawNoise.start();
                }
                if (charge >= chargeLimit) {
                    charge = chargeLimit;
                    ++overcharge;
                }
            }
            else {
                resetCharge();
                cooldown = 0;
            }
        }
        else {
            --cooldown;
        }
    }

    /**
     * Updates the arc an Arrow will have according to the current charge value.
     */
    public void updateArc() {
        int range = StaticValues.RANGE + StaticValues.DISTCHARGEFACTOR * charge;
        int height = StaticValues.HEIGHT + StaticValues.HEIGHTCHARGEFACTOR * charge;
        arc.setSize(range, height);
        arc.update();
    }

    /**
     * Returns the Arc an Arrow will have.
     * @return Arc an Arrow will have
     */
    public Arc getArc() {
        return arc;
    }

    /**
     * Returns the width and height of the Arc Object.
     * @return width and height of the Arc Object
     */
    public Point getArcSize() {
        Point arcSize = new Point(arc.getWidth(), arc.getHeight());
        return arcSize;
    }

    /**
     * Sets the value of the charge varible to 0 and stops the noise.
     */
    public void resetCharge() {
        charge = 0;
        drawNoise.stop();
        drawNoise.setFramePosition(0);
    }

    /**
     * Returns the value of the charge variable.
     * @return value of the charge variable
     */
    public int getCharge() {
        return charge;
    }

    /**
     * Returns the value of the overcharge variable.
     * @return value of the overcharge variable
     */
    public int getOvercharge() {
        return overcharge;
    }

    /**
     * Sets the value of the overcharge varible to 0.
     */
    public void resetOvercharge() {
        overcharge = 0;
    }
}