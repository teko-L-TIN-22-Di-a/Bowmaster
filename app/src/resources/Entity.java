package app.src.resources;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

import app.src.resources.StaticValues.Corners;
import app.src.resources.assets.Loader;

public class Entity {
    private BufferedImage _image;
    public Rectangle rect;
    private Point _drawPosition;
    private int _health, _distance, _speed;
    private Rectangle _mainHitbox;
    private List<Rectangle> _critBoxes;

    public Entity(String imagePath, int x, int y, int health) {
        _drawPosition = new Point(x, y);
        _image = Loader.loadImage(imagePath);
        int imageWidth = _image.getWidth();
        int imageHeight = _image.getHeight();
        rect = new Rectangle(imageWidth, imageHeight, x, y);
        _drawPosition = new Point(rect.getCorner(Corners.TOP_LEFT));
        _distance = 0;
        _speed = 0;
        setHealth(health);
    }

    public void update() {
        // to overide per entity
    }

    public void setMainHitbox(int width, int height, int offsetX, int offsetY) {
        _mainHitbox = createHitbox(width, height, offsetX, offsetY);
    }

    public void registerCritBox(int width, int height, int offsetX, int offsetY) {
        _critBoxes.add(createHitbox(width, height, offsetX, offsetY));
    }

    public Rectangle createHitbox(int width, int height, int offsetX, int offsetY) {
        Point rectPosition = rect.getPosition();
        Rectangle hitBox = new Rectangle(width, height, rectPosition.x, rectPosition.y);
        return hitBox;
    }

    public void updateHitBox(Point newPoint) {
        Rectangle box = getMainHitbox();
        if (box instanceof Rectangle) {
            box.setPosition(newPoint);
        }
    }

    public Rectangle getMainHitbox() {
        return _mainHitbox;
    }

    public List<Rectangle> getCritBoxes() {
        return _critBoxes;
    }

    public void updateDrawPosition(Point newPosition) {
        newPosition.x -= rect.getWidth()/2;
        newPosition.y -= rect.getHeight()/2;
        rect.setPosition(newPosition);
        _drawPosition = new Point(rect.getCorner(Corners.TOP_LEFT));
    }

    public BufferedImage getImage() {
        return _image;
    }

    public void setImage(BufferedImage newImage) {
        _image = newImage;
    }

    public Point getDrawPosition() {
        return _drawPosition;
    }

    public void setDrawPosition(Point position) {
        _drawPosition = position;
    }

    public int getSpeed() {
        return _speed;
    }

    public void setSpeed(int speed) {
        _speed = speed;
    }

    public int getDistance() {
        return _distance;
    }

    public void setDistance(int value) {
        _distance = value;
    }

    public void updateDistance() {
        int newDistance = getDistance() + getSpeed();
        setDistance(newDistance);
    }

    public void setHealth(int value) {
        _health = value;
    }

    public void death() {
        // determine what happens for entity death
    }

    public void updateHealth(int value) {
        _health += value;
        if (_health <= 0) {
            death();
        }
    }
}