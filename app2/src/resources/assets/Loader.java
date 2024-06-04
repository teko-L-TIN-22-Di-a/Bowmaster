package app2.src.resources.assets;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
    
    public static BufferedImage loadImage(String name) {
    BufferedImage image = null;
        try {
            image = ImageIO.read(Loader.class.getResourceAsStream(name));
            System.out.println("image loaded");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("can't load image");
        }
        return image;
    }
}
