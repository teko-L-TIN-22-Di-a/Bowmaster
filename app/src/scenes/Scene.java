package app.src.scenes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import app.src.resources.Entity;
import app.src.resources.components.Button;
import app.src.resources.components.Component;

public class Scene {
    private List<Entity> entities;
    private List<Component> components;
    private String TAG;
    private Scene newScene;
    private Point _mousepoint;

    public Scene() {
        entities = new ArrayList<>();
        components = new ArrayList<>();
        setNewScene(this);
    }

    public Scene init() {
        Scene scene = new Scene();
        System.out.println("start Scene " + TAG);
        return scene;
    }

    public void updateMousePosition(Point mousePoint) {
        _mousepoint = mousePoint;
    }

    public Point getMousePoint() {
        return _mousepoint;
    }

    public void setNewScene(Scene scene) {
        newScene = scene;
    }

    public Scene getNewScene() {
        return newScene;
    }

    public void setTAG(String tag) {
        TAG = tag;
    }

    public String getTAG() {
        return TAG;
    }

    public List<Entity> getEnties() {
        return entities;
    }

    public List<Component> getComponents() {
        return components;
    }

    public List<Button> getButtons() {
        List<Button> _buttonList = new ArrayList<>();
        for (Component c:getComponents()) {
            if (c instanceof Button) {
                _buttonList.add((Button) c);
            }
        }
        return _buttonList;
    }

    public void setBG(String bgName) {
        Component bg = new Component(bgName, 0, 0);
        Point newLocation = new Point(bg.getWidth()/2, bg.getHeight()/2);
        bg.setLocation(newLocation);
        registerComponent(bg);
    }

    public void registerEntity(Entity entity) {
        entities.add(entity);
    }

    public void unregisterEntity(Entity entity) {
        entities.remove(entity);
    }

    public void registerComponent(Component component) {
        components.add(component);
    }

    public void unregisterComponent(Component component) {
        components.remove(component);
    }
}