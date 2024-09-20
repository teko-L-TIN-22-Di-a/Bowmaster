package app.src.resources.monsters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.src.StaticValues;

/**
 * Contains utilities to Spawn new Monsters
 */
public class MonsterSpawner {
    private List<MonsterValues> monsterPool;
    private Random duck;
    private Point spawnTimeRange, playerLocation;
    private int cooldownCounter, monsterCounter;

    /**
     * Takes a lower and upper limit for the delay before the next Monster is spawned
     * to create a MonsterSpawner
     * @param lowerSpawnTime lower limit for the delay before the next Monster is spawned
     * @param upperSpawnTime upper limit for the delay before the next Monster is spawned
     * @param playerLocation Sets the playerLocation on which the spawn point is dependent on.
     */
    public MonsterSpawner(int lowerSpawnTime, int upperSpawnTime, Point playerLocation) {
        duck = new Random();
        monsterPool = new ArrayList<>();
        spawnTimeRange = new Point(lowerSpawnTime, upperSpawnTime);
        this.playerLocation = playerLocation;
        cooldownCounter = 0;
    }

    /**
     * Returns the number of already spawned Monsters.
     * @return Monster count
     */
    public int getMonsterCount() {
        return monsterCounter;
    }

    /**
     * Removes all monsters from the Monster pool.
     * @param monsters New List of Monsters
     */
    public void updateMonsterPool(List<MonsterValues> monsters) {
        monsterPool = monsters;
    }

    /**
     * Checks, if the Spawner is on cooldown.
     * Sets the cooldown to max, if its lower than 0.
     * @return false, when the Spawner is on cooldown
     */
    public Boolean spawnCheck() {
        if (cooldownCounter > 0) {
            cooldownCounter -= 1;
            return false;
        }
        else {
            setCooldown();
            return true;
        }
    }

    /**
     * Adds a Moster type to the Spawn pool.
     * @param monster MonsterValues
     */
    public void registerMonster(MonsterValues monster) {
        MonsterValues values = new MonsterValues(
            monster.getImage(),
            monster.getSpeed(),
            monster.getHealth(),
            monster.getHitboxes()
        );
        monsterPool.add(values);
    }

    /**
     * Takes a Monster type by random from the pool,
     * Creates the Monster and returns it.
     * @return random Monster from the pool
     */
    public Monster spawnMonster() {
        int index = duck.nextInt(monsterPool.size());
        MonsterValues mv = monsterPool.get(index);
        Monster nextMonster = new Monster(mv.getImage(), mv.getHealth(), mv.getSpeed(), mv.getTYPE(), mv.getNoise());
        for (int[] value: mv.getHitboxes()) {
            nextMonster.addHitBox(value[0], value[1], value[2], value[3], value[4]);
        }
        double angle = randomAngle();
        int distance = StaticValues.MONSTER_SPAWN_DISTANCE;
        int newX = playerLocation.x + (int) (Math.sin(angle) * distance);
        int newY = playerLocation.y - (int) Math.abs((Math.cos(angle) * distance));
        nextMonster.setLocation(newX, newY);
        System.out.println(
            "Spawned a " +
            nextMonster.getTYPE() +
            " at location " +
            nextMonster.getLocation().x +
            ", " +
            nextMonster.getLocation().y);
        monsterCounter += 1;
        return nextMonster;
    }

    /**
     * Uses upper and lower time range to set the cooldown
     * of the Spawner to a random time iside the range.
     */
    private void setCooldown() {
        int max = spawnTimeRange.y;
        int min = spawnTimeRange.x;
        cooldownCounter = duck.nextInt(max - min) + min;
    }

    /**
     * Creates and retuns a random double between -PI/2 and PI/2.
     * @return random angle
     */
    private double randomAngle() {
        Random random = new Random();
        double angle = random.nextDouble(-Math.PI/2, Math.PI/2);
        return angle;
    }
}
