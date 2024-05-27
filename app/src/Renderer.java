package app.src;

import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Renderer extends JFrame{
    private List<drawableComponent> components = new ArrayList<>();
    private Controller controller = new Controller();
    

    private static final int CANVAS_WIDTH = Constants.CANVAS_WIDTH;
    private static final int CANVAS_HEIGHT = Constants.CANVAS_HEIGHT;
    private static final int UPDATE_PERIOD = Constants.UPDATE_PERIOD; //Miliseconds
    
    private class Canvas extends JPanel {
        BufferedImage onScreenImage = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage offScreenImage = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D onScreen = onScreenImage.createGraphics();
        Graphics2D offScreen = offScreenImage.createGraphics();

        public Canvas() {
            onScreen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            offScreen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            offScreen.setColor(Color.orange);
            offScreen.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
            controller.update();
            Vector3D mousePosition = controller.getMousePos();
            offScreen.setColor(Color.RED);
            offScreen.drawLine(mousePosition.x, mousePosition.y, CANVAS_WIDTH/2, CANVAS_HEIGHT/2 );
            
            for (drawableComponent component : components) {
                component.update();
                offScreen.drawImage(component.image, component.rect.position.x, component.rect.position.y, null);
                offScreen.drawLine(
                    component.rect.bottomLeft.x,
                    component.rect.bottomLeft.y,
                    component.rect.topRight.x,
                    component.rect.topRight.y
                );
                offScreen.drawLine(
                    component.rect.topLeft.x,
                    component.rect.topLeft.y,
                    component.rect.bottomRight.x,
                    component.rect.bottomRight.y
                );
                offScreen.drawLine(
                    component.rect.centerLeft.x,
                    component.rect.centerLeft.y,
                    component.rect.centerRight.x,
                    component.rect.centerRight.y
                );
                offScreen.drawLine(
                    component.rect.centerTop.x,
                    component.rect.centerTop.y,
                    component.rect.centerBottom.x,
                    component.rect.centerBottom.y
                );
                offScreen.drawLine(
                    component.rect.position.x,
                    component.rect.position.y,
                    component.rect.center.x,
                    component.rect.center.y
                );
            }
            onScreen.drawImage(offScreenImage, 0, 0, null);
            g.drawImage(onScreenImage, 0, 0, null);
        }
    }

    public void addComponent(drawableComponent component) {
        components.add(component);
    }
    
    public void render() {
        Canvas canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                System.out.println("Shot!");
            }
        });
        this.setContentPane(canvas);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("BOWMASTER");
        this.setVisible(true);

        ActionListener updateTask = evt -> canvas.repaint();

        new Timer(UPDATE_PERIOD, updateTask).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Renderer renderer = new Renderer();
            renderer.render();
        });
    }
}
