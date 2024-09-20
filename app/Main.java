package app;

import javax.swing.SwingUtilities;

import app.src.core.Gameloop;

/**
 * Main class. Serves as the entry Point to the app.
 */
public class Main {
    
    /**
     * Starts the gameloop.
     * @see Gameloop
     */
    public Main() {
        Gameloop loop = new Gameloop();
        loop.start();
    }

    /**
     * Executabel for main class.
     * @param args  None
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(Main::new);
    }
}
