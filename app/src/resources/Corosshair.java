package app.src.resources;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import app.src.StaticValues;
import app.src.Utilities;

/**
 * Provides functionalities to create and handle a crosshair on the screen 
 * Currently also holds attack charging functionalties,
 * which might be moved to Arrow or Bow later on.
 */
public class Corosshair extends Entity {
    
    private int range, charge;
    private Point mouseLocation;

    /**
     * Creates a Crosshair Object to aim at Enemys on the Screen.
     */
    public Corosshair() {
        super(null, 0, 0, 1);
        BufferedImage image = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.RED);
        g.setStroke(new BasicStroke(3));
        g.drawLine(10, 0, 10, 20);
        g.drawLine(0, 10, 20, 10);
        Shape theCircle = new Ellipse2D.Double(0, 0, 20, 20);
        g.draw(theCircle);
        setImage(image);
        setTAG("crosshair");
        setDistance(StaticValues.MONSTER_SPAWN_DISTANCE);
        mouseLocation = new Point();
        range = StaticValues.RANGE;
    }

    @Override
    public void update() {
        super.update();
        Point playerLocation = getPlayerLocation();
        double angle = Utilities.calcAngle(playerLocation, mouseLocation);
        int chargedRange = range + charge * StaticValues.DISTCHARGEFACTOR;
        int x = playerLocation.x + (int) (Math.sin(angle) * chargedRange);
        int y = playerLocation.y - (int) (Math.cos(angle) * chargedRange);
        setLocation(x, y);
    }

    public void updateCharge(int value) {
        charge = value;
    }

    /**
     * Set the range of the crosshair,
     * defines the Distance between Player and Crosshair.
     * @param newRange new value for Range
     */
    public void setRange(int newRange) {
        range = newRange;
    }

    /**
     * Takes x and y coordinates to store as the mouse location.
     * Required to calculate the Arrows direction.
     * @param x x coordinate
     * @param y y coordinate
     */
    public void updateMouseLocation(int x, int y) {
        mouseLocation = new Point(x, y);
    }
}
