package app.src.resources;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Monster extends Entity {

    private Point _originalSize;
    private BufferedImage _originalImage;

    public Monster(String imageName, int health, int speed) {
        super(imageName, StaticValues.CANVAS_WIDTH/2, StaticValues.SpawnY, health);
        setSpeed(speed);
        _originalImage = getImage();
        _originalSize = new Point(_originalImage.getWidth(), _originalImage.getHeight());
    }

    @Override
    public void update() {
        int dist = getDistance();
        if (dist + getSpeed() >= StaticValues.MAX_DISTANCE) {
            dist = StaticValues.MAX_DISTANCE;
        }
        else {
            updateDistance();
        }
        double factor = scale();

        int newY = StaticValues.SpawnY + (int) (StaticValues.TRAVEL_DISTANCE_Y*factor);
        Point pos = rect.getPosition();
        pos.y = newY;
        pos.x += rect.getWidth()/2; // horizontal correction based on scaling
        updateDrawPosition(pos);
    }

    public double scale() {

        double factor = (double) getDistance()  / (double) StaticValues.MAX_DISTANCE;
        int newWidth = (int) (_originalSize.x * factor);
        int newHeight = (int) (_originalSize.y * factor);
        if (newWidth < 1) {newWidth = 1;}
        if (newHeight < 1) {newHeight = 1;}

        BufferedImage newImage = new BufferedImage(newWidth, newHeight, _originalImage.getType());
        
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(_originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();
        
        setImage(newImage);

        rect.setSize(newWidth, newHeight);

        return factor;
    }
}