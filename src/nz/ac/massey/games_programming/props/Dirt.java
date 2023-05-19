package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;

import java.awt.*;

public class Dirt extends Breakable {

    private static final int INITIAL_HEALTH = 3;
    private static final Image DIRT;

    static {
        Image sprites = GameEngine.loadImage("Images/Objects/DirtExplosion.png");
        DIRT = GameEngine.subImage(sprites, 0, 0, 32, 32);
    }

    public Dirt(int x, int y, Grid.Cell cell) {
        super(PropType.DIRT, x, y, cell, INITIAL_HEALTH);
    }
}
