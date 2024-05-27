package app.src;

import app.src.Constants.Orientation;

public class Rectangle {
    public Vector3D position, topLeft, bottomLeft, topRight, bottomRight, center, centerTop, centerBottom, centerLeft, centerRight;
    public Orientation orientation;
    public int width, height;

    public Rectangle(Vector3D position, int width, int height) {
        this.width = width;
        this.height = height;
        setOrientation(Orientation.CENTER);
        setPosition(position);
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setPosition(Vector3D newPosition) {

        int rightX = newPosition.x - width;
        int bottomY = newPosition.y - height;
        int centerX = newPosition.x - width/2;
        int centerY = newPosition.y - height/2;
        
        Orientation old_orientation = this.orientation;
        setOrientation(Orientation.CENTER);

        this.topLeft = newPosition;
        this.bottomLeft = new Vector3D(newPosition.x, bottomY, newPosition.z);
        this.topRight = new Vector3D(rightX, newPosition.y, newPosition.z);
        this.bottomRight = new Vector3D(rightX, bottomY, newPosition.z);
        this.center = new Vector3D(centerX, centerY, newPosition.z);
        this.centerTop = new Vector3D(centerX, newPosition.y, newPosition.z);
        this.centerBottom = new Vector3D(centerX, bottomY, newPosition.z);
        this.centerLeft = new Vector3D(newPosition.x, centerY, newPosition.z);
        this.centerRight = new Vector3D(rightX, centerY, newPosition.z);

        setOrientation(old_orientation);
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

    public void setOrientation(Orientation orientation) {
        switch (orientation) {
            case CENTER:
                this.position = this.center;
                this.orientation = Orientation.CENTER;
                break;
            case TOP_RIGHT:
                this.position = this.topRight;
                this.orientation = Orientation.TOP_RIGHT;
                break;
            case BOTTOM_RIGHT:
                this.position = this.bottomRight;
                this.orientation = Orientation.BOTTOM_RIGHT;
                break;
            case TOP_LEFT:
                this.position = this.topLeft;
                this.orientation = Orientation.TOP_LEFT;
                break;
            case BOTTOM_LEFT:
                this.position = this.bottomLeft;
                this.orientation = Orientation.BOTTOM_LEFT;
                break;
            case TOP_CENTER:
                this.position = this.centerTop;
                this.orientation = Orientation.TOP_CENTER;
                break;
            case BOTTOM_CENTER:
                this.position = this.centerBottom;
                this.orientation = Orientation.BOTTOM_CENTER;
                break;
            case LEFT_CENTER:
                this.position = this.centerLeft;
                this.orientation = Orientation.LEFT_CENTER;
                break;
            case RIGHT_CENTER:
                this.position = this.centerRight;
                this.orientation = Orientation.RIGHT_CENTER;
                break;
        }
    }
}
