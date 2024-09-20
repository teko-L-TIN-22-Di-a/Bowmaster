package app.src.resources;

import java.awt.Point;
import java.awt.image.BufferedImage;

import app.src.StaticValues;
import app.src.Utilities;

/**
 * Extends the Entity class to create Arrows
 */
public class Arrow extends Entity {

    private Point mouseLocation, target;
    private int targetDistance;
    /** Indicates, if the Arrow has been shot. */
    private Boolean shot;
    private Arc arc;
    private int height;
    private int travelDistance;
    private double angle;
    
    /**
     * Takes and Image, x and y coordinates to create an Arrow object.
     * @param loadedImage previously loaded Image
     * @param x x coordinate
     * @param y y coordinate
     */
    public Arrow(BufferedImage loadedImage, int x, int y) {
        super(loadedImage, x, y, 10);
        setTAG("arrow");
        mouseLocation = new Point(0,0);
        shot = false;
        target = new Point();
        travelDistance = 0;
        height = 0;

        setDistance(StaticValues.MONSTER_SPAWN_DISTANCE);
        setSpeed(-40);
    }

    /**
     * Returns the current height of the Arrow Object.
     * @return current height of the Arrow Object
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Sets the shot attribute to true.
     */
    public void setShot(Arc arc) {
        shot = true;
        this.arc = arc;
    }

    /**
     * Returns the value of the shot attribute.
     * @return value of the shot attribute
     */
    public Boolean getShot() {
        return shot;
    }

    /**
     * Takes coordinates to set as the target values
     * @param x target x coordinate
     * @param y target y coordinate
     */
    public void setTarget(int x, int y) {
        target = new Point(x, y);
        targetDistance = Utilities.calcDistance(getPlayerLocation(), target);
    }

    /**
     * Takes a distance and returns a corresponding Point for the hitcalculation.
     * The distance will usually be the distance of the Monster, that is supposed
     * to be hit. Since the distance of the Arrow is not fixed in the hit detection,
     * the exact location can be calculated with this method.
     * @return hit calculation Point
     */
    public Point getHitPoint(int dist) {
        int x = (int) (Math.tan(angle) * dist);
        Point playerLocation = getPlayerLocation();
        return new Point(playerLocation.x + x, playerLocation.y - dist);
    }

    /**
     * When the Arrow is updated there are two possible bevaviours, depending if the Arrow 
     * was already shot or not.  
     *   
     * In the first case, the traveled distance will be updated, 
     * the image will be scaled according to the distance (not travel distance!) in relation 
     * to the distance of the Monster Spawn line. The Location height will be updated and at the end, 
     * if the travaled distance is greater than the target distance, the Arrow will be destroyed.  
     *   
     * In the second case, the angle between the Player and the mouse location will be updated
     * and the Image will be rotated according to the that angle.
     */
    @Override
    public void update() {
        if (shot) {
            updateDistance();
            travelDistance -= getSpeed();
            double factor = (double) getDistance()  / (double) StaticValues.MONSTER_SPAWN_DISTANCE;
            scaleImage(factor);
            updateLocation();
            this.height = arc.getHeightOfArcPoint(travelDistance);
            int polarDistance = Utilities.calcDistance(getPlayerLocation(), getLocation());
            if (polarDistance > targetDistance) {
                setState();
            }
        }
        else {
            angle = Utilities.calcAngle(getLocation(), mouseLocation);
            rotateImage(angle);
        }
    }

    /**
     * Updates the x and y value based on the angle to the target and the speed.  
     * Also updates the angle, to minimise rounding problems.
     */
    private void updateLocation() {
        Point playerLocation = getPlayerLocation();
        angle = Utilities.calcAngle(playerLocation, target);
        int newX = playerLocation.x + (int) (Math.abs(travelDistance) * Math.sin(angle));
        int newY = playerLocation.y - (int) (Math.abs(travelDistance) * Math.cos(angle));
        setLocation(newX, newY);
    }

    /**
     * Override to allow the inclusion of the Arrow height for the drawing position.
     * This makes the arc that the Arrow flies in visible.
     */
    @Override
    public Point getDrawPosition() {
        Point drawLocation = super.getDrawPosition();
        Point newDrawLocation = new Point(drawLocation.x, drawLocation.y - height);
        return newDrawLocation;
    }

    /**
     * Takes x and y coordinates to store as the mouse location.
     * Required to calculate the Arrows direction.
     * @param x x coordinate
     * @param y y coordinate
     */
    public void updateMouseLocation(int x, int y) {
        mouseLocation = new Point(x, y);
    }
}
