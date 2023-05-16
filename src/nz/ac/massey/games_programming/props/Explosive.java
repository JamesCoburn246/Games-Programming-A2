package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;

import java.awt.*;

public class Explosive extends SpriteProp {

    private GameEngine engine;
    private int fuse;
    private int damage;
    private int range;

    private boolean ready = false;
    public Explosive(PropType type, int x, int y, Image sprite) {
        super(type, x, y, sprite);
    }
    public Explosive(PropType type, int x, int y, Image sprite, GameEngine engine, int damage, int range) {
        super(type, x, y, sprite);
        this.engine = engine;
        this.damage = damage;
        this.range = range;
    }

    public void lightFuse(int fuse) {
        this.fuse = fuse;
        System.out.println("The fuse has been lit.");
    }

}
