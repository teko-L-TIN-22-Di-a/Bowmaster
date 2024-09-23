package app.src.scenes;

import java.awt.Color;

import app.src.StaticValues;
import app.src.resources.assets.images.ImageMapping;
import app.src.resources.assets.sounds.SoundMapping;
import app.src.resources.components.Button;

/**
 * Creates the menu screen.
 * Extends the Scene class.
 * @see Button
 * @see Scene
 */
public class Menu extends Scene {
    /**
     * Sets up the Menu scene.
     */
    public Menu() {
        super(true);
        setTAG("menu");
        setBG(ImageMapping.MAP1);
        setBGM(SoundMapping.MENUBGM);
        int width = StaticValues.CANVAS_WIDTH;
        int height = StaticValues.CANVAS_HEIGHT;
        Button start = new Button(100, 50, width/2, height/2 - 100, "START", Color.GRAY);
        start.setAction(() -> {
            Level1 l = new Level1();
            setNewScene(l);
        });
        registerButton(start);
        
        Button exit = new Button(100, 50, width/2, height/2 + 100, "EXIT", Color.GRAY);
        exit.setAction(() -> {
            System.exit(0);
        });
        registerButton(exit);

        /*

        // START ARCVIEWER SCENE FOR DEBUG PURPOSES
        
        Button arcviewer = new Button(100, 50, width/2, height/2 + 300, "ARCVIEWER", Color.GRAY);
        arcviewer.setAction(() -> {
            ArcViewer av = new ArcViewer();
            setNewScene(av);
        });
        registerButton(arcviewer);
        */
    }
}
