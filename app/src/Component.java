package app.src;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Component {
    public BufferedImage image;
    public Vector3D topLeft, bottomLeft, topRight, bottomRight, center, orientation, centerTop, centerBottom, centerLeft, centerRight;

    public Component(BufferedImage image, Vector3D position) {
        this.image = image;
        setPosition(position);
        setOrientation(Orientation.CENTER);
    }

    public void draw(Graphics2D screen) {
        screen.drawImage(image, center.x, center.y, null);
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
    public enum Orientation {
        CENTER, TR, BR, TL, BL, CT, CB, CL, CR;
    }

    public void setOrientation(Orientation orientation) {
        switch (orientation) {
            case CENTER:
                this.orientation = this.center;
                break;
            case TR:
                this.orientation = this.topRight;
                break;
            case BR:
                this.orientation = this.bottomRight;
                break;
            case TL:
                this.orientation = this.topLeft;
                break;
            case BL:
                this.orientation = this.bottomLeft;
                break;
            case CT:
                this.orientation = this.centerTop;
                break;
            case CB:
                this.orientation = this.centerBottom;
                break;
            case CL:
                this.orientation = this.centerLeft;
                break;
            case CR:
                this.orientation = this.centerRight;
                break;
        }
    }
}
