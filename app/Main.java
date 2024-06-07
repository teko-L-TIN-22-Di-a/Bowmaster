package app;

import javax.swing.SwingUtilities;

import app.src.core.Gameloop;

public class Main {

    public Main() {
        Gameloop loop = new Gameloop();
        loop.start();
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(Main::new);
    }
}
