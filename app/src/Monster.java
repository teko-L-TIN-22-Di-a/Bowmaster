package app.src;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Monster extends Component {

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
        if (position.z + speed >= Constants.MAX_DISTANCE) {
            position.z = Constants.MAX_DISTANCE;
        }
        else {
            Vector3D movementZ = new Vector3D(0, 0, speed);
            updatePosition(movementZ);
        }
        scale();

        double distanceScaler = 0.6;
        Vector3D movementY = new Vector3D(0, (int)
        ((position.z*distanceScaler*Constants.CANVAS_HEIGHT)/(Constants.MAX_DISTANCE*Constants.UPDATE_PERIOD))
        );
        updatePosition(movementY);
        if (this.position.y >= distanceScaler*Constants.CANVAS_HEIGHT) {
            this.position.y = (int) (distanceScaler*Constants.CANVAS_HEIGHT);
        }
    }

    public void scale() {
        setOrientation(Orientation.TOP_LEFT);
        double factor = (double) position.z * ((double) 1 / (double) Constants.MAX_DISTANCE);
        int newWidth = (int) (originalSize.x * factor);
        int newHeight = (int) (originalSize.y * factor);
        BufferedImage newImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();
        
        this.image = newImage;
        setPosition(position);
        setOrientation(Orientation.CENTER);
    }
}