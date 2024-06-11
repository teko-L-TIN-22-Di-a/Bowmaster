package app.src.core;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import app.src.resources.StaticValues.SceneTag;
import app.src.scenes.Menu;
import app.src.scenes.Scene;
import app.src.scenes.SceneHandler;

public class Gameloop {
    private Renderer bobRoss = new Renderer();
    private Controller controller = new Controller();
    private Menu menu = new Menu();
    private SceneHandler sceneHandler = new SceneHandler();

    public Gameloop() {

    }

    public void start() {
        bobRoss.setScene(menu);
        sceneHandler.setScene(menu, SceneTag.ACTIVE);
        sceneHandler.setScene(menu, SceneTag.NEW);
        controller.setupListeners(bobRoss.canvas);
        controller.setButtonList(menu.getButtons());

        ActionListener updateTask = updateEvent -> {
            Scene activeScene = sceneHandler.getActive();
            Scene newScene = activeScene.getNewScene();
            if (sceneHandler.sceneCheck(newScene)) {
                sceneHandler.setScene(newScene, SceneTag.NEW);
                sceneHandler.startNew();
                bobRoss.setScene(sceneHandler.getActive());
            }
            activeScene.updateMousePosition(controller.getMousePos());
            bobRoss.repaint();
            controller.update();
        };

        new Timer(60, updateTask).start();
    }
}