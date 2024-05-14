import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Paint extends JPanel {
    private static final int CANVAS_WIDTH = 640;
    private static final int CANVAS_HEIGHT = 400;

    private int x1 = 0, y1 = 0, x2 = 0, y2 = 0;

    private final TheMouseHandler handler;
    private Graphics g;

    // Constructor
    public Paint() {
        setBackground(Color.white);
        setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        handler = new TheMouseHandler();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
    }

    private void setUpDrawingGraphics() {
        g = getGraphics();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private class TheMouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            x1 = e.getX();
            y1 = e.getY();
            setUpDrawingGraphics();
        }
        @Override
        public void mouseDragged(MouseEvent e) {
            x1 = e.getX();
            y1 = e.getY();

            g.drawLine(x1, y1, x2, y2);

            x2 = x1;
            y2 = y1;
            
            setUpDrawingGraphics();
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Paint");
        Paint paint = new Paint();
    }
    
}
