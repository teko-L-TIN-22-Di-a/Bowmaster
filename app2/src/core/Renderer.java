package app2.src.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import app2.src.resources.Entity;
import app2.src.resources.StaticValues;
import app2.src.resources.components.Button;
import app2.src.resources.components.Component;
import app2.src.scenes.Scene;

public class Renderer extends JFrame{
    private Scene _activeScene, _previouScene;
    private List<Component> _components;
    private List<Entity> _entities;
    public Canvas canvas = new Canvas();

    public void setScene(Scene newScene) {
        _activeScene = newScene;
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

    public void startActiveScene() {
        _activeScene.init();
        _entities = _activeScene.getEnties();
        _components = _activeScene.getComponents();
    }

    public void startPreviousScene() {
        _previouScene.init();
        _entities = _activeScene.getEnties();
        _components = _activeScene.getComponents();
    }

    private class Canvas extends JPanel {
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
            
            for (Component component: _components) {
                offScreen.drawImage(component.getImage(), component.getLocation().x, component.getLocation().y, null);
                int x1 = component.rect.getX();
                int x2 = x1 + component.rect.getWidth();
                int y1 = component.rect.getY();
                int y2 = y1 + component.rect.getHeight();
                offScreen.setColor(Color.red);
                offScreen.drawLine(x1, y1, x1, y2);
                offScreen.drawLine(x1, y1, x2, y1);
                offScreen.drawLine(x2, y2, x2, y1);
                offScreen.drawLine(x2, y2, x1, y2);
            }

            for (Entity entity: _entities) {
                offScreen.drawImage(entity.getImage(), entity.getDrawPosition().x, entity.getDrawPosition().y, null);
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
    
    public void start() {
        startActiveScene();

        ActionListener updateTask = evt -> {
            this.repaint();
        };

        new Timer(60, updateTask).start();
    }
}
