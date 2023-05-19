package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;

import java.awt.*;

public class Rock extends Breakable {

    private static final int INITIAL_HEALTH = 3;
    private static final Image ROCK;

    static {
        Image sprites = GameEngine.loadImage("Images/Objects/RockExplosion.png");
        ROCK = GameEngine.subImage(sprites, 0, 0, 32, 32);
    }

    public Rock(int x, int y, Grid.Cell cell) {
        super(PropType.ROCK, x, y, cell, INITIAL_HEALTH);
        this.setSprite(ROCK);
    }
}
