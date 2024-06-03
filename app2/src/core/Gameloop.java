package app2.src.core;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import app2.src.scenes.Menu;

public class Gameloop {
    private Renderer bobRoss = new Renderer();
    private Controller controller = new Controller();
    private Menu menu = new Menu();

    public Gameloop() {

    }

    public void start() {
        bobRoss.setScene(menu);
        bobRoss.startActiveScene();
        controller.setupListeners(bobRoss.canvas);
        controller.setButtonList(menu.getButtons());

        ActionListener updateTask = updateEvent -> {
            bobRoss.repaint();
            controller.update();
        };

        new Timer(60, updateTask).start();
    }
}