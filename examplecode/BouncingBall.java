import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class BouncingBall extends JFrame{
    private static final int CANVAS_WIDTH = 640;
    private static final int CANVAS_HEIGHT = 400;
    private static final int UPDATE_PERIOD = 60; //Miliseconds

    private int x = 100, y = 100;
    private final int size = 50;
    private double xSpeed = 0.2 * UPDATE_PERIOD, ySpeed = 0.2 * UPDATE_PERIOD;

    public void update() {
        x += xSpeed;
        y += ySpeed;
        if (x > CANVAS_WIDTH - size || x < 0) {
            xSpeed = -xSpeed;
        }
        if (y > CANVAS_HEIGHT - size || y < 0) {
            ySpeed = -ySpeed;
        }
    }

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
            
            offScreen.setColor(Color.blue);
            offScreen.fillOval(x, y, size, size);

            offScreen.setColor(Color.red);
            offScreen.drawLine(0, y + size/2, CANVAS_WIDTH, y + size/2);
            offScreen.drawLine(x + size/2, 0, x + size/2, CANVAS_HEIGHT);

            onScreen.drawImage(offScreenImage, 0, 0, null);
            g.drawImage(onScreenImage, 0, 0, null);
        }
    }

    /**
     * Constructor
     * @param args
     */
    public BouncingBall() {
        DrawCanvas canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        this.setContentPane(canvas);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setTitle("Bouncing Ball");
        this.setVisible(true);

        ActionListener updateTask = evt -> {
            update();
            this.repaint();
        };

        new Timer(UPDATE_PERIOD, updateTask).start();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(BouncingBall::new);
    }
}
