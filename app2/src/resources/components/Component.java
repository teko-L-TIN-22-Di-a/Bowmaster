package app2.src.resources.components;

import java.awt.Point;
import java.awt.image.BufferedImage;

import app2.src.resources.Rectangle;
import app2.src.resources.assets.Loader;

public class Component {

    private Point _location;
    private int _width, _height;
    public Rectangle rect;
    private BufferedImage _image;

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
        _location = new Point(newLocation.x - _width/2, newLocation.y - _height/2);
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
        setLocation(_location);
    }

    //Constructors
    public Component(int x, int y, int width, int height) {
        _width = width;
        _height = height;
        _location = new Point(x - _width/2, y - _height/2);
        rect = new Rectangle(_width, _height, x, y);
    }

    public Component(String imagePath, int x, int y) {
        BufferedImage image = Loader.loadImage(imagePath);
        setImage(image);
        _width = _image.getWidth();
        _height = _image.getHeight();
        _location = new Point(x - _width/2, y - _height/2);
        rect = new Rectangle(_width, _height, x, y);
    }
}
