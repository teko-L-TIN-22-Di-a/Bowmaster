package app.src.resources;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import app.src.Utilities;
import app.src.resources.components.Component;

/**
 * The Arc provides functinalities to get heights corresponding to points
 * on the segment line of the Arc. The segment line is used as the base of the
 * Arc, the height describes the Arc over the baseline.
 */
public class Arc extends Component {
    private double radius;
    private Point size;

    /**
     * Takes x and y coordinates, segment lenght and height to create an Arc.
     * @param x x coordinates
     * @param y y coordinates
     * @param segmentLenght lenght of the segment
     * @param segmentHeight height of the segment
     */
    public Arc(int x, int y, int segmentLenght, int segmentHeight) {
        super(segmentLenght, segmentHeight, x, y);
        setSize(segmentLenght, segmentHeight);
        updateImage();
    }

    @Override
    public void setSize(int newWidth, int newHeight) {
        super.setSize(newWidth, newHeight);
        size = new Point(newWidth, newHeight);
        radius = Utilities.calcArcRadius(size.x, size.y);
    }

    /**
     * Get the vertical height from the baseline to the Arc at a specific distance 
     * on the Arcs baseline.
     * @param arcX distance on the Arcs baseline
     * @return vertival height at distance x
     */
    public int getHeightOfArcPoint(int arcX) {
        return Utilities.calcSegmentPointHeight(size.x, size.y, arcX, radius);
    }

    /**
     * Redraws the arc depending on the current size and the Radius.
     */
    public void updateImage() {
        BufferedImage image = new BufferedImage(size.x, size.y, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.BLUE);
        g.setStroke(new BasicStroke(3));
        int drawSize = (int) (2*radius);
        int offsetX = (int) ((drawSize-size.x)/2);
        g.drawArc(-offsetX, 0, drawSize, drawSize, 0, 180);
        setImage(image);
    }

    /**
     * Takes a distance and draws the vertical Point from the Baseline onto the image of the Arc.
     * @param arcX
     */
    public void drawArcPoint(int arcX) {
        updateImage();
        BufferedImage image = getImage();
        Graphics2D g = image.createGraphics();
        int arcY = getHeightOfArcPoint(arcX);
        g.drawLine(size.x/2, size.y, arcX, size.y-arcY);
    }

    /**
     * Overrides the update method to not only update the size but also the image.
     */
    @Override
    public void update() {
        super.update();
        updateImage();
    }
}
