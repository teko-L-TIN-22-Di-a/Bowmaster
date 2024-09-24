package app.src.resources;

import java.awt.Point;
import java.awt.image.BufferedImage;

import app.src.StaticValues;
import app.src.Utilities;
import app.src.resources.components.Component;

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
    private Component shadow;
    
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
        setSpeed(-30);
    }

    /**
     * Returns the current height of the Arrow Object.
     * @return current height of the Arrow Object
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Takes an Arc and a target to store. The shot attribute is set to true, 
     * to indicate, that the Arrow has been shot. The image is stored as the original 
     * Image to keep a reference for scaling. At the end, a shadow is created, to make the 
     * actual location of the Arrow visible, since the Arrow will be displayed with an 
     * offset based on the height, to show the trajectory of the Arrow.
     * @param arc
     * @param target
     */
    public void setShot(Arc arc, Point target) {
        shot = true;
        this.arc = arc;
        this.setOriginalImage(this.getImage());
        this.setTarget(target.x, target.y);
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
     * Takes a y coordinate and returns a corresponding Point for the hitcalculation.
     * The y coordinate will usually be the y location of the Monster, that is supposed
     * to be hit. Since the location of the Arrow is not fixed in the hit detection,
     * the exact location can be calculated with this method.
     * @param y y cooridnate to calculate the exact Arrow position from
     * @return hit calculation Point
     */
    public Point getHitPoint(int y) {
        Point playerLocation = getPlayerLocation();
        int dist = playerLocation.y - y;
        int x = (int) (Math.tan(angle) * dist);
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
            updateShadow(factor);
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

    @Override
    public void setState() {
        super.setState();
        shadow.setState();
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
        Point newDrawLocation = new Point(drawLocation.x, drawLocation.y - height/2);
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

    /**
     * Creates a shadow Component for the flying Arrow, to make the actual Location
     * of the Arrow visible. The direction of the shadow is based on the current angle.
     */
    public void createShadow(BufferedImage image) {        
        BufferedImage rotatedImage = Utilities.rotateImage(image, angle);

        Point location = this.getLocation();
        shadow = new Component(rotatedImage, location.x, location.y);

        shadow.setTAG("shadow");
    }

    /**
     * Takes a factor to scale the image of the shadow and 
     * updates the location of the Arrow to the shadow.
     * @param factor
     */
    private void updateShadow(double factor) {
        shadow.scaleImage(factor);
        Point location = getLocation();
        shadow.setLocation(location.x, location.y);
    }

    /**
     * Returns the shadow Component of the Arrow.  
     * Mainly used to register the component in the
     * Scene for displaying purposes.
     * @return
     */
    public Component getShadow() {
        return shadow;
    }
}
