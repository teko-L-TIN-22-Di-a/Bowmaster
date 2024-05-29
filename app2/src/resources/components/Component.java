package app2.src.resources.components;

import java.awt.Point;
import java.awt.image.BufferedImage;

import app2.src.resources.Rectangle;

public class Component {

    private Point _location;
    private int _width, _height;
    public Rectangle rect;
    private BufferedImage _image;

    public Component(Point location, int width, int height) {
        _location = location;
        _width = width;
        _height = height;
        rect = new Rectangle(width, height, _location.x, _location.y);
    }

    public BufferedImage getImage() {
        return _image;
    }

    public void setImage(BufferedImage image) {
        _image = image;
    }

    public Point getLocation() {
        return _location;
    }

    public void setLocation(Point newLocation) {
        _location = newLocation;
        rect.setPosition(newLocation);
    }

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public void setSize(int width, int height) {
        _width = width;
        _height = height;
        rect.setSize(_width, _height);
    }
}
