package app.src.scenes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import app.src.resources.Entity;
import app.src.resources.components.Button;
import app.src.resources.components.Component;

/**
 * Baseclass for any Scene.
 * @see Entity
 * @see Component
 * @see Button
 */
public class Scene {
    private List<Entity> _entities;
    private List<Component> _components;
    private List<Button> _buttons;
    private String TAG;
    private Scene _newScene;
    private Point _mousepoint;

    /**
     * Basic constructor, 
     * initialises the lists "entities", "components" and "buttons".
     * Properties from these lists will be drawn in "Renderer.java".
     */
    public Scene() {
        _entities = new ArrayList<>();
        _components = new ArrayList<>();
        _buttons = new ArrayList<>();
        setNewScene(this);
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
        Component bg = new Component(bgName, 0, 0);
        registerComponent(bg);
    }

    /**
     * set the private mousepoint variable to a new point
     * @param mousePoint    a Point object to set a new location
     * @see                 Point
     */
    public void updateMousePosition(Point mousePoint) {
        _mousepoint = mousePoint;
    }

    /**
     * returns the private mousepoint variable
     * @return  the private Point object mousepoint
     * @see     Point
     */
    public Point getMousePoint() {
        return _mousepoint;
    }

    /**
     * set the private newScene variable to a new scene
     * @param scene     a Scene object to set a new scene
     * @see             Scene
     */
    public void setNewScene(Scene scene) {
        _newScene = scene;
    }

    /**
     * returns the private newScene variable
     * @return  the private Scene object newScene
     * @see     Scene
     */
    public Scene getNewScene() {
        return _newScene;
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
        _entities.add(entity);
    }

    /**
     * removes an Entity object from the private list entities
     * @param entity a Entity object to remove from the List entities
     * @see Entity
     */
    public void unregisterEntity(Entity entity) {
        _entities.remove(entity);
    }

    /**
     * returns the private list of Entities
     * @return the private List object entities
     * @see Entity
     */
    public List<Entity> getEnties() {
        return _entities;
    }

    /**
     * adds an Component object to the private list components
     * @param component a Component object to add to the List components
     * @see Component
     */
    public void registerComponent(Component component) {
        _components.add(component);
    }

    /**
     * removes an Component Object from the private list components
     * @param component a Component object to remove from the List components
     * @see Component
     */
    public void unregisterComponent(Component component) {
        _components.remove(component);
    }

    /**
     * returns the private list of Components
     * @return the private List object components
     * @see Component
     */
    public List<Component> getComponents() {
        return _components;
    }

    /**
     * adds an Button Object to the private list buttons
     * @param button a Button object to add to the List buttons
     * @see Button
     */
    public void registerButton(Button button) {
        _buttons.add(button);
    }

    /**
     * removes an Button Object from the private list buttons
     * @param button a Button object to remove from the List buttons
     * @see Button
     */
    public void unregisterButton(Button button) {
        _buttons.remove(button);
    }

    /**
     * returns the private List of Buttons
     * @return the private List object buttons
     * @see Button
     */
    public List<Button> getButtons() {
        return _buttons;
    }
}