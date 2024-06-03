package app2.src.core;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import javax.swing.JPanel;

import app2.src.resources.StaticValues;
import app2.src.resources.components.Button;

public class Controller extends JPanel{
    // handles Player input
    public final MouseHandler handler;
    private final MouseTracker tracker;
    private List<Button> _buttonlist;
    private Point _mousePosition;

    public Point getMousePos() {
        update();
        return _mousePosition;
    }

    public void setButtonList(List<Button> buttonList) {
        _buttonlist = buttonList;
    }

    public void setupListeners(JPanel panel) {
        panel.addMouseListener(handler);
        panel.addMouseMotionListener(tracker);
    }

    public void update() {
        int x = tracker.getLocation().x - 9;
        int y = tracker.getLocation().y - 31;
        _mousePosition = new Point(x, y);
    }


    private class MouseHandler extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println(getMousePos());
            for (Button b:_buttonlist) {
                b.actionCheck(_mousePosition);
            };
        }
    }

    private class MouseTracker extends MouseMotionAdapter {

        private Point _location = new Point(0, 0);

        @Override
        public void mouseMoved(final MouseEvent e) {
            _location = e.getLocationOnScreen();
            if (_location == null) {
                _location = new Point(0, 0);
            }
        }

        public Point getLocation() {
            return _location;
        }
    }

    // Constructor
    public Controller() {
        setSize(StaticValues.CANVAS_WIDTH, StaticValues.CANVAS_HEIGHT);
        handler = new MouseHandler();
        tracker = new MouseTracker();
        update();
    }
}