package app.src;

import java.awt.image.BufferedImage;

public class Component {
    private BufferedImage image;
    private Vector3D position;

    public Component(BufferedImage image, Vector3D position) {
        this.image = image;
        this.position = position;
    }
    public void draw() {
        
    }
    public void setPosition(Vector3D newPosition) {
        position = newPosition;
    }
}
