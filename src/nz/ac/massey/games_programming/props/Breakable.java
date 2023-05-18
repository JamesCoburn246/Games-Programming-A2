package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.Grid;

public class Breakable extends SpriteProp {

    private int health;


    public Breakable(PropType type, int x, int y, int health, Grid.Cell cell) {
        super(type, x, y, cell);
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        // Once health hits 0, it stays that way forever.
        if (this.health != 0)
            this.health = health;
    }

    /**
     * Deal damage to this sprite; potentially destroying it in the process.
     *
     * @param damage the amount of damage to be done to health.
     * @return true if the object was destroyed, or false if it survived.
     */
    public boolean dealDamage(int damage) {
        setHealth(getHealth() - damage);
        if (getHealth() <= 0) {
            setHealth(0);

            return true;
        } else {
            return false;
        }
    }

    public void outOfFrames() {
        destroy();
    }
}
