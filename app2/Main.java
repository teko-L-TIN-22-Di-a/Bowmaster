package app2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import app2.src.resources.components.Button;
import app2.src.resources.components.Textfield;

public class Main {

    private static int CANVAS_WIDTH = 500;
    private static int CANVAS_HEIGHT = 500;
    private Point buttonPosition = new Point(100, 100);
    private Button button = new Button(buttonPosition, 200, 50, "This Button", Color.GREEN);
    private Point textPosition = new Point(100, 300);
    private Textfield text = new Textfield(textPosition, "textfield 1");

    private class DrawCanvas extends JPanel {
        BufferedImage onScreenImage = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage offScreenImage = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D onScreen = onScreenImage.createGraphics();
        Graphics2D offScreen = offScreenImage.createGraphics();

        public DrawCanvas() {
            onScreen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            offScreen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        
        @Override
        public void paintComponent(Graphics g) {
            offScreen.setColor(Color.orange);
            offScreen.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
            
            offScreen.drawImage(button.getImage(), button.getLocation().x, button.getLocation().y, null);
            offScreen.drawImage(text.getImage(), text.getLocation().x, text.getLocation().y, null);

            onScreen.drawImage(offScreenImage, 0, 0, null);
            g.drawImage(onScreenImage, 0, 0, null);
        }
    }
    public Main() {
        JFrame frame = new JFrame();
        DrawCanvas canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        frame.setContentPane(canvas);
        frame.pack();
        frame.setTitle("Buttontest");
        frame.setVisible(true);

        ActionListener updateTask = evt -> {
            frame.repaint();
        };

        new Timer(60, updateTask).start();
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(Main::new);
    }
}
