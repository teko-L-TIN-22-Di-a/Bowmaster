package app2.src.resources;

import java.awt.Point;
import java.awt.image.BufferedImage;

import app2.src.resources.assets.Loader;

public class Entity {
    private BufferedImage _image;
    public Rectangle rect;
    private Point _drawPosition;
    private int _health, _distance, _speed;

    public Entity(String imagePath, int x, int y, int health) {
        _drawPosition = new Point(x, y);
        _image = Loader.loadImage(imagePath);
        rect = new Rectangle(_image.getWidth(), _image.getHeight(), x, y);
        _drawPosition = new Point(rect.getCorner(null));
        setHealth(health);
    }

    public void update() {
        // to overide per entity
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

    public int getSpeed() {
        return _speed;
    }

    public int getDistance() {
        return _distance;
    }

    public void setDistance(int value) {
        _distance = value;
    }

    public void updateDistance() {
        int newDistance = getDistance() - getSpeed();
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