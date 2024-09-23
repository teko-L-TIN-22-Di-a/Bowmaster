package app.src.core;

import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import app.src.Utilities;
import app.src.StaticValues.SceneTag;
import app.src.scenes.Menu;
import app.src.scenes.Scene;
import app.src.scenes.SceneHandler;

/**
 * Handles Events regarding the gameloop.
 * @see Renderer
 * @see Controller
 * @see SceneHandler
 */
public class Gameloop {
    private Renderer bobRoss;
    private Controller controller;
    private Menu menu;
    private SceneHandler sceneHandler;

    /**
     * Sets up a Gameloop Object, initializes Renderer, Controller and Scene.
     */
    public Gameloop() {
        bobRoss = new Renderer();
        controller = new Controller();
        menu = new Menu();
        sceneHandler = new SceneHandler(menu);
    }

    /**
     * Starts the gameloop. Sets up the Renderer, SceneHandler and Controller
     * and reacts to Game Events
     */
    public void start() {
        bobRoss.setScene(menu);
        sceneHandler.setScene(menu, SceneTag.NEW);
        controller.setButtonList(menu.getButtons());
        controller.setupListeners(bobRoss.canvas);
        menu.start();

        ActionListener updateTask = updateEvent -> {
            Scene activeScene = sceneHandler.getActive();
            updateScene(activeScene);
            Utilities.hitCalculation(activeScene);
            bobRoss.repaint();
        };

        new Timer(60, updateTask).start();
    }

    private void updateScene(Scene activeScene) {
        Scene newScene = activeScene.getNewScene();
        if (sceneHandler.sceneCheck(newScene)) {
            sceneHandler.setScene(newScene, SceneTag.NEW);
            sceneHandler.startNew();
            bobRoss.setScene(sceneHandler.getActive());
            controller.setButtonList(newScene.getButtons());
        }
        Point mouseLocation = controller.getMousePos();
        activeScene.updateMouseLocation(mouseLocation.x, mouseLocation.y);
        activeScene.update(controller.getPlayerLocation());
        activeScene.setM1down(controller.getM1down());
    }
}