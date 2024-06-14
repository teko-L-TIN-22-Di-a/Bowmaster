package app.src.core;

import java.awt.event.ActionListener;

import javax.swing.Timer;

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
    private Renderer bobRoss = new Renderer();
    private Controller controller = new Controller();
    private Menu menu = new Menu();
    private SceneHandler sceneHandler = new SceneHandler(menu);

    /**
     * Constructor.
     */
    public Gameloop() {

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
        bobRoss.startScene();

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
        };

        new Timer(60, updateTask).start();
    }
}