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
    }
}
