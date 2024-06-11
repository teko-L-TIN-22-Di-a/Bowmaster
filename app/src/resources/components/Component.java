package app.src.resources.components;

import java.awt.Point;
import java.awt.image.BufferedImage;

import app.src.resources.Rectangle;
import app.src.resources.assets.Loader;

public class Component {

    private Point size, location;
    public Rectangle rect;
    private BufferedImage image;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage newImage) {
        image = newImage;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point newLocation) {
        rect.setLocation(newLocation.x - size.x/2, newLocation.y - size.y/2);
    }

    public int getWidth() {
        return size.x;
    }

    public int getHeight() {
        return size.y;
    }

    public void setSize(int width, int height) {
        size.x = width;
        size.y = height;
        rect.setSize (width, height);
        setLocation (location);
    }

    //Constructors
    public Component(int width, int height, int x, int y) {
        size = new Point(width, height);
        location = new Point(x - width/2, y - height/2);
        rect = new Rectangle (width, height, x, y);
    }

    public Component(String imagePath, int x, int y) {
        BufferedImage image = Loader.loadImage(imagePath);
        setImage(image);
        size.x = image.getWidth();
        size.y = image.getHeight();
        location = new Point(x - size.x/2, y - size.y/2);
        rect = new Rectangle (size.x, size.y, x, y);
    }
}
