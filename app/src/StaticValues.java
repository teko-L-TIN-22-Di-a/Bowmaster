package app.src;

/**
 * Dataclass to store fixed values
 */
public class StaticValues {

    /** Empty constructor. */
    public StaticValues() {
        // empty
    }
    
    // Gamesettings
    /** name of the gamewindow */
    public static String GAMENAME = "BOWMASTER";
    /** width of the gamewindow */
    public static int CANVAS_WIDTH = 1920;
    /** height of the gamewindow */
    public static int CANVAS_HEIGHT = 1080;
    /** Framerate */
    public static int UPDATE_PERIOD = 60;


    // Boardcontext
    /** Monster distance on spawn */
    public static int MONSTER_SPAWN_DISTANCE = 800;
    /** Player start location x */
    public static int PLAYERSPAWNX = CANVAS_WIDTH/2;
    /** Player start location y */
    public static int PLAYERSPAWNY = CANVAS_HEIGHT-200;


    // Entity Values
    /** Base Value for damagecaluculation */
    public static int BASEDAMAGE = 10;
    /** Base value for the cooldown of the bow */
    public static int BOWCOOLDOWN = 5;
    /** Charging factor for the distance*/
    public static int DISTCHARGEFACTOR = 20;
    /** Charging factor for the height*/
    public static int HEIGHTCHARGEFACTOR = 4;
    /** Highest possible charge value */
    public static int CHARGELIMIT = 25;
    /** Base value for the cooldown of charging */
    public static int CHARGECOOLDOWN = 15;
    /** Highest possible overcharge value */
    public static int OVERCHARGELIMIT = 25;
    /** Base Range for Arrows*/
    public static int RANGE = 200;
    /** Base height for Arrows*/
    public static int HEIGHT = 100;



    // Enumerations for Selections
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