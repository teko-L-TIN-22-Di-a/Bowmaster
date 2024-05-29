package app2.src.scenes;

import java.util.List;

import app2.src.resources.Entity;
import app2.src.resources.components.Component;

public class Scene {
    private List<Entity> entities;
    private List<Component> components;

    public List<Entity> getEnties() {
        return entities;
    }

    public List<Component> getComponents() {
        return components;
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