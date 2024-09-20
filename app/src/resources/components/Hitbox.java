package app.src.resources.components;

import java.awt.Point;

/**
 * Extends the Rectangle class to add offsets for an entity location
 * and a damage multiplier to create different hitbox types like cirtical hitbox.
 */
public class Hitbox extends Rectangle {
    private int damageMultiplier;
    private Point offset, originalOffsets, originalSize;
    
    /**
     * Creates a Rectangle and adds offsets for x and y and a damage multiplier. 
     * @param width                 width of the hitbox
     * @param height                height of the hitbox
     * @param offsetX               offset value for x
     * @param offsetY               offset value for y
     * @param newDamageMultiplier   allows differentiation between standard and cirtical hitboxes
     */
    public Hitbox(int width, int height, int offsetX, int offsetY, int newDamageMultiplier) {
        super(width, height, offsetX, offsetY);
        offset = new Point(offsetX, offsetY);
        originalOffsets = new Point(offsetX, offsetY);
        originalSize = new Point(width, height);
        damageMultiplier = newDamageMultiplier;
    }

    /**
     * Takes a new location and adds the offsets to it to store the result in the location variable.
     * @param newX  new x location
     * @param newY  new y location
     */
    @Override
    public void setLocation(int newX, int newY) {
        super.setLocation(newX + offset.x, newY + offset.y);
    }

    /**
     * Returns the x offset value
     * @return x offset value
     */
    public int getOffsetX() {
        return offset.x;
    }

    /**
     * Returns the y offset value
     * @return y offset value
     */
    public int getOffsetY() {
        return offset.y;
    }

    /**
     * Returns the original offset values as Point
     * @return original offset values as Point
     */
    public Point getOriginalOffsets() {
        return originalOffsets;
    }

    /**
     * Takes new x and y Values for the offsets to store in the offset variable.
     * @param newX  new x location
     * @param newY  new y location
     */
    public void setOffsets(int newX, int newY) {
        offset.x = newX;
        offset.y = newY;
    }

    /**
     * Returns the original size values as Point
     * @return original offset size as Point
     */
    public Point getOriginalSize() {
        return originalSize;
    }

    /**
     * Returns the damage multiplier value
     * @return damage multiplier value
     */
    public int getDamageMutiplier() {
        return damageMultiplier;
    }
}
