package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;

import java.awt.*;
import java.util.ArrayList;

public class Explosive extends SpriteProp {

    private GameEngine engine;
    private int fuse;
    private int damage;
    private int range;
    private boolean ready = false;


    public Explosive(PropType type, int x, int y, Image sprite, Grid.Cell cell) {
        super(type, x, y, sprite, cell);
    }

    public Explosive(PropType type, int x, int y, Image sprite, Grid.Cell cell, GameEngine engine, int damage, int range) {
        super(type, x, y, sprite, cell);
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
