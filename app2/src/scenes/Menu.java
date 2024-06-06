package app2.src.scenes;

import java.awt.Color;

import app2.src.resources.StaticValues;
import app2.src.resources.components.Button;

public class Menu extends Scene {
    private Button start, exit;

    public Menu() {
        setTAG("menu");
        int width = StaticValues.CANVAS_WIDTH;
        int height = StaticValues.CANVAS_HEIGHT;
        start = new Button(width/2, height/2 - 100, 100, 50, "START", Color.GRAY);
        start.setAction(() -> {
            Level1 l = new Level1();
            setNewScene(l);
        });
        exit = new Button(width/2, height/2 + 100, 100, 50, "EXIT", Color.GRAY);
        exit.setAction(() -> {
            System.exit(0);
        });
        //setBG("HuntersHouse.png");
        registerComponent(start);
        registerComponent(exit);
    }
}
