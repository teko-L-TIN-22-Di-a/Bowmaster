package app.src.resources.components;

import java.awt.Point;
import java.awt.image.BufferedImage;

import app.src.Utilities;
import app.src.StaticValues.Corners;

/**
 * Baseclass for a Component
 */
public class Component {

    private BufferedImage image;
    private BufferedImage originalImage;
    private boolean state;
    private Rectangle rect;
    private String TAG;

    /**
     * Creates a Component with size, location and Rectangle
     * @param width width of the Component
     * @param height height of the Component
     * @param x x coordinate of the Component
     * @param y y coordinate of the Component
     */
    public Component(int width, int height, int x, int y) {
        rect = new Rectangle(width, height, x, y);
        state = true;
        TAG = "";
    }

    /**
     * Takes a previously loaded image, x and y coordinates to 
     * create a Component with image, size, location and Rectangle
     * @param image previously loaded image
     * @param x x coordinate of the Component
     * @param y y coordinate of the Component
     */
    public Component(BufferedImage image, int x, int y) {
        setImage(image);
        originalImage = image;
        int width = image.getWidth();
        int height = image.getHeight();
        rect = new Rectangle(width, height, x, y);
        state = true;
        TAG = "";
    }

    /**
     * Base for update methods of extending subclasses.
     */
    public void update() {
        // to overide per component
    }

    /**
     * Returns the Rectangle Object of the Component.
     * @return Rectangle Object of the Component
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * Takes a String to set as a TAG for the Component.
     * Mainly used to indentify different types of Components.
     * @param TAG Component identifier
     */
    public void setTAG(String TAG) {
        this.TAG = TAG;
    }

    /**
     * Returns the TAG of the Component.
     * @return TAG of the Component
     */
    public String getTAG() {
        return this.TAG;
    }

    /**
     * Returns the image of the Component
     * @return image of the Component
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
     * Takes an image and stores it as original image.
     * @param newImage new image to store
     */
    public void setOriginalImage(BufferedImage newImage) {
        originalImage = newImage;
    }

    /**
     * returns the top left corner of the Rectangle as draw position
     * @return top left corner
     */
    public Point getDrawPosition() {
        return rect.getCorner(Corners.TOP_LEFT);
    }

    /**
     * Returns the location of the Component
     * @return location of the Component
     */
    public Point getLocation() {
        return rect.getLocation();
    }

    /**
     * Takes coordinates for a new location and stores them 
     * @param newX  new x coordinate for the Component
     * @param newY  new y coordinate for the Component
     */
    public void setLocation(int newX, int newY) {
        rect.setLocation(newX, newY);
    }

    /**
     * Returns the width of the Component
     * @return width of the Component
     */
    public int getWidth() {
        Point size = rect.getSize();
        return size.x;
    }

    /**
     * Returns the height of the Component
     * @return height of the Component
     */
    public int getHeight() {
        Point size = rect.getSize();
        return size.y;
    }

    /**
     * Takes width and height to store in size.
     * Recalculates the Rectangle.
     * @param newWidth  new width of the Component
     * @param newHeight new height of the Component
     */
    public void setSize(int newWidth, int newHeight) {
        Point location = rect.getLocation();
        rect.setSize(newWidth, newHeight);
        setLocation(location.x, location.y);
    }

    /**
     * Takes a factor to create a scaled image of the original image.
     * @param factor scalefactor
     */
    public void scaleImage(double factor) {
        BufferedImage newImage = new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB);
        newImage = Utilities.scaleImage(originalImage, factor);
        setImage(newImage);
        int newWidth = newImage.getWidth();
        int newHeight = newImage.getHeight();
        rect.setSize(newWidth, newHeight);
    }

    /**
     * Sets the state of the Component to false.
     * Indicates, that the Component is not needed anymore and all references can be removed.
     */
    public void setState() {
        this.state = false;
    }

    /**
     * Returns the state of the Component.
     * @return state of the Component
     */
    public boolean getState() {
        return this.state;
    }
}
