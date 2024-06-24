package app.src.resources;

import java.awt.Point;

import app.src.StaticValues;
import app.src.Utilities;

public class Arrow extends Entity {

    private Point mouseLocation, playerLocation;
    private Boolean shot;
    private Boolean hit;
    private double direction;
    
    public Arrow(int angle, int x, int y) {
        super("arrow.png", x, y, 10);
        mouseLocation = new Point(0,0);
        playerLocation = new Point(0,0);
        shot = false;
        hit = false;
        direction = angle;

        setLocation(rect.getX(), rect.getY());
        setDistance(StaticValues.MAX_DISTANCE);
        setSpeed(-20);
    }

    public void setShot() {
        shot = true;
    }

    public Boolean getShot() {
        return shot;
    }

    public void setHit() {
        hit = true;
    }

    public Boolean getHit() {
        return hit;
    }

    @Override
    public void update() {
        if (shot) {
            int dist = getDistance();
            if (dist + getSpeed() >= StaticValues.MAX_DISTANCE) {
                dist = StaticValues.MAX_DISTANCE;
            }
            else {
                updateDistance();
            }
            
            double factor = (double) getDistance()  / (double) StaticValues.MAX_DISTANCE;
            scaleImage(factor);
            
            int newY = StaticValues.SpawnY + (int) (StaticValues.TRAVEL_DISTANCE_Y*factor);
            Point pos = rect.getLocation();
            setLocation(pos.x, newY);
        }
        else {
            direction = Utilities.calcAngle(playerLocation, mouseLocation);
            rotateImage(direction);
        }
        
        if (hit = true) {
            setState();
        }
    }

    public void updateMouseLocation(int x, int y) {
        mouseLocation = new Point(x, y);
    }

    public void updatePlayerLocation(int x, int y) {
        playerLocation = new Point(x, y);
    }
}
