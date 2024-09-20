package app.src.resources;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import app.src.Utilities;
import app.src.resources.components.Component;

public class Arc extends Component {
    private double radius;
    private Point size;

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

    public int getHeightOfArcPoint(int arcX) {
        return Utilities.calcSegmentPointHeight(size.x, size.y, arcX, radius);
    }

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

    public void drawArcPoint(int arcX) {
        updateImage();
        BufferedImage image = getImage();
        Graphics2D g = image.createGraphics();
        int arcY = getHeightOfArcPoint(arcX);
        g.drawLine(size.x/2, size.y, arcX, size.y-arcY);
    }

    @Override
    public void update() {
        super.update();
        updateImage();
    }
}
