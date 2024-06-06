package app2.src.scenes;

import app2.src.resources.Monster;

public class Level1 extends Scene {
    
    public Level1() {
        setTAG("level1");
        Monster gobclops = new Monster("gobclops.png", 1);
        registerEntity(gobclops);
    }
}
