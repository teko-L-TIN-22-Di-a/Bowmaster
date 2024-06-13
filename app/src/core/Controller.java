package app.src.core;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.JPanel;

import app.src.StaticValues;
import app.src.resources.components.Button;

/**
 * Handles Player input
 * @see MouseHandler
 * @see MouseTracker
 */
public class Controller extends JPanel{
    
    /** handles mouse input */
    private final MouseHandler handler;
    /** tracks mouse position */
    private final MouseTracker tracker;
    /** list of Buttons to check */
    private List<Button> buttonlist;

    /**
     * Constructor. Creates a Controller object.
     * Sets up a mouse handler and tracker.
     */
    public Controller() {
        setSize(StaticValues.CANVAS_WIDTH, StaticValues.CANVAS_HEIGHT);
        handler = new MouseHandler();
        tracker = new MouseTracker();
    }

    /**
     * Returns the mouse position.
     * @return mouse location
     */
    public Point getMousePos() {
        return tracker.getLocation();
    }

    /**
     * Takes a list of Buttons and stores it in the buttonList.
     * @param buttonList    list to store
     */
    public void setButtonList(List<Button> buttonList) {
        buttonlist = buttonList;
    }

    /**
     * Adds the required listeners to a JPanel
     * @param panel panel to add listeners
     */
    public void setupListeners(JPanel panel) {
        panel.addMouseListener(handler);
        panel.addMouseMotionListener(tracker);
    }

    /**
     * Extends the MouseAdapter to add the actioncheck for the buttons.
     * @see MouseAdapter
     * @see Button
     */
    private class MouseHandler extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            for (Button b: buttonlist) {
                Point mousePosition = tracker.getLocation();
                b.actionCheck(mousePosition);
            };
        }
    }

    /**
     * Extends the MouseMotionAdapter class to extract the current mouse location
     * @see MouseMotionAdapter
     */
    private class MouseTracker extends MouseMotionAdapter {

        private Point location = new Point(0, 0);

        @Override
        public void mouseMoved(final MouseEvent e) {
            location = e.getPoint();
            if (location == null) {
                location = new Point(0, 0);
            }
        }

        public Point getLocation() {
            return location;
        }
    }
}