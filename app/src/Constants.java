package app.src;

public class Constants {
    //used for fixed values which might change during implementation
    public static final int MAX_DISTANCE = 180;
    public static final int CANVAS_WIDTH = 1980;
    public static final int CANVAS_HEIGHT = 1080;
    public static final int UPDATE_PERIOD = 60;
    public enum Orientation {
        CENTER,
        TOP_CENTER,
        TOP_RIGHT,
        TOP_LEFT,
        BOTTOM_CENTER,
        BOTTOM_RIGHT,
        BOTTOM_LEFT,
        RIGHT_CENTER,
        LEFT_CENTER;
    }
}
