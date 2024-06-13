package app.src.resources.components;

import java.awt.Point;
import java.awt.image.BufferedImage;

import app.src.resources.assets.Loader;

/**
 * Baseclass for a Component
 */
public class Component {

    private Point size, location;
    public Rectangle rect;
    private BufferedImage image;

    /**
     * Returns the iamge of the Component
     * @return iamge of the Component
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Takes an image and stores it.
     * @param newImage  new image to store
     */
    public void setImage(BufferedImage newImage) {
        image = newImage;
    }

    /**
     * Returns the location of the Component
     * @return location of the Component
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Takes coordinates for a new location and stores them 
     * @param newLocation   new coordinates for the Component
     */
    public void setLocation(int newX, int newY) {
        rect.setLocation(newX - size.x/2, newY - size.y/2);
    }

    /**
     * Returns the width of the Component
     * @return width of the Component
     */
    public int getWidth() {
        return size.x;
    }

    /**
     * Returns the height of the Component
     * @return height of the Component
     */
    public int getHeight() {
        return size.y;
    }

    /**
     * Takes width and height to store in size.
     * Recalculates the Rectangle.
     * @param width     new width of the Component
     * @param height    new height of the Component
     */
    public void setSize(int newWidth, int newHeight) {
        size.x = newWidth;
        size.y = newHeight;
        rect.setSize(newWidth, newHeight);
        setLocation(location.x, location.y);
    }

    /**
     * Constructor. Creates a Component with size, location and Rectangle
     * @param width     width of the Component
     * @param height    height of the Component
     * @param x         x coordinate of the Component
     * @param y         y coordinate of the Component
     */
    public Component(int width, int height, int x, int y) {
        size = new Point(width, height);
        location = new Point(x - width/2, y - height/2);
        rect = new Rectangle(width, height, x, y);
    }

    /**
     * Constructor. Creates a Component with image, size, location and Rectangle
     * Loads an image from app/src/resources/assets.
     * @param imageName image name like "image.png"
     * @param x         x coordinate of the Component
     * @param y         y coordinate of the Component
     */
    public Component(String imageName, int x, int y) {
        BufferedImage image = Loader.loadImage(imageName);
        setImage(image);
        size.x = image.getWidth();
        size.y = image.getHeight();
        location = new Point(x - size.x/2, y - size.y/2);
        rect = new Rectangle(size.x, size.y, x, y);
    }
}
