package app.src.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import app.src.StaticValues;
import app.src.resources.Entity;
import app.src.resources.components.Button;
import app.src.resources.components.Component;
import app.src.resources.components.Hitbox;
import app.src.resources.components.Rectangle;
import app.src.scenes.Scene;

/**
 * Renders the game view to the screen
 * @see Scene
 * @see Canvas
 * @see Component
 * @see Entity
 * @see Button
 */
public class Renderer extends JFrame{
    /** Scene to render */
    private Scene Scene;
    /** Components to render */
    private List<Component> components;
    /** Entities to render */
    private List<Entity> entities;
    /** Buttons to render */
    private List<Button> buttons;
    /** Canvas to draw onto */
    public Canvas canvas = new Canvas();

    /**
     * Takes a Scene for rendering.
     * @param newScene Scene to render
     */
    public void setScene(Scene newScene) {
        Scene = newScene;
        System.out.println("start scene " + Scene.getTAG());
        startScene();
    }

    /**
     * Returns a list with the registered Buttons
     * @return buttons
     */
    public List<Button> getButtons() {
        return buttons;
    }

    /**
     * Gets the entities, components and buttons to draw them.
     */
    public void startScene() {
        entities = Scene.getEnties();
        components = Scene.getComponents();
        buttons = Scene.getButtons();
    }

    /**
     * Extends the JPanel class to create a canvas to draw onto.
     * @see JPanel
     */
    public class Canvas extends JPanel {
        /** width of the canvas */
        int width = StaticValues.CANVAS_WIDTH;
        /** height of the canvas */
        int height = StaticValues.CANVAS_HEIGHT;
        /** image that is drawn on the screen */
        BufferedImage onScreenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        /** pre rendered image */
        BufferedImage offScreenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        /** graphics for the screen */
        Graphics2D onScreen = onScreenImage.createGraphics();
        /** graphics for pre rendering */
        Graphics2D offScreen = offScreenImage.createGraphics();

        /**
         * Constructor. Creates a Canvas object to draw onto.
         */
        public Canvas() {
            onScreen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            offScreen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        
        /**
         * Takes Graphics and to draw.
         * Draws the defined components on the screen.
         */
        @Override
        public void paintComponent(Graphics g) {
            offScreen.setColor(Color.black);
            offScreen.fillRect(0, 0, width, height);
            offScreen.setColor(Color.blue);
            offScreen.drawLine(0, StaticValues.CANVAS_HEIGHT/2, StaticValues.CANVAS_WIDTH, StaticValues.CANVAS_HEIGHT/2);
            offScreen.drawLine(StaticValues.CANVAS_WIDTH/2, 0, StaticValues.CANVAS_WIDTH/2, StaticValues.CANVAS_HEIGHT);
            
            for (Component component: components) {
                Point componentLocation = component.getDrawPosition();
                offScreen.drawImage(component.getImage(), componentLocation.x, componentLocation.y, null);
                //component.rect.draw(offScreen, Color.red);
            }

            for (Entity entity: entities) {
                Point entityLocation = entity.getDrawPosition();
                offScreen.drawImage(entity.getImage(), entityLocation.x, entityLocation.y, null);
                //entity.rect.draw(offScreen, Color.red);
                if (entity.getMainHitbox() instanceof Rectangle) {
                    entity.getMainHitbox().draw(offScreen, Color.green);
                }
                for (Hitbox h: entity.getCritBoxes()) {
                    h.draw(offScreen, Color.blue);
                }
            }

            for (Button button: buttons) {
                Point buttonLocation = button.getDrawPosition();
                offScreen.drawImage(button.getImage(), buttonLocation.x, buttonLocation.y, null);
                button.rect.draw(offScreen, Color.red);
            }

            onScreen.drawImage(offScreenImage, 0, 0, null);
            g.drawImage(onScreenImage, 0, 0, null);
        }
    }

    /**
     * Constructor. Creates a Renderer Object.
     */
    public Renderer() {
        canvas.setPreferredSize(new Dimension(StaticValues.CANVAS_WIDTH, StaticValues.CANVAS_HEIGHT));
        this.setContentPane(canvas);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setTitle(StaticValues.GAMENAME);
        this.setVisible(true);
    }
}
