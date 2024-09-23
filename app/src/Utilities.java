package app.src;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;

import app.src.resources.Arrow;
import app.src.resources.Entity;
import app.src.resources.monsters.Monster;
import app.src.scenes.Scene;

/**
 * Provides functionalitites like
 * - image rotation,
 * - angle calculation between two points.
 * - scaling of 2D values and Images
 */
public class Utilities {

    /** Empty constructor. */
    private Utilities() {
        // empty
    }

    /**
     * Takes the Lenght and Height of a circle segment
     * to calculate and return the radius.
     * @param segmentLenght lenght of the segment base line
     * @param segmentHeight height of the segment
     * @return radius
     */
    public static double calcArcRadius(int segmentLenght, int segmentHeight) {
        int h = segmentHeight;
        int s = segmentLenght;
        int r = (int) ((4*h*h + s*s)/(8*h));
        return r;
    }

    /**
     * Takes a segment lenght, height, radius and a distance inside that segment, to  calculate and 
     * retun the vertical height from the segment line to the Arc at the distance of the segment.
     * @param segmentLenght baseline of the segment
     * @param segmentHeight max height of the Arc over the baselinecenter
     * @param segmentDistance distance inside the Arc
     * @param radius radius of the resulting Arc of segment lenght and heigth
     * @return vertical height from the baseline to the Arc at distance inside the Arc
     */
    public static int calcSegmentPointHeight(int segmentLenght, int segmentHeight, int segmentDistance, double radius) {
        int sl = segmentLenght;
        int sh = segmentHeight;
        double x = Math.abs(sl/2-segmentDistance);
        double r = radius;
        int h = (int) (Math.sqrt(r*r-x*x)-r+sh);
        return h;
    }

    /**
     * Takes 2 Points, calculates and returns the direct distance between them
     * @param P1 first Point for calculation (basepoint)
     * @param P2 second Point for calculation
     * @return rect distance between the two Points
     */
    public static int calcDistance(Point P1, Point P2) {
        double lenX = Math.abs(P1.x - P2.x);
        double lenY = Math.abs(P1.y - P2.y);
        int distance = (int) (Math.sqrt(lenX*lenX + lenY*lenY));
        return distance;
    }

    /**
     * Calculates the angle between two points in radiants,
     * the angle is calculated above the base with the value 0 pointing north.
     * @param P1 basepoint for calculation
     * @param P2 point to which the angle points
     * @return calculated angle in radians
     * @see Point
     */
    public static double calcAngle(Point P1, Point P2) {
        double lenX = P1.x - P2.x;
        double lenY = P1.y - P2.y;
        double angle = Math.atan(Math.abs(lenX)/Math.abs(lenY));
        if (lenX >= 0) {
            angle = -angle;
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

    /**
     * Takes a BufferedImage, rotates it and returns the rotated image.
     * @param image the BufferedImage to be rotated
     * @param angle the rotation angle
     * @return      the rotated BufferedImage
     * @see         BufferedImage
     */
    public static BufferedImage rotateImage(BufferedImage image, double angle) {
        int oldWidth = image.getWidth();
        int oldHeight = image.getHeight();
        double sin = Math.sin(Math.abs(angle));
        double cos = Math.cos(Math.abs(angle));
        int newWidth = (int) (cos*oldWidth + sin*oldHeight);
        int newheight = (int) (sin*oldWidth + cos*oldHeight);

        if (newWidth <= 0) {
            newWidth = oldWidth;
            angle = 0;
        }
        if (newheight <= 0) {
            newheight = oldHeight;
            angle = 0;
        }

        BufferedImage newImage = new BufferedImage(newWidth, newheight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        g2d.translate((newWidth - oldWidth)/2, (newheight - oldHeight)/2);
        g2d.rotate(angle, oldWidth/2, oldHeight/2);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return newImage;
    }

    /**
     * Takes a Scene. From this Scene, the Monster and Arrow Entities are being checked, 
     * if they collide with each other.  
     * To determine possible hits, the Distance of the Monster and the Arrow are beeing 
     * compared. If they match up, the x coordinate and height will be compared to the 
     * Hitboxes boundries. If those match as well, the Monster will be considered hit and 
     * the damage multiplier of the Monsters Hitbox will be used, to reduce the life of 
     * that Monster.
     * @param scene Scene to perform hit calculation on
     */
    public static void hitCalculation(Scene scene) {
        String tag = scene.getTAG();
        if (tag == "level") {
            List<Entity> monsters = scene.getEntitiesByTag("monster");
            List<Entity> arrows = scene.getEntitiesByTag("arrow");

            for (Entity monsterEntity: monsters) {
                Monster monster = (Monster) monsterEntity;
                int monsterDistance = monster.getDistance();

                for (Entity entity: arrows) {
                    Arrow arrow = (Arrow) entity;

                    // only consider Arrows, that are shot
                    if (arrow.getShot()) {
                        int arrowDistance1 = arrow.getDistance();
                        int arrowDistance2 = arrowDistance1 - arrow.getSpeed();

                        // only consider Arrows, that pass through the space the Monster is occupying
                        if (monsterDistance >= arrowDistance1 && monsterDistance <= arrowDistance2) {
                            Point monsterLocation = monster.getLocation();
                            Point hitPoint2D = arrow.getHitPoint(monsterLocation.y);

                            // add height of the Arrow to create 3D hit calculation
                            Point hitPoint3D = new Point(hitPoint2D.x, hitPoint2D.y - arrow.getHeight());
                            int multiplier = monster.getMultiplier(hitPoint3D);
                            if (multiplier > 0) {
                                monster.updateHealth(- multiplier * StaticValues.BASEDAMAGE);
                                arrow.setState();
                                scene.playHitNoise();
                                System.out.println("Hit detected! Remaining health " + monster.getHealth());
                            }
                        }
                    }
                }
            }
        }
    }
}
