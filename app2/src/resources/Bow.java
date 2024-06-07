package app2.src.resources;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import app2.src.resources.StaticValues.Corners;

public class Bow extends Entity {
    private Point _base, _mousePoint = new Point(0, 0);
    private BufferedImage _originalImage;
    private int _originalX, _originalY;

    public Bow() {
        int x = StaticValues.CANVAS_WIDTH/2;
        int y = StaticValues.CANVAS_HEIGHT-200;
        super("Bow1.png", x, y, 100);
        _base = new Point(x, y);
        _originalImage = getImage();
        _originalX = _originalImage.getWidth();
        _originalY = _originalImage.getHeight();
    }

    @Override
    public void update() {
        rotate();
    }

    public void updateMousePosition(Point mousePoint) {
        _mousePoint = mousePoint;
    }

    public void rotate() {
        double angle = calcMouseAngle();
        double sin = Math.sin(Math.abs(angle));
        double cos = Math.cos(Math.abs(angle));
        int nWidth = (int) (cos*_originalX + sin*_originalY);
        int nheight = (int) (sin*_originalX + cos*_originalY);

        if (nWidth < 1) {nWidth = 1;}
        if (nheight < 1) {nheight = 1;};

        BufferedImage newImage = new BufferedImage(nWidth, nheight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        g2d.translate((nWidth - _originalX)/2, (nheight - _originalY)/2);
        g2d.rotate(angle, _originalX/2, _originalY/2);
        g2d.drawImage(_originalImage, 0, 0, null);
        setImage(newImage);
        g2d.dispose();
        rect.setSize(nWidth, nheight);
        setDrawPosition(rect.getCorner(Corners.TOP_LEFT));
    }

    private double calcMouseAngle() {
        double lenX = _base.x - _mousePoint.x;
        double lenY = _base.y - _mousePoint.y;
        double angle = Math.atan(lenY/lenX);
        if (lenX <= 0) {
            if (lenY <= 0) {
                angle = Math.PI/2;
            } else {
                angle = Math.PI/2 + angle;
            }
        } else {
            if (lenY <= 0) {
                angle = - Math.PI/2;
            } else {
                angle = - Math.PI/2 + angle;
            }
        }
        return angle;
    }
}