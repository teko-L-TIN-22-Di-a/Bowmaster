package app.src.scenes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import app.src.resources.Bow;
import app.src.resources.Entity;
import app.src.resources.assets.images.ImageMapping;
import app.src.resources.assets.sounds.SoundMapping;
import app.src.resources.monsters.Monster;
import app.src.resources.monsters.MonsterValues;
import app.src.resources.monsters.Wave;
import app.src.resources.monsters.WaveSpawner;

/**
 * Creates the level1 screen.
 * Extends the Scene class.
 * @see Bow
 * @see Scene
 */
public class Level1 extends Scene {
    private WaveSpawner spawner;
    private int monsterLimit = 5;
    private List<Wave> waves;
    
    /**
     * Sets up the Level1 scene.
     * @see Monster
     * @see Bow
     */
    public Level1() {
        super(false);
        setTAG("level");

        MonsterValues GOBCLOPS = new MonsterValues().getGobclops();
        MonsterValues TENTATHULU = new MonsterValues().getTentathulu();
        MonsterValues FLOAKET = new MonsterValues().getFloaket();
        MonsterValues NIGHTLOATER = new MonsterValues().getNighloater();
        MonsterValues THOAT = new MonsterValues().getThoat();

        setBG(ImageMapping.MAP1);
        setBGM(SoundMapping.LEVEL1BGM);

        Wave wave1 = new Wave();
        wave1.registerMonsters(GOBCLOPS, 6);
        wave1.registerMonsters(TENTATHULU, 2);

        Wave wave2 = new Wave();
        wave2.registerMonsters(FLOAKET, 4);
        wave2.registerMonsters(NIGHTLOATER, 10);

        Wave wave3 = new Wave();
        wave3.registerMonsters(THOAT, 6);

        waves = new ArrayList<>();
        waves.add(wave1);
        waves.add(wave2);
        waves.add(wave3);

        spawner = new WaveSpawner(waves, 25, 50, getPlayerLocation());
    }

    @Override
    public void update(Point playerPoint) {
        super.update(playerPoint);
        List<Entity> monsters = getEntitiesByTag("monster");
        if (spawner.emptyCheck() && monsters.size() <= 0) {
            Scene menuScene = new Menu();
            setNewScene(menuScene);
        }
        else if (spawner.waveCheck()) {
            if (monsters.size() <= 0) {
                spawner.nextWave();
            }
        }
        else if (spawner.spawnCheck() && monsterLimit > monsters.size()) {
            Monster newMonster = spawner.spawnMonster();
            newMonster.update();
            registerEntity(newMonster);
        };
    }
}
