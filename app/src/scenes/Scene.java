package app.src.scenes;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Clip;

import app.src.StaticValues;
import app.src.resources.Arc;
import app.src.resources.Arrow;
import app.src.resources.Bow;
import app.src.resources.Corosshair;
import app.src.resources.Entity;
import app.src.resources.assets.Loader;
import app.src.resources.assets.images.ImageMapping;
import app.src.resources.assets.sounds.SoundMapping;
import app.src.resources.components.Button;
import app.src.resources.components.Component;

/**
 * Baseclass for any Scene.
 * @see Entity
 * @see Component
 * @see Button
 */
public class Scene {
    private List<Entity> entities;
    private List<Component> components;
    private List<Button> buttons;
    private boolean menu, m1down, active;
    private Corosshair crosshair;
    private Bow bow;
    private Arrow nextArrow;
    private BufferedImage imgArrow;
    private String TAG;
    private Scene newScene;
    private Point mousepoint;
    private int counter;
    private Clip bgm, hitNoise, shotSE;

    /**
     * Initialises the lists Entities, Components and Buttons.
     * Properties from these lists will be drawn in the Renderer.
     * @param isMenu determines, if the Scene is considered a Menu.
     */
    public Scene(boolean isMenu) {
        bgm = null;
        hitNoise = Loader.loadSound(SoundMapping.MONSTERHIT);
        counter = 0;
        entities = new ArrayList<>();
        components = new ArrayList<>();
        buttons = new ArrayList<>();
        menu = isMenu;

        shotSE = Loader.loadSound(SoundMapping.SHOT);
        BufferedImage imgBow = Loader.loadImage(ImageMapping.BOW);
        imgArrow = Loader.loadImage(ImageMapping.ARROW);

        bow = new Bow(imgBow);
        Point bowLocation = bow.getLocation();

        int arrowY = bowLocation.y - (bow.getSize().y-imgArrow.getHeight())/2;
        nextArrow = new Arrow(imgArrow, bowLocation.x, arrowY);
        crosshair = new Corosshair();

        if (!menu) {
            registerEntity(crosshair);
            registerEntity(nextArrow);
            registerEntity(bow);
        }
        // Fills the newScene Variable with itself to indicate, that the scene does not have to be changed
        setNewScene(this);
    }

    /**
     * Plays the hitNoise from the start.
     */
    public void playHitNoise() {
        hitNoise.stop();
        hitNoise.setFramePosition(0);
        hitNoise.start();
    }

    /**
     * Takes the name of a audio file, loads the resource and sets it as bgm.
     * @param filename name of the audio file to be loaded.
     */
    public void setBGM(String filename) {
        Clip audio = Loader.loadSound(filename);
        bgm = audio;
    }

    /**
     * Collects all operations to be done, when the Scene is started.
     */
    public void start() {
        active = true;
        if (bgm != null) {
            bgm.loop(-1);
        }
    }

    /**
     * Collects all operations to be done, when the Scene is stoped.
     */
    public void stop() {
        active = false;
        if (bgm != null) {
            bgm.stop();
            bgm.setFramePosition(0);
        }
    }

    /**
     * Calls the update method of all Entities and Components.  
     * Takes the playerlocation and updates the Entity list with all active Entities.  
     * Updates the charge feature on the Bow.
     * @param playerLocation to update in all Entities.
     */
    public void update(Point playerLocation) {
        // update the Scene counter to allow timed events
        counter += 1;
        this.updateCharge();

        // update all Entities and keep only references to active Entities
        List<Entity> entitiesUpdate = new ArrayList<>();
        for (Entity entity: entities) {
            entity.update();
            entity.setPlayerLocation(playerLocation.x, playerLocation.y);
            if (entity.getState()) {
                entitiesUpdate.add(entity);
            }
        }
        entities = entitiesUpdate;

        // update all Components and keep only references to active Components
        List<Component> componentsUpdate = new ArrayList<>();
        for (Component component: components) {
            component.update();
            if (component.getState()) {
                componentsUpdate.add(component);
            }
        }
        components = componentsUpdate;
    }

    /**
     * Updates all values of the charging feature.  
     * Handels the phases of charging up, overcharging
     * and shooting.
     */
    private void updateCharge() {
        bow.setCharging(m1down);
        int charge = bow.getCharge();
        crosshair.setCharge(charge);
        int overcharge = bow.getOvercharge();

        if (!m1down) {
            if (overcharge == StaticValues.OVERCHARGELIMIT) {
                bow.resetOvercharge();
            }
            else if (charge > 0 && bow.getCooldown() == 0) {
                shoot();
            }
        }
    }

    /**
     * Indicates, if the Mouse Button 1 is currently beeing pressed or not.
     * @param state true, if the Button M1 is beeing pressed
     */
    public void setM1down(boolean state) {
        m1down = state;
    }

    /**
     * Returns the counter of the Scene.
     * The counter is used for time based events.
     * @return Scene counter.
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Set the Background image for your Scene object.
     * If not set the Background will be black.
     * Creates a new Component object to hold the image.
     * Registers the image to the components list of your scene.
     * @param bgName the name like "image.png" of your image in src/resources/assets/
     * @see Component
     */
    public void setBG(String bgName) {
        BufferedImage bgImage = Loader.loadImage(bgName);
        Component bg = new Component(bgImage, 0, 0);
        bg.setLocation(bg.getWidth()/2, bg.getHeight());
        registerComponent(bg);
    }

