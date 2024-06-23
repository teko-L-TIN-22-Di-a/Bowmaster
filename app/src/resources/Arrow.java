package app.src.resources;

import java.awt.Point;
import java.awt.image.BufferedImage;

import app.src.StaticValues;
import app.src.Utilities;

public class Arrow extends Entity{

    private Point mousePoint;
    private BufferedImage originalImage;
    private Boolean shot;
    private Boolean hit;
    
    public Arrow(int angle) {
        super("arrow.png", StaticValues.CANVAS_WIDTH/2, StaticValues.CANVAS_HEIGHT-200, 10);

        Point mousePoint = new Point(0, 0);
        originalImage = getImage();
        double mouseAngle = Utilities.calcAngle(rect.getLocation(),  mousePoint);
        BufferedImage rotatedImage = Utilities.rotate(originalImage, mouseAngle);
        shot = false;
        hit = false;
        setImage(rotatedImage);

        rect.setSize(rotatedImage.getWidth(), rotatedImage.getHeight());
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
        }
        double factor = (double) getDistance()  / (double) StaticValues.MAX_DISTANCE;

        BufferedImage newImage = Utilities.scaleImage(originalImage, factor);
        setImage(newImage);
        int newWidth = newImage.getWidth();
        int newHeight = newImage.getHeight();

        rect.setSize(newWidth, newHeight);
        int newY = StaticValues.SpawnY + (int) (StaticValues.TRAVEL_DISTANCE_Y*factor); // not done!!
        Point pos = rect.getLocation();
        pos.y = newY;
        setLocation(pos.x, pos.y);
        
        if (hit = true) {
            setState();
        }
    }

    public void updateMousePosition(Point newMousePoint) {
        mousePoint = newMousePoint;
    }
}
