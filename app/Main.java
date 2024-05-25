package app;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import app.src.Component;
import app.src.Component.Orientation;
import app.src.Renderer;
import app.src.Vector3D;

public class Main {
    public static void main(String[] args) {
        Renderer bobRoss = new Renderer();
        
        BufferedImage characterImage = loadImage("character.png");
        Vector3D characterPosition = new Vector3D(
            bobRoss.getWidth()/2,
            bobRoss.getHeight()
            );
        Component character = new Component(characterImage, characterPosition);
        character.setOrientation(Orientation.CB);
        
        bobRoss.addComponent(character);
        bobRoss.render();
    }
    public static BufferedImage loadImage(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(
                Main.class.getResourceAsStream(path)

            );
            System.out.println("image loaded");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("can't load image");
        }
        return image;
    }
}