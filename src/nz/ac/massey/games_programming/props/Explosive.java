package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;

import java.awt.*;

public class Explosive extends SpriteProp {

    private GameEngine engine;
    private int fuse;
    private int damage;
    private int range;
    private boolean ready = false;

    public Explosive(int x, int y, Grid.Cell cell) {
        super(PropType.EXPLOSIVE, x, y, cell);
    }

    public Explosive(int x, int y, Grid.Cell cell, GameEngine engine, int damage, int range) {
        super(PropType.EXPLOSIVE, x, y, cell);
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
        super.cell.clearContents();
        // TODO Detonate explosive?
    }
}
