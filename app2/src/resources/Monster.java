package app2.src.resources;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Monster extends Entity {

    private int _speed;
    private Point _originalSize;
    private BufferedImage _originalImage;

    public Monster(String imageName, int speed) {
        super(imageName, StaticValues.CANVAS_WIDTH/2, StaticValues.SpawnY, speed);
        _originalImage = getImage();
        _speed = speed;
        _originalSize = new Point(_originalImage.getWidth(), _originalImage.getHeight());
    }

    @Override
    public void update() {
        int dist = getDistance();
        if (dist + _speed >= StaticValues.MAX_DISTANCE) {
            dist = StaticValues.MAX_DISTANCE;
        }
        else {
            updateDistance();
        }
        double factor = scale();

        int newY = StaticValues.SpawnY - (int) (StaticValues.TRAVEL_DISTANCE_Y*factor);
        Point pos = getDrawPosition();
        pos.y = newY;
        update();
    }

    public double scale() {

        double factor = (double) getDistance()  / (double) StaticValues.MAX_DISTANCE;
        int newWidth = (int) (_originalSize.x * factor);
        int newHeight = (int) (_originalSize.y * factor);

        BufferedImage newImage = new BufferedImage(newWidth, newHeight, _originalImage.getType());
        
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(_originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();
        
        setImage(newImage);

        rect.setSize(newWidth, newHeight);

        return factor;
    }
}