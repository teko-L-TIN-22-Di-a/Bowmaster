package app.src.resources;

import java.awt.Point;
import java.awt.image.BufferedImage;

import app.src.Utilities;
import app.src.resources.StaticValues.Corners;

public class Bow extends Entity {
    private Point _base, _mousePoint = new Point(0, 0);
    private BufferedImage _originalImage;

    public Bow() {
        super("Bow1.png", 0, 0, 100);
        int x = StaticValues.CANVAS_WIDTH/2;
        int y = StaticValues.CANVAS_HEIGHT-200;
        Point pos = new Point(x, y);
        rect.setPosition(pos);;
        _base = new Point(x, y);
        _originalImage = getImage();
    }

    @Override
    public void update() {
        double mouseAngle = Utilities.calcAngle( _base, _mousePoint);
        BufferedImage rotatedImage = Utilities.rotate(_originalImage, mouseAngle);
        setImage(rotatedImage);
        rect.setSize(rotatedImage.getWidth(), rotatedImage.getHeight());
        setDrawPosition(rect.getCorner(Corners.TOP_LEFT));
    }

    public void updateMousePosition(Point mousePoint) {
        _mousePoint = mousePoint;
    }
}