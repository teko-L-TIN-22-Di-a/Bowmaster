package app2.src;

import java.awt.Point;
import java.awt.image.BufferedImage;

import app2.src.utils.Loader;

public class Entity {
    private BufferedImage _image;
    public Rectangle rect;
    private Point _drawPosition;
    private int _health;

    public Entity(String imagePath, int x, int y, int health) {
        _drawPosition = new Point(x, y);
        _image = Loader.loadImage(imagePath);
        rect = new Rectangle(_image.getWidth(), _image.getHeight(), x, y);
        _drawPosition = new Point(rect.getCorner(null));
        setHealth(health);
    }

    public Point getDrawPosition() {
        return _drawPosition;
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

    public void setHealth(int value) {
        _health = value;
    }
}

