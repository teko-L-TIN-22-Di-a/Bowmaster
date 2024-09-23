package app.src.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import app.src.StaticValues;
import app.src.resources.Entity;
import app.src.resources.components.Button;
import app.src.resources.components.Component;
import app.src.resources.components.Hitbox;
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
    private Scene scene;
    /** Canvas to draw the Scene on */
    public Canvas canvas = new Canvas();
    /** Used to set debug mode */
    private boolean debug = false;

    /**
     * Creates a Renderer Object.
     */
    public Renderer() {
        canvas.setPreferredSize(new Dimension(StaticValues.CANVAS_WIDTH, StaticValues.CANVAS_HEIGHT));
        this.setContentPane(canvas);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setTitle(StaticValues.GAMENAME);
        this.setVisible(true);
    }

    /**
     * Creates a Renderer Object.
     * @param debug if true, Hitboxes and ImageBoxes will be drawn
     */
    public Renderer(boolean debug) {
        canvas.setPreferredSize(new Dimension(StaticValues.CANVAS_WIDTH, StaticValues.CANVAS_HEIGHT));
        this.setContentPane(canvas);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setTitle(StaticValues.GAMENAME);
        this.setVisible(true);
        this.debug = debug;
    }

    /**
     * Takes a Scene for rendering.
     * @param newScene Scene to render
     */
    public void setScene(Scene newScene) {
        scene = newScene;
        System.out.println("> start scene " + scene.getTAG());
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
            List<Component> components = scene.getComponents();
            for (Component component: components) {
                Point componentLocation = component.getDrawPosition();
                offScreen.drawImage(component.getImage(), componentLocation.x, componentLocation.y, null);
                if (debug) {
                    component.getRect().draw(offScreen, Color.red); // DEBUG VIEW
                }
            }
            List<Entity> entities = sortByDistance(scene.getEnties());
            for (Entity entity: entities) {
                Point entityLocation = entity.getDrawPosition();
                offScreen.drawImage(entity.getImage(), entityLocation.x, entityLocation.y, null);
                if (debug) { // DEBUG VIEW
                    entity.getRect().draw(offScreen, Color.red);
                    for (Hitbox h: entity.getHitBoxes()) {
                        h.draw(offScreen, Color.blue);
                    }
                } // DEBUG VIEW
            }
            List<Button> buttons = scene.getButtons();
            for (Button button: buttons) {
                Point buttonLocation = button.getDrawPosition();
                offScreen.drawImage(button.getImage(), buttonLocation.x, buttonLocation.y, null);
                if (debug) {
                    button.getRect().draw(offScreen, Color.red); // DEBUG VIEW
                }
            }
            onScreen.drawImage(offScreenImage, 0, 0, null);
            g.drawImage(onScreenImage, 0, 0, null);
        }

        /**
         * Takes an Entity List and sorts it by the distance attribut, from lowest to highest.
         * Used to determine the drawing sequence.
         * @param unsortedList not sorted Entity List
         * @return sorted Entity List
         */
        private List<Entity> sortByDistance(List<Entity> unsortedList) {
            List<Entity> sortedList = new ArrayList<>();
            int index = 0;
            for (Entity e: unsortedList ) {
                if (index == 0) {
                    sortedList.add(e);
                    ++index;
                }
                else {
                    int dist = e.getDistance();
                    int i = 0;
                    for (Entity ent: sortedList) {
                        if (dist > ent.getDistance()) {
                            ++i;
                        }
                        else {break;}
                    }
                    sortedList.add(i, e);
                }
            }
            return sortedList;
        }
    }
}
