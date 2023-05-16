package nz.ac.massey.games_programming.props;

import java.awt.*;

public class Breakable extends SpriteProp {

    private int health;

    public Breakable(PropType type, int x, int y, Image sprite, int health) {
        super(type, x, y, sprite);
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;

    /**
     * Deal damage to this sprite; potentially destroying it in the process.
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
}
