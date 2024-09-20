package app.src.resources.monsters;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds types of Monsters and and amount for each type.
 */
public class Wave {
    
    private List<MonsterValues> monsters;
    private List<Integer> monsterAmounts;
    private int remaining;

    /**
     * Creates a Wave that holds an amount of available Monsters.
     */
    public Wave() {
        remaining = 0;
        monsters = new ArrayList<>();
        monsterAmounts = new ArrayList<>();
    }

    /**
     * Adds an amount of a Monster type to the Wave.
     * @param monster Monster type to be added
     * @param amount amount of the Monster type
     */
    public void registerMonsters(MonsterValues monster, int amount) {
        monsters.add(monster);
        monsterAmounts.add(amount);
        remaining += amount;
    }

    /**
     * Takes a Monster TYPE and returns its index from the Monster list.
     * @param type TYPE of the Monster to return its index
     * @return index xof the Monster TYPE
     */
    private int getMonsterIndex(String type) {
        int index = -1;
        for (MonsterValues monster: monsters) {
            if (type == monster.getTYPE()) {
                return monsters.indexOf(monster);
            }
        }
        return index;
    }

    /**
     * Takes a Monster TYPE and reduces the related amount by 1.
     * @param type Monster TYPE to decrease the amount of
     */
    private void decreaseMonsterAmount(String type) {
        int index = getMonsterIndex(type);
        int amount = monsterAmounts.get(index);
        amount -= 1;
        if (amount <= 0) {
            monsters.remove(index);
            monsterAmounts.remove(index);
        }
        else {
            monsterAmounts.set(index, amount);
        }
    }

    /**
     * Decreases the remaining variable of a Monster type by 1.
     * @param type Monster type to be decreased
     */
    public void decreaseRemaining(String type) {
        decreaseMonsterAmount(type);
        remaining -= 1;
    }

    /**
     * Returns True, if the remaining variable is lower or equal 0.
     * @return True, if no Monsters are left
     */
    public boolean emtpyCheck() {
        if (remaining <= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the available Monsters.
     * @return available Monsters
     */
    public List<MonsterValues> getMonsters() {
        return monsters;
    }

    /**
     * Returns the amounts of the available Monsters.
     * @return amounts of the available Monsters
     */
    public List<Integer> getAmounts() {
        return monsterAmounts;
    }
}