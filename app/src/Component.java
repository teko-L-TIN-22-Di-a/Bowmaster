package app.src;

import java.awt.image.BufferedImage;

public class Component {
    public BufferedImage image;
    public Vector3D position, topLeft, bottomLeft, topRight, bottomRight, center, centerTop, centerBottom, centerLeft, centerRight;

    public Component(BufferedImage image, Vector3D position) {
        this.image = image;
        setPosition(position);
        setOrientation(Orientation.CENTER);
    }

    public void setPosition(Vector3D newPosition) {

        int rightX = newPosition.x - image.getWidth();
        int bottomY = newPosition.y - image.getHeight();
        int centerX = newPosition.x - image.getWidth()/2;
        int centerY = newPosition.y - image.getHeight()/2;

        this.topLeft = newPosition;
        this.bottomLeft = new Vector3D(newPosition.x, bottomY, newPosition.z);
        this.topRight = new Vector3D(rightX, newPosition.y, newPosition.z);
        this.bottomRight = new Vector3D(rightX, bottomY, newPosition.z);
        this.center = new Vector3D(centerX, centerY, newPosition.z);
        this.centerTop = new Vector3D(centerX, newPosition.y, newPosition.z);
        this.centerBottom = new Vector3D(centerX, bottomY, newPosition.z);
        this.centerLeft = new Vector3D(newPosition.x, centerY, newPosition.z);
        this.centerRight = new Vector3D(rightX, centerY, newPosition.z);

    }

    public void updatePosition(Vector3D movement) {
        this.topLeft.add(movement);
        this.bottomLeft.add(movement);
        this.topRight.add(movement);
        this.bottomRight.add(movement);
        this.center.add(movement);
        this.centerTop.add(movement);
        this.centerBottom.add(movement);
        this.centerLeft.add(movement);
        this.centerRight.add(movement);
        this.position.add(movement);
    }

    public void update() {
        //Override in subclasses
    }

    public enum Orientation {
        CENTER,
        TOP_CENTER,
        TOP_RIGHT,
        TOP_LEFT,
        BOTTOM_CENTER,
        BOTTOMO_RIGHT,
        BOTTOM_LEFT,
        RIGHT_CENTER,
        LEFT_CENTER;
    }

    public void setOrientation(Orientation orientation) {
        switch (orientation) {
            case CENTER:
                this.position = this.center;
                break;
            case TOP_RIGHT:
                this.position = this.topRight;
                break;
            case BOTTOMO_RIGHT:
                this.position = this.bottomRight;
                break;
            case TOP_LEFT:
                this.position = this.topLeft;
                break;
            case BOTTOM_LEFT:
                this.position = this.bottomLeft;
                break;
            case TOP_CENTER:
                this.position = this.centerTop;
                break;
            case BOTTOM_CENTER:
                this.position = this.centerBottom;
                break;
            case LEFT_CENTER:
                this.position = this.centerLeft;
                break;
            case RIGHT_CENTER:
                this.position = this.centerRight;
                break;
        }
    }
}
