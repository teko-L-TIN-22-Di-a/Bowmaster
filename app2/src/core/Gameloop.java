package app2.src.core;

import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import app2.src.resources.components.Button;
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
        bobRoss.canvas.addMouseListener(null);

        ActionListener updateTask = evt -> {
            Point mousePosition = controller.getMousePos();
            for (Button b:bobRoss.getButtons()) {
                if (b.rect.collidePoint(mousePosition)) {
                    b.action();
                };
            }
            bobRoss.repaint();
        };

        new Timer(60, updateTask).start();
    }
}