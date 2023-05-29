package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;

import java.awt.*;

public abstract class BombCrate extends Collectable {
    private static final Image BOMBCRATE;

    static {
        Image bombSprite = GameEngine.loadImage("Images/Objects/BombCrate.png");
        BOMBCRATE = GameEngine.subImage(bombSprite, 0, 0, 32, 32);
    }
    public BombCrate(PropType type, int x, int y, Grid.Cell cell) {
        super(type, x, y, cell);
        this.setSprite(BOMBCRATE);
    }
}
