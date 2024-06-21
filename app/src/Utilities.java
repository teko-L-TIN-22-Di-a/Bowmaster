package app.src;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * Provides functionalitites like
 * - image rotation,
 * - angle calculation between two points.
 * - scaling of 2D values and Images
 */
public class Utilities {

    /** empty Constructor */
    public Utilities() {
        // empty
    }

    /**
     * Takes a BufferedImage, rotates it and returns the rotated image.
     * @param image the BufferedImage to be rotated
     * @param angle the rotation angle
     * @return      the rotated BufferedImage
     * @see         BufferedImage
     */
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

    /**
     * Calculates the angle between two points in radiants,
     * the angle is calculated above the base with the value 0 pointing north.
     * @param P1    basepoint for calculation
     * @param P2    point to which the angle points
     * @return      calculated angle in radians
     * @see         Point
     */
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

    /**
     * Takes x and y values before scaling and multiplies them with the factor.
     * @param originalX x value before scaling
     * @param originalY y value before scaling
     * @param factor scaling factor
     * @return scaled x and y values as a Point
     */
    public static Point scaleSize(int originalX, int originalY, double factor) {
        Point newSize = new Point();
        newSize.x = (int) (originalX * factor);
        newSize.y = (int) (originalY * factor);
        return newSize;
    }

    /**
     * Takes an image and a factor to scale the image.
     * @param originalImage image before scaling
     * @param factor scaling factor
     * @return scaled image
     */
    public static BufferedImage scaleImage(BufferedImage originalImage, double factor) {

        int originalX = originalImage.getWidth();
        int originalY = originalImage.getHeight();

        Point newSize = scaleSize(originalX, originalY, factor);
        if (newSize.x < 1) {newSize.x = 1;}
        if (newSize.y < 1) {newSize.y = 1;}

        BufferedImage newImage = new BufferedImage(newSize.x, newSize.y, originalImage.getType());
        
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, newSize.x, newSize.y, null);
        g2d.dispose();

        return newImage;
    }
}
