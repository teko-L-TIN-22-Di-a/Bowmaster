package app2.src.scenes;

import java.awt.Color;
import java.awt.Point;

import app2.src.resources.StaticValues;
import app2.src.resources.components.Button;
import app2.src.resources.components.Component;

public class Menu extends Scene {
    private Button start, exit;
    private Component bg;

    public Menu() {
        int width = StaticValues.CANVAS_WIDTH;
        int height = StaticValues.CANVAS_HEIGHT;
        start = new Button(width/2, height/2 - 100, 100, 50, "START", Color.GRAY);
        start.setAction(() -> {
            System.out.println("start");
        });
        exit = new Button(width/2, height/2 + 100, 100, 50, "EXIT", Color.GRAY);
        exit.setAction(() -> {
            System.out.println("exit");
        });
        bg = new Component("assets/Hunters House.jpg", 0, 0);
        Point newLocation = new Point(bg.getWidth()/2, bg.getHeight()/2);
        bg.setLocation(newLocation);
        registerComponent(bg);
        registerComponent(start);
        registerComponent(exit);
    }

    public Menu init() {
        Menu menu = new Menu();
        return menu;
    }
}
