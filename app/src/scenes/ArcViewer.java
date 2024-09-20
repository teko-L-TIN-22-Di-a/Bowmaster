package app.src.scenes;

import java.awt.Color;
import java.awt.Point;

import app.src.StaticValues;
import app.src.resources.components.Button;

/**
 * Creates the menu screen.
 * Extends the Scene class.
 * @see Button
 * @see Scene
 */
public class ArcViewer extends Scene {
    /**
     * Sets up the Menu scene.
     */
    public ArcViewer() {
        super(false);
        setTAG("arcviewer");
        int width = StaticValues.CANVAS_WIDTH;
        int height = StaticValues.CANVAS_HEIGHT;
        Button menu = new Button(100, 50, width/2, height/2 - 100, "MENU", Color.GRAY);
        menu.setAction(() -> {
            Menu m = new Menu();
            setNewScene(m);
        });
        registerButton(menu);

        Button exit = new Button(100, 50, width/2, height/2 + 100, "EXIT", Color.GRAY);
        exit.setAction(() -> {
            System.exit(0);
        });
        registerButton(exit);

        registerComponent(getArc());
    }

    @Override
    public void update(Point playerLocation) {
        super.update(playerLocation);
    }
}
