package app.src;

/**
 * Dataclass to store fixed values
 */
public class StaticValues {

    /** empty constructor */
    public StaticValues() {
        // empty
    }
    
    /** name of the gamewindow */
    public static String GAMENAME = "BOWMASTER";
    /** width of the gamewindow */
    public static int CANVAS_WIDTH = 1920;
    /** height of the gamewindow */
    public static int CANVAS_HEIGHT = 1080;
    /** maximum distance an Entity can travel */
    public static int MAX_DISTANCE = 600;
    /** Spawnline for Monsters */
    public static int SpawnY = 100;
    /** Despawnline for Monsters */
    public static int TRAVEL_DISTANCE_Y = CANVAS_HEIGHT - 300;
    /** Framerate */
    public static int UPDATE_PERIOD = 60;
    /** Base Value for damagecaluculation */
    public static int BASEDAMAGE = 10;
    /** Available Rectangle corners */
    public static enum Corners {
        /** Top Right corner */
        TOP_RIGHT,
        /** Top Left corner */
        TOP_LEFT,
        /** Bottom Left corner */
        BOTTOM_RIGHT,
        /** Bottom Right corner */
        BOTTOM_LEFT,
    }
    /** Available Tags for a Scene */
    public static enum SceneTag {
        /** Tag for active Scene */
        ACTIVE,
        /** Tag for previous Scene */
        PREVIOUS,
        /** Tag for new Scenes */
        NEW,
    }
}