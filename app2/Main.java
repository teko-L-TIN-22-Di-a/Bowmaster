package app2;

import javax.swing.SwingUtilities;

import app2.src.core.Renderer;
import app2.src.scenes.Menu;

public class Main {

    public Main() {
        Renderer bobRoss = new Renderer();
        Menu menu = new Menu();
        bobRoss.setScene(menu);
        bobRoss.start();
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(Main::new);
    }
}
