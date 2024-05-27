package app;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import app.src.drawableComponent;
import app.src.Constants.Orientation;
import app.src.Constants;
import app.src.Monster;
import app.src.Renderer;
import app.src.Vector3D;

public class Main {
    public static void main(String[] args) {
        Renderer bobRoss = new Renderer();
        
        BufferedImage characterImage = loadImage("character.png");
        Vector3D characterPosition = new Vector3D(
            Constants.CANVAS_WIDTH/2,
            Constants.CANVAS_HEIGHT
        );
        drawableComponent character = new drawableComponent(characterImage, characterPosition);
        character.setOrientation(Orientation.BOTTOM_CENTER);

        BufferedImage monsterImage = loadImage("Gobclops.png");
        Vector3D monsterPosition = new Vector3D(Constants.CANVAS_WIDTH/2, 0, 1);
        Monster monster = new Monster(monsterImage, monsterPosition, 1);
        monster.rect.setOrientation(Orientation.TOP_LEFT);

        bobRoss.addComponent(monster);
        bobRoss.addComponent(character);
        bobRoss.render();
    }
    
    public static BufferedImage loadImage(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Main.class.getResourceAsStream(path));
            System.out.println("image loaded");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("can't load image");
        }
        return image;
    }
}