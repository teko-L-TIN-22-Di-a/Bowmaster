package app2.src.scenes;

import java.util.ArrayList;
import java.util.List;

import app2.src.resources.Entity;
import app2.src.resources.components.Button;
import app2.src.resources.components.Component;

public class Scene {
    private List<Entity> entities;
    private List<Component> components;

    public Scene() {
        entities = new ArrayList<>();
        components = new ArrayList<>();
    }

    public Scene init() {
        Scene scene = new Scene();
        return scene;
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