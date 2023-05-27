package nz.ac.massey.games_programming.props;

import nz.ac.massey.games_programming.GameEngine;
import nz.ac.massey.games_programming.Grid;

import java.awt.*;

public class Dirt extends Breakable {

    private static final int INITIAL_HEALTH = 3;
    // TODO ADD THIS WHEN SPRITES GET ADDED.
//    private static final Image DIRT;
//
//    static {
//        Image sprites = GameEngine.loadImage("Images/Objects/DirtExplosion.png");
//        DIRT = GameEngine.subImage(sprites, 0, 0, 32, 32);
//    }
    public Dirt(int x, int y, Grid.Cell cell) {
        super(PropType.DIRT, x, y, cell, INITIAL_HEALTH);
        this.setSprite(DIRT);
    }

    // TODO REMOVE THIS WHEN SPRITES GET ADDED.
    @Override
    public void draw(GameEngine engine, int x_offset, int y_offset, int x_width, int y_height) {
        engine.changeColor(new Color(131, 101, 57));
        engine.drawSolidRectangle(x_offset, y_offset, x_width, y_height);
    }
}
