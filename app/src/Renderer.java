package app.src;

import java.util.ArrayList;
import java.util.List;

public class Renderer {
    List<Component> components = new ArrayList<>();

    public void addComponent(Component component) {
        components.add(component);
    };

    public void render(String args[]) {
        for (int i = 0; i < components.size(); i++) {
            Component component = components.get(i);
            component.draw();
        }
    }
}
