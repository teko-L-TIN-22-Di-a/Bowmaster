package app2.src.resources;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Monster extends Entity {

    private int _speed;
    private Point _originalSize;
    private BufferedImage _originalImage;

    public Monster(String imageName, int x, int y, int speed) {
        super(imageName, x, y, speed);
        _originalImage = getImage();
        _speed = speed;
        _originalSize = new Point(_originalImage.getWidth(), _originalImage.getHeight());
    }

    @Override
    public void update() {
        int dist = getDistance();
        if (dist - _speed <= 0) {
            dist = 0;
        }
        else {
            updateDistance();
        }
        scale();

        double distanceScaler = 0.6;
        int movementY = (int) (
            (
                getDistance()*distanceScaler*StaticValues.CANVAS_HEIGHT
            )/(
                StaticValues.MAX_DISTANCE*StaticValues.UPDATE_PERIOD
            ));
        updatePosition(movementY);
        if (rect.position.y >= distanceScaler*StaticValues.CANVAS_HEIGHT) {
            rect.position.y = (int) (distanceScaler*StaticValues.CANVAS_HEIGHT);
        }
    }

    public void scale() {

        double factor = (double) getDistance() * ((double) 1 / (double) StaticValues.MAX_DISTANCE);
        int newWidth = (int) (_originalSize.x * factor);
        int newHeight = (int) (_originalSize.y * factor);

        BufferedImage newImage = new BufferedImage(newWidth, newHeight, _originalImage.getType());
        
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(_originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();
        
        setImage(newImage);

        rect.setSize(newWidth, newHeight);
    }

    public void updateY(int value) {
        rect.updateY(value);
    } 
}