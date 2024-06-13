package app.src.resources.components;

import java.awt.Point;

/**
 * Extends the Rectangle class to add offsets for an entity location
 * and a damage multiplier to create different hitbox types like cirtical hitbox.
 */
public class Hitbox extends Rectangle {
    private int damageMultiplier;
    private Point offset;
    
    /**
     * Constructor. Creates a Rectangle and adds offsets for x and y and a damage multiplier. 
     * @param width             width of the hitbox
     * @param height            height of the hitbox
     * @param offset            stores the offset values for x and y
     * @param damageMultiplier  allows differentiation between standard and cirtical hitboxes
     */
    public Hitbox(int width, int height, int offsetX, int offsetY, int newDamageMultiplier) {
        super(width, height, offsetX, offsetY);
        offset = new Point(offsetX, offsetY);
        damageMultiplier = newDamageMultiplier;
    }

    /**
     * Takes a new location and adds the offsets to it to store the reult in the location variable.
     * @param newLocation   new location point
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
     * Returns the damage multiplier value
     * @return damage multiplier value
     */
    public int getDamageMutiplier() {
        return damageMultiplier;
    }
}
