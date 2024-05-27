package app.src;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Monster extends drawableComponent {

    public int speed;
    private Vector3D originalSize;
    private BufferedImage originalImage;

    public Monster(BufferedImage image, Vector3D position, int speed) {
        super(image, position);
        originalImage = image;
        this.speed = speed;
        this.originalSize = new Vector3D(image.getWidth(), image.getHeight());
    }

    @Override
    public void update() {
        if (rect.position.z + speed >= Constants.MAX_DISTANCE) {
            rect.position.z = Constants.MAX_DISTANCE;
        }
        else {
            Vector3D movementZ = new Vector3D(0, 0, speed);
            updatePosition(movementZ);
        }
        scale();

        double distanceScaler = 0.6;
        Vector3D movementY = new Vector3D(0, (int)
        ((rect.position.z*distanceScaler*Constants.CANVAS_HEIGHT)/(Constants.MAX_DISTANCE*Constants.UPDATE_PERIOD))
        );
        updatePosition(movementY);
        if (rect.position.y >= distanceScaler*Constants.CANVAS_HEIGHT) {
            rect.position.y = (int) (distanceScaler*Constants.CANVAS_HEIGHT);
        }
    }

    public void scale() {

        double factor = (double) rect.position.z * ((double) 1 / (double) Constants.MAX_DISTANCE);
        int newWidth = (int) (originalSize.x * factor);
        int newHeight = (int) (originalSize.y * factor);

        BufferedImage newImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();
        
        this.image = newImage;
        int scaleDifferenceX = rect.topLeft.x - rect.position.x;
        int xCorrection = 0;
        if (scaleDifferenceX == rect.width) {
            xCorrection = scaleDifferenceX - newWidth;
        }
        else if (scaleDifferenceX == rect.width/2) {
            xCorrection = scaleDifferenceX + newWidth/2;
        }

        Vector3D newPosition = new Vector3D(
            rect.position.x + xCorrection,
            rect.position.y - (int) ((rect.position.y - rect.topLeft.y) * factor),
            rect.position.z
        );
        rect.setSize(newWidth, newHeight);
        setPosition(newPosition);
    }
}