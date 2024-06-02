package app2;

import javax.swing.SwingUtilities;

import app2.src.core.Gameloop;

public class Main {

    public Main() {
        Gameloop loop = new Gameloop();
        loop.start();
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(Main::new);
    }
}