    /**
     * Returns true, if the Scene is currently active.
     * @return true, if the Scene is currently active.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets the charge value of the crosshair to 0.
     */
    public void resetCharge() {
        bow.resetCharge();
    }

    /**
     * Returns the Crosshair Object of the Scene.
     * @return Crosshair Object
     */
    public Corosshair getCrosshair() {
        return crosshair;
    }

    /**
     * Return the Arc of the Scenes Bow Entity.
     * @return Arc of the Scenes Bow Entity
     */
    public Arc getArc() {
        return bow.getArc();
    }

    /**
     * Takes x and y coordinates and stores them in the mousepoint variable
     * @param x x coordinate a new location
     * @param y y coordinate a new location
     */
    public void updateMouseLocation(int x, int y) {
        mousepoint = new Point(x, y);
        crosshair.updateMouseLocation(x, y);
        bow.updateMouseLocation(x, y);
        nextArrow.updateMouseLocation(x, y);
        Point playerLocation = bow.getLocation();
        nextArrow.setPlayerLocation(playerLocation.x, playerLocation.y);
    }

    /**
     * Return the location of the Scenes Bow Object.
     * @return location of the Scenes Bow Object
     */
    public Point getPlayerLocation() {
        return bow.getLocation();
    }

    /**
     * returns the private mousepoint variable
     * @return  the private Point object mousepoint
     * @see     Point
     */
    public Point getMousePoint() {
        return mousepoint;
    }

    /**
     * set the private newScene variable to a new scene
     * @param scene     a Scene object to set a new scene
     * @see             Scene
     */
    public void setNewScene(Scene scene) {
        newScene = scene;
    }

    /**
     * returns the private newScene variable
     * @return  the private Scene object newScene
     * @see     Scene
     */
    public Scene getNewScene() {
        return newScene;
    }

    /**
     * set the private TAG variable to a string
     * @param tag a String object to set a new location
     * @see String
     */
    public void setTAG(String tag) {
        TAG = tag;
    }

    /**
     * returns the private TAG variable
     * @return  the private String object TAG
     * @see String
     */
    public String getTAG() {
        return TAG;
    }

    /**
     * adds an Entity Object to the private list entities
     * @param entity a Entity object to add to the List entities
     * @see Entity
     */
    public void registerEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * removes an Entity object from the private list entities
     * @param entity a Entity object to remove from the List entities
     * @see Entity
     */
    public void unregisterEntity(Entity entity) {
        entities.remove(entity);
    }

    /**
     * returns the private list of Entities
     * @return the private List object entities
     * @see Entity
     */
    public List<Entity> getEnties() {
        return entities;
    }

    /**
     * take a TAG  and returns all Entities with the provided TAG.
     * @param TAG Name of the TAG
     * @return all Entities with the provided TAG
     */
    public List<Entity> getEntitiesByTag(String TAG) {
        List<Entity> taggedEntities = new ArrayList<>();
        for (Entity entity: entities) {
            String entitiyTAG = entity.getTAG();
            if (entitiyTAG == TAG) {
                taggedEntities.add(entity);
            }
        }
        return taggedEntities;
    }

    /**
     * adds an Component object to the private list components
     * @param component a Component object to add to the List components
     * @see Component
     */
    public void registerComponent(Component component) {
        components.add(component);
    }

    /**
     * removes an Component Object from the private list components
     * @param component a Component object to remove from the List components
     * @see Component
     */
    public void unregisterComponent(Component component) {
        components.remove(component);
    }

    /**
     * returns the private list of Components
     * @return the private List object components
     * @see Component
     */
    public List<Component> getComponents() {
        return components;
    }

    /**
     * adds an Button Object to the private list buttons
     * @param button a Button object to add to the List buttons
     * @see Button
     */
    public void registerButton(Button button) {
        buttons.add(button);
    }

    /**
     * removes an Button Object from the private list buttons
     * @param button a Button object to remove from the List buttons
     * @see Button
     */
    public void unregisterButton(Button button) {
        buttons.remove(button);
    }

    /**
     * returns the private List of Buttons
     * @return the private List object buttons
     * @see Button
     */
    public List<Button> getButtons() {
        return buttons;
    }

    private void setNextArrow() {
        Point bowLocation = bow.getLocation();
        int arrowY = bowLocation.y - (bow.getSize().y-imgArrow.getHeight())/2;
        Arrow newArrow = new Arrow(imgArrow, bowLocation.x, arrowY);
        registerEntity(newArrow);
        nextArrow = newArrow;
    }

    /**
     * Sets the State of the prepared Arrow to shot and creates a new Arrow
     * If the Bow is on cooldown, nothing happens.
     */
    public void shoot() {
        if (bow.getCooldown() <= 0) {
            Corosshair crosshair = getCrosshair();
            Point crosshairLocation = crosshair.getLocation();
            Point arcSize = bow.getArcSize();
            Arc arc = new Arc(0, 0, arcSize.x, arcSize.y);
            nextArrow.setShot(arc, crosshairLocation);
            registerComponent(nextArrow.getShadow());
            setNextArrow();
            bow.setCooldown(StaticValues.BOWCOOLDOWN);
            resetCharge();
            shotSE.stop();
            shotSE.setFramePosition(0);
            shotSE.start();
        }
    }
}