package app.src.resources.assets;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/** Loads assets from the asset folder. */
public class Loader {
    
    /** 
     * Default constructor for Loader.
     * Initializes the Loader without any specific configuration.
     */
    public Loader() {
        // Initialization code can go here if needed
    }
    
    /**
     * Takes the name of the image and loads it as Buffered image.
     * @param name name like "image.png"
     * @return the loaded image as BufferedImage
     * @see BufferedImage
     */
    public static BufferedImage loadImage(String name) {
    BufferedImage image = null;
        try {
            image = ImageIO.read(Loader.class.getResourceAsStream(name));
            System.out.println("image " + name + " loaded");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("can't load image");
        }
        return image;
    }
}
