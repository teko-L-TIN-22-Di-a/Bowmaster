package app.src;

import java.awt.image.BufferedImage;

import app.src.Constants.Orientation;

public class drawableComponent {
    public BufferedImage image;
    public Rectangle rect;

    public drawableComponent(BufferedImage image, Vector3D position) {
        this.image = image;
        this.rect = new Rectangle(position, this.image.getWidth(), this.image.getHeight());
        rect.setOrientation(Orientation.CENTER);
        rect.setPosition(position);
    }

    public void setPosition(Vector3D newPosition) {
        rect.setPosition(newPosition);
    }

    public void updatePosition(Vector3D movement) {
        rect.updatePosition(movement);
    }

    public void update() {
        //Override in subclasses
    }

    public void setOrientation(Orientation orientation) {
        rect.setOrientation(orientation);
    }
}
