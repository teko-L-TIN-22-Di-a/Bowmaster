package app;

import javax.swing.SwingUtilities;

import app.src.core.Gameloop;

/**
 * Main class.
 */
public class Main {
    
    /**
     * Constructor for main class,
     * starts the gameloop.
     * 
     * @see Gameloop
     */
    public Main() {
        Gameloop loop = new Gameloop();
        loop.start();
    }

    /**
     * Executabel for main class.
     * 
     * @param args  None
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(Main::new);
    }
}
