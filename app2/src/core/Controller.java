package app2.src.core;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import app2.src.resources.StaticValues;

public class Controller extends JPanel{
    // handles Player input
    public final MouseHandler handler;
    private Point _mousePosition;

    public Point getMousePos() {
        update();
        return _mousePosition;
    }

    public void update() {
        _mousePosition = handler.getMouseLocation();
    }


    private class MouseHandler extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("Mouse Pressed!");
        }
        
        public Point getMouseLocation() {
            Point mousePoint = MouseInfo.getPointerInfo().getLocation();
            return mousePoint;
        }
    }

    // Constructor
    public Controller() {
        setSize(StaticValues.CANVAS_WIDTH, StaticValues.CANVAS_HEIGHT);
        handler = new MouseHandler();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
        update();
    }

    public static void main(String[] args) {

    }
}
