package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;

import java.awt.*;
import java.util.ArrayList;

public class Explosive extends SpriteProp {

    private GameEngine engine;
    private int fuse;
    private int damage;
    private int range;

    private boolean ready = false;
    public Explosive(PropType type, int x, int y, Image sprite, ArrayList<SpriteProp> container) {
        super(type, x, y, sprite, container);
    }
    public Explosive(PropType type, int x, int y, Image sprite, ArrayList<SpriteProp> container, GameEngine engine, int damage, int range) {
        super(type, x, y, sprite, container);
        this.engine = engine;
        this.damage = damage;
        this.range = range;
    }

    public void lightFuse(int fuse) {
        this.fuse = fuse;
        System.out.println("The fuse has been lit.");
    }

    @Override
    public void outOfFrames() {
        container.remove(this);
        // TODO Detonate explosive?
    }
}
