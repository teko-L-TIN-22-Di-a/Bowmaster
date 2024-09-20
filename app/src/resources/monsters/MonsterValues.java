package app.src.resources.monsters;

import java.awt.image.BufferedImage;

import javax.sound.sampled.Clip;

import app.src.resources.assets.Loader;
import app.src.resources.assets.images.ImageMapping;
import app.src.resources.assets.sounds.SoundMapping;

/**
 * Stores the blueprints for Monsters.
 */
public class MonsterValues {

    private BufferedImage image;
    private Clip noise;
    private String TYPE;
    private int speed;
    private int health;
    private int[][] hitboxes;

    /**
     * Creates empty MonsterValues.
     */
    public MonsterValues() {
        image = null;
        speed = 0;
        health = 0;
        hitboxes = null;
    }

    /**
     * Creates MonsterValues from the parameters.
     * @param loadedImage Image previously loaded from assets
     * @param Speed Speed value of the Monster
     * @param Health Health value of the Monster
     * @param Hitboxes Hitboxes of the Monster
     */
    public MonsterValues(BufferedImage loadedImage, int Speed, int Health, int[][] Hitboxes) {
        image = loadedImage;
        speed = Speed;
        health = Health;
        hitboxes = Hitboxes;
    }

    /**
     * Creates and returns a Gobclops!
     * @return a Gobclops!
     */
    public MonsterValues getGobclops() {
        setTYPE("Gobclops");
        setImage(ImageMapping.GOBCLOPS);
        setNoise(SoundMapping.GOBCLOPS);
        setSpeed(1);
        setHealth(100);
        final int[] hitbox1 = {130, 170, 0, -50, 1};
        final int[] hitbox2 = {45, 42, 0, -150, 2};
        final int[] hitbox3 = {80, 60, 0, -70, 2};
        final int[][] allHitboxes = {hitbox1, hitbox2, hitbox3};
        setHitboxes(allHitboxes);
        return this;
    }

    /**
     * Creates and returns a Thoat!
     * @return a Thoat!
     */
    public MonsterValues getThoat() {
        setTYPE("Thoat");
        setImage(ImageMapping.THOAT);
        setNoise(SoundMapping.THOAT);
        setSpeed(2);
        setHealth(50);
        final int[] headBox = {140, 130, 0, -130, 1};
        final int[] bodyBox = {180, 130, 0, 0, 1};
        final int[] mouthbox = {40, 90, 0, -90, 2};
        final int[] leftEyeBox = {20, 20, -35, -190, 2};
        final int[] rightEyeBox = {20, 20, 35, -190, 2};
        final int[][] allHitboxes = {headBox, bodyBox, mouthbox, leftEyeBox, rightEyeBox};
        setHitboxes(allHitboxes);
        return this;
    }

    /**
     * Creates and returns a Nighloater!
     * @return a Nighloater!
     */
    public MonsterValues getNighloater() {
        setTYPE("Nighloater");
        setImage(ImageMapping.NIGHTLOATER);
        setNoise(SoundMapping.NIGHTLOATER);
        setSpeed(5);
        setHealth(20);
        final int[] bodyBox = {100, 160, 0, -50, 1};
        final int[] eyeBox = {30, 30, -5, -150, 2};
        final int[][] allHitboxes = {bodyBox, eyeBox};
        setHitboxes(allHitboxes);
        return this;
    }

    /**
     * Creates and returns a Floaket!
     * @return a Floaket!
     */
    public MonsterValues getFloaket() {
        setTYPE("Floaket");
        setImage(ImageMapping.FLOAKET);
        setNoise(SoundMapping.FLOAKET);
        setSpeed(3);
        setHealth(30);
        final int[] lowerBodyBox = {220, 120, 0, 0, 1};
        final int[] upperBodyBox = {90, 110, 0, -120, 1};
        final int[] mouthBox = {30, 90, 0, -100, 2};
        final int[][] allHitboxes = {lowerBodyBox,upperBodyBox, mouthBox};
        setHitboxes(allHitboxes);
        return this;
    }

    /**
     * Creates and returns a Tentathulu!
     * @return a Tentathulu!
     */
    public MonsterValues getTentathulu() {
        setTYPE("Tentathulu");
        setImage(ImageMapping.TENTATHULU);
        setNoise(SoundMapping.TENTATHULU);
        setSpeed(1);
        setHealth(100);
        final int[] lowerBodyBox = {260, 140, 0, 0, 1};
        final int[] lowerWingBoxL = {100, 60, -95, -170, 1};
        final int[] lowerWingBoxR = {100, 60, 95, -170, 1};
        final int[] upperWingBoxL = {40, 60, -55, -230, 1};
        final int[] upperWingBoxR = {40, 60, 55, -230, 1};
        final int[] extendWingBoxL = {40, 40, -90, -230, 1};
        final int[] extendWingBoxR = {40, 40, 90, -230, 1};
        final int[] headBox = {100, 80, 0, -120, 3};
        final int[] eyeBoxL = {30, 30, -30, -130, 3};
        final int[] eyeBoxR = {30, 30, 30, -130, 3};
        final int[][] allHitboxes = {
            lowerBodyBox,
            lowerWingBoxL,
            lowerWingBoxR,
            upperWingBoxL,
            upperWingBoxR,
            extendWingBoxL,
            extendWingBoxR,
            headBox,
            eyeBoxL,
            eyeBoxR
        };
        setHitboxes(allHitboxes);
        return this;
    }
    
    /**
     * Sets the TYPE of the Monster.
     * @param type TYPE of the Monster
     */
    public void setTYPE(String type) {
        TYPE = type;
    }

    /**
     * Returns the TYPE of the Monster.
     * @return TYPE
     */
    public String getTYPE() {
        return TYPE;
    }

    /**
     * Takes a filename of an image file in the images folder of assets,
     * loads the Image and stores it in the image variable.
     * @param filename image filename
     */
    public void setImage(String filename) {
        image = Loader.loadImage(filename);
    }

    /**
     * Returns the Monster image.
     * @return image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Takes a filename of an audio file in the sounds folder of assets,
     * loads the Sound and stores it in the noise variable.
     * @param filename audio filename
     */
    public void setNoise(String filename) {
        noise = Loader.loadSound(filename);
    }

    /**
     * Returns the Monster noise.
     * @return noise
     */
    public Clip getNoise() {
        return noise;
    }

    /**
     * Takes a value to store in the speed variable of the Monster.
     * @param newSpeed new value for speed
     */
    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

    /**
     * Returns the speed of the Monster.
     * @return speed value
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Takes a value to store in the health variable of the Monster.
     * @param newHealth new value for health
     */
    public void setHealth(int newHealth) {
        health = newHealth;
    }

    /**
     * Returns the health of the Monster.
     * @return health value
     */
    public int getHealth() {
        return health;
    }

    /**
     * Takes a Matrix of values to create Hitboxes from them later.
     * (width, height, offsetX, offsetY, Damage Multiplier) x Amount of Hitboxes.
     * @param hitboxValues matrix of Hitbox values
     */
    public void setHitboxes(int[][] hitboxValues) {
        hitboxes = hitboxValues;
    }

    /**
     * Returns the matrix of Hitbox values of the Monster.
     * @return Monster Hitboxes
     */
    public int[][] getHitboxes() {
        return hitboxes;
    }
}