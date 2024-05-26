package app.src;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class Controller extends JPanel{

    public final MouseHandler handler;
    private Vector3D mousePosition;

    // Constructor
    public Controller() {
        setSize(Constants.CANVAS_WIDTH, Constants.CANVAS_HEIGHT);
        handler = new MouseHandler();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
        update();
    }

    public Vector3D getMousePos() {
        return mousePosition;
    }

    public void update() {
        this.mousePosition = handler.getMouseLocation();
    }


    private class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("Shot!");
        }
        
        public Vector3D getMouseLocation() {
            PointerInfo mpi = MouseInfo.getPointerInfo();
            Point p = mpi.getLocation();
            Vector3D mp = new Vector3D((int) p.getX(), (int) p.getY());
            return mp;
        }
    }
    public static void main(String[] args) {

    }
}
