package app.src;

/**
 * Dataclass to store fixed values
 */
public class StaticValues {
    public static String GAMENAME = "BOWMASTER";
    public static int CANVAS_WIDTH = 1920;
    public static int CANVAS_HEIGHT = 1080;
    public static int MAX_DISTANCE = 600;
    public static int SpawnY = 100;
    public static int TRAVEL_DISTANCE_Y = CANVAS_HEIGHT - 300;
    public static int UPDATE_PERIOD = 60;
    public static int BASEDAMAGE = 10;
    public static enum Corners {
        TOP_RIGHT,
        TOP_LEFT,
        BOTTOM_RIGHT,
        BOTTOM_LEFT,
    }
    public static enum SceneTag {
        ACTIVE,
        PREVIOUS,
        NEW,
    }
}