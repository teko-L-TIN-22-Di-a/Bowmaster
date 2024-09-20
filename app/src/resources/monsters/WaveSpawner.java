package app.src.resources.monsters;

import java.awt.Point;
import java.util.List;

import javax.sound.sampled.Clip;

import app.src.resources.assets.Loader;
import app.src.resources.assets.sounds.SoundMapping;

/**
 * Extends the MonsterSpawner to create Monsters from a List
 * and allows to limit the amount of a Monster TYPE.
 * @see MonsterSpawner
 * @see Monster
 * @see Wave
 */
public class WaveSpawner extends MonsterSpawner {

    private List<Wave> waves;
    private Wave activeWave;
    private int activeWaveIndex;
    private boolean empty;
    private Clip waveSound;

    /**
     * Takes a list of Waves, a lower and upper Spawn time limit to create a WaveSpawner
     * @param newWaves Waves to be spawned
     * @param lowerSpawnTime lower Spawn time limit
     * @param upperSpawnTime upper Spawn time limit
     */
    public WaveSpawner(List<Wave> newWaves ,int lowerSpawnTime, int upperSpawnTime, Point playerLocation) {
        super(lowerSpawnTime, upperSpawnTime, playerLocation);
        waves = newWaves;
        activeWaveIndex = 0;
        activeWave = waves.get(activeWaveIndex);
        updateMonsterPool(activeWave.getMonsters());
        waveSound = Loader.loadSound(SoundMapping.WAVESTART);
        waveSound.start();
    }

    @Override
    public Monster spawnMonster() {
        Monster monster = super.spawnMonster();
        activeWave.decreaseRemaining(monster.getTYPE());
        return monster;
    }

    /**
     * Checks, if there are any Waves left.
     * @return true, if no more Waves are left
     */
    public boolean emptyCheck() {
        if (empty) {
            return empty;
        }
        else if (activeWave.emtpyCheck() && activeWaveIndex + 1 >= waves.size()) {
            System.out.println("SPAWNER EMPTY!");
            empty = true;
            return empty;
        }
        else {
            return empty;
        }
    }

    /**
     * Checks, if the current Wave is empty.
     * @return true, if the current Wave is empty
     */
    public boolean waveCheck() {
        return activeWave.emtpyCheck();
    }

    /**
     * Uses the activeWaveIndex to set a new Wave.
     */
    public void nextWave() {
        activeWaveIndex += 1;
        activeWave = waves.get(activeWaveIndex);
        updateMonsterPool(activeWave.getMonsters());
        waveSound.setFramePosition(0);
        waveSound.start();
        System.out.println("> next wave deployed");

    }
}
