package app.src;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Utilities {
    public static BufferedImage rotate(BufferedImage image, double angle) {
        int oWidth = image.getWidth();
        int oHeight = image.getHeight();
        double sin = Math.sin(Math.abs(angle));
        double cos = Math.cos(Math.abs(angle));
        int nWidth = (int) (cos*oWidth + sin*oHeight);
        int nheight = (int) (sin*oWidth + cos*oHeight);

        if (nWidth <= 0) {
            nWidth = oWidth;
            angle = 0;
        }
        if (nheight <= 0) {
            nheight = oHeight;
            angle = 0;
        }

        BufferedImage newImage = new BufferedImage(nWidth, nheight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        g2d.translate((nWidth - oWidth)/2, (nheight - oHeight)/2);
        g2d.rotate(angle, oWidth/2, oHeight/2);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return newImage;
    }

    public static double calcAngle(Point P1, Point P2) {
        double lenX = P1.x - P2.x;
        double lenY = P1.y - P2.y;
        double angle = Math.atan(lenY/lenX);
        if (lenX <= 0) {
            if (lenY <= 0) {
                angle = Math.PI/2;
            } else {
                angle = Math.PI/2 + angle;
                if (angle == 0) {
                }
            }
        } else {
            if (lenY <= 0) {
                angle = - Math.PI/2;
            } else {
                angle = - Math.PI/2 + angle;
                if (angle == 0) {
                }
            }
        }
        return angle;
    }
}
