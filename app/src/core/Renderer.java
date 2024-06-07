package app.src.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import app.src.resources.Entity;
import app.src.resources.Rectangle;
import app.src.resources.StaticValues;
import app.src.resources.components.Button;
import app.src.resources.components.Component;
import app.src.scenes.Scene;

public class Renderer extends JFrame{
    private Scene _Scene;
    private List<Component> _components;
    private List<Entity> _entities;
    public Canvas canvas = new Canvas();

    public void setScene(Scene newScene) {
        _Scene = newScene;
        System.out.println("start scene " + _Scene.getTAG());
        startScene();
    }

    public List<Button> getButtons() {
        List<Button> buttonList = new ArrayList<Button>();
        for (Component c : _components) {
            if (c instanceof Button) {
                buttonList.add((Button) c);
            }
        }
        return buttonList;
    }

    public void startScene() {
        _entities = _Scene.getEnties();
        _components = _Scene.getComponents();
    }

    public class Canvas extends JPanel {
        int width = StaticValues.CANVAS_WIDTH;
        int height = StaticValues.CANVAS_HEIGHT;
        BufferedImage onScreenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage offScreenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D onScreen = onScreenImage.createGraphics();
        Graphics2D offScreen = offScreenImage.createGraphics();

        public Canvas() {
            onScreen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            offScreen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        
        @Override
        public void paintComponent(Graphics g) {
            offScreen.setColor(Color.black);
            offScreen.fillRect(0, 0, width, height);
            offScreen.setColor(Color.blue);
            offScreen.drawLine(0, StaticValues.CANVAS_HEIGHT/2, StaticValues.CANVAS_WIDTH, StaticValues.CANVAS_HEIGHT/2);
            offScreen.drawLine(StaticValues.CANVAS_WIDTH/2, 0, StaticValues.CANVAS_WIDTH/2, StaticValues.CANVAS_HEIGHT);
            
            for (Component component: _components) {
                offScreen.drawImage(component.getImage(), component.getLocation().x, component.getLocation().y, null);
                component.rect.draw(offScreen, Color.red);
            }

            for (Entity entity: _entities) {
                entity.update();
                offScreen.drawImage(entity.getImage(), entity.getDrawPosition().x, entity.getDrawPosition().y, null);
                entity.rect.draw(offScreen, Color.red);
                if (entity.getMainHitbox() instanceof Rectangle) {
                    entity.getMainHitbox().draw(offScreen, Color.green);
                }
            }

            onScreen.drawImage(offScreenImage, 0, 0, null);
            g.drawImage(onScreenImage, 0, 0, null);
        }
    }

    public Renderer() {
        canvas.setPreferredSize(new Dimension(StaticValues.CANVAS_WIDTH, StaticValues.CANVAS_HEIGHT));
        this.setContentPane(canvas);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setTitle(StaticValues.GAMENAME);
        this.setVisible(true);
    }
}
