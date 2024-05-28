package app2.src.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
    
    public static BufferedImage loadImage(String path) {
    BufferedImage image = null;
        try {
            image = ImageIO.read(Loader.class.getResourceAsStream(path));
            System.out.println("image loaded");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("can't load image");
        }
        return image;
    }
}
